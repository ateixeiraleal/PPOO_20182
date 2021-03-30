
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Locacao {
    private String dataLocacao;
    private String dataExpiracao;
    private Cliente cliente;
    private ArrayList<Video> videos;

    public Locacao(Cliente cliente) {
        this.dataLocacao = pegarData();
        dataExpiracao = pegarData();
        this.cliente = cliente;
        videos = new ArrayList<Video>();
    }

    public String getDataLocacao() {
        return dataLocacao;
    }

    public String getDataExpiracao() {
        return dataExpiracao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }
    
    public void adicionarVideo(Video video){
        if(videos.size() <= 5){
            videos.add(video);
        }
    }
    
    public String pegarData(){
        Date data = new Date();
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yy");
        String dataFormatada = formatar.format(data);
        return dataFormatada;
    } 
}
