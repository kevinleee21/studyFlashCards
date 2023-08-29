//package Controller;
//
//import org.junit.jupiter.api.Test;
//import java.io.ByteArrayInputStream;
//import java.util.Scanner;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
///**
// * Test class for MenuOptions.
// */
//class MenuOptionsTest {
//
//  /**
//   * Tests the readOption method to ensure correct interpretation of user-selected option.
//   */
//  @Test
//  void readOption_shouldReturnCorrectOption() {
//    // Arrange
//    String input = "hard";
//    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
//    Scanner scanner = new Scanner(inputStream);
//    MenuOptions menuOptions = new MenuOptions((Readable) inputStream, scanner);
//
//    // Act
//    Options option = menuOptions.readOption();
//
//    // Assert
//    assertEquals(Options.HARD, option, "The selected option should be HARD.");
//  }
//
//  /**
//   * Tests the readOption method to ensure correct handling of invalid option.
//   */
//  @Test
//  void readOption_shouldThrowExceptionForInvalidOption() {
//    // Arrange
//    String input = "invalid";
//    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
//    Scanner scanner = new Scanner(inputStream);
//    MenuOptions menuOptions = new MenuOptions((Readable) inputStream, scanner);
//
//    // Assert
//    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, menuOptions::readOption);
//    assertEquals("Please enter one of the 4 options!", exception.getMessage(), "An IllegalArgumentException should be thrown for invalid option.");
//  }
//}
