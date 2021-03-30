package excecoes;

/* Classe responsável pelo tratamento de exceção de Tarefas Repetidas*/
public class TarefaRepetidaException extends Exception {

    /* Construtor que receberá como Strings o motivo da deadline não ser aceita.
       Quem irá fornecer o motivo será o método que lançará a exceção. */
    public TarefaRepetidaException(String motivo) {
        super("Tarefa Repetida.\n\n" + motivo);
    } 
}