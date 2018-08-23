package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BEAN.Produto;

/**
 *
 * @author Fátima
 */
public class ProdutoDAO {

    private Connection connection;
    private PreparedStatement stmt;
    private String sql;

    public ProdutoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void Adicionar(Produto p) {
        connection = new ConnectionFactory().getConnection();

        sql = "insert into produtos(nome, descricao, preco, codBarra, qtd) values(?,?,?,?,?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, p.getNome().getValue());
            stmt.setString(2, p.getDescricao().getValue());
            stmt.setDouble(3, p.getPreco().getValue());
            stmt.setString(4, p.getCodBarra().getValue());
            stmt.setInt(5, p.getQtd().getValue());
            stmt.execute();
            System.out.println("Adiconado com sucesso!");
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("erro de add " + e);
        }
    }

    public ObservableList<Produto> gerarLista() {
        connection = new ConnectionFactory().getConnection();
        ObservableList<Produto> Lista
                = FXCollections.observableArrayList();

        try {
            stmt = connection.prepareStatement("select * from produtos;");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lista.add(new Produto(
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getString("codBarra"),
                        rs.getInt("qtd"),
                        rs.getLong("id"))
                );
            }
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Lista;
    }
    
    public int verificarEstoque(int cod){
        int qtd = 0;
        connection = new ConnectionFactory().getConnection();
        sql = "SELECT * FROM produtos where codBarra = ?;";
                try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cod);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
                qtd = rs.getInt("qtd");
            }
        } catch (SQLException ex) {
            System.out.println("Erro: "+ex);
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return qtd;
    }

    public long pegarID(String cod){
        Long id = null;
        connection = new ConnectionFactory().getConnection();
        sql = "SELECT * FROM produtos where codBarra = ?;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cod);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
                id = rs.getLong("id");
            }
        } catch (SQLException ex) {
            System.out.println("Erro: "+ex);
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public Double pegarPreco(String cod){
        Double total = null;
        connection = new ConnectionFactory().getConnection();
        sql = "SELECT * FROM produtos where codBarra = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cod);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
                total = rs.getDouble("preco");
            }
        } catch (SQLException ex) {
            System.out.println("Erro: "+ex);
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    public ObservableList<Produto> FiltrarLista(String n) {
        connection = new ConnectionFactory().getConnection();
        ObservableList<Produto> Lista
                = FXCollections.observableArrayList();

        try {
            stmt = connection.prepareStatement("select * from produtos where nome like ? or codBarra like ?;");
            stmt.setString(1, "%" + n + "%");
            stmt.setString(2, "%" + n + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lista.add(new Produto(
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getString("codBarra"),
                        rs.getInt("qtd"),
                        rs.getLong("id"))
                );
            }
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Lista;
    }

    public void remover(long id) throws SQLException {
        connection = new ConnectionFactory().getConnection();

        sql = "delete from produtos where id = ?";
        stmt = connection.prepareStatement(sql);
        // seta os valores
        stmt.setLong(1, id);
        // executa
        stmt.execute();
        System.out.println("Excluido com sucesso!");
        stmt.close();
    }

    public void update(Produto c) {
        connection = new ConnectionFactory().getConnection();

        try {
            stmt = connection.prepareStatement("UPDATE produtos SET nome = ?, descricao = ?, preco = ?, codBarra = ?, qtd = ? WHERE id = ?;");
            stmt.setString(1, c.getNome().getValue());
            stmt.setString(2, c.getDescricao().getValue());
            stmt.setDouble(3, c.getPreco().getValue());
            stmt.setString(4, c.getCodBarra().getValue());
            stmt.setInt(5, c.getQtd().getValue());
            stmt.setLong(6, c.getId().longValue());
            stmt.executeUpdate();
            connection.close();
            stmt.close();
            System.out.println("\n Produto Atualizado!\n");
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateQTD(int qtd, long id) {
        connection = new ConnectionFactory().getConnection();

        try {
            stmt = connection.prepareStatement("UPDATE produtos SET qtd = qtd + ? WHERE id = ?;");
            stmt.setInt(1, qtd);
            stmt.setLong(2, id);
            stmt.executeUpdate();
            connection.close();
            stmt.close();
            System.out.println("\nQuantidade Atualizada!\n");
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void baixaQTD(int qtd, long id) {
        connection = new ConnectionFactory().getConnection();
        sql = "UPDATE produtos SET qtd = qtd - ? WHERE id = ?;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, qtd);
            stmt.setLong(2, id);
            stmt.execute();
            System.out.println("Atualizou qtd do produto!");
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("erro: " + ex);
            Logger.getLogger(ItemVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String PegarDescricao(long id) {
        connection = new ConnectionFactory().getConnection();
        Produto p = new Produto();
        try {
            stmt = connection.prepareStatement("select * from produtos WHERE id = ?;");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                p = new Produto(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("preco"),
                        rs.getString("codBarra"),
                        rs.getInt("qtd"),
                        rs.getLong("id")
                );

            }
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p.getDescricao().getValue();
    }

}
