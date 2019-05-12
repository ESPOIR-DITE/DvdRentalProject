package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sample.domain.DvdClass;
import sample.domain.NetWorkClass;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DvdController implements Initializable {
    DvdClass dvdClass;
    NetWorkClass netWorkClass =new NetWorkClass();
    private int dvdNumberv;
    private String titlev;
    private String categoryv;
    String value;       // this is a variable that takes the valeu from the combobox
    //private double price;
    private boolean newReleasev;
    private boolean availableForRentv;

    @FXML
    private AnchorPane  dvdPanel;    //addPane;
    @FXML
    private Button saveDvdbtn;
    @FXML
    public ComboBox<String> categoryCombo;
    @FXML
    private TextField dvdNumber;
    @FXML
    private TextField title;
    private double price;
    @FXML
    private CheckBox newRelease;
    @FXML
    private RadioButton availableForRen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {                //horror, sci-fi, drama, romance, comedy, action, cartoon.

        ObservableList<String> combolist = FXCollections.observableArrayList("ACTION","COMEDY","DRAMA","ROMANCE","CARTOON","SCI-FI","HORROR");
        categoryCombo.setItems(combolist);
       dvdNumber.setText(netWorkClass.sendData("i"));
    }

    public String dvdNumberInt()
    {

        //System.out.println(netWorkClass.sendData("i"));  //**************seding i to get the highest dvdNumber

       String rs= netWorkClass.sendData("i");

return rs;
    }

    @FXML
    public void close()
    {
    System.exit(0);
    }

    public void setCategoryCombo(ComboBox categoryCombo) {
        this.categoryCombo = categoryCombo;
    }
    public void pressButton()
    {
        System.out.println("ok");

        value = categoryCombo.getValue();
        System.out.println("ok: "+value);
    }

    //the following methode is to perform what we should do when the button SAVE of the AddDvd is pressed
    @FXML
    public void savebtnPressed()
    {

        System.out.println("NOW WE GETTING THE VAleu from the dvd form");
        try {
            dvdNumberv = Integer.parseInt(dvdNumber.getText());
            titlev = title.getText();
            categoryv = value;
            if (newRelease.isSelected()) {
                newReleasev = true;
                price=15;

            } else {newReleasev = false;
                price=10;}
            if (availableForRen.isSelected()) {
                availableForRentv = true;
            } else availableForRentv = false;

            System.out.println(dvdNumberv + " " + titlev + " " + categoryv + " " + newReleasev + " " + availableForRentv);
        }catch (NullPointerException n)
        {
            n.getMessage();
        }

        /*
        we are sending the data to the dvd class
        (int dvdNumber, String title, String category, double price, boolean newRelease, boolean availableForRent)
         */
       // dvdClass = new DvdClass(dvdNumberv,titlev,categoryv,price,newReleasev,availableForRentv);
       // System.out.println(dvdNumberv + " " + titlev + " " + categoryv + " " + newReleasev + " " + availableForRentv);
        String s=String.format("b#"+dvdNumberv+"#"+titlev+"#"+categoryv+"#"+price +"#"+newReleasev +"#"+ availableForRentv);
        //System.out.println(dvdClass.toString()+"\n"+dvdClass.secondtoString());
        //SEDING THE VALEU TO THE NETWORK CLASS.
        //netWorkClass=new NetWorkClass();
        netWorkClass.sendData(s);
        JOptionPane.showMessageDialog
                (null,"NEW DVD ADDED\n SUCCESSFULY\n________________\n TITLE: "+titlev+"\nDVD NUMBER:  "+dvdNumberv+"\n CATEGORY: "+categoryv+"\n THANK YOU!","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
        try {
            System.out.println("we are there");
            AnchorPane rootPanelredonel = FXMLLoader.load(getClass().getResource("homeUI.fxml"));
            dvdPanel.getChildren().addAll(rootPanelredonel);
        } catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"we are dissapointer\n an error has occur\nplease contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
        }

    }
    @FXML
    public void cancel()
    {
        try {
            System.out.println("we are there");
            AnchorPane rootPanelredonel = FXMLLoader.load(getClass().getResource("homeUI.fxml"));
            dvdPanel.getChildren().addAll(rootPanelredonel);
        } catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"we are dissapointer\n an error has occur\nplease contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
        }

    }



}
