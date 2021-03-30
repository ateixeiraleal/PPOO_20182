public class Caminhao extends Veiculo{
    private int numeroEixos;
    private int capacidadeCarga;

    public Caminhao(String marca, int ano, String cor, int numeroEixos, int capacidadeCarga) {
        super(marca, ano, cor);
        this.numeroEixos = numeroEixos;
        this.capacidadeCarga = capacidadeCarga;
    }

    public int getNumeroEixos() {
        return numeroEixos;
    }

    public int getCapacidadeCarga() {
        return capacidadeCarga;
    }
    
    public String toString() {
        return "\nMarca: " + getMarca() + "\nAno: " + getAno() + "\nCor: " + getCor()
                + "\nNúmero de eixos: " + getNumeroEixos()
                + "\nCapacidade de carga: " + getCapacidadeCarga();
    }
}
