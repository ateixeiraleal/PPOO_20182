package pratica5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class TesteAgenda {
    static Scanner entrada = new Scanner(System.in);
    
    public static int exibirMenu() {
        System.out.println("\n*********** MENU ***********");
        System.out.println("*  1 - Adicionar Contato   *");
        System.out.println("*  2 - Remover Contato     *");
        System.out.println("*  3 - Exbir Agenda        *");
        System.out.println("*  4 - Sair                *");
        System.out.println("****************************\n");
        System.out.print("Entre com a opção desejada: ");
        int opcao = entrada.nextInt();        
        return opcao;
    }
    
    private static boolean adicionarContato(ArrayList<Contato> agenda) {
        int inscricao;
        char s;
        String nome, endereco, email, numeroCadastro, estadoCivil, cnpj;
        entrada.nextLine();
        System.out.print("Digite o nome: ");
        nome = entrada.nextLine();
        System.out.print("Digite o endereço: ");
        endereco = entrada.nextLine();
        System.out.print("Digite o e-mail: ");
        email = entrada.nextLine();
               
        do{
            System.out.print("Digite o cpf ou o cnpj: ");
            numeroCadastro = entrada.nextLine();
            if(numeroCadastro.length() == 11) {
                System.out.print("Digite o estado civil: ");
                estadoCivil = entrada.nextLine();
                Contato contato = new PessoaFisica(numeroCadastro, estadoCivil, nome, endereco, email);
                agenda.add(contato);
                salvarEmDocumentoDeTexto(contato);
                return true;
            }else if(numeroCadastro.length() == 14) {
                System.out.print("Digite a inscrição estadual: ");
                inscricao = entrada.nextInt();
                Contato contato = new PessoaJuridica(numeroCadastro, inscricao, nome, endereco, email);
                agenda.add(contato);
                salvarEmDocumentoDeTexto(contato);
                return true;
            }
            System.out.println("CPF ou CNPJ inválido!!!");
        }while(numeroCadastro.length() != 11 && numeroCadastro.length() != 14);
        
        return false;
    }
    
    /**
     * Busca pelo contato na agenda e caso ele exista retorna a sua posicao.
     * @param agenda A lista de contatos.
     * @return Retorna um inteiro contendo a posicao do contato na agenda.
    */    
    private static int buscarContato(ArrayList<Contato> agenda) {
        entrada.nextLine();
        System.out.print("Digite o CPF ou o CNPJ do contato a ser removido: ");
        String numero = entrada.nextLine();
        for(Contato contato: agenda) {
            if(contato instanceof PessoaFisica) {
                if(((PessoaFisica) contato).getCpf().equals(numero)) {
                    return agenda.indexOf(contato);
                }
            }else {
                if(((PessoaJuridica) contato).getCnpj().equals(numero)){
                    return agenda.indexOf(contato);
                }
            }
        }
        return -1;
    }
    
    /**
     * Remove o contato da lista.
     * @param 
     */
    private static boolean removerContato(ArrayList<Contato> agenda) {
        int posicao = buscarContato(agenda);
        if(posicao != -1){
            agenda.remove(posicao);
            return true;
        }
        return false;
    }
    
    /**
     * Armazena dos dados do contato em documento de texto
     * @param obj Recebe um objeto do tipo contado que poderá ser uma Pessoa
     * Física ou uma Pessoa jurídica
     */
    private static void salvarEmDocumentoDeTexto(Contato obj) {
        BufferedWriter bw = null;
        try {
            /* Cria um objeto da classe FileWrite, passando para o construtor o nome do arquivo a ser manipulado.
                Se o arquivo não existir, ele o cria, caso contrário o sobrescreve. */
            bw = new BufferedWriter(new FileWriter("agenda.txt", true));
            /* Verifica se o objeto passado é do tipo Pessoa Física */
            if(obj instanceof PessoaFisica) {
                PessoaFisica contato = (PessoaFisica)obj;
                bw.write(contato.getNome() + "; " + contato.getEndereco()+ "; " +
                        contato.getEmail()+ "; " + contato.getCpf()+ "; " + contato.getEstadoCivil());
            }else {
                PessoaJuridica contato = (PessoaJuridica)obj;
                bw.write(contato.getNome() + "; " + contato.getEndereco()+ "; " +
                        contato.getEmail()+ "; " + contato.getCnpj()+ "; " + contato.getInscricaoEstatual());
            }
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o contato para arquivo.txt!");
        } finally {
            if (bw != null) {
                try {
                    bw.close(); // Fecho o arquivo após terminar de utilizá-lo.
                } catch (IOException ioex) {
                    System.out.println("Erro ao fechar arquivo de texto!");
                }
            }
        }
    }
    
    /**
     * Importa os dados dos contatos por meio de um documento de texto.
     * @param agenda Recebe um objeto do tipo Lista de Contados que poderá ser uma Pessoa
     * Física ou uma Pessoa jurídica.
     */
    private static void importarDoDocumentoDeTexto(ArrayList<Contato> agenda) {
        BufferedReader br = null;
        try {
            // Cria um objeto da classe que irá ler o arquivo, passando para o construtor um objeto do tipo FileRider.
            br = new BufferedReader(new FileReader("agenda.txt"));
            // Lê o arquivo até o fim (situação na qual o retorno do método ready será false.
            String[] t = new String[5];
            String linha;

            while (br.ready()) {
                linha = br.readLine(); // O método readLine retorna uma string contendo a próxima linha do arquivo de texto.
                Scanner scanner = new Scanner(linha).useDelimiter("; ");
                while (scanner.hasNext()) {
                    t[0] = scanner.next();
                    t[1] = scanner.next();
                    t[2] = scanner.next();
                    t[3] = scanner.next();
                    t[4] = scanner.next();
                }
                if(t[3].length() == 11) {
                    Contato contato = new PessoaFisica(t[3], t[4], t[0], t[1], t[2]);
                    agenda.add(contato);
                }else {
                    Contato contato = new PessoaJuridica(t[3], Integer.parseInt(t[4]), t[0], t[1], t[2]);
                    agenda.add(contato);
                }
            }
            System.out.println("Sua agenda já contém contatos, os quais foram importados de \"agenda.txt\".");
        } catch (IOException e) {
            System.out.println("Sua agenda está incialmente vazia, pois o documento \"agenda.txt\" não existe.");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ioex) {
                    System.out.println("Erro ao fechar o documento \"tarefas.txt\"!");
                }
            }
        }
    }
    
    public static void exibirAgenda(ArrayList<Contato> agenda) {
        int count = 1;
        if(agenda == null){
            System.out.println("Agenda vazia!!!");
        }
        for(Contato contato : agenda) {
            System.out.printf("*** Contato %s ***\n", count);
            System.out.println(contato.toString());
            count ++;
        }
    }
    
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
    
    public static void main(String[] args) {
        ArrayList<Contato> agenda = new ArrayList<>();
        int opcao;
        importarDoDocumentoDeTexto(agenda);
        opcao = exibirMenu();
        while(opcao != 4) {
            do {
            if (opcao < 1 || opcao > 4) {
                System.out.println("Opção inválida!!!");
                System.out.print("\nEntre com a opção desejada: ");
                opcao = entrada.nextInt();
            }
            }while(opcao < 1 || opcao > 4);

            switch (opcao) {
                //Adiciona contato
                case 1:
                    if(adicionarContato(agenda)) {
                        System.out.println("\nContato adicionado com sucesso!");
                    }else {
                        System.out.println("\nContato não adicionado!");
                    }
                    break;
                //Remove contato
                case 2:
                    if(removerContato(agenda)) {
                        System.out.println("\nContato removido com sucesso!");
                    }else {
                        System.out.println("\nContato não localizado na agenda!");
                    }
                    break;
                //Exibe a agenda
                case 3:
                    exibirAgenda(agenda);
                    break;
            }
            esperarTecla();
            opcao = exibirMenu();
        }
        System.out.println("Obrigado por utilizar esta aplicação!");
    }
}
