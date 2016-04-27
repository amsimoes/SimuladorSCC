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
