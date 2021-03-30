public class Investimento {
    private TipoInvestidor investidor;

    public Investimento(TipoInvestidor investidor) {
        this.investidor = investidor;
    }
    
    public double calcularTotalRendimento(double valor, int tempo) {
        double valorRendimento = 0;
        double valorTotalRendimento = 0;
        for (int i = 0; i < tempo; i++) {
            System.out.printf("*** taxa de rendimento no %dº mês -> ", i+1);
            valorRendimento = investidor.calcularRendimento(valor);
            valor += valorRendimento;
            System.out.printf(" = %.2f -> Total = %.2f\n", valorRendimento, valor);
            valorTotalRendimento += valorRendimento;
        }
        return valorTotalRendimento;
    }
}
