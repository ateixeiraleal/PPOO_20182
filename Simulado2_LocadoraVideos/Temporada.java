public class Temporada extends Video{
    private int qdeEpisodios;

    public Temporada(int qdeEpisodios, String titulo, int anoLancamento, double preco) {
        super(titulo, anoLancamento, preco);
        this.qdeEpisodios = qdeEpisodios;
    }

    public int getQdeEpisodios() {
        return qdeEpisodios;
    }
    
    @Override
    public double getValorVideo(){
        return getQdeEpisodios()*getPreco();
    }
    
    @Override
    public String getInformacoes(){
        return "\t" + getTitulo() + " (" + getQdeEpisodios() + " ep.) (" 
            + getQdeEpisodios() + " x R$ " + getPreco() + " = R$ "
            + getValorVideo() + ")";
    }
}
