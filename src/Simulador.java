public class Simulador {
	// Relogio de simulacao - variavel que contem o valor do tempo em cada instante
	private double instante;
	// Medias das distribuicoes de chegadas e de atendimento no servico
	private double media_cheg, media_serv;
	// Numero de clientes que vao ser atendidos
	private int n_clientes;
	// Servico - pode haver mais do que um num simulador
	private Servico servico;
	private Servico gasolina;
	// Lista de eventos - onde ficam registados todos os eventos que vao ocorrer na simulacao
	// Cada simulador so tem uma
	private ListaEventos lista;

	// Construtor
	public Simulador() {
		// Inicializacao de parametros do simulador
		media_cheg = 1.2;
		media_serv = 1.5;
		n_clientes = 10;
		// Inicializacao do relogio de simulacao
		instante = 0;
		// Criacao do servico
		servico = new Servico(this);
		gasolina = new Servico(this);
		// Criacao da lista de eventos
		lista = new ListaEventos(this);
		// Agendamento da primeira chegada
		// Se nao for feito, o simulador nao tem eventos para simular
		insereEvento (new Chegada(instante, this));
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
		gasolina.act_stats();
	}

	// Metodo que apresenta os resultados de simulacao finais
	private void relat (){
		System.out.println();
		System.out.println("------- Resultados finais -------");
		System.out.println();
		gasolina.relat();
	}

	// Metodo executivo do simulador
	public void executa (){
		Evento e1;
		// Enquanto nao atender todos os clientes
		while (gasolina.getAtendidos() < n_clientes){
			lista.print();  // Mostra lista de eventos - desnecessario; e' apenas informativo
			e1 = (Evento)(lista.removeFirst());  // Retira primeiro evento (e' o mais iminente) da lista de eventos
			instante = e1.getInstante();         // Actualiza relogio de simulacao
			act_stats();                         // Actualiza valores estatisticos
			e1.executa(gasolina);                 // Executa evento
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
}