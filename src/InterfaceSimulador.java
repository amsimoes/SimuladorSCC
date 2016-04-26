/**
 * Created by ams on 26-04-2016.
 */
import java.awt.*;
import javax.swing.*;


public class InterfaceSimulador extends JFrame {
    protected JPanel panel_gasolina;
    protected JPanel panel_box_gasolina;
    protected JLabel label_tempo_medio_espera_gasolina;
    protected JLabel label_comp_medio_fila_gasolina;
    protected JLabel label_util_serv_gasolina;
    protected JLabel label_temp_sim_gasolina;
    protected JLabel label_n_client_atend_gasolina;
    protected JLabel label_n_client_fila_gasolina;
    protected JTabbedPane panel_tabs = new JTabbedPane();

    protected void init() {
        setTitle("Simulador Bomba de Gasolina");
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

        panel_tabs.addTab("Gasolina", panel_gasolina);

        this.add(panel_tabs);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
    }

    protected void refresh() {
        this.revalidate();
    }
}
