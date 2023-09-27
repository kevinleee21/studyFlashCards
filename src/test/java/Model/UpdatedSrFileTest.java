package Model;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * test class for the UpdatedSrFile class
 */
class UpdatedSrFileTest {

    @Test
    void updateDifficulty() {
        String q1 = "What is the largest lake in Africa? ::: The largest lake is Lake Victoria.***Easy";
        String q2 = "Which city is located on two continents?::: Istanbul, Turkey. " +
                "It is located on both Europe and Asia.***Hard";
        String q3 = "Which country is known as the Land of a Thousand Lakes? ::: Finland.***Easy";
        ArrayList<String> sorted = new ArrayList<>();
        sorted.add(q1);
        sorted.add(q2);
        sorted.add(q3);
        UpdatedSrFile file = new UpdatedSrFile();
        file.updateDifficulty(sorted, sorted.get(0), "hard");
        assertEquals(1, file.getEasyTohard());
        assertEquals(0, file.getHardToeasy());
        file.updateDifficulty(sorted, sorted.get(1), "easy");
        assertEquals(1, file.getHardToeasy());
        file.updateDifficulty(sorted, sorted.get(2), "show");
        assertEquals(2, file.getEasyTohard());
    }
}