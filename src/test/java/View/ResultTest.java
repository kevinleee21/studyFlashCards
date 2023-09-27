package View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

class ResultTest {
    private Appendable appendable;
    private static final StringBuilder VALUE = new StringBuilder();
    private ArrayList<String> questions = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        this.appendable = new StringBuilder();
        String ln = System.lineSeparator();
        VALUE.append("Good Job!").append(ln);;
        VALUE.append("You answered 1 questions").append(ln);
        VALUE.append("0 questions went from hard to easy").append(ln);
        VALUE.append("0 questions went from easy to hard").append(ln);
        VALUE.append("2 Easy Questions").append(ln);
        VALUE.append("1 Hard Questions").append(ln);

        questions.add("Which continent is known as the \"Roof of the World\"?:::Asia.***Easy");
        questions.add("Which country is located in both Europe and Asia?:::Russia.***Easy");
        questions.add("Which city is located on two continents?::: Istanbul, Turkey." +
                " It is located on both Europe and Asia.***Hard");
    }

    @Test
    void displayFinal() throws IOException {
        // check empty StringBuilder
        assertEquals("", this.appendable.toString());

        // write to it
        Result.displayFinal(appendable, 1, 0, 0, questions);

        // check only that value appears in the StringBuilder
        Assertions.assertEquals(VALUE.toString(), this.appendable.toString());

    }
}