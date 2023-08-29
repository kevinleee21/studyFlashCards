//package Controller;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import java.io.ByteArrayInputStream;
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
///**
// * Test class for DesiredQuestions.
// */
//class DesiredQuestionsTest {
//
//  @Mock
//  private Readable mockReadable;
//
//  @Mock
//  private Scanner mockScanner;
//
//  /**
//   * Initializes Mockito and the mocks.
//   */
//  void setup() {
//    MockitoAnnotations.openMocks(this);
//  }
//
//  /**
//   * Tests the readNumber method to ensure correct reading of the number typed by the user.
//   */
//  @Test
//  void readNumber_shouldReturnTypedNumber() {
//    // Arrange
//    setup();
//    String input = "5";
//    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
//    when(mockReadable.read()).thenReturn(inputStream);
//    when(mockScanner.nextInt()).thenReturn(5);
//    DesiredQuestions desiredQuestions = new DesiredQuestions(mockReadable, mockScanner);
//
//    // Act
//    int number = desiredQuestions.readNumber();
//
//    // Assert
//    assertEquals(5, number, "The typed number should be 5.");
//  }
//
//  /**
//   * Tests the readNumber method to ensure it throws NoSuchElementException for non-numeric input.
//   */
//  @Test
//  void readNumber_shouldThrowExceptionForNonNumericInput() {
//    // Arrange
//    setup();
//    String input = "abc";
//    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
//    when(mockReadable.read()).thenReturn(inputStream);
//    when(mockScanner.nextInt()).thenThrow(NoSuchElementException.class);
//    DesiredQuestions desiredQuestions = new DesiredQuestions(mockReadable, mockScanner);
//
//    // Assert
//    assertThrows(NoSuchElementException.class, desiredQuestions::readNumber, "A NoSuchElementException should be thrown for non-numeric input.");
//  }
//}
