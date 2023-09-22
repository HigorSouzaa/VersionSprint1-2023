package telas;

import classes.SuperUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JLabel jlUser, jlPass, jlMenssagem;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btLogin;
    private SuperUser Adm = new SuperUser();

    public Login(String title) throws HeadlessException {
        super(title);
        setSize(280, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#690303"));
        setLocationRelativeTo(this);
        iniciarComponente();
        criarEventos();
    }

    private void iniciarComponente() {
        jlMenssagem = new JLabel("Entre com usuario e senha de adm.");
        jlUser = new JLabel("Usuario:");
        jlPass = new JLabel("Senha:");
        txtUser = new JTextField();
        txtPass = new JPasswordField();
        btLogin = new JButton("Logar");

        add(jlPass);
        add(jlMenssagem);
        add(jlUser);
        add(txtPass);
        add(txtUser);
        add(btLogin);

        jlMenssagem.setBounds(35, 25, 200, 20);
        jlMenssagem.setForeground(Color.white);

        jlUser.setBounds(65, 55, 50, 20);
        jlUser.setForeground(Color.white);

        jlPass.setBounds(65, 95, 80, 20);
        jlPass.setForeground(Color.white);

        txtUser.setBounds(115, 55, 100, 20);
        txtUser.setForeground(Color.white);
        txtUser.setBackground(Color.decode("#91413f"));

        txtPass.setBounds(115, 95, 100, 20);
        txtPass.setForeground(Color.white);
        txtPass.setBackground(Color.decode("#91413f"));

        btLogin.setBounds(90, 140, 100, 20);
        btLogin.setForeground(Color.white);
        btLogin.setBackground(Color.decode("#91413f"));

    }

    private void criarEventos() {
        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtUser.getText().isEmpty() && !txtPass.getText().isEmpty()) {
                    if (txtUser.getText().equals(Adm.getUser()) && Adm.getPassword().equals(String.valueOf(txtPass.getText()))){
                        Menu menu = new Menu("Menu Principal");
                        menu.setVisible(true);
                        setVisible(false);
                    } else {
                        txtPass.setText("");
                        txtUser.setText("");

                        JOptionPane.showMessageDialog(null, "Cadastro invalido!!!", "Barao Lanches",JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos!!!", "Barao Lanches",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        Login teste = new Login("Barao Lanches");
        teste.setVisible(true);
    }
}
