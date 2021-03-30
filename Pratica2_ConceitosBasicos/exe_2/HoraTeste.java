package exe_2;

public class HoraTeste {
    public static void main(String[] args) {
        Horario umaHora = new Horario(10, 15, 43);
        umaHora.exibirHorario();
        umaHora.formatarTempo(3690);
        umaHora.formatarTempo(3521);
        umaHora.formatarTempo(59);
    }
}
