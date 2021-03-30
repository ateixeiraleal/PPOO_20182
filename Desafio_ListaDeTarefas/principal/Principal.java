package principal;

import excecoes.DeadlineInvalidaException;
import excecoes.TarefaInvalidaException;
import excecoes.TarefaRepetidaException;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import tarefa.Tarefa;

public class Principal extends JFrame implements Runnable {

    private GridBagLayout gbl; // Layout da tela. -> Mecanismo que me permite posicionar os elementos na tela.
    private GridBagConstraints gbc;
    /* Mecanismo auxiliar para a GridBagLayout. Restrições adicionados 
        aos componetes antes de serem adicionados ao layout. (posicionamento, largura, alinhamento) */

    private JLabel lbDescricao; // Rótulo (texto que aparece na tela)
    private JLabel lbDeadline; // Rótulo
    private JLabel lbRelogio;

    private JTextField tfTarefa; // Caixa de texto
    private JTextField tfDeadline; // Caixa de texto

    private JButton btSalvar; // Botão
    private JButton btCopiar;
    private JButton btRemover;
    private JButton btLimpar;
    private JButton btExportarEmTexto;
    private JButton btExportarEmHtml;
    private JButton btImportarDeTexto;
    private JPanel painelBotoes; // container para os botões da tela
    private JPanel painelBotoes2;

    private JTable tbTarefas; // Tabela -> Componete visual
    private DefaultTableModel mdTarefas; // Componente que representa os dados da tabela.
    private JScrollPane scTabelaTarefas; // Painel de rolagem. Permite visualizar componentes qdo a tabela vai enchendo.

    private Tarefa tarefa;

    public Principal() {
        super("Lista de Tarefas");// Título da tela.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Modo de como a tela será fechada.
        setResizable(false);// Evita que a tela possa ser redimensionada pelo usuário
        construirTela(); // Chamada do método de inserção dos componentes da tela.
        carregarConfiguracoes(); // Carrega as configurações do sistema.
        carregarTarefasDoArquivo(); // Carega as tarefas do aquivo.
        iniciarRelogio(); // Inicia o relógio da tela
        pack(); // Método da classe JFrame que redmensiona a tela de acordo com os componentes que estão nela inseridos.
    }

    private void iniciarRelogio() {
        new Thread(this).start();
    }

