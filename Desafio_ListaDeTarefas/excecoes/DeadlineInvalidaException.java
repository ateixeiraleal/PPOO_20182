package excecoes;

/* Classe responsável pelo tratamento de exceção de Deadline inválidas*/
public class DeadlineInvalidaException extends Exception {

    /* Construtor que receberá como Strings a data e o motivo da deadline não ser aceita.
       Quem irá fornecer o motivo será o método que lançará a exceção. */
    public DeadlineInvalidaException(String data, String motivo) {
        super("A data \"" + data + "\" é inválida!\n" + motivo);
    } 
}