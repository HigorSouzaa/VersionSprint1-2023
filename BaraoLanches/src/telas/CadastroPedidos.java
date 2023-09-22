package telas;

import classes.Cliente;
import classes.Pedido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CadastroPedidos extends JFrame {
    private JLabel jlNumMesa, jlValor, jlQuantidade, jlNumIdentificacaoPed;
    private JTextField txtNumMesa, txtValor, txtQuantidade, txtNumIdentificacaoPed;
    private JButton btCadastrar, btVoltar, btMostrar;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    private List<Pedido> pedidos = new ArrayList<>(); // Lista de pedidos

    public CadastroPedidos(String title) throws HeadlessException {
        super(title);
        setSize(350, 550);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
        getContentPane().setBackground(Color.decode("#690303"));
        iniciarComponentes();
        criarEventos();
    }

    private void iniciarComponentes() {
        jlNumMesa = new JLabel("Numero da Mesa:");
        jlNumMesa.setBounds(20, 20, 130, 25);
        txtNumMesa = new JTextField();
        txtNumMesa.setBounds(160, 20, 160, 25);
        jlNumMesa.setForeground(Color.white);
        txtNumMesa.setBackground(Color.decode("#91413f"));
        txtNumMesa.setForeground(Color.white);

        jlValor = new JLabel("Valor:");
        jlValor.setBounds(20, 60, 80, 25);
        txtValor = new JTextField();
        txtValor.setBounds(160, 60, 160, 25);
        jlValor.setForeground(Color.white);
        txtValor.setBackground(Color.decode("#91413f"));
        txtValor.setForeground(Color.white);

        jlQuantidade = new JLabel("Quantidade:");
        jlQuantidade.setBounds(20, 100, 130, 25);
        txtQuantidade = new JTextField();
        txtQuantidade.setBounds(160, 100, 160, 25);
        jlQuantidade.setForeground(Color.white);
        txtQuantidade.setBackground(Color.decode("#91413f"));
        txtQuantidade.setForeground(Color.white);

        jlNumIdentificacaoPed = new JLabel("Numero do Pedido:");
        jlNumIdentificacaoPed.setBounds(20, 140, 130, 25);
        txtNumIdentificacaoPed = new JTextField();
        txtNumIdentificacaoPed.setBounds(160, 140, 160, 25);
        jlNumIdentificacaoPed.setForeground(Color.white);
        txtNumIdentificacaoPed.setBackground(Color.decode("#91413f"));
        txtNumIdentificacaoPed.setForeground(Color.white);

        btCadastrar = new JButton("Cadastrar");
        btCadastrar.setBounds(75, 210, 200, 25);
        btCadastrar.setForeground(Color.white);
        btCadastrar.setBackground(Color.decode("#91413f"));

        btVoltar = new JButton("Voltar");
        btVoltar.setBounds(75, 245, 200, 25);
        btVoltar.setForeground(Color.white);
        btVoltar.setBackground(Color.decode("#91413f"));

        btMostrar = new JButton("Mostrar Pedidos");
        btMostrar.setBounds(75, 460, 200, 25);
        btMostrar.setForeground(Color.white);
        btMostrar.setBackground(Color.decode("#91413f"));

        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 290, 300, 150);


        // Adicionar os componentes ao JFrame
        add(jlNumMesa);
        add(txtNumMesa);
        add(jlValor);
        add(txtValor);
        add(jlQuantidade);
        add(txtQuantidade);
        add(jlNumIdentificacaoPed);
        add(txtNumIdentificacaoPed);
        add(btCadastrar);
        add(btVoltar);
        add(btMostrar);
        add(scrollPane);
    }

    private void criarEventos() {
        btCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numIdentificacaoPedStr = txtNumIdentificacaoPed.getText();
                String valorStr = txtValor.getText();
                String quantidadeStr = txtQuantidade.getText();
                String numMesaStr = txtNumMesa.getText();

                // Verificar campos vazios
                if (numIdentificacaoPedStr.isEmpty() || valorStr.isEmpty() || quantidadeStr.isEmpty() || numMesaStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "ALERT!!!", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    try {
                        int numIdentificacaoPed = Integer.parseInt(numIdentificacaoPedStr);
                        int valor = Integer.parseInt(valorStr);
                        int quantidade = Integer.parseInt(quantidadeStr);
                        int numMesa = Integer.parseInt(numMesaStr);

                        // Criar um novo pedido e adicioná-lo à lista
                        Pedido pedido = new Pedido(numMesa, valor, quantidade, numIdentificacaoPed);
                        pedidos.add(pedido);

                        // Limpar campos após o cadastro
                        txtNumMesa.setText("");
                        txtValor.setText("");
                        txtQuantidade.setText("");
                        txtNumIdentificacaoPed.setText("");

                        JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Código do Pedido, Valor e Quantidade devem ser números válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }


            }
        });

        btMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("\t Todos os pedidos\n");
                if (!pedidos.isEmpty()) {
                    int mesaPesquisa = Integer.parseInt(JOptionPane.showInputDialog("Qual a mesa vc quer Pesquisar??"));
                    for (Pedido pedido: pedidos) {
                        if (pedido.getNumMesa() == mesaPesquisa) {
                            textArea.append(pedido.mostrarDados());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum pedido cadastrado!!", "ALERT!!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu("Menu Principal");
                menu.setVisible(true);
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        CadastroPedidos cadastroPedidos = new CadastroPedidos("Cadastro de Pedidos");
        cadastroPedidos.setVisible(true);
    }
}
