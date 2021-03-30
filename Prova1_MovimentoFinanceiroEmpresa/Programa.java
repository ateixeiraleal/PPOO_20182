public class Programa {
    private static Extrato e = new Extrato();
    
    public static void main(String[] args) {
        MovimentacaoFinanceira m1 = new Despesa("012.345.678/0001-10", 3, 8, 2, 2018, "15:30", 500);
        MovimentacaoFinanceira m2 = new Despesa("765.432.109/0001-10", 1, 9, 4, 2018, "13:49", 407);
        MovimentacaoFinanceira m3 = new Receita("123.456.789-0", "Debito", 10, 6, 2018, "09:28", 1500);
        MovimentacaoFinanceira m4 = new Receita("098.756.432-1", "Dinheiro", 11, 10, 2018, "17:00", 1000);
        
        e.adicionarMovimento(m1);
        e.adicionarMovimento(m2);
        e.adicionarMovimento(m3);
        e.adicionarMovimento(m4);
        
        e.exibirExtrato();
    }
}
