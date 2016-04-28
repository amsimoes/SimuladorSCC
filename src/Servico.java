import java.util.*;

// Classe que representa um servico com uma fila de espera associada

public class Servico {
	private String tipo; // Tipo de serviço (Chegada, Transicao, Saida)
	private int estado; // Variavel que regista o estado do servico: 0 - livre; 1 - ocupado
	private int atendidos; // Numero de clientes atendidos ate ao momento
	private int n_empregados;
	private double temp_ult, soma_temp_esp, soma_temp_serv; // Variaveis para calculos estatisticos
	private Vector<Cliente> fila; // Fila de espera do servico
	private Simulador s; // Referencia para o simulador a que pertence o servico
	private double desvio_bombas, desvio_loja;
	private static int contador_bombas, contador_loja;
	private double media_bombas_1, media_bombas_2;
	private double media_loja_1, media_loja_2;
	private double[] x_normal_loja;
	private double[] x_normal_bombas;

	// Construtor
	Servico (Simulador s, String tipo, int n_empregados, double desvio){
		this.s = s;
		this.tipo = tipo;
		this.n_empregados = n_empregados;
		fila = new Vector<Cliente>(); // Cria fila de espera
		estado = 0; // Livre
		temp_ult = s.getInstante(); // Tempo que passou desde o ultimo evento. Neste caso 0, porque a simulacao ainda nao comecou.
		atendidos = 0;  // Inicializacao de variaveis
		soma_temp_esp = 0;
		soma_temp_serv = 0;
		this.contador_bombas = 0;
		this.contador_loja = 0;
		if(this.tipo.equals("loja")) {
			this.desvio_loja = desvio;
		} else {
			this.desvio_bombas = desvio;
		}
	}

	// Metodo que insere cliente (c) no servico
	public void insereServico (Cliente c){
		if(estado < n_empregados) {
			estado++;



			if(tipo.equals("loja")) {
				System.out.println("insereServico | Contador loja = "+contador_loja);
				if(contador_loja % 2 == 0) {
					x_normal_loja = normal(s.getMedia_serv_loja(), desvio_loja, 30);
					media_loja_1 = x_normal_loja[0];
					media_loja_2 = x_normal_loja[1];
					s.insereEvento(new Saida(s.getInstante()+media_loja_1, s, this));
					contador_loja++;
				} else {
					s.insereEvento(new Saida(s.getInstante()+media_loja_2, s, this));
					contador_loja++;
				}
			} else {
				System.out.println("insereServico | Contador bombas = "+contador_bombas);
				if(contador_bombas % 2 == 0) {
					x_normal_bombas = normal(s.getMedia_serv(), desvio_bombas, 40);
					media_bombas_1 = x_normal_bombas[0];
					media_bombas_2 = x_normal_bombas[1];
					s.insereEvento(new Transicao(s.getInstante()+media_bombas_1, s, this));
					contador_bombas++;
				} else {
					s.insereEvento(new Transicao(s.getInstante() + media_bombas_2, s, this));
					contador_bombas++;
				}
			}
		} else {
			fila.addElement(c);
		}
	}

	// Metodo que remove cliente do servico
	public Cliente removeServico (){
		Cliente c = null;
        atendidos++; // Regista que acabou de atender + 1 cliente
		if (fila.isEmpty()) estado --; // Se a fila esta vazia, liberta o servico
		else { // Se nao,
			// vai buscar proximo cliente a fila de espera e
			c = (Cliente)fila.firstElement();
			fila.removeElementAt(0);

            // agenda a sua saida para daqui a s.getMedia_serv() instantes
            if(tipo.equals("loja")) {
				System.out.println("removeServico | Contador loja = "+contador_loja);
				if(contador_loja % 2 == 0) {
					x_normal_loja = normal(s.getMedia_serv_loja(), desvio_loja, 30);
					media_loja_1 = x_normal_loja[0];
					media_loja_2 = x_normal_loja[1];
					s.insereEvento(new Saida(s.getInstante()+media_loja_1, s, this));
					contador_loja++;
				} else {
					s.insereEvento(new Saida(s.getInstante()+media_loja_2, s, this));
					contador_loja++;
				}
            } else { // Bomba -> Loja
				System.out.println("removeServico | Contador bombas = "+contador_bombas);
				if(contador_bombas % 2 == 0) {
					x_normal_bombas = normal(s.getMedia_serv(), desvio_bombas, 40);
					media_bombas_1 = x_normal_bombas[0];
					media_bombas_2 = x_normal_bombas[1];
					s.insereEvento(new Transicao(s.getInstante()+media_bombas_1, s, this));
					contador_bombas++;
				} else {
					s.insereEvento(new Transicao(s.getInstante() + media_bombas_2, s, this));
					contador_bombas++;
				}
            }
		}
        return c;
	}

