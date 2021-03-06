package Controladores;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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
public class FXML_VendasController extends JanelaController implements Initializable{

    vendas v = new vendas(0.0);
    VendaDAO vdao = new VendaDAO();
    ItemVendaDAO idao = new ItemVendaDAO();
    ProdutoDAO pdao = new ProdutoDAO();
    
    long aux = 0, aux2 = 0;
    
    KeyEvent event;
    int op = 0;
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
    private TableColumn<ItemVenda, String> idItemVenda;
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
    @FXML
    private MenuItem cont_RemoverItemVenda;
    @FXML
    private MenuItem cont_AlterarQTDItemVenda;
    @FXML
    private Label labelTotalCompra;
    @FXML
    private MenuItem MenuItemProduto;
    @FXML
    private StackPane pane;


    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * KeyEvent event = null; A primeira veda é niciada quando inicia a
         * tela, já com total de venda zerada! *
         */
        vendas vi = new vendas(0.0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        txtCod.requestFocus();
        txtQTD.setText("1");
        //********Tabela Vendas*************//
        colDataVendas.setCellValueFactory(cellData -> cellData.getValue().getDataV());
        colID.setCellValueFactory(cellData -> cellData.getValue().getId().asString());
        colTotalvendas.setCellValueFactory(cellData -> cellData.getValue().getValorTotal().asString());

        DataVendas = vdao.gerarLista(dateFormat.format(date));
        tabelaVendas.setItems(DataVendas);

        //*******Tabela Itens************///
        col_IDvenda.setCellValueFactory(cellData -> cellData.getValue().getVenda().asString());
        colPrecoU.setCellValueFactory(cellData -> cellData.getValue().getPreco().asString());
        colProduto.setCellValueFactory(cellData -> cellData.getValue().getProduto().asString());
        colQTD.setCellValueFactory(cellData -> cellData.getValue().getQtd().asString());
        colTotal.setCellValueFactory(cellData -> cellData.getValue().getTotal().asString());
        idItemVenda.setCellValueFactory(cellData -> cellData.getValue().getId().asString());

        DataItem = idao.gerarLista(vdao.retornarID());
        tabelaItens.setItems(DataItem);
        labelTotalVendas();
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
//        try {
        ItemVenda iv = new ItemVenda(vdao.retornarID(), pdao.pegarID(txtCod.getText()), Integer.parseInt(txtQTD.getText()), Double.parseDouble(txtPrecoU.getText()), (Integer.parseInt(txtQTD.getText()) * Double.parseDouble(txtPrecoU.getText())));
//        } catch (NumberFormatException e) {
//            System.out.println("Nao há uma venda criada"+e);
//        }

        if (vdao.verificarTotal() != 0) {
            if (pdao.verificarEstoque(Integer.parseInt(txtCod.getText())) >= Integer.parseInt(txtQTD.getText())) {
                vdao.atualizarTotal(Double.NaN, Long.MIN_VALUE);
                idao.adicionarItem(iv);
                vdao.atualizarTotal(idao.totalVenda(vdao.retornarID()), vdao.retornarID());
                atualizarTabelaItens();
                atualizarTabelaVendas();
                labelTotalCompra();
            } else {
                Alert dialogo1 = new Alert(Alert.AlertType.INFORMATION);
                dialogo1.setTitle("Atenção");
                dialogo1.setHeaderText("Não há itens suficientes em estoque!"
                        + "Tente uma quantidade menor!");
                dialogo1.getButtonTypes().add(ButtonType.OK);

            }
        }
        //idao.adicionarItem(i);
        // vdao.atualizarTotal(idao.totalVenda(v.retornarID()), vdao.retornarID());
        atualizarTabelaItens();
        atualizarTabelaVendas();
    }

    @FXML
    private void on_menuItem_novaVenda(ActionEvent event) {
        atualizarTabelaItens();
        atualizarTabelaVendas();
        if (vdao.verificarTotal() != 0) {
            vdao.adicionarVenda(v);
        }
    }

