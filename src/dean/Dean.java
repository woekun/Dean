/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dean;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Tuan Anh
 */
public class Dean extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Giaodien.fxml")));   
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    
    
    
}
