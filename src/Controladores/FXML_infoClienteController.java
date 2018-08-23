package Controladores;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.BEAN.Conta;
import model.DAO.ClienteDAO;
import model.DAO.ContaDAO;

/**
 * FXML Controller class
 *
 * @author Fátima
 */
public class FXML_infoClienteController extends FXML_ClientesController implements Initializable {

    FXML_ClientesController c = new FXML_ClientesController();

    ClienteDAO clientedao = new ClienteDAO();
    ContaDAO cdao = new ContaDAO();

    @FXML
    private TableView<Conta> tabela_compras;
    @FXML
    private TableColumn<Conta, String> colData;
    @FXML
    private TableColumn<Conta, String> colValor;
    @FXML
    private TableColumn<Conta, String> colDescricao;
    @FXML
    private TableColumn<Conta, String> colID;
    @FXML
    private Label labelTotal;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelTel;
    @FXML
    private Label labelCPF;
    @FXML
    private Label labelEnder;
    @FXML
    private TextField txtDescricao;
    @FXML
    private TextField txtValor;
    @FXML
    private JFXButton btnAdicinar;
    @FXML
    private Label labelTotal1;
    @FXML
    private Label labelTotal11;

    private ObservableList<Conta> Data
            = FXCollections.observableArrayList();
    @FXML
    private MenuItem contextEditar;
    @FXML
    private MenuItem contextEcluir;
    @FXML
    private MenuItem contextAV;

    boolean bol = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colData.setCellValueFactory(cellData -> cellData.getValue().getData());
        colValor.setCellValueFactory(cellData -> cellData.getValue().getTotal().asString());
        colDescricao.setCellValueFactory(cellData -> cellData.getValue().getDescricao());
        colID.setCellValueFactory(cellData -> cellData.getValue().getId().asString());
        Data = cdao.gerarLista(c.getId());
        tabela_compras.setItems(Data);
//        atualizarTabela();

        labelNome.setText("Nome: " + clientedao.retornarCliente(c.getId()).getNome().getValue());
        labelCPF.setText("CPF:  " + clientedao.retornarCliente(c.getId()).getCpf().getValue());
        labelTel.setText("Telefone:  " + clientedao.retornarCliente(c.getId()).getTelefone().getValue());
        labelEnder.setText("Endereço: " + clientedao.retornarCliente(c.getId()).getEndereco().getValue());
        double valor = 0;
        valor = Data.stream().map((conta) -> conta.getTotal().doubleValue()).reduce(valor, (accumulator, _item) -> accumulator + _item);
        labelTotal.setText("Valor Total:  " + valor);

