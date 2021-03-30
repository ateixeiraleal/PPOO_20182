package exe1;

import java.util.Scanner;

public class TesteDiabetes {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String nome;
        int qdeExames;
        System.out.print("Digete o nome do paciente: ");
        nome = entrada.nextLine();
        System.out.print("Entre com a quantidade de amostras coletadas: ");
        qdeExames = entrada.nextInt();
        Paciente umPaciente = new Paciente(nome, qdeExames);
        
        double exame;
        for(int i = 1; i <= qdeExames; i++){
            System.out.printf("Insira o resultado do teste %d: ", i);
            exame = entrada.nextDouble();
            umPaciente.inserirExame(exame);
        }
        
        System.out.println("\n############### Relatório Final ###############");
        System.out.printf("Resultado médio: %.6f mg/dll\n", umPaciente.getMediaExames());
        System.out.println("Diagnóstico: " + umPaciente.getDiagnostico());
        
        
    }
}