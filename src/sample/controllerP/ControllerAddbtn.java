package sample.controllerP;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.util.*;

public class ControllerAddbtn {

    @FXML
    private AnchorPane addPane;
    List<String> list = new ArrayList<String>();





    @FXML
    public ComboBox categoryCombo;

    public void closeProgram()
    {
        System.exit(0);
    }


}

