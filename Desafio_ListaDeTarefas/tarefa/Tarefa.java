package tarefa;

import java.io.Serializable;

/* implements Serializable permite gravar os dados em um arquivo binário. */
public class Tarefa implements Serializable{
    private String tarefa;
    private String deadline;

    public Tarefa(String tarefa, String deadline) {
        this.tarefa = tarefa;
        this.deadline = deadline;
    }

    public String getTarefa() {
        return tarefa;
    }

    public String getDeadline() {
        return deadline;
    }
}
