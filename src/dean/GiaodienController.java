/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dean;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Tuan Anh
 */
public class GiaodienController implements Initializable {

    @FXML
    TextField thu, phat;
    @FXML
    Button okbutton;
    @FXML
    Pane P1, P2;
    @FXML
    Button Gobutton;
    @FXML
    Label min;

    Ochiphi chiphi[][];
    Othuphat Thu[];
    Othuphat Phat[];
    float MTT[];
    float MTP[];
    float MTC[][];
    int socotthu, socotphat;
    float KQ[][];

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void exitclick() {
        System.exit(0);
    }

    public void okclick() {

        try {
            socotthu = Integer.parseInt(thu.getText());
            socotphat = Integer.parseInt(phat.getText());
        } catch (Exception e) {

        }

        P1.setVisible(false);
        P2.setVisible(true);
        Gobutton.setVisible(true);

        GridPane g = new GridPane();
        g.setVgap(socotthu + 1);
        g.setHgap(socotphat + 1);
        
        double prefV = 600 / (socotthu + 1) - socotthu * 2.5;
        double prefH = 320 / (socotphat + 1) - socotphat * 2.5;

        chiphi = new Ochiphi[socotphat][socotthu];
        Thu = new Othuphat[socotthu];
        Phat = new Othuphat[socotphat];

        BorderPane bbb = new BorderPane();
        bbb.setRight(new Label("Thu"));
        bbb.setBottom(new Label("Phat"));

        for (int i = 0; i < socotthu + 1; i++) {
            for (int j = 0; j < socotphat + 1; j++) {
                if (i != 0 && j != 0) {
                    chiphi[j - 1][i - 1] = new Ochiphi(prefV, prefH);
                    g.add(chiphi[j - 1][i - 1], i, j);
                } else if (i == 0 && j == 0) {
                    g.add(bbb, i, j);
                } else if (i == 0) {
                    Phat[j - 1] = new Othuphat(prefV, prefH);
                    g.add(Phat[j - 1], i, j);
                } else {
                    Thu[i - 1] = new Othuphat(prefV, prefH);
                    g.add(Thu[i - 1], i, j);
                }
            }
        }

        P2.getChildren().add(g);

    }

    public void Tinhtoan() {

        MTT = new float[socotthu];
        MTP = new float[socotphat];
        MTC = new float[socotphat][socotthu];

        for (int i = 0; i < socotphat; i++) {
            MTP[i] = Float.parseFloat(Phat[i].cost.getText());
        }

        for (int i = 0; i < socotthu; i++) {
            MTT[i] = Float.parseFloat(Thu[i].cost.getText());
        }

        for (int i = 0; i < socotphat; i++) {
            for (int j = 0; j < socotthu; j++) {
                MTC[i][j] = Float.parseFloat(chiphi[i][j].cost.getText());
            }
        }

        KQ = new float[socotphat][socotthu];

        Tinhtoan tt = new Tinhtoan();
        KQ = tt.setAll(MTT, MTP, MTC, socotphat, socotthu);

        for (int i = 0; i < socotphat; i++) {
            for (int j = 0; j < socotthu; j++) {
                chiphi[i][j].transport.setText(KQ[i][j] + "");
                if (KQ[i][j] != 0) {
                    chiphi[i][j].setStyle("-fx-background-color: pink;");
                }
            }
        }
        min.setText("Cước phí: " + tt.sum1);

    }

    class Ochiphi extends BorderPane {

        TextField cost = new TextField();
        Label transport = new Label("0");

        public Ochiphi(double prefV, double prefH) {
            
            this.setPrefSize(prefV, prefH);
            this.setStyle("-fx-background-color: skyblue;");
            this.setTop(cost);
            this.setCenter(transport);
        }

    }

    class Othuphat extends BorderPane {

        int index;
        TextArea cost = new TextArea();

        public Othuphat(double prefV, double prefH) {

            this.setPrefSize(prefV, prefH);
            this.setStyle("-fx-background-color: green;");
            this.setCenter(cost);

        }

    }

}
