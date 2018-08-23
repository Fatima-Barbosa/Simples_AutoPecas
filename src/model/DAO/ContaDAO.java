package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BEAN.Conta;

/**
 *
 * @author Fátima
 */
public class ContaDAO {

    private Connection connection;
    private PreparedStatement stmt;
    private String sql;
    ClienteDAO cdao = new ClienteDAO();

    public ContaDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adicionar(Conta c) {
        connection = new ConnectionFactory().getConnection();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        sql = "insert into contas(data_conta, cliente, descricao, total) values(?,?,?,?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, dt.format(d).toString());
            stmt.setLong(2, c.getCliente().getValue());
            stmt.setString(3, c.getDescricao().getValue());
            stmt.setDouble(4, c.getTotal().doubleValue());
            stmt.execute();
            cdao.updateSoma(c.getCliente().getValue());
            System.out.println("Conta criada");
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao criar: " + ex);
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double atualizar(long idCliente) {

        double total = 0;
        connection = new ConnectionFactory().getConnection();
        sql = "select * from contas where cliente = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                total += rs.getDouble("total");

            }
            stmt.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public void av(long idcliente, double av) {
        connection = new ConnectionFactory().getConnection();
        
        try {
            stmt = connection.prepareStatement("delete from contas where cliente = ?");
            stmt.setLong(1, idcliente);
            stmt.execute();            
            stmt.close();
            connection.close();
            
        } catch (SQLException ex) {
            System.out.println("erro: " + ex);
        }
        
    }

    public void remover(long id) {
        connection = new ConnectionFactory().getConnection();
        try {
            stmt = connection.prepareStatement("delete from contas where id = ?");
            stmt.setLong(1, id);
            stmt.execute();
            connection.close();
            stmt.close();
            System.out.println("conta removida");
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Conta> gerarLista(long idcliente) {
        connection = new ConnectionFactory().getConnection();
        ObservableList<Conta> Lista
                = FXCollections.observableArrayList();
        try {
            stmt = connection.prepareStatement("select * from contas where cliente = ?;");
            stmt.setLong(1, idcliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lista.add(new Conta(
                        rs.getString("data_conta"),
                        rs.getLong("cliente"),
                        rs.getString("descricao"),
                        rs.getDouble("total"),
                        rs.getLong("id")
                ));
            }
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("erro: " + e);
        }
        return Lista;
    }

    public void update(Conta c, long v) {
        connection = new ConnectionFactory().getConnection();

        try {
            stmt = connection.prepareStatement("UPDATE contas SET descricao = ?,total = ? WHERE id = ?;");
            stmt.setString(1, c.getDescricao().getValue());
            stmt.setDouble(2, c.getTotal().getValue());
            stmt.setLong(3, v);
            stmt.executeUpdate();
            connection.close();
            stmt.close();
            System.out.println("\n Conta Atualizada!\n");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
