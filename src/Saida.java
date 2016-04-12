// Classe que representa a sa�da de um cliente. Deriva de Evento.

public class Saida extends Evento {

	//Construtor
	Saida (double i, Simulador s){
		super(i, s);
	}

	// Metodo que executa as accoes correspondentes a saida de um cliente
    @Override
	void executa (Servico serv){
		// Retira cliente do servi�o
        serv.removeServico();
    }

    // Metodo que descreve o evento.
    // Para ser usado na listagem da lista de eventos.
    @Override
    public String toString(){
         return "Saida em " + instante;
    }


}