    @FXML
    private void on_menuItem_Sair(ActionEvent event) {
//        Stage stage = (Stage)event.clone();
//        getScene().getWindow();
//        stage.close();
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
        if (event.getCode() == KeyCode.ENTER) {
            if (pdao.verificarEstoque(Integer.parseInt(txtCod.getText())) >= Integer.parseInt(txtQTD.getText())) {
                if (op == 1) {
                    ItemVenda i = new ItemVenda(
                            vdao.retornarID(), 
                            pdao.pegarID(txtCod.getText()), 
                            Integer.parseInt(txtQTD.getText()), 
                            Double.parseDouble(txtPrecoU.getText()), 
                            Integer.parseInt(txtQTD.getText()) * Double.parseDouble(txtPrecoU.getText()), 
                            aux);
                    //pdao.baixaQTD(Integer.parseInt(txtQTD.getText()), pdao.pegarID(txtCod.getText()));
            //vdao.atualizarTotalRemovido(tabelaItens.getSelectionModel().getSelectedItem().getTotal().getValue(), tabelaItens.getSelectionModel().getSelectedItem().getVenda().longValue());
            
                    idao.atualizar(i);
                    vdao.atualizarTotal(idao.totalVenda(vdao.retornarID()), aux2);
                    atualizarTabelaItens();
                    atualizarTabelaVendas();
                    op = 0;
                } else {

                    VendaDAO v = new VendaDAO();

                    Double total = Integer.parseInt(txtQTD.getText()) * Double.parseDouble(txtPrecoU.getText());
                    txtTotal.setText(total.toString());
                    // fazer um try
                    ItemVenda i = new ItemVenda(v.retornarID(), pdao.pegarID(txtCod.getText()), Integer.parseInt(txtQTD.getText()), Double.parseDouble(txtPrecoU.getText()), Double.parseDouble(txtTotal.getText()));
                    idao.adicionarItem(i);
                    vdao.atualizarTotal(idao.totalVenda(v.retornarID()), vdao.retornarID());
                    atualizarTabelaVendas();
                    atualizarTabelaItens();
                    LimparCampos();
                }
//                txtTotal.requestFocus();
//                btnNovaVenda.requestFocus();
            } else {
                Alert dialogo1 = new Alert(Alert.AlertType.INFORMATION);
                dialogo1.setTitle("Atenção");
                dialogo1.setContentText("Não há itens suficientes em estoque!\n"
                        + "Tente uma quantidade menor!");
                dialogo1.showAndWait();
            }
        }

    }