	public double dp(double media, double desvio) {
		Random r = new Random();
		double n;
		do {
			 n = r.nextGaussian()*desvio+media;
		} while (Double.compare(n, media+desvio) > 0 || Double.compare(n, media-desvio) < 0);
		return n;
	}

	static double[] normal(double m, double d, int randStream){
		// System.out.println("Funcao normal a ser chamada... :-)");

		double v1, v2, w;
		double y[] = new double[2];
		double x[] = new double[2];
		do {
			do {
				v1 = 2 * RandomGenerator.rand(randStream) - 1;
				v2 = 2 * RandomGenerator.rand(randStream) - 1;
				w = Math.pow(v1, 2) + Math.pow(v2, 2);
			} while (w > 1);
			y[0] = v1 * Math.pow(((-2 * Math.log(w)) / w), 0.5);
			y[1] = v2 * Math.pow(((-2 * Math.log(w)) / w), 0.5);
		} while (y[0]<-1 || y[1] <-1 || y[0]>1 || y[1]>1);

		x[0] = m + y[0] * d;
		x[1] = m + y[1] * d;

		/* System.out.println("x1="+x[0]+" x2="+x[1]); */

		return x;
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
		if (Double.isNaN(temp_med_fila))
			temp_med_fila = 0;
		// Comprimento medio da fila de espera
		// s.getInstante() neste momento e' o valor do tempo de simulacao,
		// uma vez que a simulacao comecou em 0 e este metodo so e' chamado no fim da simulacao
		double comp_med_fila = soma_temp_esp / s.getInstante();
		// Tempo medio de atendimento no servico
		/* double utilizacao_serv = 1 - (soma_temp_serv / (soma_temp_serv + soma_temp_esp)); */
		double utilizacao_serv = (soma_temp_serv / n_empregados) / s.getInstante();
		System.out.println("getInstante = "+s.getInstante()+" soma_temp_servico = "+soma_temp_serv);

		if(this.tipo.equals("gasolina")) {
			s.label_tempo_medio_espera_gasolina.setText("Tempo medio de espera: "+temp_med_fila);
			s.label_comp_medio_fila_gasolina.setText("Comprimento medio da fila: "+comp_med_fila);
			s.label_util_serv_gasolina.setText("Utilizacao do servico: "+utilizacao_serv);
			s.label_n_client_fila_gasolina.setText("Numero de clientes na fila: "+fila.size());
			s.label_n_client_atend_gasolina.setText("Numero de clientes atendidos: "+atendidos);
			s.label_temp_sim_gasolina.setText("Tempo de simulacao: "+s.getInstante());
		} else if (this.tipo.equals("gasoleo")) {
			System.out.println("Atendidos: "+atendidos+" Fila size: "+fila.size()+" Soma temp espera: "+soma_temp_esp);
			s.label_tempo_medio_espera_gasoleo.setText("Tempo medio de espera: "+temp_med_fila);
			s.label_comp_medio_fila_gasoleo.setText("Comprimento medio da fila: "+comp_med_fila);
			s.label_util_serv_gasoleo.setText("Utilizacao do servico: "+utilizacao_serv);
			s.label_n_client_fila_gasoleo.setText("Numero de clientes na fila: "+fila.size());
			s.label_n_client_atend_gasoleo.setText("Numero de clientes atendidos: "+atendidos);
			s.label_temp_sim_gasoleo.setText("Tempo de simulacao: "+s.getInstante());
		} else if (this.tipo.equals("loja")){
			s.label_tempo_medio_espera_loja.setText("Tempo medio de espera: "+temp_med_fila);
			s.label_comp_medio_fila_loja.setText("Comprimento medio da fila: "+comp_med_fila);
			s.label_util_serv_loja.setText("Utilizacao do servico: "+utilizacao_serv);
			s.label_n_client_fila_loja.setText("Numero de clientes na fila: "+fila.size());
			s.label_n_client_atend_loja.setText("Numero de clientes atendidos: "+atendidos);
			s.label_temp_sim_loja.setText("Tempo de simulacao: "+s.getInstante());
		}

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

    public String toString() {
        return tipo.toUpperCase();
    }
}