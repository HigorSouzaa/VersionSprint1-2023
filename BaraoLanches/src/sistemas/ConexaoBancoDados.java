package sistemas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDados {
    private static final String URL = "jdbc:mysql://localhost:3306/baraolanches";
    private static final String USUARIO = "root";
    private static final String SENHA = "12345678";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }

    public static void fechar(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                // Tratar erro ao fechar a conexão, se necessário
            }
        }
    }
}
