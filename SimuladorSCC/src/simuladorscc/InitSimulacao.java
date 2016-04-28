/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorscc;

/**
 * Created by ams on 26-04-2016.
 */

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/*
    Indice da Lista de Dados:
    0 - Tempo de simulacao | 1 - Intervalo de chegada
    2 - Tempo Servico Bombas | 3 - Tempo Servico Loja
    4 - Empregados Gasolina | 5 - Empregados Gasoleo
    6 - Empregados Loja | 7 - Desvio Bombas
    8 - Desvio Loja
 */

public class InitSimulacao {
    public static ArrayList<String> executa() {
        ArrayList<String> lista = new ArrayList<>();

        JTextField tempo_simulacao = new JTextField("");
        JTextField intervalo_chegada = new JTextField("");
        JTextField tempo_servico_bombas = new JTextField("");
        JTextField desvio_servico_bombas = new JTextField("");
        JTextField tempo_servico_loja = new JTextField("");
        JTextField desvio_servico_loja = new JTextField("");
        JTextField empregados_gasolina = new JTextField("");
        JTextField empregados_gasoleo = new JTextField("");
        JTextField empregados_loja = new JTextField("");

        JPanel panel = new JPanel(new GridLayout(0,1));

        panel.add(new JLabel("Tempo de simulacao: "));
        panel.add(tempo_simulacao);
        panel.add(new JLabel("Intervalo de chegada de clientes: "));
        panel.add(intervalo_chegada);
        panel.add(new JLabel("Tempo medio de servico nas Bombas: "));
        panel.add(tempo_servico_bombas);
        panel.add(new JLabel("Desvio Padrao Servico Bombas: "));
        panel.add(desvio_servico_bombas);
        panel.add(new JLabel("Tempo medio de servico na Loja: "));
        panel.add(tempo_servico_loja);
        panel.add(new JLabel("Desvio Padrao Servico Loja: "));
        panel.add(desvio_servico_loja);
        panel.add(new JLabel("Numero de empregados na bomba de Gasolina: "));
        panel.add(empregados_gasolina);
        panel.add(new JLabel("Numero de empregados na bomba de Gasoleo: "));
        panel.add(empregados_gasoleo);
        panel.add(new JLabel("Numero de empregados na Loja: "));
        panel.add(empregados_loja);

        int confirm = JOptionPane.showConfirmDialog(null, panel, "Configuracao Parametros Simulador", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (confirm == JOptionPane.OK_OPTION) {
            lista.add(tempo_simulacao.getText());
            lista.add(intervalo_chegada.getText());
            lista.add(tempo_servico_bombas.getText());
            lista.add(tempo_servico_loja.getText());
            lista.add(empregados_gasolina.getText());
            lista.add(empregados_gasoleo.getText());
            lista.add(empregados_loja.getText());
            lista.add(desvio_servico_bombas.getText());
            lista.add(desvio_servico_loja.getText());

            // Verificar se foram inseridos números apenas
            try {
                for(int i=0;i<lista.size();i++) {
                    Double.parseDouble(lista.get(i));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Parametros invalidos. Tente novamente.");
                executa();
            }
        } else {
            System.exit(0);
        }
        return lista;
    }
}
