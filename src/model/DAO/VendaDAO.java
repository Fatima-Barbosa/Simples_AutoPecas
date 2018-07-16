package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BEAN.vendas;

/**
 *
 * @author Fátima
 */
public class VendaDAO {
    private Connection connection;
    private PreparedStatement stmt;
    private String sql;

    public VendaDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adicionarVenda(vendas v){
        connection = new ConnectionFactory().getConnection();

        sql = "insert into venda(valorTotal) values(?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, v.getValorTotal().doubleValue());
            
            stmt.execute();
            System.out.println("Venda realizada");
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluir(Long id){
        try {
            connection = new ConnectionFactory().getConnection();
            
            sql = "delete from venda where id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();
            System.out.println("Venda excluida");
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir venda: "+ex);
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void atualizarTotal(Double t, Long id){
        connection = new ConnectionFactory().getConnection();
        sql = "UPDATE venda SET valorTotal = ? WHERE id = ?;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, t);
            stmt.setLong(2, id);
            stmt.executeUpdate();
            System.out.println("Atualizada a venda");
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro :"+ex);
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizar(vendas v){
        connection = new ConnectionFactory().getConnection();
        sql = "UPDATE venda SET dataV = ?, valorTotal = ? WHERE id = ?;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, v.getDataV().getValue());
            stmt.setDouble(2, v.getValorTotal().getValue());
            stmt.executeUpdate();
            System.out.println("Atualizada a venda");
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro :"+ex);
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<vendas> gerarLista(){
        connection = new ConnectionFactory().getConnection();
        ObservableList<vendas> Lista
                = FXCollections.observableArrayList();
        
        sql = "select * from venda";
        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
                Lista.add(new vendas(
                        rs.getString("dataV"),
                        rs.getDouble("valorTotal"),
                        rs.getLong("id"))
                );
            }
            stmt.close();
            connection.close(); 
            //rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Lista;
    }
    
    public Long retornarID(){
        Long id = null;
        connection = new ConnectionFactory().getConnection();
        ObservableList<vendas> Lista
                = FXCollections.observableArrayList();
        sql = "select * from venda";
        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
            
                id = rs.getLong("id");
            
            }
            stmt.close();
            connection.close(); 
            //rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public Double verificarTotal(){
        Double n = null;
        connection = new ConnectionFactory().getConnection();
        sql = "select * from venda";
        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {          
            
                n = rs.getDouble("valorTotal");
            
            }
            stmt.close();
            connection.close(); 
            //rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getStmt() {
        return stmt;
    }

    public void setStmt(PreparedStatement stmt) {
        this.stmt = stmt;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
    
    
}
