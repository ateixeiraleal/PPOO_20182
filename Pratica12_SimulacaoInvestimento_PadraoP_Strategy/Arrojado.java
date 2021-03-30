public class Arrojado implements TipoInvestidor{
    @Override
    public double calcularRendimento(double valor) {
        double prob = randon.nextDouble();
        if (prob <= 0.65) {
            System.out.print("0.3%");
            return valor * 0.003;
        }else if (prob <= 0.9) {
            System.out.print("2%");
            return valor * 0.02;
        }
        System.out.print("5%");
        return valor * 0.05;
    }
}
