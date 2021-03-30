public class Bicicleta extends Veiculo{
    private int marchas;

    public Bicicleta(String marca, String modelo, int anoFabricacao, int marchas) {
        super(marca, modelo, anoFabricacao);
        this.marchas = marchas;
    }

    public int getMarchas() {
        return marchas;
    }
    
    public String toString() {
        return "Marca: " + getMarca() + "\nModelo: " + getModelo()
                + "\nAno de Fabrição: " + getAnoFabricacao()
                + "\nMarchas: " + getMarchas() + "\n-----------------";
    }
    
}
