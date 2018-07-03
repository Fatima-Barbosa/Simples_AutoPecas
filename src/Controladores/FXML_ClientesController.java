package Controladores;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.BEAN.Cliente;
import model.DAO.ClienteDAO;

/**
 * FXML Controller class
 *
 * @author Fátima
 */
public class FXML_ClientesController implements Initializable {

    ClienteDAO dao = new ClienteDAO();
    @FXML
    private TableView<Cliente> tabela_cliente;
    @FXML
    private TableColumn<Cliente, String> colNome;
    @FXML
    private TableColumn<Cliente, String> colCPF;
    @FXML
    private TableColumn<Cliente, String> colTelefone;
    @FXML
    private TableColumn<Cliente, String> colConta;
    @FXML
    private TableColumn<Cliente, String> colID;
    @FXML
    private TextField txtBusc;
    @FXML
    private Button btnOk;
    @FXML
    private TextField txt_nome;
    @FXML
    private TextField txt_cpf;
    @FXML
    private TextField txt_telefone;
    @FXML
    private TextField txt_conta;
    @FXML
    private TextArea txt_endereco;
    @FXML
    private Button btnSalvar;

    private ObservableList<Cliente> Data
            = FXCollections.observableArrayList();
    @FXML
    private MenuItem contExcluir;
    @FXML
    private MenuItem contEditar;

    int op = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        colNome.setCellValueFactory(cellData -> cellData.getValue().getNome());
        colCPF.setCellValueFactory(cellData -> cellData.getValue().getCpf());
        colConta.setCellValueFactory(cellData -> cellData.getValue().getConta().asString());
        colTelefone.setCellValueFactory(cellData -> cellData.getValue().getTelefone());
        colID.setCellValueFactory(cellData -> cellData.getValue().getId().asString());

        Data = dao.gerarLista();
        tabela_cliente.setItems(Data);

        assert tabela_cliente != null : "fx:id=\"tabela_cliente\" was not injected: check your FXML file 'FXML_Clientes.fxml'.";
        assert colNome != null : "fx:id=\"colNome\" was not injected: check your FXML file 'FXML_Clientes.fxml'.";
        assert colCPF != null : "fx:id=\"colCPF\" was not injected: check your FXML file 'FXML_Clientes.fxml'.";
        assert colTelefone != null : "fx:id=\"colTelefone\" was not injected: check your FXML file 'FXML_Clientes.fxml'.";
        assert colConta != null : "fx:id=\"colConta\" was not injected: check your FXML file 'FXML_Clientes.fxml'.";
        assert colID != null : "fx:id=\"colID\" was not injected: check your FXML file 'FXML_Clientes.fxml'.";
        assert txtBusc != null : "fx:id=\"txtBusc\" was not injected: check your FXML file 'FXML_Clientes.fxml'.";
        assert btnOk != null : "fx:id=\"btnOk\" was not injected: check your FXML file 'FXML_Clientes.fxml'.";
        assert txt_nome != null : "fx:id=\"txt_nome\" was not injected: check your FXML file 'FXML_Clientes.fxml'.";
        assert txt_cpf != null : "fx:id=\"txt_cpf\" was not injected: check your FXML file 'FXML_Clientes.fxml'.";
        assert txt_telefone != null : "fx:id=\"txt_telefone\" was not injected: check your FXML file 'FXML_Clientes.fxml'.";
        assert txt_conta != null : "fx:id=\"txt_conta\" was not injected: check your FXML file 'FXML_Clientes.fxml'.";
        assert txt_endereco != null : "fx:id=\"txt_endereco\" was not injected: check your FXML file 'FXML_Clientes.fxml'.";
        assert btnSalvar != null : "fx:id=\"btnSalvar\" was not injected: check your FXML file 'FXML_Clientes.fxml'.";
    }

    @FXML
    private void on_busc(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Data = dao.pesquisar(txtBusc.getText());
            tabela_cliente.setItems(Data);
        }
    }

    @FXML
    private void on_ok(ActionEvent event) {
        Data = dao.pesquisar(txtBusc.getText());
        tabela_cliente.setItems(Data);
    }

    @FXML
    private void on_Salvar(ActionEvent event) {
        if (op == 0) {
            Cliente c = new Cliente(
                    txt_nome.getText(),
                    txt_cpf.getText(),
                    txt_endereco.getText(),
                    txt_telefone.getText(),
                    Double.valueOf(txt_conta.getText())
            );
            dao.adicionar(c);
            limparCampos();
            atualizarTabela();
        } else {
            Cliente c = new Cliente(
                    txt_nome.getText(),
                    txt_cpf.getText(),
                    txt_endereco.getText(),
                    txt_telefone.getText(),
                    Double.valueOf(txt_conta.getText()),
                    tabela_cliente.getSelectionModel().getSelectedItem().getId().longValue()
            );
            dao.update(c);
            limparCampos();
            op = 0;
            btnSalvar.setText("Salvar");
            atualizarTabela();
        }
    }

    public void limparCampos() {
        txt_conta.setText("");
        txt_cpf.setText("");
        txt_endereco.setText("");
        txt_nome.setText("");
        txt_telefone.setText("");
    }

    public void atualizarTabela() {
        Data = dao.gerarLista();
        tabela_cliente.setItems(Data);
    }

    @FXML
    private void on_contExcluir(ActionEvent event) {

        try {
            dao.remover(tabela_cliente.getSelectionModel().getSelectedItem().getId().getValue());
            atualizarTabela();
        } catch (SQLException ex) {
            Logger.getLogger(FXML_ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void on_contEditar(ActionEvent event) {
        op = 1;
        txt_nome.setText(tabela_cliente.getSelectionModel().getSelectedItem().getNome().getValue());
        txt_cpf.setText(tabela_cliente.getSelectionModel().getSelectedItem().getCpf().getValue());
        txt_endereco.setText(tabela_cliente.getSelectionModel().getSelectedItem().getEndereco().getValue());
        txt_telefone.setText(tabela_cliente.getSelectionModel().getSelectedItem().getTelefone().getValue());
        txt_conta.setText(tabela_cliente.getSelectionModel().getSelectedItem().getConta().getValue().toString());
        btnSalvar.setText("Editar");
    }
}
