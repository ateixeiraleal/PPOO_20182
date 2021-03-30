package exe_3;

import java.util.Scanner;

public class CarrinhoTeste {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String cliente;
        int dia, mes, ano;
        char op;
        System.out.print("Digite o nome do cliente: ");
        cliente = entrada.nextLine();
        System.out.print("Digite o dia da compra: ");
        dia = entrada.nextInt();
        System.out.print("Digite o mês da compra: ");
        mes = entrada.nextInt();
        System.out.print("Digite o ano da compra: ");
        ano = entrada.nextInt();
        System.out.println("\nDeseja informar a quantidade de itens a serem incluídos no carrinho?");
        System.out.println(">>> Digite \'s\' - sim ou \'n\' - não <<<");
        System.out.print("Opção: ");
        op = entrada.next().charAt(0);
        Carrinho umCarrinho;
        switch (op) {
            case 's':
                int qde;
                System.out.print("Digite a quantidade de itens: ");
                qde = entrada.nextInt();
                entrada.nextLine();
                umCarrinho = new Carrinho(cliente, dia, mes, ano, qde);
                System.out.println("\n*** Acrescendando itens ao carrinho ***");
                for(int i = 1; i <= qde; i++){
                    System.out.printf("digite o %dº item: ", i);
                    umCarrinho.inserirItem(entrada.nextLine()); 
                }
                break;
            case 'n':
                entrada.nextLine();
                umCarrinho = new Carrinho(cliente, dia, mes, ano);
                System.out.println("\n*** Acrescendando itens ao carrinho ***");
                for(int i = 1; i <= 5; i++){
                    System.out.printf("digite o %dº item: ", i);
                    umCarrinho.inserirItem(entrada.nextLine()); 
                }
                break;
            default:
                throw new AssertionError();
        }
    
        umCarrinho.exibirCarrinho();
        System.out.println("\n");
    }
}
