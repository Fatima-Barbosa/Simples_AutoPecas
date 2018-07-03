package model.BEAN;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Fátima
 */
public class Produto {
    private SimpleStringProperty nome;
    private SimpleStringProperty descricao;
    private SimpleDoubleProperty preco;
    private SimpleStringProperty codBarra;
    private SimpleIntegerProperty qtd;
    private SimpleLongProperty id;

    public Produto() {
    }

    public Produto(String nome, String descricao, Double preco, String codBarra, int qtd) {
        this.nome = new SimpleStringProperty(nome);
        this.descricao = new SimpleStringProperty(descricao);
        this.preco = new SimpleDoubleProperty(preco);
        this.codBarra = new SimpleStringProperty(codBarra);
        this.qtd = new SimpleIntegerProperty(qtd);
    }

    public Produto(String nome, String descricao, Double preco, String codBarra, int qtd, Long id) {
        this.nome = new SimpleStringProperty(nome);
        this.descricao = new SimpleStringProperty(descricao);
        this.preco = new SimpleDoubleProperty(preco);
        this.codBarra = new SimpleStringProperty(codBarra);
        this.qtd = new SimpleIntegerProperty(qtd);
        this.id = new SimpleLongProperty(id);
    }

    
    
    public SimpleStringProperty getNome() {
        return nome;
    }

    public void setNome(SimpleStringProperty nome) {
        this.nome = nome;
    }

    public SimpleStringProperty getDescricao() {
        return descricao;
    }

    public void setDescricao(SimpleStringProperty descricao) {
        this.descricao = descricao;
    }

    public SimpleDoubleProperty getPreco() {
        return preco;
    }

    public void setPreco(SimpleDoubleProperty preco) {
        this.preco = preco;
    }

    public SimpleStringProperty getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(SimpleStringProperty codBarra) {
        this.codBarra = codBarra;
    }

    public SimpleIntegerProperty getQtd() {
        return qtd;
    }

    public void setQtd(SimpleIntegerProperty qtd) {
        this.qtd = qtd;
    }

    public SimpleLongProperty getId() {
        return id;
    }

    public void setId(SimpleLongProperty id) {
        this.id = id;
    }
    
    
}
