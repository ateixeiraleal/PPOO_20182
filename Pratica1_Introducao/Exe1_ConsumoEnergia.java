//package pratica1;

import java.util.Scanner;

public class Exe1_ConsumoEnergia {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int consumo;
        double valor = 5.00;
        System.out.print("Digite a quantidade de energia consumida: ");
        consumo = entrada.nextInt();
        if(consumo > 10 && consumo <= 100) {
            valor = consumo * 0.6 + 2;
        }else {
            if(consumo > 150){
                valor = 3;
                valor += (100 * 0.6) + (50 * 0.85);
                consumo -= 150;
                valor += consumo * 1.2;
            }
        }
        System.out.println("Valor da fatura: R$ " + valor);
    }
}