    // Configura o sistemas de acordo com o arquivo que ele irá ler.
    public void carregarConfiguracoes() {
        BufferedReader br = null; // Classe que fará a leitura do arquivo de texto.
        /* A exceção deve ser tratada caso o arquivo não exista. */
        try {
            br = new BufferedReader(new FileReader("config.txt"));
            if (br.ready()) { //Verifica se há algo para ser lido no arquivo. Retorna true caso seja lido e false caso chegue ao fim do aquivo.
                setTitle(br.readLine()); // Seta o título do JFrame de acordo com a linha lida do arquivo.
                if (br.ready()) {
                    String cor = br.readLine(); // A cada chamada será lida a linha seguinte do arquivo.
                    switch (cor) {
                        case "AMARELO":
                            getContentPane().setBackground(Color.yellow);
                            break;
                        case "VERDE":
                            getContentPane().setBackground(Color.green);
                            break;
                        case "BRANCO":
                            getContentPane().setBackground(Color.white);
                            break;
                        case "AZUL":
                            getContentPane().setBackground(Color.blue);
                            break;
                        case "LARANJA":
                            getContentPane().setBackground(Color.orange);
                            break;
                        case "VERMELHO":
                            getContentPane().setBackground(Color.red);
                            break;
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao ler configurações!",
                    "Erro!", JOptionPane.ERROR_MESSAGE);
            // Fecha o Buffer de leitura do arquivo.
        } finally { // Bloco finally sempre será executado, independente se houver exceção ou não.
            if (br != null) {
                try {
                    br.close(); // Fecho o arquivo após terminar de utilizá-lo.
                } catch (IOException ioex) {
                    JOptionPane.showMessageDialog(this, "Erro ao fechar arquivo de configurações!",
                            "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void incluirListaDeTarefas(List<Tarefa> lstTarefas) {
        for (Tarefa t : lstTarefas) {
            String[] tarefa = new String[2]; // O tamenho deve ser a quantidade de colunas da tabela. Cada célula armazenará os dados.
            tarefa[0] = t.getTarefa();
            tarefa[1] = t.getDeadline();
            mdTarefas.addRow(tarefa); // Adiciona o vetor na tabela
        }
    }

    private void carregarTarefasDoArquivo() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("tarefas.bin"));
            /* Carrega toda a lista de tarefas armazenadas no arquivo.           
                Faz ainda um casting explícito, pois foi carregado uma lista no arquivo e o método retorna um object. */
            List<Tarefa> lstTarefas = (ArrayList<Tarefa>) ois.readObject();
            incluirListaDeTarefas(lstTarefas);
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar tarefas do arquivo!",
                    "Erro!", JOptionPane.ERROR_MESSAGE);
            // Fecha o Buffer de leitura do arquivo.
        } finally { // Bloco finally sempre será executado, independente se houver exceção ou não.
            if (ois != null) {
                try {
                    ois.close(); // Fecho o arquivo após terminar de utilizá-lo.
                } catch (IOException ioex) {
                    JOptionPane.showMessageDialog(this, "Erro ao fechar arquivo de tarefas!",
                            "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /* Método que irá, de fato, inserir os componentes na tela. */
    public void construirTela() {
        gbl = new GridBagLayout(); // Objeto da classe que definirá o Layout.
        gbc = new GridBagConstraints();
        setLayout(gbl); // Método da classe JFrame que define qual será o layout da janela. Diz ao java como adicionar o objeto na tela.

        lbDescricao = new JLabel("TAREFA");
        lbDeadline = new JLabel("DEDLINE");
        tfTarefa = new JTextField(34); // Recebe como parâmetro a qde de colunas (tamanho) da caixa de texto.
        tfDeadline = new JTextField(12);

        btSalvar = new JButton("SALVAR", new ImageIcon(getClass().getResource("salvar.png"))); // Instancia o botão com o texto a ser exibido no mesmo.
        /* Classe interna anônima para o evento do botão SALVAR. -> Tratador de eventos de clique*/
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarefa = new Tarefa(tfTarefa.getText(), tfDeadline.getText());
                salvarTarefa(); // Método invocado para salvar uma tarefa.
            }
        });

        btCopiar = new JButton("COPIAR", new ImageIcon(getClass().getResource("copiar.png")));
        btCopiar.setEnabled(false); // Desabilita o botão
        btCopiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copiarTarefa(); // Método invocado para copiar uma tarefa.
            }
        });
        btRemover = new JButton("REMOVER", new ImageIcon(getClass().getResource("remover.png")));
        btRemover.setEnabled(false);
        btRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerTarefa(); // Método invocado para remover uma tarefa.
            }
        });
        btLimpar = new JButton("LIMPAR", new ImageIcon(getClass().getResource("limpar.png")));
        btLimpar.setEnabled(false);
        btLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparLista(); // Método invocado para remover toda a lista.
            }
        });
        btExportarEmTexto = new JButton("Exportar .txt", new ImageIcon(getClass().getResource("exportar_txt.png")));
        btExportarEmTexto.setEnabled(false);
        btExportarEmTexto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarEmDocumentoDeTexto(); // Método invocado para exportar a tabela em formato.txt.
            }
        });
        btExportarEmHtml = new JButton("Exportar .html", new ImageIcon(getClass().getResource("exportar_html.png")));
        btExportarEmHtml.setEnabled(false);
        btExportarEmHtml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarEmDocumentoHtml(); // Método invocado para exportar a tabela em formato.txt.
            }
        });
        btImportarDeTexto = new JButton("Importar .txt", new ImageIcon(getClass().getResource("importar_txt.png")));
        btImportarDeTexto.setEnabled(true);
        btImportarDeTexto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importarDoDocumentoDeTexto(); // Método invocado para importar as tarefas do documento de texto.
            }
        });
        configurarBotoesEstadoInsercao();

        // Instancia o painel (container) de botões e adiciona os botões a ele
        painelBotoes = new JPanel(new GridLayout(1, 4, 5, 5)); //1 linha e 5 colunas; 5px e 5px de espaçamentos - horizontal e vertical
        painelBotoes.add(btSalvar);
        painelBotoes.add(btCopiar);
        painelBotoes.add(btRemover);
        painelBotoes.add(btLimpar);
        painelBotoes2 = new JPanel(new GridLayout(1, 3, 5, 5));
        painelBotoes2.add(btImportarDeTexto);
        painelBotoes2.add(btExportarEmTexto);
        painelBotoes2.add(btExportarEmHtml);

        mdTarefas = new DefaultTableModel(); // Criando os dados -> Informações que serão apresentadas na tabela.
        mdTarefas.addColumn("Tarefa");
        mdTarefas.addColumn("Dedline");
        mdTarefas.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
                salvarTarefasEmArquivo();
                if (mdTarefas.getRowCount() == 0) {
                    btLimpar.setEnabled(false);
                    btExportarEmTexto.setEnabled(false);
                    btExportarEmHtml.setEnabled(false);
                } else {
                    btLimpar.setEnabled(true);
                    btExportarEmTexto.setEnabled(true);
                    btExportarEmHtml.setEnabled(true);
                }
            }
        });

        tbTarefas = new JTable(mdTarefas); // Cria a tabela e adiciona nela o modelo de dados.
        tbTarefas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                configurarBotoesEstadoSelecao();
            }
        });

        // Configura o tamanho das colunas da tabela
        tbTarefas.getColumnModel().getColumn(0).setMaxWidth(400); // Dimensiona a largura da coluna em pixels.
        tbTarefas.getColumnModel().getColumn(1).setMaxWidth(150);

        scTabelaTarefas = new JScrollPane(tbTarefas); // Cria o ScrollPane e adicona nele a tabela.

        // Cria o rótulo que irá exibir a data/hora do sistema
        lbRelogio = new JLabel();

        // Adicionando os componentes recém-criados à tela
        adicionarComponente(lbDescricao, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0, 0, 1, 1);
        adicionarComponente(lbDeadline, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0, 1, 1, 1);
        adicionarComponente(tfTarefa, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1, 0, 1, 1);
        adicionarComponente(tfDeadline, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1, 1, 1, 1);
        adicionarComponente(painelBotoes, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 2, 0, 2, 1);
        adicionarComponente(painelBotoes2, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 3, 0, 2, 1);
        adicionarComponente(scTabelaTarefas, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 4, 0, 2, 1);
        adicionarComponente(lbRelogio, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5, 0, 2, 1);
    }

    /* Método responsável por adicionar os componentes na tela, conforme especifições pré-determinadas. */
    private void adicionarComponente(Component comp, int anchor, int fill, int linha, int coluna, int larg, int alt) {
        gbc.anchor = anchor; // posicionamento do componente na tela (esquerda, direita, centralizado, etc)
        gbc.fill = fill; // define se o tamanho do componente será expandido ou não
        gbc.gridy = linha; // linha do grid onde o componente será inserido
        gbc.gridx = coluna; // coluna do grid onde o componente será inserido
        gbc.gridwidth = larg; // quantidade de colunas do grid que o componente irá ocupar
        gbc.gridheight = alt; // quantidade de linhas do grid que o componente irá ocupar
        gbc.insets = new Insets(3, 3, 3, 3); // espaçamento (em pixels) entre os componentes da tela
        gbl.setConstraints(comp, gbc); // adiciona o componente "comp" ao layout com as restrições previamente especificadas
        add(comp); // efetivamente insere o componente na tela
    }

    /* Método que indica para o compilador que ao ser invocado ele pode lançar exceções de tarefas inválidas.
       Por ser um exceção verificada, quem chamar esse método deverá fazer o seu tramento. */
    private String obterTarefa() throws TarefaInvalidaException {
        if (tarefa.getTarefa().trim().isEmpty()) {
            throw new TarefaInvalidaException("Deadline correspondente: " + tarefa.getDeadline()
                    + "\n\nMotivo: \nA descrição da tarefa está vazia.");
        }
        if (tarefa.getTarefa().length() > 50) {
            throw new TarefaInvalidaException("Seu texto possui " + tfTarefa.getText().length()
                    + " caracteres.\n\n" + ">>> 50 é a quantidade máxima permitida.");
        }
        return tarefa.getTarefa();
    }

    /* Método que indica para o compilador que ao ser invocado ele pode lançar exceções de deadline inválidas.
       Por ser um exceção verificada, quem chamar esse método deverá fazer o seu tramento. */
    private String obterDeadline() throws DeadlineInvalidaException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); // Verificação mais restritiva da data. | Seta falso, pois o padrão já vem verdadeiro.
            Date dataInformada = sdf.parse(tarefa.getDeadline());
            /* Pega uma string que representa uma data e tenta converter em uma data.
                Este método pode lançar uma exceção. */
            Calendar cal = Calendar.getInstance(); // Objeto criado para alterar seus valores
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Date dataAtual = cal.getTime(); // Retorna a data atual do sistema.
            if (dataInformada.before(dataAtual)) {
                /* Retorna true se a data informada é anterior a data atual.
                Caso acontece é um cenário para exceção a ser tratada. */
                throw new DeadlineInvalidaException(tarefa.getDeadline(), "Tarefa relacionada: "
                        + tarefa.getTarefa() + "\n\nMotivo:\nData anterior a data atual.");
            }
            return tarefa.getDeadline();
        } catch (ParseException pex) {
            throw new DeadlineInvalidaException(tarefa.getDeadline(), "Tarefa correspondente: "
                    + tarefa.getTarefa() + "\n\nMotivos possíveis: \n-> Campo vazio \n-> Data inexistente "
                    + "\n-> Formato incompatível \"dd/mm/aaaa\"");
        }
    }

    private void verificarRepeticao() throws TarefaRepetidaException {
        for (int i = 0; i < mdTarefas.getRowCount(); i++) {
            if (tarefa.getTarefa().equalsIgnoreCase((String) mdTarefas.getValueAt(i, 0))) {
                if (tarefa.getDeadline().equalsIgnoreCase((String) mdTarefas.getValueAt(i, 1))) {
                    throw new TarefaRepetidaException("Tarefa: " + tarefa.getTarefa() + "\n"
                            + "Deadline: " + tarefa.getDeadline() + "\n\nVocê já possui uma tarefa idêntica na mesma data.");
                }
            }
        }
    }

    private void salvarTarefa() {
        try { // Método que irá tratar a exceção de tarefa inválida.
            String[] t = new String[2]; // O tamenho deve ser a quantidade de colunas da tabela. Cada célula armazenará os dados.
            t[0] = obterTarefa();
            t[1] = obterDeadline();
            verificarRepeticao();
            mdTarefas.addRow(t); // Adiciona o vetor na tabela
            tfTarefa.setText(""); // Limpa os campos de texto
            tfDeadline.setText(""); // Limpa os campos de texto
            JOptionPane.showMessageDialog(this, "Tarefa adicionada com sucesso!", "Sucesso :-)",
                    JOptionPane.INFORMATION_MESSAGE);
            configurarBotoesEstadoInsercao();

        } catch (TarefaInvalidaException | DeadlineInvalidaException | TarefaRepetidaException ex) { // Será executado somente se for gerada uma exceção.
            // tiex -> atributo message que vem da classe de execeção. -> .getMessage pega a mensagem.
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<Tarefa> obterListaDeTarefas() {
        // Cria uma lista, vazia, de tarefas.
        List<Tarefa> lstTarefas = new ArrayList<Tarefa>();
        // Percorre todas as linhas da tabela de tarefas.
        for (int i = 0; i < mdTarefas.getRowCount(); i++) {
            Tarefa tarefa = new Tarefa(
                    (String) mdTarefas.getValueAt(i, 0), // Casting, pois getValueAt retorna um object. 
                    (String) mdTarefas.getValueAt(i, 1)
            );
            lstTarefas.add(tarefa);
        }
        return lstTarefas;
    }

    private void salvarTarefasEmArquivo() {
        ObjectOutputStream oos = null; //Classe responsável por escrever um arquivo.
        try {
            // Se o arquivo não existir ele o cria, caso contrário o sobrescreve.
            oos = new ObjectOutputStream(new FileOutputStream("tarefas.bin"));
            // Salvar as tarefas no arquivo.
            oos.writeObject(obterListaDeTarefas());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar tarefas em arquivo!",
                    "Erro!", JOptionPane.ERROR_MESSAGE);
        } finally { // Bloco finally sempre será executado, independente se houver exceção ou não.
            if (oos != null) {
                try {
                    oos.close(); // Fecho o arquivo após terminar de utilizá-lo.
                } catch (IOException ioex) {
                    JOptionPane.showMessageDialog(this, "Erro ao fechar arquivo de tarefas!",
                            "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void importarDoDocumentoDeTexto() {
        BufferedReader br = null;
        try {
            // Cria um objeto da classe que irá ler o arquivo, passando para o construtor um objeto do tipo FileRider.
            br = new BufferedReader(new FileReader("tarefas.txt"));
            // Lê o arquivo até o fim (situação na qual o retorno do método ready será false.
            String[] t = new String[2];
            String linha;

            while (br.ready()) {
                linha = br.readLine();
                Scanner scanner = new Scanner(linha).useDelimiter("; ");
                while (scanner.hasNext()) {
                    t[0] = scanner.next();
                    t[1] = scanner.next();
                }
                tarefa = new Tarefa(t[0], t[1]);
                salvarTarefa();
                // O método readLine retorna uma string contendo a próxima linha do arquivo de texto.
            }
            JOptionPane.showMessageDialog(this, "Documento \"tarefas.txt\" importado com sucesso!", "Sucesso :-)",
                    JOptionPane.INFORMATION_MESSAGE);
            configurarBotoesEstadoInsercao();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "O documento \"tarefas.txt\" não existe!",
                    "Erro!", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ioex) {
                    JOptionPane.showMessageDialog(this, "Erro ao fechar o documento \"tarefas.txt\"!",
                            "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void exportarEmDocumentoDeTexto() {
        BufferedWriter bw = null;
        try {
            /* Cria um objeto da classe FileWrite, passando para o construtor o nome do arquivo a ser manipulado.
                Se o arquivo não existir, ele o cria, caso contrário o sobrescreve. */
            bw = new BufferedWriter(new FileWriter("tarefas.txt"));
            for (int i = 0; i < mdTarefas.getRowCount(); i++) {
                bw.write((String) mdTarefas.getValueAt(i, 0) + "; " + (String) mdTarefas.getValueAt(i, 1));
                bw.newLine();
            }
            JOptionPane.showMessageDialog(this, "Documento \"tarefas.txt\" exportado com sucesso!", "Sucesso :-)",
                    JOptionPane.INFORMATION_MESSAGE);
            configurarBotoesEstadoInsercao();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao exportar as tarefas para arquivo.txt!",
                    "Erro!", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (bw != null) {
                try {
                    bw.close(); // Fecho o arquivo após terminar de utilizá-lo.
                } catch (IOException ioex) {
                    JOptionPane.showMessageDialog(this, "Erro ao fechar arquivo de texto!",
                            "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void exportarEmDocumentoHtml() {
        BufferedWriter bw = null;
        try {
            /* Cria um objeto da classe FileWrite, passando para o construtor o nome do arquivo a ser manipulado.
                Se o arquivo não existir, ele o cria, caso contrário o sobrescreve. */
            bw = new BufferedWriter(new FileWriter("tarefas.html"));
            bw.write("<html>");
            bw.newLine();
            bw.write("\t<head>");
            bw.newLine();
            bw.write("\t\t<title>Lista de Tarefas do Anderson</title>");
            bw.newLine();
            bw.write("\t</head>");
            bw.newLine();
            bw.write("\t<body>");
            bw.newLine();
            bw.write("\t\t<table width=\"580\" border=\"1\">");
            bw.newLine();
            bw.write("\t\t\t<tr bgcolor=\"#ADD8E6\" align=\"center\">");
            bw.newLine();
            bw.write("\t\t\t\t<td colspan=\"3\"><strong>Lista de Tarefas</strong></td>");
            bw.newLine();
            bw.write("\t\t\t</tr>");
            bw.newLine();
            bw.write("\t\t\t<tr bgcolor=\"#DDA0DD\" align=\"center\">");
            bw.newLine();
            bw.write("\t\t\t\t<td width=\"30\"><strong>#</strong></td>");
            bw.newLine();
            bw.write("\t\t\t\t<td width=\"80\"><strong>Tarefa</strong></td>");
            bw.newLine();
            bw.write("\t\t\t\t<td width=\"60\"><strong>Deadline</strong></td>");
            bw.newLine();
            bw.write("\t\t\t</tr>");
            bw.newLine();
            for (int i = 0; i < mdTarefas.getRowCount(); i++) {
                bw.write("\t\t\t<tr>");
                bw.newLine();
                bw.write("\t\t\t\t<td align=\"center\">" + (i + 1) + "</td>");
                bw.newLine();
                bw.write("\t\t\t\t<td>" + (String) mdTarefas.getValueAt(i, 0) + "</td>");
                bw.newLine();
                bw.write("\t\t\t\t<td>" + (String) mdTarefas.getValueAt(i, 1) + "</td>");
                bw.newLine();
                bw.write("\t\t\t</tr>");
                bw.newLine();
            }
            bw.write("\t\t</table>");
            bw.newLine();
            bw.write("\t</body>");
            bw.newLine();
            bw.write("</html>");
            JOptionPane.showMessageDialog(this, "Documento \"tarefas.html\" exportado com sucesso!", "Sucesso :-)",
                    JOptionPane.INFORMATION_MESSAGE);
            configurarBotoesEstadoInsercao();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao exportar as tarefas para arquivo.html!",
                    "Erro!", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (bw != null) {
                try {
                    bw.close(); // Fecho o arquivo após terminar de utilizá-lo.
                } catch (IOException ioex) {
                    JOptionPane.showMessageDialog(this, "Erro ao fechar arquivo de texto!",
                            "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void copiarTarefa() {
        int linhaSelecionada = tbTarefas.getSelectedRow();
        tfTarefa.setText((String) mdTarefas.getValueAt(linhaSelecionada, 0));
        tfDeadline.setText((String) mdTarefas.getValueAt(linhaSelecionada, 1));
        tbTarefas.getSelectionModel().clearSelection();
        configurarBotoesEstadoInsercao();
    }

    private void removerTarefa() {
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Confirma a remoção?",
                "Remoção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
            int linhaSelecionada = tbTarefas.getSelectedRow(); // Captura a linha da tabela que foi selecionada pelo usuário
            mdTarefas.removeRow(linhaSelecionada);
            JOptionPane.showMessageDialog(this, "Tarefa removida com sucesso!", "Sucesso :-)", JOptionPane.INFORMATION_MESSAGE);
            if (linhaSelecionada == 0) {
            }
        }
        tbTarefas.getSelectionModel().clearSelection(); // Desmarca a linha selecionada na tabela pelo usuário
        configurarBotoesEstadoInsercao();
    }

    private void limparLista() {
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Confirma a exclusão de todas as tarefas?",
                "Exclusão da Lista", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
            int qde = mdTarefas.getRowCount();
            for (int i = 0; i < qde; i++) {
                mdTarefas.removeRow(0);
            }
            JOptionPane.showMessageDialog(this, "Lista removida com sucesso!", "Sucesso :-)", JOptionPane.INFORMATION_MESSAGE);
        }
        configurarBotoesEstadoInsercao();
    }

    private void configurarBotoesEstadoInsercao() {
        btCopiar.setEnabled(false);
        btRemover.setEnabled(false);
        btSalvar.setEnabled(true);
    }

    private void configurarBotoesEstadoSelecao() {
        btCopiar.setEnabled(true);
        btRemover.setEnabled(true);
        btSalvar.setEnabled(false);
    }

    public static void main(String[] args) {
        new Principal().setVisible(true); // Instancia a tela e a torna visivel.
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            while (true) {
                lbRelogio.setText(sdf.format(Calendar.getInstance().getTime()));
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            System.out.println("Erro ao atualizar relógio da tela!");
        }
    }
}
