public class Caminhao extends Veiculo implements Tributavel{
    private int numEixos;

    public Caminhao(String marca, String modelo, int anoFabricacao, int numEixos) {
        super(marca, modelo, anoFabricacao);
        this.numEixos = numEixos;
    }

    public int getNumEixos() {
        return numEixos;
    }
    
    @Override
    public String toString() {
        return "Marca: " + getMarca() + "\nModelo: " + getModelo()
                + "\nAno de Fabrição: " + getAnoFabricacao()
                + "\nNúmero de Eixos: " + getNumEixos()
                + "\nIPVA: " + calcularIPVA() + "\n-----------------";
    }
    
    @Override
    public double calcularIPVA() {
        return 200 * getNumEixos()/(2018 - getAnoFabricacao());
    }
}
