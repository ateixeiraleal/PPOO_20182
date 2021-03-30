public class Moderado implements TipoInvestidor{
    @Override
    public double calcularRendimento(double valor) {
        if (randon.nextDouble() <= 0.8) {
            System.out.print("0.6%");
            return valor * 0.006;
        }
        System.out.print("2%");
        return valor * 0.02;        
    }
}
