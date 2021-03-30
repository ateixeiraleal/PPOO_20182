package exe2;

import java.util.Scanner;

public class Prova {
    private Questao questoes[] = new Questao[5];
    private Correcao correcoes[] = new Correcao[questoes.length];
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
            Correcao umaCorrecao = new Correcao(questoes[i].consultarId(), "acertou", x);
            correcoes[i] = umaCorrecao;
            return "\nVocê tentou " + x + " vez(es) e acertou a questão.\n";
        }else {
            System.out.println("\nVocê ganhou mais uma chance!");
            System.out.print("Digite outra resposta para a questão: ");
            resposta = entrada.nextInt();
            x = 2;
            if(questoes[i].verificarQuestao(resposta)){
                Correcao umaCorrecao = new Correcao(questoes[i].consultarId(), "acertou", x);
                correcoes[i] = umaCorrecao;
                return "\nVocê tentou " + x + " vez(es) e acertou a questão.\n";
            }
        }
        Correcao umaCorrecao = new Correcao(questoes[i].consultarId(), "errou", x);
        correcoes[i] = umaCorrecao;
        return "\nVocê tentou " + x + " vez(es) e errou a questão.\n";
        
    }
    
    public void gerarRelaltorio(){
        System.out.println("############## RELATÓRIO ##############");
        System.out.println("Questão  Situação  Número de tentativas");
        for(int i = 0; i < questoes.length; i++){
            System.out.printf("   %d     %s         %d\n", correcoes[i].getQuestao(), 
                    correcoes[i].getSituacao(), correcoes[i].getTentativas());
        }
    }
    
}