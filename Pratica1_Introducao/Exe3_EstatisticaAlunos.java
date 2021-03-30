package pratica1;

import java.util.Scanner;

public class Exe3_EstatisticaAlunos {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int[] vetor = new int[9];
        int count = 1;
        for(int i = 0; i < 9; i++) {
            System.out.print("Digite a nota do " + count + "º aluno: ");
            vetor[i] = entrada.nextInt();
            count++;
        }
        int moda = vetor[0];
        int aux = 1;
        for(int i = 0; i < 9; i++) {
            count = 1;
            for(int j = i+1; j < 9; j++) {
                if(vetor[i] == vetor[j]){
                    count++;
                }
            }
            if(count > aux) {
                aux = count;
                moda = vetor[i];
            }
        }
        System.out.println("\nModa = " + moda);
    }
}