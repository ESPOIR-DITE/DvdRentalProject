package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sample.domain.NetWorkClass;
import sample.domain.serverPack.serve.Dvdservice;
import sample.domain.serverPack.serve.VideoList;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.StringTokenizer;


public class HomeUIController implements Initializable {


    @FXML
    private AnchorPane rootPanel;
    @FXML
    TableView<VideoList>myTable;
    @FXML
    private TableColumn<VideoList,String>dvdNumber;
    @FXML
    private TableColumn<VideoList,String>title;
    @FXML
    private TableColumn<VideoList,String>dispo;
    @FXML
     TextArea myArea;
    @FXML
    AnchorPane rootPane1;
    String movieDetails,movieDetails2;
    Dvdservice dvdservice;
    @FXML
    private
    AnchorPane dvdPanel;
    @FXML
    private ComboBox<String>listCombo;
    NetWorkClass netWorkClass=new NetWorkClass();

    @FXML
    private ComboBox <String>addCombo;
    private ObservableList<String> combolist = FXCollections.observableArrayList("ADD DVD","ADD CUSTOMER","dhdhd");       // INITIALISING THE COMBOBOX WITH IT ELEMENTS
    private ObservableList<String> combolist2 = FXCollections.observableArrayList("MOVIE LIST","CUSTOMER LIST","ALL RENTAL","OUT STANDING");

    private String comboElement;
    private String getListComboElement;

    @Override
    public void initialize(URL location, ResourceBundle resources)                                                            // where we are going to initialize our instances.
    {
       addCombo.setItems(combolist);
       listCombo.setItems(combolist2);

        dvdNumber.setCellValueFactory(new PropertyValueFactory<VideoList, String>("dvdNumber"));
        title.setCellValueFactory(new PropertyValueFactory<VideoList, String>("title"));
        dispo.setCellValueFactory(new PropertyValueFactory<VideoList, String>("category"));
        myTable.setItems(population());


    }
    public ObservableList population()
    {
        ObservableList<VideoList>video=FXCollections.observableArrayList();
        video.add(new VideoList("dhdhd","dhhdhd","dhdhdh"));


        return video;
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
        rootPanel.getChildren().addAll(rootPane1);
    }
    @FXML//THIS METHOD IS RESPONSABLE OF CHECKING WHAT IS SELECTED IN THE COMBOBOX AND CHANGE THE STAGE RESPECTIVELY TO THE APROPRIATE STAGE
    public void getComboValue()
    {
        comboElement=addCombo.getValue();
        System.out.println("HomeUIControler: selected: "+comboElement);
        if(comboElement.equalsIgnoreCase("ADD DVD"))
        {           //if we choose in the combobox the value ADD DVD then we load the dvdpanel into rootPane which is the Panrent panel.

            try {

                dvdPanel = FXMLLoader.load(getClass().getResource("AddDvd.fxml"));
                System.out.println("has got the class  HomeUIControler ");
               rootPanel.getChildren().addAll(dvdPanel);
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
                rootPanel.getChildren().addAll(rootPane1);
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
            rootPanel.getChildren().addAll(rentPanel);
        } catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"we are dissapointer\n an error has occur\nplease contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    @FXML
    public void goReturn()
    {
        try{
            AnchorPane returnPanel = FXMLLoader.load(getClass().getResource("ReturnUI.fxml"));
            System.out.println("has got the class  HomeUIControler ");
            rootPanel.getChildren().addAll(returnPanel);
        } catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"we are dissapointer\n an error has occur\nplease contact\n 0617825205\n for support","LOARDING ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    @FXML
    public void listComboGetValeu()
    {
        getListComboElement=listCombo.getValue();
        ArrayList<String>movieDetailsArrayList=new ArrayList<String>();
        if(getListComboElement.equalsIgnoreCase("MOVIE LIST"))
        {
            toDisplay("MOVIE LIST");
            /** sending a character'h' to get all the movies details sorted*/

            netWorkClass.sendData("h");
            System.out.println(netWorkClass.getString()+": result");
            movieDetails=netWorkClass.getString();
            dvdservice=new Dvdservice();
        try {
            StringTokenizer token = new StringTokenizer(movieDetails, ",");
            String c="gd";
            while (c != null)
            {
                movieDetailsArrayList.add(token.nextToken());
            }



        }catch (Exception e)
        {
            System.out.println("some error");
        }
        /** NOW WE ARE APPENDING THE RESULT TO THE TEXT AREA*/
            for (int i = 0; i < movieDetailsArrayList.size(); i++) {
                System.out.println(movieDetailsArrayList.get(i));
               // toDisplay(movieDetailsArrayList.get(i));
                myArea.appendText(String.format(""+movieDetailsArrayList.get(i)+"\n"));
            }
            for (int i = 0; i < movieDetailsArrayList.size(); i++)
            {
                System.out.println(movieDetailsArrayList.get(i));
            }
        }


        if(getListComboElement.equalsIgnoreCase("CUSTOMER LIST"))
        {
            netWorkClass=new NetWorkClass();
            movieDetails="";
            movieDetailsArrayList.clear();
            toDisplay("CUSTOMER LIST");
            /** sending a character'h' to get all the movies details sorted*/
            netWorkClass.sendData("K");
            System.out.println(netWorkClass.getString()+": result");
            movieDetails=netWorkClass.getString();
            dvdservice=new Dvdservice();
            try {
                StringTokenizer token = new StringTokenizer(movieDetails, ",");
                String c="gd";
                while (c != null)
                {

                    movieDetailsArrayList.add(token.nextToken());

                }
            }catch (Exception e)
            {
                System.out.println("some error");
            }
            /** NOW WE ARE APPENDING THE RESULT TO THE TEXT AREA*/
            for (int i = 0; i < movieDetailsArrayList.size(); i++) {
                System.out.println(movieDetailsArrayList.get(i));
                // toDisplay(movieDetailsArrayList.get(i));
                myArea.appendText(String.format(""+movieDetailsArrayList.get(i)+"\n"));
            }
            for (int i = 0; i < movieDetailsArrayList.size(); i++)
            {
                System.out.println(movieDetailsArrayList.get(i));
            }

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
    public void toDisplay(String text)
    {
        myArea.setText("");
        myArea.setText(text+"\n");
    }




}
