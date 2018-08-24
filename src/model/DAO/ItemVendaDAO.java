package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BEAN.ItemVenda;
import model.DAO.ProdutoDAO;
/**
 * 
 *
 * @author Fátima
 */
public class ItemVendaDAO {

    private Connection connection;
    private PreparedStatement stmt;
    private String sql;

    ProdutoDAO pdao = new ProdutoDAO();
    VendaDAO vdao = new VendaDAO();
    public ItemVendaDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adicionarItem(ItemVenda i) {
        connection = new ConnectionFactory().getConnection();
        sql = "insert into item_venda(venda, produto, qtd, preco, valorTotal) values(?,?,?,?,?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, i.getVenda().getValue());
            stmt.setLong(2, i.getProduto().getValue());
            stmt.setInt(3, i.getQtd().getValue());
            stmt.setDouble(4, i.getPreco().doubleValue());
            stmt.setDouble(5, i.getTotal().doubleValue());
            stmt.execute();
            System.out.println("item adicionado com sucesso!");
            pdao.baixaQTD(i.getQtd().getValue(), i.getProduto().getValue());
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("erro : " + ex);
            Logger.getLogger(ItemVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<ItemVenda> gerarLista(Long venda) {
        ObservableList<ItemVenda> Lista = FXCollections.observableArrayList();

        connection = new ConnectionFactory().getConnection();
        sql = "SELECT * FROM item_venda WHERE venda = ?;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, venda);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lista.add(new ItemVenda(
                        rs.getLong("venda"),
                        rs.getLong("produto"),
                        rs.getInt("qtd"),
                        rs.getDouble("preco"),
                        rs.getDouble("valorTotal"),
                        rs.getLong("id"))
                );
            }
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro no gerarLista de itemVenda: " + ex);
            Logger.getLogger(ItemVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Lista;
    }

    public void excluir(ItemVenda iv) {
        connection = new ConnectionFactory().getConnection();
        sql = "delete from item_venda where id = ?";
        try {
            pdao.updateQTD(iv.getQtd().getValue(), iv.getProduto().getValue());
            vdao.atualizarTotalRemovido(iv.getTotal().getValue(), iv.getProduto().getValue());
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, iv.getProduto().longValue());
            stmt.execute();
            System.out.println("Excluido item");
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizar(ItemVenda iv) {
        connection = new ConnectionFactory().getConnection();
        sql = "UPDATE item_venda SET venda = ?, produto = ?, qtd = ?, preco = ?, valorTotal = ? WHERE id = ?;";
        try {
            pdao.baixaQTD(iv.getQtd().getValue(), iv.getProduto().getValue());
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, iv.getVenda().getValue());
            stmt.setLong(2, iv.getProduto().getValue());
            stmt.setInt(3, iv.getQtd().getValue());
            stmt.setDouble(4, iv.getPreco().doubleValue());
            stmt.setDouble(5, iv.getTotal().doubleValue());
            stmt.setLong(6, iv.getId().getValue());
            stmt.execute();
            System.out.println("Atualizado");
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("erro: " + ex);
            Logger.getLogger(ItemVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Double totalVenda(Long venda) {
        Double total = 0.0;

        connection = new ConnectionFactory().getConnection();
        sql = "SELECT * FROM item_venda WHERE venda = ?;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, venda);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                total += rs.getDouble("valorTotal");
            }
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Erro no gerarLista de itemVenda: " + ex);
            Logger.getLogger(ItemVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
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