    private void atualizarTabelaVendas() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        Date hora = Calendar.getInstance().getTime();
//        String horaformatada = sdf.format(hora);
        DataVendas = vdao.gerarLista(dateFormat.format(date));
        tabelaVendas.setItems(DataVendas);
        labelTotalVendas();
        labelTotalCompra();
    }

    private void atualizarTabelaItens() {
        DataItem = idao.gerarLista(vdao.retornarID());
        tabelaItens.setItems(DataItem);
        labelTotalCompra();
        labelTotalVendas();
    }

    @FXML
    private void on_menuItemExcluirVenda(ActionEvent event) {

        //ObservableList<vendas> Lista1 = FXCollections.observableArrayList();
        ObservableList<ItemVenda> Lista = FXCollections.observableArrayList();
        try {
            vdao.excluir(tabelaVendas.getSelectionModel().getSelectedItem().getId().longValue());
            tabelaVendas.setItems(DataVendas);

            tabelaItens.setItems(Lista);
            atualizarTabelaVendas();
            atualizarTabelaItens();
            labelTotalVendas();
        } catch (Exception e) {
            System.out.println("erro menuitemEcluirVenda: " + e);
        }
    }

    @FXML
    private void on_NovaVendaF1(KeyEvent event) {
        if (event.getCode() == KeyCode.F1) {
            vendas v = new vendas(0.0);
            vdao.adicionarVenda(v);
            atualizarTabelaVendas();
            atualizarTabelaItens();
            labelTotalVendas();
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
        labelTotalVendas();
        atualizarTabelaVendas();
        atualizarTabelaItens();
        LimparCampos();
    }

    @FXML
    private void on_cont_RemoverItemVenda(ActionEvent event) {
        try {
            ItemVenda iv = new ItemVenda();
            iv.setProduto(tabelaItens.getSelectionModel().getSelectedItem().getProduto());
            iv.setQtd(tabelaItens.getSelectionModel().getSelectedItem().getQtd());
            iv.setVenda(tabelaItens.getSelectionModel().getSelectedItem().getVenda());
            iv.setTotal(tabelaItens.getSelectionModel().getSelectedItem().getTotal());
            iv.setPreco(tabelaItens.getSelectionModel().getSelectedItem().getPreco());
            iv.setId(tabelaItens.getSelectionModel().getSelectedItem().getId());
            idao.excluir(iv);
            System.out.println("" + tabelaItens.getSelectionModel().getSelectedItem().getProduto().getValue());
            System.out.println(""+iv.toString());
            atualizarTabelaItens();
            atualizarTabelaVendas();
        } catch (Exception e) {
            System.out.println("erro:" + e);
        }

    }

    @FXML
    private void on_cont_AlterarQTDItemVenda(ActionEvent event) {
        aux = tabelaItens.getSelectionModel().getSelectedItem().getId().getValue();
        aux2 = tabelaItens.getSelectionModel().getSelectedItem().getVenda().getValue();
        try {
            op = 1;
            txtCod.setText(pdao.pegarCodigo(tabelaItens.getSelectionModel().getSelectedItem().getProduto().getValue()));
            txtPrecoU.setText(tabelaItens.getSelectionModel().getSelectedItem().getPreco().getValue().toString());
            pdao.updateQTD(tabelaItens.getSelectionModel().getSelectedItem().getQtd().getValue(), tabelaItens.getSelectionModel().getSelectedItem().getProduto().getValue());
            vdao.atualizarTotalRemovido(tabelaItens.getSelectionModel().getSelectedItem().getTotal().getValue(), tabelaItens.getSelectionModel().getSelectedItem().getVenda().longValue());
                            
            atualizarTabelaItens();
            atualizarTabelaVendas();
        } catch (Exception e) {
            System.out.println("erro: " + e);
        }
    }

    @FXML
    private void on_ver_itens(MouseEvent event) {
//        String id = tabelaVendas.getSelectionModel().getSelectedItem().getId().toString();
//        System.out.println(id);
        System.out.println("evento: " + event.getClickCount());
        try {
            DataItem = idao.gerarLista(tabelaVendas.getSelectionModel().getSelectedItem().getId().longValue());
            tabelaItens.setItems(DataItem);

            double total = 0;
//            for (int i = 0; i < tabelaItens.getSelectionModel().getSelectedItems().size(); i++) {
//                total += tabelaItens.getSelectionModel().getSelectedItems().get(i).getTotal().getValue();
//            }
            labelTotalCompra.setText("Total da compra: " + (total + idao.CalcularTotal(tabelaVendas.getSelectionModel().getSelectedItem().getId().getValue())));
        } catch (Exception e) {
            System.out.println("não há nada  " + e);
        }
    }

    private void on_keyTotal_addItem(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
//            VendaDAO v = new VendaDAO();
//            ItemVenda i = new ItemVenda(v.retornarID(), pdao.pegarID(txtCod.getText()), Integer.parseInt(txtQTD.getText()), Double.parseDouble(txtPrecoU.getText()), Double.parseDouble(txtTotal.getText()));
//            idao.adicionarItem(i);
//            vdao.atualizarTotal(idao.totalVenda(v.retornarID()), vdao.retornarID());
//            atualizarTabelaVendas();
//            atualizarTabelaItens();
//            LimparCampos();
//            txtTotal.requestFocus();
//            btnNovaVenda.requestFocus();
        }
    }

    public void labelTotalCompra() {
//        System.out.println("tamanho " + tabelaItens.getSelectionModel().getSelectedItems().size());
        double total = 0;
//        for (int i = 0; i < tabelaItens.getSelectionModel().getSelectedItems().size(); i++) {
//            total += tabelaItens.getSelectionModel().getSelectedItems().get(i).getTotal().getValue();
//        }
//        labelTotalCompra.setText("Total da compra: " + total);
        labelTotalCompra.setText("Total da compra: " + (total + idao.CalcularTotal(vdao.retornarID())));

    }

    public void labelTotalVendas() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date d = new Date();

        labelTotalVendas.setText("Total das vendas: " + vdao.cacularTotalVendas(sdf.format(d)));
    }

    @FXML
    private void On_menuItemProduto(ActionEvent event) {
        JanelaController j = new JanelaController();
//        Alert a = new Alert(Alert.AlertType.INFORMATION);
//        a.setTitle("hello");
//        a.
//        a.show();
    }

}
