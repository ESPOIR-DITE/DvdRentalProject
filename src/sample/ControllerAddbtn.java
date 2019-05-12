package sample;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.domain.Customer;
import sample.domain.NetWorkClass;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ControllerAddbtn implements Initializable {
    Customer customer;
    NetWorkClass netWorkClass =new NetWorkClass();

    @FXML
    private TextArea myArea;
    @FXML
    private AnchorPane rootPane1;
    @FXML
    private Button customerSavebtn; //save btn on the custmer panel
    @FXML
    private AnchorPane addPane;
    List<String> list = new ArrayList<String>();
    @FXML
    private TextField customerNumberfield;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField surNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField CreditField;
    @FXML
    private CheckBox canRentfield;




    @FXML
    public ComboBox categoryCombo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerNumberfield.setText(netWorkClass.sendData("j"));
    }
    public void closeProgram()
    {
        System.exit(0);
    }
    public void custNumbeIni()
    {
        String newvalue;
        System.out.println("sending : j");
        System.out.println(netWorkClass.sendData("j"));
    }

    @FXML
    public void saverButton(ActionEvent event)  //this button will help to go back to the home page and collect all the need details of the customen
    {
        String firstName,phoneNumber,surName;
        double credit;
        boolean canRent=false;
        int custNumber;
        if(canRentfield.isSelected())
        {
            canRent=true;
        }else canRent=false;


        //now we are going to get all the containt of the customer details

        custNumber=Integer.parseInt(customerNumberfield.getText());
        firstName=firstNameField.getText();
        surName=surNameField.getText();
        phoneNumber=phoneNumberField.getText();
        credit=Double.parseDouble(CreditField.getText());
        canRent=Boolean.parseBoolean(canRentfield.getText());
        if(canRentfield.isSelected()&&credit>0){canRent=true;}else canRent=false;

        //(int custNumber, String firstName, String surname, String phoneNum, double credit, boolean canRent)
        //customer=new Customer(custNumber,firstName,surName,phoneNumber,credit,canRent);
        JOptionPane.showMessageDialog
                (null,"NEW CUSTOMER ADDED\n SUCCESSFULY\n________________\n NAME:         "+firstName+"\nSURNAME:     "+surName+"\n phone Number: "+phoneNumber+"\n THANK YOU!","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
       // myArea.setText(customer.toString());
        String s=String.format("a#"+custNumber+"#"+firstName+"#"+surName+"#"+phoneNumber+"#"+credit+"#"+canRent);

        //NOW HERE I'M GOING TO CREATE AN OBJECT OF NETWORK CLASS SO THAT WE CAN SEND THE STRING FROM THE SECONDTOSTRING METHOD

        //netWorkClass = new NetWorkClass();
       // String i=customer.secondtoString();     //here we are sending a String that is returned from customer secondtoString.
       // "a#"+custNumber+"#"+firstName+"#"+surname+"#"+phoneNum+"#"+credit+"#"+canRent+"";
        netWorkClass.sendData(s);
       // System.out.println(i+"\n sent successfully");

        try {
            System.out.println("we are there");
            AnchorPane rootPanelredonel = FXMLLoader.load(getClass().getResource("homeUI.fxml"));
            rootPane1.getChildren().addAll(rootPanelredonel);
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
            rootPane1.getChildren().addAll(rootPanelredonel);
        } catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"we are dissapointer\n an error has occur\nplease contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
        }

    }



}

