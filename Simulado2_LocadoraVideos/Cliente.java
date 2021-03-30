

public class Cliente {
    private int qdeLocacao;
    private String nome;
    private String dataNascimento;

    public Cliente(String nome, String dataNascimento) {
        this.qdeLocacao = 0;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public void setQdeLocacao() {
        this.qdeLocacao ++;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
}
