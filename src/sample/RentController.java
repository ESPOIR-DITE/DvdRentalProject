package sample;

import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import sample.domain.NetWorkClass;
import sample.domain.Rental;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class RentController implements Initializable {
    String check;

    NetWorkClass netWorkClass=new NetWorkClass();
    ArrayList <String> Arayli=new ArrayList<>();
    @FXML
    private TextArea myArea;
    @FXML
    ComboBox<String>customerCombo;
    @FXML
    ComboBox<String>categoryCombo;
    @FXML
    ComboBox<String>movieCombo;
    @FXML
    CheckBox availlableChech;
    @FXML
    Button checkVideo;
    String first, check2;

    private int rentalNumber;                   //this number must be generated within the app. one method should do that
    private String dateRented;
    private String dateReturned;
    private int custNumber;
    private int dvdNumber;
    private String dvdtitre;
    private int respons=0;
    String myCustomers;
    Rental rental;
    String titlesdvd;
    String newValx;
    ArrayList<String>mylist=new ArrayList<String>();
    ArrayList<String>mylist1=new ArrayList<String>();

    @FXML
    private ComboBox<String>listCombo;
    private ObservableList<String> combolist2 = FXCollections.observableArrayList("MOVIE LIST","CUSTOMER LIST","ALL RENTAL","OUT STANDING");
    private ObservableList<String> combolist3 = FXCollections.observableArrayList("ACTION","COMEDY","DRAMA","ROMANCE","CARTOON","SCI-FI","HORROR");


    private String getListComboElement;
    String []getArray;
    String []choose;
    @FXML
    private AnchorPane rentPanel;
    @FXML
    private AnchorPane rootPanel;
    @FXML
    private ComboBox<String> addCombo;

    private ObservableList<String> combolist = FXCollections.observableArrayList("ADD DVD","ADD CUSTOMER","dhdhd");       // INITIALISING THE COMBOBOX WITH IT ELEMENTS
    //private ObservableList<String> customerComboList = FXCollections.observableArrayList();
    private String comboElement;
    private String categoryValues;
    ObservableList<String> customerComboList;
    ObservableList<String> movieComboList;
    ArrayList savingList=new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)                                                            // where we are going to initialize our instances.
    {
        getCustomerList();

        addCombo.setItems(combolist);  // these comboboxes need to be filled in the data from database
        listCombo.setItems(combolist2);
        customerCombo.setItems(customerComboList);
        //getCategory();
        categoryCombo.setItems(combolist3);
        mylist.clear();
        String newValx;

       // movieCombo.setItems(movieComboList);
    }
    //method to close the Stage.
    public void closeProgram()
    {
        System.exit(0);
    }


    @FXML
    public void getCategory()
    {
        System.out.println("CATEGORY FROM THE RENTAL PANEL GOTTEN");
        categoryValues=categoryCombo.getValue();
        savingList.add(categoryCombo+"<<CATEGO");/******************************************************************ADDING CATEGORY TO MY SAVINGlIST*/
        System.out.println("the category choosen is: "+categoryValues);
        netWorkClass=new NetWorkClass();

        //IM SENDING E TO GET THE MOVIE TITLE
        newValx=netWorkClass.sendData("e#"+categoryValues);
       // savingList.add(newValx+"<<movie title");/******************************************************************ADDING movie title TO MY SAVINGlIST*/
        Arayli.add(newValx);
        System.out.println("value received"+newValx);
        try {
            StringTokenizer token;
            String nextValeu="wu";
            mylist.clear();
            token = new StringTokenizer(newValx, ",");
            System.out.println(newValx+"in the getCategory");
            try{
                while (nextValeu!=null)
                {
                    mylist.add(token.nextToken());
                }
            }catch (NoSuchElementException e){
                System.out.println("contitnue");
                myArea.appendText("WE DONT HAVE MOVIES FOR THIS CAT");
            }catch(NullPointerException n){
                System.out.println("null from receiving customer Number");
                myArea.appendText("WE DONT HAVE MOVIES FOR THIS CAT");
            }
            System.out.println(newValx+"in the getCategory>>>>after tokenizer");
            try {
                token = new StringTokenizer(newValx, ",");
                if(nextValeu!=null){
                String first=token.nextToken();
                    check=token.nextToken();
                    System.out.println(check+"<<<<<<>>>>>>");
                }
            }catch (NoSuchElementException e){
                System.out.println("contitnue");

            }catch(NullPointerException n){
                System.out.println("null from receiving customer Number");

            }




           // movieComboList = FXCollections.observableArrayList(mylist);
            for(int i =0;i<mylist.size();i++)
            {
                System.out.println("ArrayList"+mylist.get(i));
                myArea.appendText(mylist.get(i));
            }
            movieComboList = FXCollections.observableArrayList(mylist);
            categoryCombo.setItems(combolist3);

            goRentbtn();


        }
        catch (NullPointerException e)
        {
            JOptionPane.showMessageDialog(null,"we are sorry\n a Network error has occur\nplease try again or contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
        }

        System.out.println("the category choosen is sent: "+categoryValues);
        mylist.clear();
        System.out.println(newValx+" at the end of getcat");
        checkVideoM(newValx);
    }

                                       //through this method we need to send to the database the following data{
                                       // rentalNumber; dateRented;
                                        // dateReturned;// dvdNumber;
                                         // custNumber
     @FXML public void severRentPanel()
    {
          rentalNumber=Integer.parseInt(netWorkClass.sendData("f"));
          dateRented =getTime();
          dateReturned="NA";
         // custNumber=Integer.parseInt(customerCombo.getValue());
          //dvdNumber=getDvdNumber(movieCombo.getValue());   //we are sending the selected valeu from the movieCombo.
        //dvdtitre=getDvdNumber(movieCombo.getValue());
        //System.out.println(dvdtitre);

        //getTime();

        //int  rentalNumber, String dateRented, int custNumber , int dvdNumber
        System.out.println("NEW RENTAL ADD: "+rentalNumber+" "+dateRented+" "+custNumber+" "+dvdNumber);
        for(int i=0;i<savingList.size();i++)
        {
            System.out.println(savingList.get(i)+"<<<<<<<<<<<<<<<<my arraylist never fail");
        }
        //rental=new Rental(rentalNumber,dateRented,custNumber,dvdNumber);



        try {
            System.out.println("we are there");
            AnchorPane rootPanelredonel = FXMLLoader.load(getClass().getResource("homeUI.fxml"));
            rentPanel.getChildren().addAll(rootPanelredonel);
        } catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"we are dissapointer\nan error has occur\nplease contact\n0617825205\nfor support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
        }
        outSideMethod();
    }//END SAVE RENTAL
    @FXML
    public void outSideMethod()
    {


        String v=customerCombo.getValue();
        System.out.println(v+"<<<<<this is what is selected>>>>>>");
        savingList.add(v);/******************************************************************************customer selected number**/
    }


    @FXML
    public void goReturn()
    {
        try{
            AnchorPane returnPanel = FXMLLoader.load(getClass().getResource("ReturnUI.fxml"));
            System.out.println("has got the class  HomeUIControler ");
            rentPanel.getChildren().addAll(returnPanel);
        } catch (IOException e)
        {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null,"we are dissapointer\n an error has occur\nplease contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
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

                AnchorPane dvdPanel = FXMLLoader.load(getClass().getResource("AddDvd.fxml"));
                System.out.println("has got the class  HomeUIControler ");
                rentPanel.getChildren().addAll(dvdPanel);
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
                rentPanel.getChildren().addAll(rootPane1);
            } catch (IOException e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"we are dissapointer\n an error has occur\nplease contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
            }


        }
        System.out.println("selected: "+comboElement);
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

    //this is method returns the current date.
    public String getTime()
    {
        GregorianCalendar gcalendar = new GregorianCalendar();
        int day = gcalendar.get(Calendar.DAY_OF_MONTH);
        int month=gcalendar.get(Calendar.MONTH);
        int year = gcalendar.get(Calendar.YEAR);

        return ""+day+"/"+month+"/"+year;
    }





    //this methode need to access the database and fetch for the highest number by sending a string that will be directed to a specific column
    public int hightsnumber(String z)
    {
       if(z.equalsIgnoreCase("ha")) //this will be a request to get the highEst RENTAL NUMBER
       {
           try {
               netWorkClass.sendData("f"); //I'M SENDING F TO THE NETWORK CLASS SO THAT I CAN GET highEst RENTAL NUMBER
               respons = Integer.parseInt(netWorkClass.getString());  //here i am sending this to the the Network class so that they can check to the db and return the highst valeu
           }catch (NullPointerException e)
               {
                   respons=0000; //IN CAS OF IF THE TBLE STILL NEW(EMPTY);


       }}


       return respons+1;
    }


    //this method goes to the database to fetch for the dvd number of the dvd selected from the dvd Tittle combobox
    public String getDvdNumber(String z)
    {
        System.out.println("INTO GETdVDnUMBER");
        int dVDNumber;

        try {
            netWorkClass.sendData("e#"+categoryValues); // WE ARE SENDING THE 'e'#+category = which is the DVD Name TO GET A it's number.
            titlesdvd= netWorkClass.getString();  // we are returning the (dvd number)integer.
        }
        catch (NullPointerException e)
        {
            JOptionPane.showMessageDialog(null,"we are sorry\n a Network error has occur\nplease try again or contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
        }
        //return dvdNumber;
        return titlesdvd;
    }
    public void getCustomerList()//THIS METHOD WILL CONTACT THE DATABASE TO GET ALL THE CUSTOMER ON THE DATABASE AND PUT THEM ON THE LIST
    {
        try {mylist.clear();
           // netWorkClass.sendData("D"); // WE ARE SENDING THE 'D' SO THAT WE CAN GET ALL THE CUSTOMER LIST
            myCustomers = netWorkClass.sendData("D");  // we are returning the customer list
            int leng=myCustomers.length();
            //myCustomers.replace("[","");
            System.out.println(myCustomers+"<<<<<CUSTOMER numbers");
            customerComboList=null;
            StringTokenizer token;

            String nextValeu="wu";
            token = new StringTokenizer(myCustomers, ",");
            try{
                while (nextValeu!=null)
                {
                   // nextValeu = token.nextToken();
                    //System.out.println("**"+nextValeu);

                    //customerComboList.add(nextValeu);
                    mylist.add(token.nextToken());

                }}catch (NoSuchElementException e){
                System.out.println("contitnue");
            }catch(NullPointerException n){
                System.out.println("null from receiving customer Number");
            }
            customerComboList = FXCollections.observableArrayList(mylist);
            for(int i =0;i<mylist.size();i++)
            {
                System.out.println("ArrayList"+mylist.get(i));
                //System.out.println("###"+customerComboList.get(i));
            }
            /*
            i need to loop and set the output of this String in the right way in the
             */

            return ;
        }catch (NoSuchElementException e){
            System.out.println("contitnue");
        }
       /** catch (NullPointerException e)
        {
            JOptionPane.showMessageDialog(null,"we are sorry\n a Network error has occur\nplease try again or contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
        }*/


    } @FXML
    public void cancel()
    {
        try {
            System.out.println("we are there");
            AnchorPane rootPanelredonel = FXMLLoader.load(getClass().getResource("homeUI.fxml"));
            rentPanel.getChildren().addAll(rootPanelredonel);
        } catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"we are dissapointer\n an error has occur\nplease contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
        }

    }
    @FXML
    public void goRentbtn()         //when the rent btn is pressed
    {
        try{
            AnchorPane rentPane = FXMLLoader.load(getClass().getResource("RentUI.fxml"));
            System.out.println("has got the class  HomeUIControler ");
            rentPanel.getChildren().addAll(rentPane);
        } catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"we are dissapointer\n an error has occur\nplease contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }


    @FXML
    public void checkVideoM(String newVal)
    {
        //categoryValues=categoryCombo.getValue();
       // newValx=netWorkClass.sendData("e#"+categoryValues);
        System.out.println(newVal+"########");
        String s=newVal.replace(" ","");
        System.out.println(s+"<<<after replace");
        try {


            if(s!=null){
               // first=tokenx.nextToken();
                StringTokenizer tokenx = new StringTokenizer(s, ",");
                for (int i=0;s!=null;i++)
                {
                   mylist.add(tokenx.nextToken());
                }
               // System.out.println(first+"<<first");
            }
        }catch (NoSuchElementException e){
            System.out.println("contitnue in check");

        }catch(NullPointerException n){}
        try{

            System.out.println("second try excuting");
            //choose[0]=newVal;
            for(int i =0;i<mylist.size();i++)
            {
                System.out.println("ArrayList"+mylist.get(i)+"int check the new Array");
                // myArea.appendText(mylist.get(i));
            }/**
            for(int i =0;i<Arayli.size();i++)
            {
                System.out.println("ArrayList"+Arayli.get(i)+"int check the new ArrayList");
                // myArea.appendText(mylist.get(i));
            }
            for(int i =0;i<mylist.size();i++)
            {
                System.out.println("ArrayList"+mylist.get(i)+"int check");
                // myArea.appendText(mylist.get(i));
            }
            String []getArray=mylist.toArray(new String[mylist.size()]);
            check=Arrays.toString(getArray);
            System.out.println(check+"<<<<<<<<>>");*/
            System.out.println(s+"<<<<check s");

            }catch (NullPointerException x){
            System.out.println("null here");
        }
                catch (ArrayIndexOutOfBoundsException w){
                    System.out.println("Array problem");
                }
        if(!s.equals(null))
        {

            getArray=mylist.toArray(new String[mylist.size()]);
            try{String chooz=(String)JOptionPane.showInputDialog(null,"choose the title","TITLE CHOICE"
                    ,JOptionPane.QUESTION_MESSAGE,null,getArray,getArray[0]);
            System.out.println("this is choosen>>>>>"+chooz);
            savingList.add(chooz);
            }
            catch (NullPointerException e){
                System.out.println("there is null here");
            }}
        else JOptionPane.showMessageDialog(null,"WE DONT HAVE MOVE FOR THIS\n category!!!","NOT AVAILLABLE",JOptionPane.INFORMATION_MESSAGE);
    }


}
