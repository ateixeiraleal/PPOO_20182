package exe2;

public class ProvaTeste {
    public static void main(String[] args) {
        Prova prova = new Prova();
        for(int i = 0; i < prova.getQdeQuestoes(); i++){
            System.out.println(prova.aplicar(i));
        }
    }
}
