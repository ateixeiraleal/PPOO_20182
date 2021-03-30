package exe_2;

public class Horario {
    private int hora;
    private int minuto;
    private int segundo;

    public Horario(int hora, int minuto, int segundo) {
        this.hora = hora;
        this.minuto = minuto;
        this.segundo = segundo;
    }
    
    public void exibirHorario(){
        System.out.printf("%02dh:%02dm:%02ds\n", hora, minuto, segundo);
    }
    
    public void exibirHorario(int hora, int minuto, int segundo){
        System.out.printf("%02dh:%02dm:%02ds\n", hora, minuto, segundo);
    }
    
    public void formatarTempo(int tempo){
        int hora;
        int minuto;
        int segundo;
        minuto = tempo / 60;
        hora = minuto / 60;
        minuto = minuto % 60;
        segundo = tempo % 60;
        
        exibirHorario(hora, minuto, segundo);
    }
}
