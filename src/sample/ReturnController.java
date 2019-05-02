package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReturnController implements Initializable {
    @FXML
    private TextArea myArea;
    @FXML
    private ComboBox<String>listCombo;

    private ObservableList<String> combolist2 = FXCollections.observableArrayList("MOVIE LIST","CUSTOMER LIST","ALL RENTAL","OUT STANDING");
    private String comboElement;
    private String getListComboElement;

    @FXML
    private AnchorPane returnPanel;

    @FXML
    private ComboBox<String> addCombo;
    private ObservableList<String> combolist = FXCollections.observableArrayList("ADD DVD","ADD CUSTOMER","dhdhd");       // INITIALISING THE COMBOBOX WITH IT ELEMENTS




    @Override
    public void initialize(URL location, ResourceBundle resources)                                                            // where we are going to initialize our instances.
    {
        addCombo.setItems(combolist);
        listCombo.setItems(combolist2);
    }
    //method to close the Stage.
    public void closeProgram()
    {
        System.exit(0);
    }
    @FXML
    public void severRentPanel()  //suppose to be called save return.. !!! becarefull. this is where we will be handling every inputs
    {
        try {
            System.out.println("we are there");
            AnchorPane rootPanelredonel = FXMLLoader.load(getClass().getResource("homeUI.fxml"));
            returnPanel.getChildren().addAll(rootPanelredonel);
        } catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"we are dissapointer\n an error has occur\nplease contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    @FXML//THIS METHOD IS RESPONSABLE OF CHECKING WHAT IS SELECTED IN THE COMBOBOX AND CHANGE THE STAGE RESPECTIVELY TO THE APROPRIATE STAGE
    public void getComboValue()
    {
        comboElement=addCombo.getValue();
        System.out.println("HomeUIControler: selected: "+comboElement);
        if(comboElement.equalsIgnoreCase("ADD DVD"))
        {           //if we choose in the combobox the value ADD DVD then we load the dvdpanel into rootPane which is the Panrent panel.

            try {

                AnchorPane  dvdPanel = FXMLLoader.load(getClass().getResource("AddDvd.fxml"));
                System.out.println("has got the class  HomeUIControler ");
                returnPanel.getChildren().addAll(dvdPanel);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"we are dissapointer\n an error has occur\nplease contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
            }

        }
        else if(comboElement.equalsIgnoreCase("ADD CUSTOMER"))
        {       //if we choose in the combobox the value ADD CUSTOMER then we load the rootPane1 into rootPane which is the Panrent panel.
            try{
                AnchorPane rootPane1 = FXMLLoader.load(getClass().getResource("Addbtn.fxml"));
                System.out.println("has got the class  HomeUIControler ");
                returnPanel.getChildren().addAll(rootPane1);
            } catch (IOException e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"we are dissapointer\n an error has occur\nplease contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
            }


        }
        System.out.println("selected: "+comboElement);
    }
    @FXML
    public void goRentbtn()         //when the rent btn is pressed
    {
        try{
            AnchorPane rentPanel = FXMLLoader.load(getClass().getResource("RentUI.fxml"));
            System.out.println("has got the class  HomeUIControler ");
            returnPanel.getChildren().addAll(rentPanel);
        } catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"we are dissapointer\n an error has occur\nplease contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    @FXML
    public void listComboGetValeu()
    {//MOVIE LIST","CUSTOMER LIST","ALL RENTAL","OUT STANDING
        getListComboElement=listCombo.getValue();
        if(getListComboElement.equalsIgnoreCase("MOVIE LIST"))
        {
            toDisplay("MOVIE LIST");
        }
        if(getListComboElement.equalsIgnoreCase("CUSTOMER LIST"))
        {
            toDisplay("CUSTOMER LIST");
        }
        if(getListComboElement.equalsIgnoreCase("ALL RENTAL"))
        {
            toDisplay("RENTAL LIST");

        }
        if(getListComboElement.equalsIgnoreCase("OUT STANDING"))
        {
            toDisplay("STAND OUT LIST");
        }

        //THIS METHOD IS AN HELP TO HANDLE ALL THE STRINGS THAT WE WILL DISPLAY IN THE TEXT AREA


    }
    public void toDisplay(String text)
    {
        myArea.setText("");
        myArea.setText(text+"\n");
    }

}
