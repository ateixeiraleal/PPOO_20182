public class Conservador implements TipoInvestidor{
    @Override
    public double calcularRendimento(double valor){
        System.out.print("0.8%");
        return valor * 0.008;
    }
}
