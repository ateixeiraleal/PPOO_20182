
import java.util.Scanner;

public class Principal {
    
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Investimento investimento;
        double valor, total;
        int tempo;
        int opcao;
        String op;
        do{
            System.out.print("Entre com o valor inicial do investimento: ");
            valor = entrada.nextDouble();
            System.out.print("Entre com o tempo (meses) da aplicação: ");
            tempo = entrada.nextInt();
            System.out.println("\n\tEscolha o tipo de investidor: ");
            System.out.println("\t1 - Conservador");
            System.out.println("\t2 - Moderado");
            System.out.println("\t3 - Arrojado");
            System.out.print("\nDigite sua opção: ");
            opcao = entrada.nextInt();
            if(opcao == 1) {
                investimento = new Investimento(new Conservador());
                total = valor + investimento.calcularTotalRendimento(valor, tempo);
                System.out.printf("Valor final esperado: %.2f", total);
            }else if (opcao == 2) {
                investimento = new Investimento(new Moderado());
                total = valor + investimento.calcularTotalRendimento(valor, tempo);
                System.out.printf("Valor final esperado: %.2f", total);
            }else {
                investimento = new Investimento(new Arrojado());
                total = valor + investimento.calcularTotalRendimento(valor, tempo);
                System.out.printf("Valor final esperado: %.2f", total);
            }
            System.out.print("\n\nDeseja realizar nova simulação?\n(sim/nao): ");
            entrada.nextLine();
            op = entrada.nextLine();
            System.out.println("");
        }while (op.equalsIgnoreCase("sim"));
    }
}
