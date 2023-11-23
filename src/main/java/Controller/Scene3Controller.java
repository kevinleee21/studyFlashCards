package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class Scene3Controller {

    @FXML
    Label QuestionAnswered;

    @FXML
    Label HardToEasy;

    @FXML
    Label EasyToHard;

    @FXML
    Label EasyQuestions;

    @FXML
    Label HardQuestions;

    public void loadResults(int answered, int hard, int easy, ArrayList<String> sorted) {
        QuestionAnswered.setText("You answered " + answered + " questions!");
        HardToEasy.setText(hard + " questions went from hard to easy");
        EasyToHard.setText(easy + " questions went from easy to hard");
        EasyQuestions.setText(easyCount(sorted) + " Easy Questions");
        HardQuestions.setText(hardCount(sorted) + " HardQuestions");

    }

    /**
     * Counts the number of easy questions in the sorted list.
     *
     * @param sorted The sorted list of questions.
     * @return The number of easy questions.
     */
    static int easyCount(ArrayList<String> sorted) {
        int count = 0;
        for (String s : sorted) {
            if (s.contains("***Easy")) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of hard questions in the sorted list.
     *
     * @param sorted The sorted list of questions.
     * @return The number of hard questions.
     */
    static int hardCount(ArrayList<String> sorted) {
        int count = 0;
        for (String s : sorted) {
            if (s.contains("***Hard")) {
                count++;
            }
        }
        return count;
    }
}
