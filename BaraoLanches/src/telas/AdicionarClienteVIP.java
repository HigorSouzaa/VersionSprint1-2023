package telas;

import classes.ClienteVIP;
import sistemas.ConexaoBancoDados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdicionarClienteVIP extends JFrame {
    private JLabel jlCpf, jlNivelVIP, jlMenssagem;
    private JTextField txtCpf, txtNivelVIP;
    private JButton btAdicionarVip, btVoltar;


    public AdicionarClienteVIP(String title) throws HeadlessException {
        super(title);
        setSize(350, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
        getContentPane().setBackground(Color.decode("#690303"));
        iniciarComponentes();
        criarEventos();
    }

    private void iniciarComponentes() {
        jlMenssagem = new JLabel("Informe o seu Cpf para adicionar vc para vip.");
        jlMenssagem.setBounds(35, 25, 260, 20);
        jlMenssagem.setForeground(Color.white);

        jlNivelVIP = new JLabel("Informe o nivelVIP: ");
        jlNivelVIP.setBounds(20, 90, 150, 25);
        txtNivelVIP = new JTextField();
        txtNivelVIP.setBounds(180, 90, 140, 25);
        jlNivelVIP.setForeground(Color.white);
        txtNivelVIP.setBackground(Color.decode("#91413f"));
        txtNivelVIP.setForeground(Color.white);

        jlCpf = new JLabel("CPF: { 583-452-859.46 }");
        jlCpf.setBounds(20, 60, 150, 25);
        txtCpf = new JTextField();
        txtCpf.setBounds(180, 60, 140, 25);
        jlCpf.setForeground(Color.white);
        txtCpf.setBackground(Color.decode("#91413f"));
        txtCpf.setForeground(Color.white);

        btVoltar = new JButton("Voltar");
        btVoltar.setBounds(75, 170, 200, 25);
        btVoltar.setForeground(Color.white);
        btVoltar.setBackground(Color.decode("#91413f"));

        btAdicionarVip = new JButton("Adicionar VIP");
        btAdicionarVip.setBounds(75, 140, 200, 25);
        btAdicionarVip.setForeground(Color.white);
        btAdicionarVip.setBackground(Color.decode("#91413f"));

        add(jlCpf);
        add(txtCpf);
        add(jlNivelVIP);
        add(txtNivelVIP);
        add(jlMenssagem);
        add(btAdicionarVip);
        add(btVoltar);

    }

    private void criarEventos() {
            btAdicionarVip.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cpf = txtCpf.getText();
                    int nivelVIP = Integer.parseInt(txtNivelVIP.getText());

                    // Realizar a busca no banco de dados pelo CPF
                    Connection conexao = ConexaoBancoDados.conectar();
                    String consultaSql = "SELECT * FROM cliente WHERE cpf = ?";
                    try (PreparedStatement stmt = conexao.prepareStatement(consultaSql)) {
                        stmt.setString(1, cpf);
                        ResultSet resultSet = stmt.executeQuery();

                        if (resultSet.next()) {
                            // Cliente encontrado, criar um objeto ClienteVIP
                            String nome = resultSet.getString("nome");
                            String datNasc = resultSet.getString("dataNasc");
                            String telefone = resultSet.getString("telefone");
                            String endereco = resultSet.getString("endereco");

                            ClienteVIP clienteVIP = new ClienteVIP(nome, datNasc, cpf, telefone, endereco, nivelVIP);
                            double desconto = clienteVIP.calcularDesconto(nivelVIP);

                            // Você pode fazer o que quiser com o objeto ClienteVIP, por exemplo, exibi-lo em uma janela
                            JOptionPane.showMessageDialog(null, "Cliente VIP criado:\n" + clienteVIP.mostrarDados() + "Desconto: " + desconto);
                        } else {
                            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao buscar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        ConexaoBancoDados.fechar(conexao);
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
        AdicionarClienteVIP clienteVIP = new AdicionarClienteVIP("Adicionar VIP");
        clienteVIP.setVisible(true);
    }
}