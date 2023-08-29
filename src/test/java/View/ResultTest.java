//package View;
//
//import org.junit.jupiter.api.Test;
//import java.io.IOException;
//import java.util.ArrayList;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
///**
// * Test class for Result.
// */
//class ResultTest {
//
//  /**
//   * Tests the displayFinal method to ensure correct display of final results.
//   */
//  @Test
//  void displayFinal_shouldDisplayCorrectResults() throws IOException {
//    // Arrange
//    Appendable output = mock(Appendable.class);
//    int answered = 10;
//    int hard = 3;
//    int easy = 2;
//    ArrayList<String> sorted = new ArrayList<>();
//    sorted.add("***Easy Question");
//    sorted.add("***Hard Question");
//    sorted.add("***Easy Question");
//    sorted.add("***Hard Question");
//
//    // Act
//    Result.displayFinal(output, answered, hard, easy, sorted);
//
//    // Assert
//    verify(output, times(5)).append(anyString()); // Verifies append method is called 5 times
//    verify(output).append("Good Job!").append(System.lineSeparator());
//    verify(output).append("You answered 10 questions").append(System.lineSeparator());
//    verify(output).append("3 questions went from hard to easy").append(System.lineSeparator());
//    verify(output).append("2 questions went from easy to hard").append(System.lineSeparator());
//    verify(output).append("2 Easy Questions").append(System.lineSeparator());
//    verify(output).append("2 Hard Questions").append(System.lineSeparator());
//  }
//
//  /**
//   * Tests the easyCount method to ensure correct counting of easy questions.
//   */
//  @Test
//  void easyCount_shouldReturnCorrectCount() {
//    // Arrange
//    ArrayList<String> sorted = new ArrayList<>();
//    sorted.add("***Easy Question");
//    sorted.add("***Hard Question");
//    sorted.add("***Easy Question");
//    sorted.add("***Easy Question");
//
//    // Act
//    int count = Result.easyCount(sorted);
//
//    // Assert
//    assertEquals(3, count, "The count of easy questions should be 3.");
//  }
//
//  /**
//   * Tests the hardCount method to ensure correct counting of hard questions.
//   */
//  @Test
//  void hardCount_shouldReturnCorrectCount() {
//    // Arrange
//    ArrayList<String> sorted = new ArrayList<>();
//    sorted.add("***Easy Question");
//    sorted.add("***Hard Question");
//    sorted.add("***Hard Question");
//    sorted.add("***Hard Question");
//
//    // Act
//    int count = Result.hardCount(sorted);
//
//    // Assert
//    assertEquals(3, count, "The count of hard questions should be 3.");
//  }
//}
