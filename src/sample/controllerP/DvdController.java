package sample.controllerP;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DvdController implements Initializable {
    @FXML
    private AnchorPane addPane;






    @FXML
    public ComboBox<String> categoryCombo;
    ObservableList<String> combolist = FXCollections.observableArrayList("esoo","ditekk","dhdhd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        categoryCombo.setItems(combolist);
    }

    public DvdController() {


    }

    public void addToComboBox()
    {

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


}
