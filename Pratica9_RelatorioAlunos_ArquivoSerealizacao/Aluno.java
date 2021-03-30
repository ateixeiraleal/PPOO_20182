import java.io.Serializable;
import java.util.ArrayList;

public class Aluno implements Serializable {
    private long matricula;
    private String nome;
    private double nota;
    private String situacao;

    public Aluno(long matricula, String nome, double nota) {
        this.matricula = matricula;
        this.nome = nome;
        this.nota = nota;
        if(nota >= 60) {
            this.situacao = "Aprovado";
        }else {
            this.situacao = "Reprovado";
        }
    }

    public long getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public double getNota() {
        return nota;
    }

    public String getSituacao() {
        return situacao;
    }
    /*
    public int compareTo(Aluno a) {
        if(this.nota > a.getNota()) {
            return 1;
        }else if(this.nota < a.getNota()) {
            return -1;
        }
        return 0;
    }
    */
}
