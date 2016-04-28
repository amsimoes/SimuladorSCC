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
import javax.swing.*;


public class InterfaceSimulador extends JFrame {
    protected JTabbedPane panel_tabs = new JTabbedPane();

    protected JPanel panel_gasolina;
    protected JPanel panel_box_gasolina;
    protected JLabel label_tempo_medio_espera_gasolina;
    protected JLabel label_comp_medio_fila_gasolina;
    protected JLabel label_util_serv_gasolina;
    protected JLabel label_temp_sim_gasolina;
    protected JLabel label_n_client_atend_gasolina;
    protected JLabel label_n_client_fila_gasolina;

    protected JPanel panel_gasoleo;
    protected JPanel panel_box_gasoleo;
    protected JLabel label_tempo_medio_espera_gasoleo;
    protected JLabel label_comp_medio_fila_gasoleo;
    protected JLabel label_util_serv_gasoleo;
    protected JLabel label_temp_sim_gasoleo;
    protected JLabel label_n_client_atend_gasoleo;
    protected JLabel label_n_client_fila_gasoleo;


    protected JPanel panel_loja;
    protected JPanel panel_box_loja;
    protected JLabel label_tempo_medio_espera_loja;
    protected JLabel label_comp_medio_fila_loja;
    protected JLabel label_util_serv_loja;
    protected JLabel label_temp_sim_loja;
    protected JLabel label_n_client_atend_loja;
    protected JLabel label_n_client_fila_loja;


    protected void init() {
        setTitle("Simulador de Bombas de Gasolina");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel_gasolina = new JPanel();
        panel_gasolina.setLayout(new BorderLayout());

        panel_box_gasolina = new JPanel();
        panel_box_gasolina.setLayout(new BoxLayout(panel_box_gasolina, BoxLayout.PAGE_AXIS));

        label_tempo_medio_espera_gasolina = new JLabel("Tempo medio de espera Gasolina: ");
        panel_box_gasolina.add(label_tempo_medio_espera_gasolina);
        panel_box_gasolina.add(Box.createVerticalGlue());
        label_comp_medio_fila_gasolina = new JLabel("Comprimento medio de fila Gasolina: ");
        panel_box_gasolina.add(label_comp_medio_fila_gasolina);
        panel_box_gasolina.add(Box.createVerticalGlue());
        label_util_serv_gasolina = new JLabel("Utilizacao servico Gasolina: ");
        panel_box_gasolina.add(label_util_serv_gasolina);
        panel_box_gasolina.add(Box.createVerticalGlue());
        label_temp_sim_gasolina = new JLabel("Tempo de simulacao Gasolina: ");
        panel_box_gasolina.add(label_temp_sim_gasolina);
        panel_box_gasolina.add(Box.createVerticalGlue());
        label_n_client_atend_gasolina = new JLabel("No clientes atendidos Gasolina: ");
        panel_box_gasolina.add(label_n_client_atend_gasolina);
        panel_box_gasolina.add(Box.createVerticalGlue());
        label_n_client_fila_gasolina = new JLabel("No clientes em fila Gasolina: ");
        panel_box_gasolina.add(label_n_client_fila_gasolina);
        panel_box_gasolina.add(Box.createVerticalGlue());

        panel_gasolina.add(panel_box_gasolina, BorderLayout.CENTER);

        panel_gasoleo = new JPanel();
        panel_gasoleo.setLayout(new BorderLayout());

        panel_box_gasoleo = new JPanel();
        panel_box_gasoleo.setLayout(new BoxLayout(panel_box_gasoleo, BoxLayout.PAGE_AXIS));

        label_tempo_medio_espera_gasoleo = new JLabel("Tempo medio de espera Gasoleo: ");
        panel_box_gasoleo.add(label_tempo_medio_espera_gasoleo);
        panel_box_gasoleo.add(Box.createVerticalGlue());
        label_comp_medio_fila_gasoleo = new JLabel("Comprimento medio de fila Gasoleo: ");
        panel_box_gasoleo.add(label_comp_medio_fila_gasoleo);
        panel_box_gasoleo.add(Box.createVerticalGlue());
        label_util_serv_gasoleo = new JLabel("Utilizacao servico Gasoleo: ");
        panel_box_gasoleo.add(label_util_serv_gasoleo);
        panel_box_gasoleo.add(Box.createVerticalGlue());
        label_temp_sim_gasoleo = new JLabel("Tempo de simulacao Gasoleo: ");
        panel_box_gasoleo.add(label_temp_sim_gasoleo);
        panel_box_gasoleo.add(Box.createVerticalGlue());
        label_n_client_atend_gasoleo = new JLabel("No clientes atendidos Gasoleo: ");
        panel_box_gasoleo.add(label_n_client_atend_gasoleo);
        panel_box_gasoleo.add(Box.createVerticalGlue());
        label_n_client_fila_gasoleo = new JLabel("No clientes em fila Gasoleo: ");
        panel_box_gasoleo.add(label_n_client_fila_gasoleo);
        panel_box_gasoleo.add(Box.createVerticalGlue());

        panel_gasoleo.add(panel_box_gasoleo, BorderLayout.CENTER);

        panel_loja = new JPanel();
        panel_loja.setLayout(new BorderLayout());

        panel_box_loja = new JPanel();
        panel_box_loja.setLayout(new BoxLayout(panel_box_loja, BoxLayout.PAGE_AXIS));

        label_tempo_medio_espera_loja = new JLabel("Tempo medio de espera Loja: ");
        panel_box_loja.add(label_tempo_medio_espera_loja);
        panel_box_loja.add(Box.createVerticalGlue());
        label_comp_medio_fila_loja = new JLabel("Comprimento medio de fila Loja: ");
        panel_box_loja.add(label_comp_medio_fila_loja);
        panel_box_loja.add(Box.createVerticalGlue());
        label_util_serv_loja = new JLabel("Utilizacao servico Loja: ");
        panel_box_loja.add(label_util_serv_loja);
        panel_box_loja.add(Box.createVerticalGlue());
        label_temp_sim_loja = new JLabel("Tempo de simulacao Loja: ");
        panel_box_loja.add(label_temp_sim_loja);
        panel_box_loja.add(Box.createVerticalGlue());
        label_n_client_atend_loja = new JLabel("No clientes atendidos Loja: ");
        panel_box_loja.add(label_n_client_atend_loja);
        panel_box_loja.add(Box.createVerticalGlue());
        label_n_client_fila_loja = new JLabel("No clientes em fila Loja: ");
        panel_box_loja.add(label_n_client_fila_loja);
        panel_box_loja.add(Box.createVerticalGlue());

        panel_loja.add(panel_box_loja, BorderLayout.CENTER);

        panel_tabs.addTab("Gasolina", panel_gasolina);
        panel_tabs.addTab("Gasoleo", panel_gasoleo);
        panel_tabs.addTab("Loja", panel_loja);

        this.add(panel_tabs);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
    }

    protected void refresh() {
        this.revalidate();
    }
}
