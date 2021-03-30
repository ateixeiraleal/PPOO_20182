import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public abstract class ManipuladorArquivoBinario {
    
    public static void gravarEmArquivoBinario(ArrayList<Aluno> diario) {
        if(diario.size() > 0) {
            ObjectOutputStream oos = null; //Classe responsável por escrever um arquivo.
            try {
                // Se o arquivo não existir ele o cria, caso contrário o sobrescreve.
                oos = new ObjectOutputStream(new FileOutputStream("diario.bin"));
                // Salvar as tarefas no arquivo.
                oos.writeObject(diario);
                System.out.println("\tDados armazenados com sucesso em \"diario.bin\"!!!");
            } catch (IOException e) {
                System.out.println("\tErro!!!\n\tNão conseguimos salvar em \"diario.bin\"!");
            } finally { // Bloco finally sempre será executado, independente se houver exceção ou não.
                if (oos != null) {
                    try {
                        oos.close(); // Fecha o arquivo após terminar de utilizá-lo.
                    } catch (IOException ioex) {
                        System.out.println("\tErro!!!\n\tNao conseguimos fechar o arquivo \"diario.bin\"!");
                    }
                }
            }
        } else {
            System.out.println("\tERRO!!!\n\tNão existem dados a serem armazenados!");
        }
    }
    
    public static ArrayList<Aluno> carregarDadosDoArquivo() {
        ObjectInputStream ois = null;
        ArrayList<Aluno> diario = new ArrayList<Aluno>();
        try {
            ois = new ObjectInputStream(new FileInputStream("diario.bin"));
            System.out.println("\tDados lidos com sucesso!!!");
            /* Carrega os dados dos alunos armazenados no arquivo.           
                Faz ainda um casting explícito, pois foi carregado uma lista no arquivo e o método retorna um object. */
            diario = (ArrayList<Aluno>)ois.readObject();
            return diario;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("\tERRO!!!\n\tNão localizamos o arquivo \"diario.bin\"!");
        } finally { // Bloco finally sempre será executado, independente se houver exceção ou não.
            if (ois != null) {
                try {
                    ois.close(); // Fecha o arquivo após terminar de utilizá-lo.
                } catch (IOException ioex) {
                    System.out.println("\tErro!!!\n\t ao fechar o arquivo \"diario.bin\"!");
                }
            }
        }
        return diario;
    }
}
