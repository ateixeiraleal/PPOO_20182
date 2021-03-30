package exe_1;

public class PontoTeste {
    public static void main(String[] args) {
        Ponto umPonto = new Ponto(1.5, 2.5);
        System.out.println("As coordenadas (x,y) são: (" + umPonto.getPonto() + ")");
        System.out.printf("A distância entre os pontos é: %.2f\n\n", umPonto.retornarDistancia(3.5, 5.5));
    }
}
