package Controller;

import Model.QuestionSortComparator;
import Model.UpdatedSrFile;
import View.Result;
import cs3500.pa02.SrFileWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Scene2Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Button HardButton;

    @FXML
    Button EasyButton;

    @FXML
    Button ShowButton;

    @FXML
    Button ExitButton;

    @FXML
    Label QuestionBox;

    @FXML
    Label QuestionNumber;

    private String filepath;
    private String  numQuestion;


    // Method to set received data and handle button click
    public void getFilePath(String data) {
        this.filepath = data;
    }

    // Method to set received data and handle button click
    public void getNumber(String data) {
        this.numQuestion = data;
    }

    /**
     * Represents the flow of the flashcard component of the application, managing user input
     * and updating both the model and view aspects accordingly.
     * @throws IOException IOException if an I/O error occurs during execution.
     */
    private void flow(ActionEvent event) throws IOException {
        //get the filepath
        File desiredFile = new File(filepath);
        ArrayList<String> sorted = randomize(desiredFile);
        sorted.sort(new QuestionSortComparator());
        //get the number of questions
        int numberofQues = Integer.parseInt(numQuestion);
        UpdatedSrFile updated = new UpdatedSrFile();

        if (numberofQues > 0) {
            for (int i = 0; i < numberofQues && i < sorted.size(); i++) {
                String question = sorted.get(i).substring(0, sorted.get(i).indexOf("?"));
//                String answer = sorted.get(i).substring(sorted.get(i).lastIndexOf(":::")
//                        + ":::".length(), sorted.get(i).lastIndexOf("***"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Questions.fxml"));
                root = loader.load();
                Scene2Controller scene2Controller = loader.getController();
                scene2Controller.QuestionNumber.setText(question);
                handleUserOption(determineButtonType(event), sorted, i, updated, desiredFile);
            }
            // Assuming Scene3Controller is associated with "Scene3.fxml"
            FXMLLoader loader = new FXMLLoader(getClass().getResource("finalresults.fxml"));
            Parent root = loader.load();
            Scene3Controller scene3Controller = loader.getController();

            // Now you can call loadResults with your data
            scene3Controller.loadResults(numberofQues, updated.getHardToeasy(), updated.getEasyTohard(), sorted);

            // Assuming you have a Stage and want to show it
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            SrFileWriter writer = new SrFileWriter();
            writer.writeFile(desiredFile, sorted);
        }
    }


    /**
     * Converts the question bank lines from the Sr File to Strings and randomizes them
     * @param file Sr file that contains the questions, answers, and difficulty
     * @return an randomized ArrayList of Strings where a single string
     * represents a question, answer, and difficulty
     */
    private ArrayList<String> randomize(File file) {
        boolean validFile = false;
        ArrayList<String> output = new ArrayList<>();
        Scanner scanner;
        /**
         * continue loop until a user types in a valid file
         */
        while (!validFile) {
            try {
                // read the file
                scanner = new Scanner(file);
                // process of creating list of string from the file
                while(scanner.hasNext()) {
                    String line = scanner.nextLine();
                    output.add(line);
                }
                validFile = true;
                // randomizes the list
                Collections.shuffle(output);
            } catch(FileNotFoundException e) {
                System.out.println("Not a file. Try again!");
                Scanner inputScanner = new Scanner(System.in);
                String newPath = inputScanner.nextLine();
                file = new File(newPath);
            }
        }
        return output;
    }

    private void handleUserOption(Options userOption, ArrayList<String> sorted,
                                  int index, UpdatedSrFile updated, File desiredFile) throws IOException {
        SrFileWriter writer = new SrFileWriter();
        switch (userOption) {
            case HARD:
                updated.updateDifficulty(sorted, sorted.get(index), "hard");
                break;
            case EASY:
                updated.updateDifficulty(sorted, sorted.get(index), "easy");
                break;
            case SHOW:
                updated.updateDifficulty(sorted, sorted.get(index), "show");

                break;
            case EXIT:
                // Assuming Scene3Controller is associated with "Scene3.fxml"
                FXMLLoader loader = new FXMLLoader(getClass().getResource("finalresults.fxml"));
                Parent root = loader.load();
                Scene3Controller scene3Controller = loader.getController();

                // Now you can call loadResults with your data
                scene3Controller.loadResults(index, updated.getHardToeasy(), updated.getEasyTohard(), sorted);

                // Assuming you have a Stage and want to show it
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                writer.writeFile(desiredFile, sorted);
                System.exit(0);
                break;
            default:
                throw new IllegalArgumentException("Invalid menu option. Please choose a valid option.");
        }
    }
    // Method to determine the button type based on the clicked button
    private Options determineButtonType(ActionEvent event) {
        Object source = event.getSource();

        if (source == HardButton) {
            return Options.HARD;
        } else if (source == EasyButton) {
            return Options.EASY;
        } else if (source == ShowButton) {
            return Options.SHOW;
        } else if (source == ExitButton) {
            return Options.EXIT;
        } else {
            return null;
        }
    }


}
