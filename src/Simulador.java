import java.util.ArrayList;

public class Simulador extends InterfaceSimulador {
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
	private int n_empregados_gasolina, n_empregados_gasoleo, n_empregados_loja;
	private double desvio_bombas, desvio_loja;


	// Construtor
	public Simulador() {
		super();
		ArrayList<String> ListaDados = InitSimulacao.executa(); // Vai buscar dados inseridos pelo utilizador e guarda nos indices
		init();  // Inicia a simulacao grafica
		// Inicializacao do relogio de simulacao
		instante = 0;
		instante_final = Double.parseDouble(ListaDados.get(0));
		// Inicializacao de parametros do simulador
		media_cheg = Double.parseDouble(ListaDados.get(1));
		media_serv = Double.parseDouble(ListaDados.get(2));
		media_serv_loja = Double.parseDouble(ListaDados.get(3));
		// n_clientes = 10;
		n_empregados_gasolina = Integer.parseInt(ListaDados.get(4));
		n_empregados_gasoleo = Integer.parseInt(ListaDados.get(5));
		n_empregados_loja = Integer.parseInt(ListaDados.get(6));
		desvio_bombas = Double.parseDouble(ListaDados.get(7));
		desvio_loja = Double.parseDouble(ListaDados.get(8));
		// Criacao do servico
		servico_gasolina = new Servico(this, "gasolina", n_empregados_gasolina, desvio_bombas);
		servico_gasoleo = new Servico(this, "gasoleo", n_empregados_gasoleo, desvio_bombas);
		servico_loja = new Servico(this, "loja", n_empregados_loja, desvio_loja);
		// Criacao da lista de eventos
		lista = new ListaEventos(this);
		// Agendamento da primeira chegada
		// Se nao for feito, o simulador nao tem eventos para simular

		Double rand = RandomGenerator.rand(10);
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
		s.setVisible(true);
		s.refresh();
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
			//lista.print();  // Mostra lista de eventos - desnecessario; e' apenas informativo
			e1 = (Evento)(lista.removeFirst());  // Retira primeiro evento (e' o mais iminente) da lista de eventos
			//System.out.println(e1.tipo+" do instante "+e1.getInstante()+" REMOVIDO da lista de eventos!");
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