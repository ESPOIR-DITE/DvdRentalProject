package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Controller {

    JTable myTable = new JTable();
    @FXML
    private AnchorPane rootPane;
   // private JComboBox categoryCombo;


    public void closeProgram()
    {
        System.exit(0);
    }
    public void addelementComboBox()
    {
        String addelements[]={"add1","add2","add3"};
        JComboBox comboBox1 = new JComboBox(addelements);
    }
//    public void loadSecond(ActionEvent event ) throws IOException {
//
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("Assbtn.fxml"));
//        rootPane.getChildren().addAll(pane);
//    }
    public void remove()
    {

    }
    @FXML
    public void toSecond() throws IOException {             //"Addbtn.fxml"
        System.out.println("activate second panel");
        AnchorPane rootPane1 =FXMLLoader.load(getClass().getResource("Addbtn.fxml"));
        rootPane.getChildren().addAll(rootPane1);
    }

    public void setCategoryCombo() {
        System.out.println("setCategory from Controller.class");


    }
}
