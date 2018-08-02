/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Fátima
 */
public class FXML_infoClienteController implements Initializable {

    @FXML
    private TableView<?> tabela_compras;
    @FXML
    private TableColumn<?, ?> colData;
    @FXML
    private TableColumn<?, ?> colValor;
    @FXML
    private Label labelTotal;
    @FXML
    private JFXDatePicker dataPiker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
   
}
