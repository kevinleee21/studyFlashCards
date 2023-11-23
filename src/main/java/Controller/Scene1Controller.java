package Controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Model.QuestionSortComparator;
import Model.UpdatedSrFile;
import View.Result;
import cs3500.pa02.SrFileWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Scene1Controller {
    @FXML
    TextField DesiredQuestions;

    @FXML
    Button ContinueButton;

    @FXML
    TextField UserFile;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToQuestions(ActionEvent event) throws IOException {
        String filepath = UserFile.getText();
        String numberofQues = DesiredQuestions.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Questions.fxml"));
        root = loader.load();

        Scene2Controller scene2Controller = loader.getController();
        scene2Controller.getFilePath(filepath);
        scene2Controller.getNumber(numberofQues);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}
