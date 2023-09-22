package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    JButton btnCadastroClientes, btnCadastroPedidos, btnAdicionarClienteVip;

    public Menu(String title) {
        super(title);
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#690303"));
        iniciarComponentes();
        criarEventos();
    }

    private void iniciarComponentes() {
        btnCadastroClientes = new JButton("Cadastro de Clientes");
        btnCadastroClientes.setBounds(50, 30, 200, 30);
        btnCadastroClientes.setForeground(Color.white);
        btnCadastroClientes.setBackground(Color.decode("#91413f"));


        btnCadastroPedidos = new JButton("Cadastro de Pedidos");
        btnCadastroPedidos.setBounds(50, 80, 200, 30);
        btnCadastroPedidos.setForeground(Color.white);
        btnCadastroPedidos.setBackground(Color.decode("#91413f"));

        btnAdicionarClienteVip = new JButton("Adicionar Cliente Vip");
        btnAdicionarClienteVip.setBounds(50, 130, 200, 30);
        btnAdicionarClienteVip.setForeground(Color.white);
        btnAdicionarClienteVip.setBackground(Color.decode("#91413f"));


        add(btnCadastroClientes);
        add(btnCadastroPedidos);
        add(btnAdicionarClienteVip);
    }

    private void criarEventos() {
        btnCadastroClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastroClientes cadastroClientes = new CadastroClientes("Cadastro de Clientes");
                cadastroClientes.setVisible(true);
            }
        });

        btnCadastroPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastroPedidos cadastroPedidos = new CadastroPedidos("Cadastro de Pedidos");
                cadastroPedidos.setVisible(true);
            }
        });

        btnAdicionarClienteVip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            AdicionarClienteVIP adicionarClienteVIP = new AdicionarClienteVIP("Adicionar VIP");
            adicionarClienteVIP.setVisible(true);
            setVisible(false);
            }
        });

    }

    public static void main(String[] args) {
        Menu menu = new Menu("Menu Principal");
        menu.setVisible(true);
    }
}
