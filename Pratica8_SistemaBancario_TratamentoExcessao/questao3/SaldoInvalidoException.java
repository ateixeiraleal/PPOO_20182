public class SaldoInvalidoException extends Exception {

    public SaldoInvalidoException(String message) {
        super("ERRO!!!\n>>> " + message);
    }
    
}
