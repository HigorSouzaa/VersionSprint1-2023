package telas;

import classes.Cliente;
import sistemas.Conexao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CadastroClientes extends JFrame {
    private JLabel jlTelefone, jlEndereco, jlNome, jlCpf, jlDatNasc;
    private JTextField txtTelefone, txtEndereco, txtNome, txtCpf, txtDatNasc;
    private JButton btCadastrar, btVoltar, btMostrar;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    private List<Cliente> clientes = new ArrayList<>(); // Lista de clientes

    public CadastroClientes(String title) throws HeadlessException {
        super(title);
        setSize(350, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
        getContentPane().setBackground(Color.decode("#690303"));
        iniciarComponentes();
        criarEventos();
    }

    private void iniciarComponentes() {
        jlNome = new JLabel("Nome:");
        jlNome.setBounds(20, 20, 80, 25);
        txtNome = new JTextField();
        txtNome.setBounds(120, 20, 200, 25);
        jlNome.setForeground(Color.white);
        txtNome.setBackground(Color.decode("#91413f"));
        txtNome.setForeground(Color.white);


        jlCpf = new JLabel("CPF: { 583-452-859.46 }");
        jlCpf.setBounds(20, 60, 150, 25);
        txtCpf = new JTextField();
        txtCpf.setBounds(180, 60, 140, 25);
        jlCpf.setForeground(Color.white);
        txtCpf.setBackground(Color.decode("#91413f"));
        txtCpf.setForeground(Color.white);


        jlDatNasc = new JLabel("Data de Nascimento: { aaaa-mm-dd }");
        jlDatNasc.setBounds(20, 100, 230, 25);
        txtDatNasc = new JTextField();
        txtDatNasc.setBounds(250, 100, 70, 25);
        jlDatNasc.setForeground(Color.white);
        txtDatNasc.setBackground(Color.decode("#91413f"));
        txtDatNasc.setForeground(Color.white);


        jlTelefone = new JLabel("Telefone: { (19) 9997-37199 }");
        jlTelefone.setBounds(20, 140, 180, 25);
        txtTelefone = new JTextField();
        txtTelefone.setBounds(220, 140, 100, 25);
        jlTelefone.setForeground(Color.white);
        txtTelefone.setBackground(Color.decode("#91413f"));
        txtTelefone.setForeground(Color.white);


        jlEndereco = new JLabel("Endereço:");
        jlEndereco.setBounds(20, 180, 80, 25);
        txtEndereco = new JTextField();
        txtEndereco.setBounds(120, 180, 200, 25);
        jlEndereco.setForeground(Color.white);
        txtEndereco.setBackground(Color.decode("#91413f"));
        txtEndereco.setForeground(Color.white);


        btCadastrar = new JButton("Cadastrar");
        btCadastrar.setBounds(75, 250, 200, 25);
        btCadastrar.setForeground(Color.white);
        btCadastrar.setBackground(Color.decode("#91413f"));

        btMostrar = new JButton("Mostrar Clientes");
        btMostrar.setBounds(75, 500, 200, 25);
        btMostrar.setForeground(Color.white);
        btMostrar.setBackground(Color.decode("#91413f"));

        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 340, 300, 150);

        btVoltar = new JButton("Voltar");
        btVoltar.setBounds(75, 295, 200, 25);
        btVoltar.setForeground(Color.white);
        btVoltar.setBackground(Color.decode("#91413f"));

        // Adicionar os componentes ao JFrame
        add(jlTelefone);
        add(txtTelefone);
        add(jlEndereco);
        add(txtEndereco);
        add(jlNome);
        add(txtNome);
        add(jlCpf);
        add(txtCpf);
        add(jlDatNasc);
        add(txtDatNasc);
        add(btCadastrar);
        add(btVoltar);
        add(btMostrar);
        add(scrollPane);
    }

    private void criarEventos() {
        btCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String cpf = txtCpf.getText();
                String datNasc = txtDatNasc.getText();
                String telefone = txtTelefone.getText();
                String endereco = txtEndereco.getText();

                // Verificar campos vazios
                if (nome.isEmpty() || cpf.isEmpty() || datNasc.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Alert!!!", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        // Criar um novo cliente e adicioná-lo à lista
                        Cliente cliente = new Cliente(nome, cpf, datNasc, telefone, endereco);
                        clientes.add(cliente);


                        //Manda o cadastro do cliente pra banco atraves do metodo *inserCliente()
                        Conexao conexao = new Conexao();
                        conexao.insertCliente(cpf, nome, datNasc, telefone, endereco);

                        // Limpar campos após o cadastro
                        txtNome.setText("");
                        txtCpf.setText("");
                        txtDatNasc.setText("");
                        txtTelefone.setText("");
                        txtEndereco.setText("");

                        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Telefone deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }


            }
        });

        btMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("\t Clientes Cadastrados\n");
                if (!clientes.isEmpty()) {
                    for (Cliente cliente:clientes) {
                        textArea.append(cliente.mostrarDados());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado!!", "ALERT!!!!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
//        btMostrar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                textArea.setText("\t Clientes Cadastrados\n");
//                String cpfPesquisa = JOptionPane.showInputDialog("Informe o cpf pra Consulta");
//                if (!clientes.isEmpty()) {
//                    for (Cliente cliente:clientes) {
//                        textArea.append(cliente.mostrarDados());
//                    }
//                }
//            }
//        });
//        { 583-452-859.46 }

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
        CadastroClientes cadastroClientes = new CadastroClientes("Cadastro de Clientes");
        cadastroClientes.setVisible(true);
    }
}