        assert labelNome != null : "fx:id=\"labelNome\" was not injected: check your FXML file 'FXML_infoCliente.fxml'.";
        assert labelTel != null : "fx:id=\"labelTel\" was not injected: check your FXML file 'FXML_infoCliente.fxml'.";
        assert labelCPF != null : "fx:id=\"labelCPF\" was not injected: check your FXML file 'FXML_infoCliente.fxml'.";
        assert labelEnder != null : "fx:id=\"labelEnder\" was not injected: check your FXML file 'FXML_infoCliente.fxml'.";
        assert tabela_compras != null : "fx:id=\"tabela_compras\" was not injected: check your FXML file 'FXML_infoCliente.fxml'.";
        assert colData != null : "fx:id=\"colData\" was not injected: check your FXML file 'FXML_infoCliente.fxml'.";
        assert colValor != null : "fx:id=\"colValor\" was not injected: check your FXML file 'FXML_infoCliente.fxml'.";
        assert colDescricao != null : "fx:id=\"colDescricao\" was not injected: check your FXML file 'FXML_infoCliente.fxml'.";
        assert colID != null : "fx:id=\"colID\" was not injected: check your FXML file 'FXML_infoCliente.fxml'.";
        assert labelTotal != null : "fx:id=\"labelTotal\" was not injected: check your FXML file 'FXML_infoCliente.fxml'.";
        assert txtDescricao != null : "fx:id=\"txtDescricao\" was not injected: check your FXML file 'FXML_infoCliente.fxml'.";
        assert txtValor != null : "fx:id=\"txtValor\" was not injected: check your FXML file 'FXML_infoCliente.fxml'.";
        assert btnAdicinar != null : "fx:id=\"btnAdicinar\" was not injected: check your FXML file 'FXML_infoCliente.fxml'.";
        assert labelTotal1 != null : "fx:id=\"labelTotal1\" was not injected: check your FXML file 'FXML_infoCliente.fxml'.";
        assert labelTotal11 != null : "fx:id=\"labelTotal11\" was not injected: check your FXML file 'FXML_infoCliente.fxml'.";

    }

    @FXML
    private void on_addConta(ActionEvent event) {
        Conta conta = new Conta(c.getId(), txtDescricao.getText(), Double.valueOf(txtValor.getText()));
        if (bol == false) {
            //Conta conta = new Conta(c.getId(), txtDescricao.getText(), Double.valueOf(txtValor.getText()));
            cdao.adicionar(conta);
            cdao.atualizar(getId());
            atualizarTabelaConta();
            limparCampos();
        } else {
            cdao.update(conta, tabela_compras.getSelectionModel().getSelectedItem().getId().getValue());
            cdao.atualizar(getId());
            atualizarTabelaConta();
            limparCampos();
            btnAdicinar.setText("Adicionar");
        }

    }

    public void atualizarTabelaConta() {
        double valor = 0;
        Data = cdao.gerarLista(c.getId());
        tabela_compras.setItems(Data);
        valor = Data.stream().map((conta) -> conta.getTotal().doubleValue()).reduce(valor, (accumulator, _item) -> accumulator + _item);
        labelTotal.setText("Valor total: " + valor);

    }

    @FXML
    private void checValor(KeyEvent event) {
        if (event.getCode() == event.getCode().MINUS) {
            System.out.println("deu");
            Alert dialogo1 = new Alert(Alert.AlertType.ERROR);
            dialogo1.setTitle("Atenção");
            dialogo1.setHeaderText("Valor negativo");
            txtValor.setText("");
//            ButtonType btn = new ButtonType("Sim", ButtonBar.ButtonData.YES);
//            dialogo1.getButtonTypes().add(ButtonType.OK);
            dialogo1.showAndWait();
        } else if (event.getCode() == KeyCode.ENTER) {
            Conta conta = new Conta(c.getId(), txtDescricao.getText(), Double.valueOf(txtValor.getText()));
            if (bol == false) {
                //Conta conta = new Conta(c.getId(), txtDescricao.getText(), Double.valueOf(txtValor.getText()));
                cdao.adicionar(conta);
                cdao.atualizar(getId());
                atualizarTabelaConta();
                limparCampos();
            } else {
                cdao.update(conta, tabela_compras.getSelectionModel().getSelectedItem().getId().getValue());
                cdao.atualizar(getId());
                atualizarTabelaConta();
                limparCampos();
                btnAdicinar.setText("Adicionar");
            }
        }
    }

    public void limparCampos() {
        txtDescricao.setText("");
        txtValor.setText("");
    }

    @FXML
    private void on_av(ActionEvent event) {
        double v = 0;
        TextInputDialog t = new TextInputDialog();
        Alert dialogo1 = new Alert(Alert.AlertType.INFORMATION);
        t.setTitle("Atenção");
        t.setHeaderText("AV");
        t.setContentText("valor da baixa: ");
        v = Double.parseDouble(t.showAndWait().get());
        System.out.println("v: " + v);
        txtValor.setText("");
        double valor = 0;
        valor = cdao.gerarLista(getId()).stream().map((conta) -> conta.getTotal().getValue()).reduce(valor, (accumulator, _item) -> accumulator + _item);
        Conta c = new Conta(getId(), "conta restante", (valor - v));
        cdao.av(getId(), v);
        cdao.adicionar(c);
        atualizarTabelaConta();
    }

    @FXML
    private void on_excluir(ActionEvent event) {

        cdao.remover(tabela_compras.getSelectionModel().getSelectedItem().getId().get());
        atualizarTabelaConta();
    }

    @FXML
    private void on_editar(ActionEvent event) {
        btnAdicinar.setText("Editar");
        bol = true;
        txtDescricao.setText(tabela_compras.getSelectionModel().getSelectedItem().getDescricao().getValue());
        txtValor.setText(tabela_compras.getSelectionModel().getSelectedItem().getTotal().getValue().toString());
    }

    @FXML
    private void on_desc_enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            txtValor.requestFocus();
        }
    }

}
