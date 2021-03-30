package exe2;

import java.util.Scanner;

public class Prova {
    private Questao questoes[] = new Questao[5];
    Scanner entrada = new Scanner(System.in);

    public Prova() {
        for(int i = 0; i < questoes.length; i++){
            Questao questao = new Questao();
            questoes[i] = questao;
        }
    }
    
    public int getQdeQuestoes(){
        return questoes.length;
    }
    
    public String aplicar(int i){
        int resposta;
        int x = 1;
        System.out.println("Questão " + questoes[i].consultarId());
        System.out.print(questoes[i].getEnunciado() + "\nResposta: ");
        resposta = entrada.nextInt();
        if(questoes[i].verificarQuestao(resposta)){
            return "\nVocê tentou " + x + " vez(es) e acertou a questão.\n";
        }else {
            System.out.println("\nVocê ganhou mais uma chance!");
            System.out.print("Digite outra resposta para a questão: ");
            resposta = entrada.nextInt();
            x = 2;
            if(questoes[i].verificarQuestao(resposta)){
                return "\nVocê tentou " + x + " vez(es) e acertou a questão.\n";
            }
        }
        return "\nVocê tentou " + x + " vez(es) e errou a questão.\n";
    }
}
