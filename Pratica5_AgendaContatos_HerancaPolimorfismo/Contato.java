package pratica5;

public class Contato {
    private String nome;
    private String endereco;
    private String email;

    public Contato(String nome, String endereco, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;        
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }
    
    public boolean removerContato() {
        return false;
    }
    
    @Override
    public String toString() {
        return "";
    }
}
