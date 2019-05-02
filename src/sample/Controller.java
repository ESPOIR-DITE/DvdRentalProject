package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    JTable myTable = new JTable();
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ComboBox <String>addCombo;
    private ObservableList<String> combolist = FXCollections.observableArrayList("ADD DVD","ADD CUSTOMER","dhdhd");       // INITIALISING THE COMBOBOX WITH IT ELEMENTS

    private String comboElement;

    @Override
    public void initialize(URL location, ResourceBundle resources)                                                            // where we are going to initialize our instances.
    {
       addCombo.setItems(combolist);
    }
    //method to close the Stage.
    public void closeProgram()
    {
        System.exit(0);
    }


    @FXML
    public void toSecond() throws IOException {             //"Addbtn.fxml"
        System.out.println("activate second panel");
        AnchorPane rootPane1 =FXMLLoader.load(getClass().getResource("Addbtn.fxml"));
        rootPane.getChildren().addAll(rootPane1);
    }
    @FXML//THIS METHOD IS RESPONSABLE OF CHECKING WHAT IS SELECTED IN THE COMBOBOX AND CHANGE THE STAGE RESPECTIVELY TO THE APROPRIATE STAGE
    public void getComboValue()
    {
        comboElement=addCombo.getValue();
        System.out.println("selected: "+comboElement);
        if(comboElement.equalsIgnoreCase("ADD DVD"))
        {           //if we choose in the combobox the value ADD DVD then we load the dvdpanel into rootPane which is the Panrent panel.

            try {
               AnchorPane dvdPanel = FXMLLoader.load(getClass().getResource("controllerP\\AddDvd.fxml"));
                rootPane.getChildren().addAll(dvdPanel);
            } catch (IOException e) {
                e.printStackTrace();
JOptionPane.showMessageDialog(null,"we are dissapointer\n an error has occur\nplease contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
            }

        }
        else if(comboElement.equalsIgnoreCase("ADD CUSTOMER"))
        {       //if we choose in the combobox the value ADD CUSTOMER then we load the rootPane1 into rootPane which is the Panrent panel.
            AnchorPane rootPane1 = null;
            try {
                rootPane1 = FXMLLoader.load(getClass().getResource("Addbtn.fxml"));
                rootPane.getChildren().addAll(rootPane1);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"we are dissapointer\n an error has occur\nplease contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
        System.out.println("selected: "+comboElement);
    }




}
