package exe_1;

public class Ponto {
    private double x;
    private double y;

    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public String getPonto(){
        return x + "; "+y;
    }
       
    public double retornarDistancia(double x, double y){
        double distancia;
        distancia = Math.sqrt(Math.pow(x-this.x,2) + Math.pow(y-this.y,2));
        return distancia;
    }
}