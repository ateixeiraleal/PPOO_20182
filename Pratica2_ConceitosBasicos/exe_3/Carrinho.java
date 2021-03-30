package exe_3;

public class Carrinho {
    private String cliente;
    private String[] carrinho;
    private int dia;
    private int mes;
    private int ano;
    private int contadorItens;
    
    public Carrinho(String cliente, int dia, int mes, int ano){
    this.cliente = cliente;
    this.dia = dia;
    this.mes = mes;
    this.ano = ano;
    carrinho = new String[5];
    contadorItens = 0;
    }
    
    public Carrinho(String cliente, int dia, int mes, int ano, int qde){
    this.cliente = cliente;
    this.dia = dia;
    this.mes = mes;
    this.ano = ano;
    carrinho = new String[qde];
    contadorItens = 0;
    }
    
    public String getCliente(){
        return cliente;
    }
    
    public void inserirItem(String produto){
        carrinho[contadorItens] = produto;
        contadorItens++;
    }
    
    public void exibirCarrinho(){
        System.out.printf("\nCliente: %s\n", cliente);
        System.out.printf("Data da compra: %d/%d/%d\n", dia, mes, ano);
        System.out.print("Itens do carrinho: ");
        for(int i = 0; i < carrinho.length; i++){
            System.out.printf("%s ", carrinho[i]);
        }
    }
}