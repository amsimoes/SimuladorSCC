/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorscc;

/**
 * Created by ams on 19-04-2016.
 */
public class Transicao extends Evento {
    public Transicao(double instante, Simulador s, Servico tipo) {
        super(instante, s, tipo);
    }

    @Override
    void executa() {
        s.getServico_loja().insereServico(tipo.removeServico());
    }

    @Override
    public String toString() {
        return "TRANSICAO " +tipo+" -> Loja | Instante: "+ instante;
    }
}
