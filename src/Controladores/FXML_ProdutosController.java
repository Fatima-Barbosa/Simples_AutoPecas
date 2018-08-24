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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.BEAN.Produto;
import model.DAO.ProdutoDAO;

/**
 * FXML Controller class
 *
 * @author Fátima
 */
public class FXML_ProdutosController implements Initializable {

    @FXML
    private AnchorPane AnchorPane_Vendas;
    @FXML
    private TableView<Produto> tabelaProdutos;
    @FXML
    private TableColumn<Produto, String> col_Nome;
    @FXML
    private TableColumn<Produto, String> col_Cod;
    @FXML
    private TableColumn<Produto, String> col_qtd;
    @FXML
    private TableColumn<Produto, String> col_Preco;
    @FXML
    private TableColumn<Produto, String> col_ID;
    @FXML
    private TextField txt_busc;
    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_salvar;
    @FXML
    private TextField txt_nome;
    @FXML
    private TextField txt_cod;
    @FXML
    private TextField txt_qtd;
    @FXML
    private TextField txt_preco;
    @FXML
    private TextArea txt_descricao;

    ProdutoDAO dao = new ProdutoDAO();
    @FXML
    private MenuItem cont_Editar;
    @FXML
    private MenuItem cont_Excluir;

    private ObservableList<Produto> Data
            = FXCollections.observableArrayList();

