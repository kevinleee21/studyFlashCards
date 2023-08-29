//package Model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class UpdatedSrFileTest {
//  private UpdatedSrFile updatedSrFile;
//  private ArrayList<String> sorted;
//
//  @BeforeEach
//  void setUp() {
//    updatedSrFile = new UpdatedSrFile();
//    sorted = new ArrayList<>();
//    sorted.add("***Hard Question 1");
//    sorted.add("***Easy Question 2");
//    sorted.add("***Hard Question 3");
//  }
//
//  @Test
//  void updateDifficulty_shouldConvertHardToEasyAndIncrementCounter() {
//    String line = "***Hard Question 1";
//    String input = "easy";
//    int initialHardToEasy = UpdatedSrFile.hardToeasy;
//
//    updatedSrFile.updateDifficulty(sorted, line, input);
//
//    assertEquals("***Easy Question 1", sorted.get(0));
//    assertEquals(initialHardToEasy + 1, UpdatedSrFile.hardToeasy);
//  }
//
//  @Test
//  void updateDifficulty_shouldConvertEasyToHardAndIncrementCounter() {
//    String line = "***Easy Question 2";
//    String input = "hard";
//    int initialEasyToHard = UpdatedSrFile.easyTohard;
//
//    updatedSrFile.updateDifficulty(sorted, line, input);
//
//    assertEquals("***Hard Question 2", sorted.get(1));
//    assertEquals(initialEasyToHard + 1, UpdatedSrFile.easyTohard);
//  }
//
//  @Test
//  void updateDifficulty_shouldConvertEasyToHardWithShowInputAndIncrementCounter() {
//    String line = "***Easy Question 2";
//    String input = "show";
//    int initialEasyToHard = UpdatedSrFile.easyTohard;
//
//    updatedSrFile.updateDifficulty(sorted, line, input);
//
//    assertEquals("***Hard Question 2", sorted.get(1));
//    assertEquals(initialEasyToHard + 1, UpdatedSrFile.easyTohard);
//  }
//
//  @Test
//  void updateDifficulty_shouldNotConvertWhenLineNotFound() {
//    String line = "***Easy Question 4"; // Line not present in the list
//    String input = "hard";
//    int initialEasyToHard = UpdatedSrFile.easyTohard;
//
//    updatedSrFile.updateDifficulty(sorted, line, input);
//
//    assertEquals(initialEasyToHard, UpdatedSrFile.easyTohard);
//  }
//
//  @Test
//  void updateDifficulty_shouldNotConvertWhenInputDoesNotMatch() {
//    String line = "***Hard Question 3";
//    String input = "show"; // Invalid input for conversion
//    int initialEasyToHard = UpdatedSrFile.easyTohard;
//
//    updatedSrFile.updateDifficulty(sorted, line, input);
//
//    assertEquals(initialEasyToHard, UpdatedSrFile.easyTohard);
//  }
//}
