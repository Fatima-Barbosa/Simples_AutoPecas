package model.BEAN;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Fátima
 */
public class Cliente {

    private SimpleStringProperty nome;
    private SimpleStringProperty cpf;
    private SimpleStringProperty endereco;
    private SimpleStringProperty telefone;
    private SimpleDoubleProperty conta;
    private SimpleLongProperty id;
    

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String endereco, String telefone) {
        this.nome = new SimpleStringProperty(nome);
        this.cpf = new SimpleStringProperty(cpf);
        this.endereco = new SimpleStringProperty(endereco);
        this.telefone = new SimpleStringProperty(telefone);
    }

    public Cliente(String nome, String cpf, String endereco, String telefone, double conta, Long id) {
        this.nome = new SimpleStringProperty(nome);
        this.cpf = new SimpleStringProperty(cpf);
        this.endereco = new SimpleStringProperty(endereco);
        this.telefone = new SimpleStringProperty(telefone);
        this.conta = new SimpleDoubleProperty(conta);
        this.id = new SimpleLongProperty(id);
    }

    public Cliente(String nome, String cpf, String endereco, String telefone, Long id) {
        this.nome = new SimpleStringProperty(nome);
        this.cpf = new SimpleStringProperty(cpf);
        this.endereco = new SimpleStringProperty(endereco);
        this.telefone = new SimpleStringProperty(telefone);
        this.id = new SimpleLongProperty(id);
    }
    
    public SimpleStringProperty getNome() {
        return nome;
    }

    public void setNome(SimpleStringProperty nome) {
        this.nome = nome;
    }

    public SimpleStringProperty getCpf() {
        return cpf;
    }

    public void setCpf(SimpleStringProperty cpf) {
        this.cpf = cpf;
    }

    public SimpleStringProperty getEndereco() {
        return endereco;
    }

    public void setEndereco(SimpleStringProperty endereco) {
        this.endereco = endereco;
    }

    public SimpleStringProperty getTelefone() {
        return telefone;
    }

    public void setTelefone(SimpleStringProperty telefone) {
        this.telefone = telefone;
    }

    public SimpleDoubleProperty getConta() {
        return conta;
    }

    public void setConta(SimpleDoubleProperty conta) {
        this.conta = conta;
    }

    public SimpleLongProperty getId() {
        return id;
    }

    public void setId(SimpleLongProperty id) {
        this.id = id;
    }





    
}
