package pratica1;

import java.util.Scanner;

public class Exe2_CorridaCarros {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int distanciaAirton;
        int distanciaRubens;
        distanciaRubens = 1500 * 10;
        distanciaAirton = distanciaRubens - 120;
        int velocidadeRubens;
        int velocidadeAirton;
        System.out.print("Digite a velocidade do carro de Rubens: ");
        velocidadeRubens = entrada.nextInt();
        System.out.print("\n*O carro de Airton está mais veloz que o de Rubens."
                + "\nDigite a velocidade do carro de Airton: ");
        velocidadeAirton = entrada.nextInt();
        while(distanciaRubens >= distanciaAirton){
            distanciaAirton += velocidadeAirton;
            distanciaRubens += velocidadeRubens;
        }
        System.out.println("\nO carro de Airton ultrapassará o de Robens na " + 
                distanciaAirton / 1500 + "ª volta.");
    }
}
