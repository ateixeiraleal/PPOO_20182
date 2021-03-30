

public class Filme extends Video{
    private int duracao;

    public Filme(int duracao, String titulo, int anoLancamento, double preco) {
        super(titulo, anoLancamento, preco);
        this.duracao = duracao;
    }

    public int getDuracao() {
        return duracao;
    }
    
    @Override
    public double getValorVideo(){
        return getPreco();
    }
    
    @Override
    public String getInformacoes(){
        return "\t" + getTitulo() + " (" + getDuracao() + ") " + "(R$ " + getPreco() + ")";
    }
}