    int op = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        col_Nome.setCellValueFactory(cellData -> cellData.getValue().getNome());
        col_Cod.setCellValueFactory(cellData -> cellData.getValue().getCodBarra());
        col_Preco.setCellValueFactory(cellData -> cellData.getValue().getPreco().asString());
        col_qtd.setCellValueFactory(cellData -> cellData.getValue().getQtd().asString());
        col_ID.setCellValueFactory(cellData -> cellData.getValue().getId().asString());
        Data = dao.gerarLista();
        tabelaProdutos.setItems(Data);
        assert AnchorPane_Vendas != null : "fx:id=\"AnchorPane_Vendas\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert tabelaProdutos != null : "fx:id=\"tabelaProdutos\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert col_Nome != null : "fx:id=\"col_Nome\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert col_Cod != null : "fx:id=\"col_Cod\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert col_qtd != null : "fx:id=\"col_qtd\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert col_Preco != null : "fx:id=\"col_Preco\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert col_ID != null : "fx:id=\"col_ID\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert cont_Editar != null : "fx:id=\"cont_Editar\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert cont_Excluir != null : "fx:id=\"cont_Excluir\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert txt_busc != null : "fx:id=\"txt_busc\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert btn_ok != null : "fx:id=\"btn_ok\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert btn_salvar != null : "fx:id=\"btn_salvar\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert txt_nome != null : "fx:id=\"txt_nome\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert txt_cod != null : "fx:id=\"txt_cod\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert txt_qtd != null : "fx:id=\"txt_qtd\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert txt_preco != null : "fx:id=\"txt_preco\" was not injected: check your FXML file 'FXML_Produto.fxml'.";
        assert txt_descricao != null : "fx:id=\"txt_descricao\" was not injected: check your FXML file 'FXML_Produto.fxml'.";

    }

    @FXML
    private void txt_busc_key(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Data = dao.FiltrarLista(txt_busc.getText());
            tabelaProdutos.setItems(Data);
        }
    }

    @FXML
    private void on_ok(ActionEvent event) {
        Data = dao.FiltrarLista(txt_busc.getText());
        tabelaProdutos.setItems(Data);
    }

    @FXML
    private void on_salvar(ActionEvent event) {
        if (op == 0) {
            Produto p = new Produto(
                    txt_nome.getText(),
                    txt_descricao.getText(),
                    Double.valueOf(txt_preco.getText()),
                    txt_cod.getText(),
                    Integer.valueOf(txt_qtd.getText()));

            dao.Adicionar(p);
            atualizar();
            limparCampos();

        } else {

            Produto c = new Produto(
                    txt_nome.getText(),
                    txt_descricao.getText(),
                    Double.valueOf(txt_preco.getText()),
                    txt_cod.getText(),
                    Integer.valueOf(txt_qtd.getText()),
                    tabelaProdutos.getSelectionModel().getSelectedItem().getId().longValue()
            );
            dao.update(c);
            atualizar();
            op = 0;
            btn_salvar.setText("Salvar");
            limparCampos();
        }
    }

    @FXML
    private void on_Editar(ActionEvent event) {
        try {
            op = 1;
            txt_cod.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getCodBarra().getValue());
            txt_nome.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getNome().getValue());
            txt_preco.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getPreco().getValue().toString());
            txt_qtd.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getQtd().getValue().toString());
            txt_descricao.setText(dao.PegarDescricao(tabelaProdutos.getSelectionModel().getSelectedItem().getId().getValue()));
            btn_salvar.setText("Editar");
        } catch (Exception e) {
            System.out.println("erro: " + e);
        }

    }

    @FXML
    private void on_excluir(ActionEvent event) {
        try {

            boolean r = false;
            try {

                Alert dialogo1 = new Alert(Alert.AlertType.CONFIRMATION);
                //mensagem de confirmaçao para excluir
                dialogo1.setTitle("Atenção");
                dialogo1.setHeaderText("Deseja realmente excluir o item: " + tabelaProdutos.getSelectionModel().getSelectedItem().getNome().getValue() + "?");
                dialogo1.getButtonTypes().add(ButtonType.YES);
                ButtonType btn = new ButtonType("Sim", ButtonBar.ButtonData.YES);
                ButtonType btn1 = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
                dialogo1.getButtonTypes().setAll(btn, btn1);
                r = dialogo1.showAndWait().get().equals(btn);

                if (r) {
                    dao.remover(tabelaProdutos.getSelectionModel().getSelectedItem().getId().longValue());
                    atualizar();
                    Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
                    dialogo.setTitle("Item excluido!");
                    dialogo.setHeaderText("Operaçao bem sucedida!");
                    dialogo.showAndWait();
                }

            } catch (SQLException ex) {
                Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
                dialogo.setTitle("Não foi possivel realizar a operação!");
                dialogo.setHeaderText("erro!");
                dialogo.showAndWait();
                Logger.getLogger(FXML_ProdutosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
        }
    }

    public void atualizar() {
        Data = dao.gerarLista();
        tabelaProdutos.setItems(Data);
    }

    public void limparCampos() {
        txt_descricao.setText("");
        txt_cod.setText("");
        txt_qtd.setText("");
        txt_nome.setText("");
        txt_preco.setText("");
    }

    @FXML
    private void on_nome_enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            txt_cod.requestFocus();
        }
    }

    @FXML
    private void on_cod_enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            txt_descricao.requestFocus();
        }
    }

    @FXML
    private void on_qtd_enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            txt_preco.requestFocus();
        }
    }

    @FXML
    private void on_preco_enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (op == 0) {
                Produto p = new Produto(
                        txt_nome.getText(),
                        txt_descricao.getText(),
                        Double.valueOf(txt_preco.getText()),
                        txt_cod.getText(),
                        Integer.valueOf(txt_qtd.getText()));

                dao.Adicionar(p);
                atualizar();
                limparCampos();

            } else {
                Produto c = new Produto(
                        txt_nome.getText(),
                        txt_descricao.getText(),
                        Double.valueOf(txt_preco.getText()),
                        txt_cod.getText(),
                        Integer.valueOf(txt_qtd.getText()),
                        tabelaProdutos.getSelectionModel().getSelectedItem().getId().longValue()
                );
                dao.update(c);
                atualizar();
                op = 0;
                btn_salvar.setText("Salvar");
                limparCampos();
            }
        }
    }

    @FXML
    private void on_desc_enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            txt_qtd.requestFocus();
        }
    }
}
