public class SaldoInvalidoException extends Exception {

    public SaldoInvalidoException(int numeroConta) {
        super("ERRO!!!\n>>> A conta nº " + numeroConta + " não há saldo suficiente!");
    }
    
}
