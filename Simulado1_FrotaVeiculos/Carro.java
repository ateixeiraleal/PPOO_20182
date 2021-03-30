public class Carro extends Veiculo {
    private float capacidadePortaMalas;
    private int numPortas;

    public Carro(String marca, int ano, String cor, float capacidade, int numPortas) {
        super(marca, ano, cor);
        this.capacidadePortaMalas = capacidade;
        this.numPortas = numPortas;
    }

    public float getCapacidadePortaMalas() {
        return capacidadePortaMalas;
    }

    public int getNumPortas() {
        return numPortas;
    }
    
    public String toString() {
        return "\nMarca: " + getMarca() + "\nAno de Fabrição: " + getAno()
                + "\nCor: " + getCor() + "\nCapacidade do Porta-malas: "
                + getCapacidadePortaMalas() + "\nNúmero de Portas: "
                + getNumPortas();
    }
    
    public double calcularIPVA() {
        return 0;
    }
}
