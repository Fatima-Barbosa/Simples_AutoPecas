package Controladores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Fátima
 */
public class SistemaAutoPecas extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/VIEW/FXML_Inicio.fxml"));
        
        Scene scene = new Scene(root);
        stage.setResizable(false);
        //stage.setMaximized(true);
//        if(stage.isMaximized()){
//            System.out.println("ok");
//        }
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
