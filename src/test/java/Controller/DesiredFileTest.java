//package Controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.IOException;
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Test class for DesiredFile.
// */
//class DesiredFileTest {
//
//  /**
//   * Tests the readFile method to ensure correct .sr file reading from the input source.
//   *
//   * @throws IOException If an I/O error occurs.
//   */
//  @Test
//  void readFile_shouldReturnSrFile() throws IOException {
//    // Arrange
//    String filePath = "test.sr";
//    String input = filePath;
//    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
//    Readable mockReadable = () -> inputStream;
//    DesiredFile desiredFile = new DesiredFile(mockReadable, new Scanner(mockReadable));
//
//    // Act
//    File file = desiredFile.readFile();
//
//    // Assert
//    assertNotNull(file, "The file object should not be null.");
//    assertEquals(filePath, file.getPath(), "The file path should match the input.");
//    assertTrue(file.isFile(), "The file should exist.");
//    assertTrue(file.getName().endsWith(".sr"), "The file should have a .sr extension.");
//  }
//
//  /**
//   * Tests the readFile method to ensure it throws NoSuchElementException when no file input is found.
//   */
//  @Test
//  void readFile_shouldThrowExceptionForMissingInput() {
//    // Arrange
//    String input = "";
//    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
//    Readable mockReadable = () -> inputStream;
//    DesiredFile desiredFile = new DesiredFile(mockReadable, new Scanner(mockReadable));
//
//    // Assert
//    assertThrows(NoSuchElementException.class, desiredFile::readFile, "A NoSuchElementException should be thrown for missing input.");
//  }
//
//  /**
//   * Tests the readFile method to ensure it throws IOException for an invalid file path or non-.sr file.
//   */
//  @Test
//  void readFile_shouldThrowExceptionForInvalidFilePath() {
//    // Arrange
//    String filePath = "nonexistent.txt";
//    String input = filePath;
//    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
//    Readable mockReadable = () -> inputStream;
//    DesiredFile desiredFile = new DesiredFile(mockReadable, new Scanner(mockReadable));
//
//    // Assert
//    assertThrows(IOException.class, desiredFile::readFile, "An IOException should be thrown for an invalid file path or non-.sr file.");
//  }
//}
