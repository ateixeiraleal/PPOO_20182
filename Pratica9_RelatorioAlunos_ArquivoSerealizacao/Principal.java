import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Principal {
    private static Scanner entrada = new Scanner(System.in);
    private static ArrayList<Aluno> diario = new ArrayList<Aluno>();
    /**
     * Exibe na tela o texto "ENTER para continuar" e aguarda um ENTER
     */
    private static void esperarTecla() {
        try {
            System.out.print("\n\nENTER para continuar...");
            Scanner entrada = new Scanner(System.in);
            entrada.nextLine();
        } catch(Exception e) {}
    }
    
    private static void exibirMenu(){
        System.out.println("=========== MENU =============");
        System.out.println("1 - Inserir Aluno");
        System.out.println("2 - Gerar Relatório na Tela");
        System.out.println("3 - Gravar em Arquivo de Texto");
        System.out.println("4 - Ler do Arquivo de texto");
        System.out.println("5 - Gravar em Arquivo Binario");
        System.out.println("6 - Ler do Arquivo Binario");
        System.out.println("7 - Sair");
        System.out.println("==============================\n");
        System.out.print("Digite sua opcao: ");
    }
    
    private static void cadastrarAluno() {
        System.out.println("==== Cadastando Aluno ====");
        System.out.print("Matrícula: ");
        long matricula = entrada.nextLong();
        System.out.print("Nome: ");
        entrada.nextLine();
        String nome = entrada.nextLine();
        System.out.print("Nota: ");
        double nota = entrada.nextDouble();
        Aluno umAluno = new Aluno(matricula, nome, nota);
        diario.add(umAluno);
        System.out.println("Cadastro efetuado com sucesso!");
    }
    
    private static void gerarRelatorio() {
        if(diario.size() > 0) {
            System.out.println("Matrícula \t\tNome\t\t\tNota\tSituação");
            for (Aluno a: diario) {
                System.out.printf("%d \t%25s \t%.2f \t%s\n", a.getMatricula(), 
                        a.getNome(), a.getNota(), a.getSituacao());
            }
        } else {
            System.out.println("\tERRO!!!\n\tNão existem dados a serem exibidos!");
        }
    }
    
    private static void gravarEmArquivoDeTexto(String nomeArquivo) {
        nomeArquivo = nomeArquivo + ".txt";
        ArrayList<Aluno> copyDiario = (ArrayList<Aluno>)diario.clone();
        //Collections.sort(copyDiario);
        BufferedWriter bw = null;
        try{
            for(Aluno a: copyDiario) {
                bw = new BufferedWriter(new FileWriter(nomeArquivo, true));
                bw.write(a.getMatricula() + "; " + a.getNome() + "; " + a.getNota()
                    + "; " + a.getSituacao());
                bw.newLine();
            }
            System.out.println("\t" + nomeArquivo + " armazenado com sucesso!!!");
        } catch (IOException e) {
            System.out.println("\tERRO!!!\n\tNão conseguimos salvar os dados em diario.txt!");
        } finally {
            if(bw != null) {
                try {
                    bw.close();
                } catch (IOException e){
                    System.out.println("\tERRO!!!\nNão conseguimos fechar diario.txt!");
                }
            }
        }
    }
    
    private static void lerDoArquivoDeTexto(String nomeArquivo) {
        nomeArquivo = nomeArquivo + ".txt";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(nomeArquivo));
            String[] s = new String[4];
            String linha;
            int qde = 0;
            while (br.ready()) {
                linha = br.readLine();
                Scanner scanner = new Scanner(linha).useDelimiter("; ");
                while (scanner.hasNext()) {
                    s[0] = scanner.next();
                    s[1] = scanner.next();
                    s[2] = scanner.next();
                    s[3] = scanner.next();
                }
                Aluno umAluno = new Aluno(Long.parseLong(s[0]), s[1], Double.parseDouble(s[2]));
                diario.add(umAluno);
                qde += 1;
            }
            System.out.println("\tDados de " + qde + " aluno(s) lido(s) com sucesso!!!");
        } catch (IOException e) {
            System.out.println("\tERRO!!!\n\tArquivo \"" + nomeArquivo + "\" não localizado!");
        }finally {
            if(br == null) {
                try {
                    br.close();
                } catch (IOException e){
                    System.out.println("\tERRO!!!\n\t Não conseguimos fechar o diarito.txt!");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        String nomeArquivo;
        int opcao;
        do {
            exibirMenu();
            opcao = entrada.nextInt();
            entrada.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    esperarTecla();
                    break;
                case 2:
                    gerarRelatorio();
                    esperarTecla();
                    break;
                case 3:
                    if(diario.size() > 0) {
                        System.out.print("Digito um nome para o arquivo: ");
                        nomeArquivo = entrada.nextLine();
                        gravarEmArquivoDeTexto(nomeArquivo);
                    }else {
                        System.out.println("\tERRO!!!\n\tNão existem dados a serem armazenados!");
                    }
                    esperarTecla();
                    break;
                case 4:
                    System.out.print("Digite o nome do arquivo: ");
                    nomeArquivo = entrada.nextLine();
                    lerDoArquivoDeTexto(nomeArquivo);
                    esperarTecla();
                    break;
                case 5:
                    ManipuladorArquivoBinario.gravarEmArquivoBinario(diario);
                    esperarTecla();
                    break;
                case 6:
                    diario = ManipuladorArquivoBinario.carregarDadosDoArquivo();
                    esperarTecla();
                    break;
                case 7:
                    System.out.println("Obrigado por utilizar nossa aplicação!\n");
                    break;
                default:
                    throw new AssertionError();
            }
        }while (opcao != 7);
    }
}
