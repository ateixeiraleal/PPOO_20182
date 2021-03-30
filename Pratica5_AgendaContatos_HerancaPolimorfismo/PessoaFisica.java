package pratica5;

public class PessoaFisica extends Contato{
    private String cpf;
    private String estadoCivil;

    public PessoaFisica(String cpf, String estadoCivil, String nome, String endereco, String email) {
        super(nome, endereco, email);
        this.cpf = cpf;
        this.estadoCivil = estadoCivil;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }
    
    @Override
    public String toString() {
        return "\tNome: " + getNome() + "\n\tEndereço: " + getEndereco()
                + "\n\tEmail: " + getEmail() + "\n\tCPF: " + getCpf()
                + "\n\tEstado civil: " + getEstadoCivil();
    }
}
