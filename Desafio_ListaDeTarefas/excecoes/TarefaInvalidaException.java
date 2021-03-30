package excecoes;

/* Classe responsável pelo tratamento de exceção de tarefas inválidas*/
public class TarefaInvalidaException extends Exception {

    /* Construtor que receberá como String o motivo da tarefa não ser aceita.
       Quem irá fornecer o motivo será o método que lançará a exceção. */
    public TarefaInvalidaException(String motivo) {
        super("Tarefa Inválida\n" + motivo);
    }
    
}
