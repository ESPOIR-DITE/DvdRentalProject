package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DvdController implements Initializable {
    @FXML
    private AnchorPane  dvdPanel;    //addPane;
    @FXML
    private Button saveDvdbtn;
    @FXML
    public ComboBox<String> categoryCombo;
    ObservableList<String> combolist = FXCollections.observableArrayList("esoo","ditekk","dhdhd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        categoryCombo.setItems(combolist);

    }

    public DvdController() {


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

        String value = categoryCombo.getValue();
        System.out.println("ok: "+value);
    }

    //the following methode is to perform what we should do when the button SAVE of the AddDvd is pressed
    @FXML
    public void savebtnPressed()
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
