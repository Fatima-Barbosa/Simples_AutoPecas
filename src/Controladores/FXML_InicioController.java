package Controladores;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Fátima
 */
public class FXML_InicioController implements Initializable {

    @FXML
    private JFXButton btnVendas;
    @FXML
    private JFXButton bntEstoque;
//    private JFXButton bntGraficos;
//    private JFXButton bntRelatorios;
    @FXML
    public AnchorPane AnchorPaneMae;
    @FXML
    private JFXButton btnClientes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        assert btnVendas != null : "fx:id=\"btnVendas\" was not injected: check your FXML file 'FXML_Inicio.fxml'.";
        assert bntEstoque != null : "fx:id=\"bntEstoque\" was not injected: check your FXML file 'FXML_Inicio.fxml'.";
//        assert bntGraficos != null : "fx:id=\"bntGraficos\" was not injected: check your FXML file 'FXML_Inicio.fxml'.";
//        assert bntRelatorios != null : "fx:id=\"bntRelatorios\" was not injected: check your FXML file 'FXML_Inicio.fxml'.";
        assert btnClientes != null : "fx:id=\"btnClientes\" was not injected: check your FXML file 'FXML_Inicio.fxml'.";
        assert AnchorPaneMae != null : "fx:id=\"AnchorPaneMae\" was not injected: check your FXML file 'FXML_Inicio.fxml'.";

    }

    @FXML
    private void ActionVendas() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/VIEW/FXML_Vendas.fxml"));
        AnchorPaneMae.getChildren().setAll(a);
        
        
    }

    @FXML
    private void ActionEstoque(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/VIEW/FXML_Produto.fxml"));
        AnchorPaneMae.getChildren().setAll(a);
    }


    @FXML
    private void ActionClientes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/VIEW/FXML_Clientes.fxml"));
        AnchorPaneMae.getChildren().setAll(a);
    }

}
