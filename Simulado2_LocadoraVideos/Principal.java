
import java.util.ArrayList;

/**
 * @author Anderson Leal
 * @version 14/04/2018
 */
public class Principal {
    
    public void imprimirLocacao(ArrayList<Locacao> locacao){
        double valor = 0;
        for(Locacao i : locacao){
            System.out.printf("Cliente: %s (%s)", i.getCliente().getNome(), i.getCliente().getDataNascimento());
            System.out.printf("\nLocação: %s à %s", i.getDataLocacao(), i.getDataExpiracao());
            System.out.println("\nVídeos:");
            for(Video j : i.getVideos()){ 
                System.out.println(j.getInformacoes());
                valor += j.getValorVideo();
            }
            System.out.printf("Valor total da locação: R$ %.2f\n\n)", valor);
        }
    }

    public static void main(String[] args) {
        ArrayList<Locacao> locacoes = new ArrayList<Locacao>();
        Principal principal = new Principal();
        
        Cliente c1 = new Cliente("Lon Musk", "28/06/1971");
        Video v1 = new Filme(120, "Piratas do Vale do Silício", 2010, 3.9);
        Video v2 = new Temporada(10, "The Big Bang Theory - Primeira Temporada", 2012, 0.9);
        Locacao loc = new Locacao(c1);
        loc.adicionarVideo(v1);
        loc.adicionarVideo(v2);
        locacoes.add(loc);
        principal.imprimirLocacao(locacoes);
    }
}
