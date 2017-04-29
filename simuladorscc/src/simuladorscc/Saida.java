/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorscc;

// Classe que representa a saida de um cliente. Deriva de Evento.

public class Saida extends Evento {
	//Construtor
	Saida (double i, Simulador s, Servico tipo){
		super(i, s, tipo);
	}

	// Metodo que executa as accoes correspondentes a saida de um cliente
    @Override
	void executa(){
		// Retira cliente do servico
        tipo.removeServico();
    }

    // Metodo que descreve o evento.
    // Para ser usado na listagem da lista de eventos.
    @Override
    public String toString(){
         return "SAIDA "+tipo+" | INSTANTE: " + instante;
    }


}