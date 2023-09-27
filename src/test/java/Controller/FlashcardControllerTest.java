package Controller;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardControllerTest {

    @Test
    void run() throws IOException {
        StringBuilder VALUE = new StringBuilder();
        String ln = System.lineSeparator();
        VALUE.append("Hello, please start the study session by typing in the filepath to the .sr file").append(ln);;
        VALUE.append("Please enter the number of questions you want to be asked!").append(ln);
        VALUE.append("Which country is located in both Europe and Asia").append(ln);
        VALUE.append("Please enter one of the 4 options: hard - easy - show - exit").append(ln);
        VALUE.append("Which country is located in both Europe and Asia").append(ln);
        VALUE.append("Please enter one of the 4 options: hard - easy - show - exit").append(ln);
        VALUE.append("Which country is located in both Europe and Asia").append(ln);
        VALUE.append("Please enter one of the 4 options: hard - easy - show - exit").append(ln);
        VALUE.append("Russia.").append(ln);
        VALUE.append("Good Job!").append(ln);;
        VALUE.append("You answered 3 questions").append(ln);
        VALUE.append("1 questions went from hard to easy").append(ln);
        VALUE.append("1 questions went from easy to hard").append(ln);
        VALUE.append("1 Easy Questions").append(ln);
        VALUE.append("2 Hard Questions").append(ln);
        // Define our input we want to test
        String string = "/Users/kevinslee/IdeaProjects/studyFlashCards/Output/test.sr\n3\nhard\neasy\nshow";

        // StringReader implements Readable
        Readable input = new StringReader(string);

        // StringBuilder implements Appendable
        Appendable output = new StringBuilder();

        // pass them into the controller
        FlashcardController controller = new FlashcardController(input, output);

        // check that the StringBuilder is empty to start
        assertEquals("", output.toString());

        // run the workflow
        controller.run();

        // check that the StringBuilder collected the correct output
        assertEquals(VALUE.toString(), output.toString());

    }
}