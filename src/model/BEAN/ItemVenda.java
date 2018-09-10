package model.BEAN;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

/**
 *
 * @author Fátima
 */
public class ItemVenda {
    private SimpleLongProperty venda;
    private SimpleLongProperty produto;
    private SimpleIntegerProperty qtd;
    private SimpleDoubleProperty preco;
    private SimpleDoubleProperty total;
    private SimpleLongProperty id;

    public ItemVenda() {
    }

    public ItemVenda(Long venda, Long produto, Integer qtd, Double preco, Double total) {
        this.venda = new SimpleLongProperty(venda);
        this.produto = new SimpleLongProperty(produto);
        this.qtd = new SimpleIntegerProperty(qtd);
        this.preco = new SimpleDoubleProperty(preco);
        this.total = new SimpleDoubleProperty(total);
    }

    public ItemVenda(Long venda, Long produto, Integer qtd, Double preco, Double total, Long id) {
        this.venda = new SimpleLongProperty(venda);
        this.produto = new SimpleLongProperty(produto);
        this.qtd = new SimpleIntegerProperty(qtd);
        this.preco = new SimpleDoubleProperty(preco);
        this.total = new SimpleDoubleProperty(total);
        this.id = new SimpleLongProperty(id);
    }

    public SimpleLongProperty getVenda() {
        return venda;
    }

    public void setVenda(SimpleLongProperty venda) {
        this.venda = venda;
    }

    public SimpleLongProperty getProduto() {
        return produto;
    }

    public void setProduto(SimpleLongProperty produto) {
        this.produto = produto;
    }

    public SimpleIntegerProperty getQtd() {
        return qtd;
    }

    public void setQtd(SimpleIntegerProperty qtd) {
        this.qtd = qtd;
    }

    public SimpleDoubleProperty getPreco() {
        return preco;
    }

    public void setPreco(SimpleDoubleProperty preco) {
        this.preco = preco;
    }

    public SimpleDoubleProperty getTotal() {
        return total;
    }

    public void setTotal(SimpleDoubleProperty total) {
        this.total = total;
    }

    public SimpleLongProperty getId() {
        return id;
    }

    public void setId(SimpleLongProperty id) {
        this.id = id;
    }
    
    @Override
    public String toString(){
        return "Item de Venda:\n"+"produto: "+getProduto()+"Venda: "+getVenda();
    }
    
}
