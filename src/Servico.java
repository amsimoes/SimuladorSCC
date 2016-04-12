import java.util.*;

// Classe que representa um servico com uma fila de espera associada

public class Servico {
	private int estado; // Variavel que regista o estado do servico: 0 - livre; 1 - ocupado
	private int atendidos; // Numero de clientes atendidos ate ao momento
	private double temp_ult, soma_temp_esp, soma_temp_serv; // Variaveis para calculos estatisticos
	private Vector<Cliente> fila; // Fila de espera do servico
	private Simulador s; // Referencia para o simulador a que pertence o servico

	// Construtor
	Servico (Simulador s){
		this.s = s;
		fila = new Vector<Cliente>(); // Cria fila de espera
		estado = 0; // Livre
		temp_ult = s.getInstante(); // Tempo que passou desde o ultimo evento. Neste caso 0, porque a simulacao ainda nao comecou.
		atendidos = 0;  // Inicializacao de variaveis
		soma_temp_esp = 0;
		soma_temp_serv = 0;
	}

	// Metodo que insere cliente (c) no servico
	public void insereServico (Cliente c){
		if (estado == 0) { // Se servico livre,
			estado ++;     // fica ocupado e
			// agenda saida do cliente c para daqui a s.getMedia_serv() instantes
			s.insereEvento (new Saida(s.getInstante()+s.getMedia_serv(), s));
		}
		else fila.addElement(c); // Se servico ocupado, o cliente vai para a fila de espera
	}

	// Metodo que remove cliente do servico
	public void removeServico (){
		atendidos++; // Regista que acabou de atender + 1 cliente
		if (fila.isEmpty()) estado --; // Se a fila esta vazia, liberta o servico
		else { // Se nao,
			// vai buscar proximo cliente a fila de espera e
			// Cliente c = (Cliente)fila.firstElement();
			fila.removeElementAt(0);
			// agenda a sua saida para daqui a s.getMedia_serv() instantes
			s.insereEvento (new Saida(s.getInstante()+s.getMedia_serv(),s));
		}
	}

	// Metodo que calcula valores para estatisticas, em cada passo da simulacao ou evento
	public void act_stats(){
		// Calcula tempo que passou desde o ultimo evento
		double temp_desde_ult = s.getInstante() - temp_ult;
		// Actualiza variavel para o proximo passo/evento
		temp_ult = s.getInstante();
		// Contabiliza tempo de espera na fila
		// para todos os clientes que estiveram na fila durante o intervalo
		soma_temp_esp += fila.size() * temp_desde_ult;
		// Contabiliza tempo de atendimento
		soma_temp_serv += estado * temp_desde_ult;
	}

	// Metodo que calcula valores finais estatisticos
	public void relat (){
		// Tempo medio de espera na fila
		double temp_med_fila = soma_temp_esp / (atendidos+fila.size());
		// Comprimento medio da fila de espera
		// s.getInstante() neste momento e' o valor do tempo de simulacao,
		// uma vez que a simulacao comecou em 0 e este metodo so e' chamado no fim da simulacao
		double comp_med_fila = soma_temp_esp / s.getInstante();
		// Tempo medio de atendimento no servico
		double utilizacao_serv = soma_temp_serv / s.getInstante();
		// Apresenta resultados
		System.out.println("Tempo medio de espera "+temp_med_fila);
		System.out.println("Comp. medio da fila "+comp_med_fila);
		System.out.println("Utilizacao do servico "+utilizacao_serv);
		System.out.println("Tempo de simulacao "+s.getInstante()); // Valor actual
		System.out.println("Numero de clientes atendidos "+atendidos);
		System.out.println("Numero de clientes na fila "+fila.size()); // Valor actual
	}

	// Metodo que devolve o numero de clientes atendidos no servico ate ao momento
	public int getAtendidos() {
		return atendidos;
	}

}