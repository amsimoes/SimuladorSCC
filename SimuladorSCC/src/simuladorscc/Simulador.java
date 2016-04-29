/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorscc;

import java.util.ArrayList;
public class Simulador extends Interface {
	private double instante; // Relogio de simulacao - variavel que contem o valor do tempo em cada instante
	private double media_cheg, media_serv_gasolina, media_serv_gasoleo; // Medias das distribuicoes de chegadas e de atendimento no servico
	// private int n_clientes; // Numero de clientes que vao ser atendidos
	private Servico servico_gasolina; // Servico - pode haver mais do que um num simulador
	private Servico servico_gasoleo;
	private Servico servico_loja;
        private Servico servico_self;
	private ListaEventos lista; // Lista de eventos - onde ficam registados todos os eventos que vao ocorrer na simulacao
	// Cada simulador so tem uma
	private double instante_final;
	private double media_serv_loja, media_serv_self;
	private int n_empregados_gasolina, n_empregados_gasoleo, n_empregados_loja, n_empregados_self;
	private double desvio_gasolina, desvio_gasoleo, desvio_loja, desvio_self;
        private int cenario;


	// Construtor
	public Simulador(ArrayList<String> ListaDados, int cenario) {
		//super();
		// Inicializacao do relogio de simulacao
		instante = 0;
		instante_final = Double.parseDouble(ListaDados.get(0));
		// Inicializacao de parametros do simulador
		media_cheg = Double.parseDouble(ListaDados.get(1));
                
                // Criacao da lista de eventos
		lista = new ListaEventos(this);
                this.cenario = cenario;
                
                if(cenario == 1) {
                    media_serv_gasolina = Double.parseDouble(ListaDados.get(2));
                    media_serv_gasoleo = Double.parseDouble(ListaDados.get(3));
                    media_serv_loja = Double.parseDouble(ListaDados.get(4));
                    desvio_gasolina = Double.parseDouble(ListaDados.get(5));
                    desvio_gasoleo = Double.parseDouble(ListaDados.get(6));
                    desvio_loja = Double.parseDouble(ListaDados.get(7));
                    n_empregados_gasolina = Integer.parseInt(ListaDados.get(8));
                    n_empregados_gasoleo = Integer.parseInt(ListaDados.get(9));
                    n_empregados_loja = Integer.parseInt(ListaDados.get(10));
                    // Criacao do servico
                    servico_gasolina = new Servico(this, "gasolina", n_empregados_gasolina);
                    servico_gasoleo = new Servico(this, "gasoleo", n_empregados_gasoleo);
                    servico_loja = new Servico(this, "loja", n_empregados_loja);
                    
                    // Agendamento da primeira chegada
                    // Se nao for feito, o simulador nao tem eventos para simular

                    Double rand = RandomGenerator.rand(10);
                    if(rand <= 0.2) {
                            insereEvento (new Chegada(instante, this, servico_gasoleo));
                    } else {
                            insereEvento (new Chegada(instante, this, servico_gasolina));
                    }
                } else {
                    media_serv_self = Double.parseDouble(ListaDados.get(2));
                    desvio_self = Double.parseDouble(ListaDados.get(3));
                    n_empregados_self = Integer.parseInt(ListaDados.get(4));
                    
                    servico_self = new Servico(this, "self", n_empregados_self);
                    insereEvento (new Chegada(instante, this, servico_self));
                }
	}

	// programa principal
	public static void main(String[] args) {
            initInterface();
	}

	// Metodo que insere o evento e1 na lista de eventos
	void insereEvento (Evento e1){
		lista.insereEvento (e1);
	}

	// Metodo que actualiza os valores estatisticos do simulador
	private void act_stats(){
            //System.out.println("Actualiza Stats!");
            if(cenario == 1) {
               servico_gasolina.act_stats();
                servico_gasoleo.act_stats();
                servico_loja.act_stats(); 
            } else
                servico_self.act_stats();
            //System.out.println("Fim Stats!");
	}

	// Metodo que apresenta os resultados de simulacao finais
	public String relat (){
            String res;
            if (cenario == 1) {
                String s1 = servico_gasolina.relat();
		String s2 = servico_gasoleo.relat();
		String s3 = servico_loja.relat();
                res = s1+s2+s3;
            } else {
                String s4 = servico_self.relat();
                res = s4;
            }  
            return res;
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
	public double getMedia_serv_gasolina() {
		return media_serv_gasolina;
	}
        
        public double getMedia_serv_gasoleo() {
                return media_serv_gasoleo;
        }
        
	public double getMedia_serv_loja() {
		return media_serv_loja;
	}
        
        public double getMedia_serv_self() {
                return media_serv_self;
        }

        public double getDesvio_gasolina() {
            return desvio_gasolina;
        }

        public double getDesvio_gasoleo() {
            return desvio_gasoleo;
        }

        public double getDesvio_loja() {
            return desvio_loja;
        }

        public double getDesvio_self() {
            return desvio_self;
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
        public Servico getServico_self() {
                return servico_self;
        }
}