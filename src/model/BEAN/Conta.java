package model.BEAN;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Fátima
 */
public class Conta {

    private SimpleStringProperty data;
    private SimpleLongProperty cliente;
    private SimpleStringProperty descricao;
    private SimpleDoubleProperty total;
    private SimpleLongProperty id;

    public Conta(String data, long cliente, String descricao, Double total, long id) {
        this.data = new SimpleStringProperty(data);
        this.cliente = new SimpleLongProperty(cliente);
        this.descricao = new SimpleStringProperty(descricao);
        this.total = new SimpleDoubleProperty(total);
        this.id = new SimpleLongProperty(id);
    }

    public Conta(String data, long cliente, String descricao, Double total) {
        this.data = new SimpleStringProperty(data);
        this.cliente = new SimpleLongProperty(cliente);
        this.descricao = new SimpleStringProperty(descricao);
        this.total = new SimpleDoubleProperty(total);
    }

    public Conta(long cliente, String descricao, Double total) {
        this.cliente = new SimpleLongProperty(cliente);
        this.descricao = new SimpleStringProperty(descricao);
        this.total = new SimpleDoubleProperty(total);
    }

    public Conta() {

    }

    public SimpleStringProperty getData() {
        return data;
    }

    public void setData(SimpleStringProperty data) {
        this.data = data;
    }

    public SimpleLongProperty getCliente() {
        return cliente;
    }

    public void setCliente(SimpleLongProperty cliente) {
        this.cliente = cliente;
    }

    public SimpleStringProperty getDescricao() {
        return descricao;
    }

    public void setDescricao(SimpleStringProperty descricao) {
        this.descricao = descricao;
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

}
