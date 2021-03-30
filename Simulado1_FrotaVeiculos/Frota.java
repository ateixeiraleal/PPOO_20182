import java.util.ArrayList;

public class Frota {
    ArrayList<Veiculo> frota;

    public Frota() {
        this.frota = new ArrayList<Veiculo>();
    }
    
    public void adicionarVeiculo(Veiculo v) {
        frota.add(v);
    }
    
    public void exibirVeiculos() {
        for(Veiculo v: frota) {
            System.out.println(v.toString());
        }
    }
}
