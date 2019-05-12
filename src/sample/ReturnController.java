package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import sample.domain.NetWorkClass;
import sample.domain.Rental;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReturnController implements Initializable {
    Rental rental = new Rental();
    @FXML
    private TextArea myArea;
    double payInt,fine, balance;
    NetWorkClass netWorkClass=new NetWorkClass();
    @FXML
    private ComboBox<String>listCombo;
    @FXML
    private ComboBox<String>returntList;

    private ObservableList<String> combolist2 = FXCollections.observableArrayList("MOVIE LIST","CUSTOMER LIST","ALL RENTAL","OUT STANDING");
    private String comboElement;
    private String getListComboElement;
    String date,credit;

    @FXML
    private AnchorPane returnPanel;
    @FXML
    private Button checkbtn;

    @FXML
    private ComboBox<String> addCombo;
    private ObservableList<String> combolist = FXCollections.observableArrayList("ADD DVD","ADD CUSTOMER","dhdhd");       // INITIALISING THE COMBOBOX WITH IT ELEMENTS
    double creditInt;



    @Override
    public void initialize(URL location, ResourceBundle resources)                                                            // where we are going to initialize our instances.
    {
        returntList.setItems(getrentalList());
        listCombo.setItems(combolist2);
    }

    public ObservableList<String> getrentalList()
    {
        ArrayList<String>myArrayList=new ArrayList<>();
        String va=netWorkClass.sendData("l");
        try {
            StringTokenizer token;
            String nextValeu="wu";
            token = new StringTokenizer(va, ",");

                while (nextValeu!=null)
                {
                    myArrayList.add(token.nextToken());
                }
            }
            catch (NoSuchElementException y){
                System.out.println("contitnue");
            }
            catch(NullPointerException n){
                System.out.println("null from receiving customer Number");
            }
            for(int i =0;i<myArrayList.size();i++)
            {
                System.out.println("ArrayList"+myArrayList.get(i));
            }

        ObservableList<String> returnList = FXCollections.observableArrayList(myArrayList);
        return returnList;
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
    @FXML
    public void cancel()
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
    @FXML
    public void checker()
    {
        try {
            String cust = null;
            String custnumber=null;
            cust = returntList.getValue();
            System.out.println(cust);

            String valeure=netWorkClass.sendData("n#"+cust);

            StringTokenizer token = new StringTokenizer(valeure, "#");
            if(valeure!=null)
            {
                custnumber=token.nextToken();
                 date =token.nextToken();
                 credit=token.nextToken();
                System.out.println(custnumber+" "+date+" "+credit);
            }
            String creditTrim=credit.trim();
            creditInt=Double.parseDouble(creditTrim);
             JOptionPane.showMessageDialog(null,"YOUR ACTUAL CREDIT BALANCE\n BALANCE IS:\n"+credit,"CREDIT",JOptionPane.INFORMATION_MESSAGE);
            int differenceDate=rental.dateDifference(date,getDate());
            System.out.println("difference days is: "+differenceDate+"days");
            JOptionPane.showMessageDialog(null,"YOUR SPENDED DAYS \n BALANCE IS:\n"+differenceDate,"CREDIT",JOptionPane.INFORMATION_MESSAGE);
            if(differenceDate>3)
            {
                fine=differenceDate*5;
                System.out.println("the fine is: R"+fine);
                int dialogButton=JOptionPane.YES_NO_CANCEL_OPTION;
                JOptionPane.showConfirmDialog(null,"R "+fine+"\nDO YOU WANT TO PAY \n THE FINE OR DIDUCT\nFROM CREDIT\nYES FOR DIDUCTING\n"+credit,"WARNING",dialogButton);
                if(dialogButton==JOptionPane.YES_OPTION)
                     {
                         if(creditInt<fine)
                         {String pay=JOptionPane.showInputDialog(null,"YOU DONT HAVE \nSUFFITIANT CREDIT \nPLEASE PAY  R "+fine,"WARNING",dialogButton);
                             payInt=Integer.parseInt(pay);
                             System.out.println("checking if the fine is higher the ");
                            while (payInt+creditInt<fine)
                                        { pay=JOptionPane.showInputDialog(null,"YOU DONT HAVE \nSUFFITIANT CREDIT \nPLEASE PAY  R "+fine,"WARNING",dialogButton);
                                             payInt=Integer.parseInt(pay)+creditInt;
                                         }
                         }else creditInt=creditInt-fine; toDisplay("successfull return!!");
                    }else if(dialogButton==JOptionPane.NO_OPTION)
                {
                    String pay=JOptionPane.showInputDialog(null,"MUCH MONEY  \nR "+fine,"WARNING",dialogButton);
                         payInt=Integer.parseInt(pay)+creditInt;
                        while (payInt<fine)
                        { pay=JOptionPane.showInputDialog(null,"YOU DONT HAVE \nSUFFITIANT CREDIT \nPLEASE PAY  R "+fine,"WARNING",dialogButton);
                            payInt=Integer.parseInt(pay)+creditInt;
                        }
                }
            }
            if(payInt-fine>creditInt){ balance=payInt-fine-creditInt; toDisplay("curent credit: R"+balance);}else balance=0;
            JOptionPane.showMessageDialog(null,"thank you the current balance:\n>>>>"+balance);

            System.out.println("balance:>>>"+balance+"actual date:>>>"+getDate()+" rental Number>>>"+cust+"customerNumber>>>>: "+custnumber);
            String tosend=String.format("o#"+balance+"#"+getDate()+"#"+cust+"#"+custnumber);
            netWorkClass.sendData(tosend);

        }catch (NullPointerException e){ JOptionPane.showMessageDialog(null,"PLEASE SELECT\nA RENTAL NUMBER\n IN THE COMBOBOX","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);}
        catch (NoSuchElementException x){JOptionPane.showMessageDialog(null,"PLEASE SELECT\nA RENTAL NUMBER\n IN THE COMBOBOX","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
            System.out.println("NoSuchElementException");}
       /**need to return the combobox to the initial index*/
    }
    public void toDisplay(String text)
    {
        myArea.setText("");
        myArea.setText(text+"\n");
    }

    public String getDate()
    {
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate=LocalDate.now();
        String time=String.format(dtf.format(localDate));
        //System.out.println(time);
return time;
    }

}
