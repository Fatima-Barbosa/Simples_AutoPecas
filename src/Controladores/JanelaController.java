/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Fátima
 */
public class JanelaController implements Initializable {

    @FXML
    private AnchorPane PaneJanela;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public AnchorPane getPaneJanela() {
        return PaneJanela;
    }

    public void setPaneJanela(AnchorPane PaneJanela) {
        this.PaneJanela = PaneJanela;
    }
    
    
    
}
