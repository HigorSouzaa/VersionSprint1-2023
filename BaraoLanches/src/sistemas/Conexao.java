package sistemas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {
    private String url = "jdbc:mysql://localhost:3306/baraolanches";
    private String user = "root";
    private String senha = "12345678";

    public void insertCliente(String getCPF, String getNome, String getDataNasc, String getTelefone, String getEndereco) {
        try {
            // Conectar ao banco de dados
            Connection conexao = DriverManager.getConnection(url, user, senha);

            // Query SQL para inserir dados (exemplo)
            String sql = "INSERT INTO cliente (cpf, nome, dataNasc, telefone, endereco) VALUES (?, ?, ?, ?, ?)";

            // Preparar a declaração SQL
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Definir os valores dos parâmetros
            preparedStatement.setString(1, getCPF);
            preparedStatement.setString(2, getNome);
            preparedStatement.setString(3, getDataNasc);
            preparedStatement.setString(4, getTelefone);
            preparedStatement.setString(5, getEndereco);


            // Executar a inserção
            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Dados inseridos com sucesso!");
            } else {
                System.out.println("Falha ao inserir dados.");
            }

            // Fechar a conexão
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}