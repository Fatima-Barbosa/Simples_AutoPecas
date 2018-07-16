package Controladores;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.BEAN.ItemVenda;
import model.BEAN.vendas;
import model.DAO.ItemVendaDAO;
import model.DAO.ProdutoDAO;
import model.DAO.VendaDAO;

/**
 * FXML Controller class
 *
 * @author Fátima
 */
public class FXML_VendasController implements Initializable {

    vendas v = new vendas();
    VendaDAO vdao = new VendaDAO();
    ItemVendaDAO idao = new ItemVendaDAO();
    ProdutoDAO pdao = new ProdutoDAO();
    KeyEvent event;
    @FXML
    private AnchorPane AnchorPane_Vendas;
    @FXML
    private JFXButton btnVender;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtCod;
    @FXML
    private TextField txtPrecoU;
    @FXML
    private TextField txtQTD;
    @FXML
    private TableView<ItemVenda> tabelaItens;
    @FXML
    private TableColumn<ItemVenda, String> colProduto;
    @FXML
    private TableColumn<ItemVenda, String> colQTD;
    @FXML
    private TableColumn<ItemVenda, String> colPrecoU;
    @FXML
    private TableColumn<ItemVenda, String> col_IDvenda;
    @FXML
    private TableColumn<ItemVenda, String> colTotal;
    @FXML
    private TableView<vendas> tabelaVendas;
    @FXML
    private TableColumn<vendas, String> colDataVendas;
    @FXML
    private TableColumn<vendas, String> colID;
    @FXML
    private TableColumn<vendas, String> colTotalvendas;
    @FXML
    private Label labelTotalVendas;
    @FXML
    private MenuItem menuItemNova_venda;
    @FXML
    private MenuItem menuItem_sair;

    private ObservableList<ItemVenda> DataItem
            = FXCollections.observableArrayList();

    private ObservableList<vendas> DataVendas
            = FXCollections.observableArrayList();
    @FXML
    private MenuItem menuItemExcluirVenda;
    @FXML
    private JFXButton btnNovaVenda;

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KeyEvent event = null;
        //A primeira veda é niciada quando inicia a tela, já com total de venda zerada!
        if (vdao.verificarTotal() != 0) {
            vendas v = new vendas(0.0);
            vdao.adicionarVenda(v);
        }
//        if (event.getCode() == KeyCode.F1) {
//             vendas v = new vendas(0.0);
//            vdao.adicionarVenda(v); 
//        }
        //********Tabela Vendas*************//
        colDataVendas.setCellValueFactory(cellData -> cellData.getValue().getDataV());
        colID.setCellValueFactory(cellData -> cellData.getValue().getId().asString());
        colTotalvendas.setCellValueFactory(cellData -> cellData.getValue().getValorTotal().asString());

        DataVendas = vdao.gerarLista();
        tabelaVendas.setItems(DataVendas);

        //*******Tabela Itens************///
        col_IDvenda.setCellValueFactory(cellData -> cellData.getValue().getVenda().asString());
        colPrecoU.setCellValueFactory(cellData -> cellData.getValue().getPreco().asString());
        colProduto.setCellValueFactory(cellData -> cellData.getValue().getProduto().asString());
        colQTD.setCellValueFactory(cellData -> cellData.getValue().getQtd().asString());
        colTotal.setCellValueFactory(cellData -> cellData.getValue().getTotal().asString());

