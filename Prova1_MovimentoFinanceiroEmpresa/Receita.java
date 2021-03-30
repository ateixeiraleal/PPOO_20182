public class Receita extends MovimentacaoFinanceira {
    private String cpf;
    private String formaRecebimento;

    public Receita(String cpf, String formaRecebimento, int dia, int mes, int ano, String horario, double valor) {
        super(dia, mes, ano, horario, valor);
        this.cpf = cpf;
        this.formaRecebimento = formaRecebimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getFormaRecebimento() {
        return formaRecebimento;
    }
    
    public double calcularImposto() {
        if(getFormaRecebimento() == "Dinheiro"){
            return retornarTaxa() + getValor() * 0.015;
        }else if(getFormaRecebimento() == "Debito"){
            return retornarTaxa() + getValor() * 0.03;
        }
        return retornarTaxa() + getValor() * 0.04;
    }

    public String toString() {
        return "\nReceita recebida em: " + getDia() + "/" + getMes() + "/" + getAno()
                + "\nValor: " + getValor() + "\nCPF: " + getCpf()
                + "\nForma de pagamento: " + getFormaRecebimento()
                + "\nImposto a pagar: " + calcularImposto();  
    }
}
