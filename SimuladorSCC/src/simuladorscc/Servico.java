package simuladorscc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
	private double desvio_gasolina, desvio_gasoleo, desvio_loja;
	private static int contador_gasolina, contador_gasoleo, contador_loja;
	private double media_gasolina_1, media_gasolina_2;
        private double media_gasoleo_1, media_gasoleo_2;
	private double media_loja_1, media_loja_2;
	private double[] x_normal_loja;
	private double[] x_normal_gasolina, x_normal_gasoleo;

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
		this.contador_gasolina = 0;
                this.contador_gasoleo = 0;
		this.contador_loja = 0;
		if(this.tipo.equals("loja")) {
			this.desvio_loja = desvio;
		} else if (this.tipo.equals("gasolina")) {
			this.desvio_gasolina = desvio;
		} else if (this.tipo.equals("gasoleo")) {
                        this.desvio_gasoleo = desvio;
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
			} else if (tipo.equals("gasolina")) {
				System.out.println("insereServico | Contador bombas = "+contador_gasolina);
				if(contador_gasolina % 2 == 0) {
					x_normal_gasolina = normal(s.getMedia_serv_gasolina(), desvio_gasolina, 40);
					media_gasolina_1 = x_normal_gasolina[0];
					media_gasolina_2 = x_normal_gasolina[1];
					s.insereEvento(new Transicao(s.getInstante()+media_gasolina_1, s, this));
					contador_gasolina++;
				} else {
					s.insereEvento(new Transicao(s.getInstante() + media_gasolina_2, s, this));
					contador_gasolina++;
				}
			} else if (tipo.equals("gasoleo")) {
                                System.out.println("insereServico | Contador gasoleo = "+contador_gasoleo);
				if(contador_gasoleo % 2 == 0) {
					x_normal_gasoleo = normal(s.getMedia_serv_gasoleo(), desvio_gasoleo, 40);
					media_gasoleo_1 = x_normal_gasoleo[0];
					media_gasoleo_2 = x_normal_gasoleo[1];
					s.insereEvento(new Transicao(s.getInstante()+media_gasoleo_1, s, this));
					contador_gasoleo++;
				} else {
					s.insereEvento(new Transicao(s.getInstante() + media_gasoleo_2, s, this));
					contador_gasoleo++;
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
			} else if (tipo.equals("gasolina")) {
				System.out.println("insereServico | Contador bombas = "+contador_gasolina);
				if(contador_gasolina % 2 == 0) {
					x_normal_gasolina = normal(s.getMedia_serv_gasolina(), desvio_gasolina, 40);
					media_gasolina_1 = x_normal_gasolina[0];
					media_gasolina_2 = x_normal_gasolina[1];
					s.insereEvento(new Transicao(s.getInstante()+media_gasolina_1, s, this));
					contador_gasolina++;
				} else {
					s.insereEvento(new Transicao(s.getInstante() + media_gasolina_2, s, this));
					contador_gasolina++;
				}
			} else if (tipo.equals("gasoleo")) {
                                System.out.println("insereServico | Contador gasoleo = "+contador_gasoleo);
				if(contador_gasoleo % 2 == 0) {
					x_normal_gasoleo = normal(s.getMedia_serv_gasoleo(), desvio_gasoleo, 40);
					media_gasoleo_1 = x_normal_gasoleo[0];
					media_gasoleo_2 = x_normal_gasoleo[1];
					s.insereEvento(new Transicao(s.getInstante()+media_gasoleo_1, s, this));
					contador_gasoleo++;
				} else {
					s.insereEvento(new Transicao(s.getInstante() + media_gasoleo_2, s, this));
					contador_gasoleo++;
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
	public String relat (){
                String res;
            
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
		// System.out.println("getInstante = "+s.getInstante()+" soma_temp_servico = "+soma_temp_serv);

		// Apresenta resultados
                res = "---"+tipo.toUpperCase()+"---\n";
                res += "Tempo medio de espera "+temp_med_fila+"\n";
                res += "Comp. medio da fila "+comp_med_fila+"\n";
                res += "Utilizacao do servico "+utilizacao_serv+"\n";
                res += "Tempo de simulacao "+s.getInstante()+"\n";
                res += "Numero de clientes atendidos "+atendidos+"\n";
                res += "Numero de clientes na fila "+fila.size()+"\n";
                
                //System.out.println("---"+tipo.toUpperCase()+"---");
		//System.out.println("Tempo medio de espera "+temp_med_fila);
		//System.out.println("Comp. medio da fila "+comp_med_fila);
		//System.out.println("Utilizacao do servico "+utilizacao_serv);
		//System.out.println("Tempo de simulacao "+s.getInstante()); // Valor actual
		//System.out.println("Numero de clientes atendidos "+atendidos);
		//System.out.println("Numero de clientes na fila "+fila.size()); // Valor actual
                return res;
	}

	// Metodo que devolve o numero de clientes atendidos no servico ate ao momento
	public int getAtendidos() {
		return atendidos;
	}

    public String toString() {
        return tipo.toUpperCase();
    }
}