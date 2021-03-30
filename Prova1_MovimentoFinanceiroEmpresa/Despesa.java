public class Despesa extends MovimentacaoFinanceira {
    private String cnpj;
    private int qdeParcelas;

    public Despesa(String cnpj, int qdeParcelas, int dia, int mes, int ano, String horario, double valor) {
        super(dia, mes, ano, horario, valor);
        this.cnpj = cnpj;
        this.qdeParcelas = qdeParcelas;
    }

    public String getCnpj() {
        return cnpj;
    }

    public int getQdeParcelas() {
        return qdeParcelas;
    }
    
    public double calcularImposto() {
        return retornarTaxa() + (getValor() * 0.015) * getQdeParcelas();
    }
    
    public String toString() {
        return "\nDespesa realizada em: " + getDia() + "/" + getMes() + "/" + getAno()
                + "\nValor: " + getValor() + "\nCNPJ: " + getCnpj()
                + "\nNumero de parcelas: " + getQdeParcelas()
                + "\nImposto a pagar: " + calcularImposto();  
    }
    
}
