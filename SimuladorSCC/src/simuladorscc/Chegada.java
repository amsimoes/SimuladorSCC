/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorscc;

// Classe que representa a chegada de um cliente. Deriva de Evento.

public class Chegada extends Evento {
    //Construtor
    Chegada (double i, Simulador s, Servico tipo){
        super (i, s, tipo);
    }

    // Metodo que executa as accoes correspondentes a chegada de um cliente
    @Override
    void executa (){
        // Coloca cliente no servico - na fila ou a ser atendido, conforme o caso
        tipo.insereServico (new Cliente());

        double rand = RandomGenerator.rand(2);

        if(rand <= 0.2) {
            s.insereEvento(new Chegada(s.getInstante()+Aleatorio.exponencial(s.getMedia_cheg()), s, s.getServico_gasoleo()));
        } else {
            // Agenda nova chegada para daqui a Aleatorio.exponencial(s.media_cheg) instantes
            s.insereEvento (new Chegada(s.getInstante()+Aleatorio.exponencial(s.getMedia_cheg()), s, s.getServico_gasolina()));
        }
    }

    // Metodo que descreve o evento.
    // Para ser usado na listagem da lista de eventos.
    @Override
    public String toString(){
        return "Servico " + tipo + " | Chegada em " + instante;
    }
}