
import java.util.ArrayList;

public class Extrato {
    ArrayList<MovimentacaoFinanceira> e;

    public Extrato() {
        this.e = new ArrayList<MovimentacaoFinanceira>();
    }
    
    public void exibirExtrato() {
        for(MovimentacaoFinanceira m: e){
            System.out.println(m.toString());
        }
    }
    public void adicionarMovimento(MovimentacaoFinanceira m) {
        e.add(m);
    }
}
