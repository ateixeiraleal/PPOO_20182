public class Carro extends Veiculo implements Tributavel{
    private String categoria;

    public Carro(String marca, String modelo, int anoFabricacao, String categoria) {
        super(marca, modelo, anoFabricacao);
        this.categoria = categoria;
    }
    
    public String getCategoria() {
        return categoria;
    }  
    
    @Override
    public String toString() {
        return "Marca: " + getMarca() + "\nModelo: " + getModelo()
                + "\nAno de Fabrição: " + getAnoFabricacao()
                + "\nCategoria: " + getCategoria() 
                + "\nIPVA: " + calcularIPVA() +"\n-----------------";
    }
    
    @Override
    public double calcularIPVA() {
        String categoria = getCategoria();
        if( categoria == "passeio"){
            return 1500/(2018 - getAnoFabricacao());
        }else if(getCategoria() == "utilitario") {
            return 1000/(2018 - getAnoFabricacao());
        }
        return 0;
    }
    
}
