package pratica5;

public class PessoaJuridica extends Contato{
    private String cnpj;
    private int inscricaoEstatual;

    public PessoaJuridica(String cnpj, int inscricaoEstatual, String nome, String endereco, String email) {
        super(nome, endereco, email);
        this.cnpj = cnpj;
        this.inscricaoEstatual = inscricaoEstatual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public int getInscricaoEstatual() {
        return inscricaoEstatual;
    }
    
    @Override
    public String toString() {
        return "\tNome: " + getNome() + "\n\tEndereço: " + getEndereco()
                + "\n\tEmail: " + getEmail() + "\n\tCNPJ: " + getCnpj()
                + "\n\tInscrição Estadual: " + getInscricaoEstatual();
    }
}
