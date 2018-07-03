package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BEAN.Cliente;

/**
 *
 * @author Fátima
 */
public class ClienteDAO {

    private Connection connection;
    private PreparedStatement stmt;
    private String sql;

    public ClienteDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adicionar(Cliente c) {
        connection = new ConnectionFactory().getConnection();

        sql = "insert into clientes(nome, cpf, endereco, telefone, conta) values(?,?,?,?,?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, c.getNome().getValue());
            stmt.setString(2, c.getCpf().getValue());
            stmt.setString(3, c.getEndereco().getValue());
            stmt.setString(4, c.getTelefone().getValue());
            stmt.setDouble(5, c.getConta().getValue());
            stmt.execute();
            System.out.println("Adicionado com sucesso!");
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<Cliente> gerarLista() {
        connection = new ConnectionFactory().getConnection();
        ObservableList<Cliente> Lista
                = FXCollections.observableArrayList();

        try {
            stmt = connection.prepareStatement("select * from clientes;");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lista.add(new Cliente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getDouble("conta"),
                        rs.getLong("id"))
                );
            }
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Lista;
    }

    public void remover(long id) throws SQLException {
        connection = new ConnectionFactory().getConnection();

        sql = "delete from clientes where id = ?";
        stmt = connection.prepareStatement(sql);
        // seta os valores
        stmt.setLong(1, id);
        // executa
        stmt.execute();
        System.out.println("Excluido com sucesso!");
        stmt.close();

    }

    public void update(Cliente c) {
        connection = new ConnectionFactory().getConnection();

        try {
            stmt = connection.prepareStatement("UPDATE clientes SET nome = ?, cpf = ?, endereco = ?, telefone = ?, conta = ? WHERE id = ?;");
            stmt.setString(1, c.getNome().getValue());
            stmt.setString(2, c.getCpf().getValue());
            stmt.setString(3, c.getEndereco().getValue());
            stmt.setString(4, c.getTelefone().getValue());
            stmt.setDouble(5, c.getConta().getValue());
            stmt.setLong(6, c.getId().longValue());
            stmt.executeUpdate();
            connection.close();
            stmt.close();
            System.out.println("\n Clinte Atualizado!\n");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Cliente> pesquisar(String n) {
        connection = new ConnectionFactory().getConnection();
        ObservableList<Cliente> Lista
                = FXCollections.observableArrayList();

        try {
            stmt = connection.prepareStatement("select * from clientes where cpf like ? or nome like ?;");
            stmt.setString(1, "%" + n + "%");
            stmt.setString(2, "%" + n + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lista.add(new Cliente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getDouble("conta"),
                        rs.getLong("id"))
                );
            }
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Lista;
    }

}
