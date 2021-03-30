public class ContaInvalidaException extends Exception {

    public ContaInvalidaException(String motivo) {
        super("ERRO!!!\n>>> " + motivo);
    }
    
    
}
