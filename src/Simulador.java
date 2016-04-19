public class Simulador {
	private double instante; // Relogio de simulacao - variavel que contem o valor do tempo em cada instante
	private double media_cheg, media_serv; // Medias das distribuicoes de chegadas e de atendimento no servico
	// private int n_clientes; // Numero de clientes que vao ser atendidos
	private Servico servico_gasolina; // Servico - pode haver mais do que um num simulador
    private Servico servico_gasoleo;
    private Servico servico_loja;
	private ListaEventos lista; // Lista de eventos - onde ficam registados todos os eventos que vao ocorrer na simulacao
	// Cada simulador so tem uma
	private double instante_final;
    private double media_serv_loja;
    private int n_empregados_gasolina;
	private int n_empregados_gasoleo;
	private int n_empregados_loja;

	// Construtor
	public Simulador() {
		// Inicializacao de parametros do simulador
		media_cheg = 1.2;
		media_serv = 4;
		media_serv_loja = 1;
		// n_clientes = 10;
        n_empregados_gasolina = 2;
		n_empregados_gasoleo = 1;
		n_empregados_loja = 1;
		// Inicializacao do relogio de simulacao
		instante = 0;
        instante_final = 400;
		// Criacao do servico
		servico_gasolina = new Servico(this, "gasolina", n_empregados_gasolina);
        servico_gasoleo = new Servico(this, "gasoleo", n_empregados_gasoleo);
		servico_loja = new Servico(this, "loja", n_empregados_loja);
		// Criacao da lista de eventos
		lista = new ListaEventos(this);
		// Agendamento da primeira chegada
		// Se nao for feito, o simulador nao tem eventos para simular

        Double rand = RandomGenerator.rand(2);
        if(rand <= 0.2) {
            insereEvento (new Chegada(instante, this, servico_gasoleo));
        } else {
            insereEvento (new Chegada(instante, this, servico_gasolina));
        }
	}

	// programa principal
	public static void main(String[] args) {
		// Cria um simulador e
		Simulador s = new Simulador();
		// poe-o em marcha
		s.executa();
	}

	// Metodo que insere o evento e1 na lista de eventos
	void insereEvento (Evento e1){
		lista.insereEvento (e1);
	}

	// Metodo que actualiza os valores estatisticos do simulador
	private void act_stats(){
		servico_gasolina.act_stats();
        servico_gasoleo.act_stats();
        servico_loja.act_stats();
	}

	// Metodo que apresenta os resultados de simulacao finais
	private void relat (){
		System.out.println();
		System.out.println("------- Resultados finais GASOLINA -------");
		servico_gasolina.relat();
		System.out.println();
		System.out.println("------- Resultados finais GASOLEO -------");
		servico_gasoleo.relat();
		System.out.println();
		System.out.println("------- Resultados finais LOJA -------");
		servico_loja.relat();
	}

	// Metodo executivo do simulador
	public void executa (){
		Evento e1;
		// Enquanto nao atender todos os clientes
		while (instante < instante_final){
			lista.print();  // Mostra lista de eventos - desnecessario; e' apenas informativo
			e1 = (Evento)(lista.removeFirst());  // Retira primeiro evento (e' o mais iminente) da lista de eventos
			instante = e1.getInstante();         // Actualiza relogio de simulacao
			act_stats();                         // Actualiza valores estatisticos
			e1.executa();                        // Executa evento
		}
		relat();  // Apresenta resultados de simulacao finais
	}

	// Metodo que devolve o instante de simulacao corrente
	public double getInstante() {
		return instante;
	}
	// Metodo que devolve a media dos intervalos de chegada
	public double getMedia_cheg() {
		return media_cheg;
	}
	// Metodo que devolve a media dos tempos de servico
	public double getMedia_serv() {
		return media_serv;
	}
    public double getMedia_serv_loja() {
        return media_serv_loja;
    }

    public Servico getServico_gasolina() {
        return servico_gasolina;
    }
    public Servico getServico_gasoleo() {
        return servico_gasoleo;
    }
    public Servico getServico_loja() {
        return servico_loja;
    }
}