        assert AnchorPane_Vendas != null : "fx:id=\"AnchorPane_Vendas\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert txtCod != null : "fx:id=\"txtCod\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert txtPrecoU != null : "fx:id=\"txtPrecoU\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert txtQTD != null : "fx:id=\"txtQTD\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert btnVender != null : "fx:id=\"btnVender\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert txtTotal != null : "fx:id=\"txtTotal\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert tabelaItens != null : "fx:id=\"tabelaItens\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert colProduto != null : "fx:id=\"colProduto\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert colQTD != null : "fx:id=\"colQTD\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert colPrecoU != null : "fx:id=\"colPrecoU\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert col_IDvenda != null : "fx:id=\"colData\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert colTotal != null : "fx:id=\"colTotal\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert tabelaVendas != null : "fx:id=\"tabelaVendas\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert colDataVendas != null : "fx:id=\"colDataVendas\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert colID != null : "fx:id=\"colQTDitens\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert colTotalvendas != null : "fx:id=\"colTotalvendas\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert labelTotalVendas != null : "fx:id=\"labelTotalVendas\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert menuItemNova_venda != null : "fx:id=\"menuItemNova_venda\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";
        assert menuItem_sair != null : "fx:id=\"menuItem_sair\" was not injected: check your FXML file 'FXML_Vendas.fxml'.";

    }

    @FXML
    private void on_vender(ActionEvent event) {
        VendaDAO v = new VendaDAO();
        System.out.println("" + v.retornarID());
        ItemVenda i = new ItemVenda(v.retornarID(), pdao.pegarID(txtCod.getText()), Integer.parseInt(txtQTD.getText()), Double.parseDouble(txtPrecoU.getText()), Double.parseDouble(txtTotal.getText()));
        idao.adicionarItem(i);
        vdao.atualizarTotal(idao.totalVenda(v.retornarID()), vdao.retornarID());
        atualizarTabelaItens();
        atualizarTabelaVendas();
    }

    @FXML
    private void on_menuItem_novaVenda(ActionEvent event) {
    }

    @FXML
    private void on_menuItem_Sair(ActionEvent event) {
    }

    public String inverteData(Date dataInicio) {
        String data1 = "";
        String dia = dataInicio.toString().substring(8, 10);
        String mes = dataInicio.toString().substring(5, 7);
        String ano = dataInicio.toString().substring(0, 4);
        data1 += dia + "/" + mes + "/" + ano;
        return data1;
    }

    @FXML
    private void onKey_PegarValores(KeyEvent event) {
//      !"".equals(txtCod.getText())
//     Pega o evento dentro da textfield codigo      
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("entrou");
            txtPrecoU.setText(pdao.pegarPreco(txtCod.getText()).toString());
            txtQTD.requestFocus();
        }
    }

    @FXML
    private void onKey_CalcularTotal(KeyEvent event) {
//        !"".equals(txtQTD.getText())
//      Pega o evento dentro da textfield QTD
        VendaDAO v = new VendaDAO();
        if (event.getCode() == KeyCode.ENTER) {
            Double total = Integer.parseInt(txtQTD.getText()) * Double.parseDouble(txtPrecoU.getText());
            txtTotal.setText(total.toString());
            ItemVenda i = new ItemVenda(v.retornarID(), pdao.pegarID(txtCod.getText()), Integer.parseInt(txtQTD.getText()), Double.parseDouble(txtPrecoU.getText()), Double.parseDouble(txtTotal.getText()));
            idao.adicionarItem(i);
            vdao.atualizarTotal(idao.totalVenda(v.retornarID()), vdao.retornarID());
            atualizarTabelaVendas();
            atualizarTabelaItens();
            LimparCampos();
            btnNovaVenda.requestFocus();
        }
    }

    private void atualizarTabelaVendas() {
        DataVendas = vdao.gerarLista();
        tabelaVendas.setItems(DataVendas);
    }

    private void atualizarTabelaItens() {
        DataItem = idao.gerarLista(vdao.retornarID());
        tabelaItens.setItems(DataItem);
    }

    @FXML
    private void on_menuItemExcluirVenda(ActionEvent event) {
        vdao.excluir(tabelaVendas.getSelectionModel().getSelectedItem().getId().longValue());
        atualizarTabelaVendas();
    }

    @FXML
    private void on_NovaVendaF1(KeyEvent event) {
        if (event.getCode() == KeyCode.F1) {
            vendas v = new vendas(0.0);
            vdao.adicionarVenda(v);
            atualizarTabelaVendas();
            LimparCampos();
        }
    }

    private void LimparCampos() {
        txtCod.setText("");
        txtPrecoU.setText("");
        txtQTD.setText("1");
        txtTotal.setText("");
    }

    @FXML
    private void on_NovaVenda(ActionEvent event) {
        vendas v = new vendas(0.0);
        vdao.adicionarVenda(v);
        atualizarTabelaVendas();
        LimparCampos();
    }
}
