package exe2;

import java.util.Random;

public class Questao {
    private String enunciado;
    private float gabarito;
    private int idQuestao;
    private static int qdeQuestao = 0;

    public Questao() {
        int num1, num2;
        Random r = new Random();
        num1 = r.nextInt(10);
        num2 = r.nextInt(10);
        this.gabarito = num1 * num2;
        this.enunciado = "Quanto é " + num1 + "*" + num2 + "?";
        qdeQuestao++;
        idQuestao = qdeQuestao;
    }
    
    public String getEnunciado(){
        return enunciado;
    }
    
    public boolean verificarQuestao(int valor){
        if(valor == gabarito){
            return true;
        }
        return false;
    }
    
    public int consultarId(){
        return idQuestao;
    }
    
    
}