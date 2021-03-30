
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author israel
 */
public class Principal extends JFrame{
    private GridBagLayout gbl; // Layout da tela. -> Mecanismo que me permite posicionar os elementos na tela.
    private GridBagConstraints gbc;
    /* Mecanismo auxiliar para a GridBagLayout. Restrições adicionados 
        aos componetes antes de serem adicionados ao layout. (posicionamento, largura, alinhamento) */

    // Rotulo
    private JLabel lbNumero1;
    private JLabel lbNumero2;
    // Caixa de texto
    private JTextField tfNumero1;
    private JTextField tfNumero2;
    
    // Botão
    private JButton btSoma;
    private JButton btSubtrai;
    private JButton btDivide;
    private JButton btMultiplica;
    
       
    // container para os botões da tela
    private JPanel painelBotoes;
    
    public Principal() {
        super("Calculadora");// Título da tela.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Modo de como a tela será fechada.
        setResizable(false);//setResizable(false);// Evita que a tela possa ser redimensionada pelo usuário
        construirTela(); // Chamada do método de inserção dos componentes da tela.
        pack(); // Método da classe JFrame que redmensiona a tela de acordo com os componentes que estão nela inseridos.
    }
    
    /* Método que irá, de fato, inserir os componentes na tela. */
    public void construirTela() {
        gbl = new GridBagLayout(); // Objeto da classe que definirá o Layout.
        gbc = new GridBagConstraints();
        setLayout(gbl); // Método da classe JFrame que define qual será o layout da janela. Diz ao java como adicionar o objeto na tela.
        lbNumero1 = new JLabel("Número 1:");
        lbNumero2 = new JLabel("Número 2:");
        tfNumero1 = new JTextField(5);
        tfNumero2 = new JTextField(5); // Recebe como parâmetro a qde de colunas (tamanho) da caixa de texto.
        
        btSoma = new JButton("+");
        btSoma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float resultado = Float.parseFloat(tfNumero1.getText()) + Float.parseFloat(tfNumero2.getText());
                JOptionPane.showMessageDialog(rootPane, "O resultado da soma é " + resultado);
            }
        });
        
        btSubtrai = new JButton("-");
        btSubtrai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float resultado = Float.parseFloat(tfNumero1.getText()) - Float.parseFloat(tfNumero2.getText());
                JOptionPane.showMessageDialog(rootPane, "O resultado da subtração é " + resultado);
            }
        });
        
        btMultiplica = new JButton("*");
        btMultiplica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float resultado = Float.parseFloat(tfNumero1.getText()) * Float.parseFloat(tfNumero2.getText());
                JOptionPane.showMessageDialog(rootPane, "O resultado da multiplicação é " + resultado);
            }
        });
        
        btDivide = new JButton("÷");
        btDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float resultado = Float.parseFloat(tfNumero1.getText()) / Float.parseFloat(tfNumero2.getText());
                JOptionPane.showMessageDialog(rootPane, "O resultado da divisão é " + resultado);
            }
        });
        
        // Instancia o painel (container) de botões e adiciona os botões a ele
        painelBotoes = new JPanel(new GridLayout(2, 4)); //2 linhas e 4 colunas
        painelBotoes.add(btSoma);
        painelBotoes.add(btSubtrai);
        painelBotoes.add(btDivide);
        painelBotoes.add(btMultiplica);
        
        // Adicionando os componentes recém-criados à tela
        adicionarComponente(lbNumero1, GridBagConstraints.WEST, GridBagConstraints.NONE, 0, 0, 1, 1);
        adicionarComponente(tfNumero1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0, 1, 1, 1);
        adicionarComponente(lbNumero2, GridBagConstraints.WEST, GridBagConstraints.NONE, 0, 2, 1, 1);
        adicionarComponente(tfNumero2, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0, 3, 1, 1);
        adicionarComponente(btSoma, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 1, 0, 1, 1);
        adicionarComponente(btSubtrai, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 1, 1, 1, 1);
        adicionarComponente(btMultiplica, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 1, 2, 1, 1);
        adicionarComponente(btDivide, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 1, 3, 1, 1);
       
              
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
    public static void main(String[] args) {
        new Principal().setVisible(true); // Instancia a tela e a torna visivel.
    }
}