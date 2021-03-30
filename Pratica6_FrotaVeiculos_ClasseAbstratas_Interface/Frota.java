import java.util.ArrayList;

public class Frota {
    ArrayList<Veiculo> frota;

    public Frota() {
        this.frota = new ArrayList<Veiculo>();
    }
    
    public void inserirVeiculo(Veiculo v) {
        frota.add(v);
    }
    
    public void gerarRelatorio() {
        for(Veiculo v: frota) {
            System.out.println(v.toString());
        }
    }
}