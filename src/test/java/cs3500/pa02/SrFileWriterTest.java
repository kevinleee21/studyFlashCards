package cs3500.pa02;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class SrFileWriterTest {

  /**
   * Tests the writeSrFile method to ensure it writes the contents to the specified .sr file.
   *
   * @throws IOException If an I/O error occurs.
   */
  @Test
  void writeSrFile_shouldWriteContentsToFile() throws IOException {
    // Arrange
    File file = createTempFile();
    ArrayList<String> contents = new ArrayList<>();
    contents.add("[[Question1:::Answer1]]");
    contents.add("[[Question2:::Answer2]]");
    SrFileWriter srFileWriter = new SrFileWriter();

    // Act
    FileWriter fileWriter = srFileWriter.writeSrFile(file, contents);

    // Assert
    assertNotNull(fileWriter, "The FileWriter object should not be null.");
    assertTrue(file.exists(), "The .sr file should be created.");
    assertEquals(2, countLines(file), "The number of lines in the file should match the number of contents.");
  }

  /**
   * Tests the writeSrFile method to ensure it appends ".sr" extension if not present in the file path.
   *
   * @throws IOException If an I/O error occurs.
   */
  @Test
  void writeSrFile_shouldAppendSrExtension() throws IOException {
    // Arrange
    File file = createTempFileWithExtension("test");
    ArrayList<String> contents = new ArrayList<>();
    contents.add("[[Question1:::Answer1]]");
    SrFileWriter srFileWriter = new SrFileWriter();

    // Act
    FileWriter fileWriter = srFileWriter.writeSrFile(file, contents);

    // Assert
    assertNotNull(fileWriter, "The FileWriter object should not be null.");
    assertTrue(file.exists(), "The .sr file should be created.");
  }

  /**
   * Tests the writeSrFile method to ensure it only writes lines containing ":::" to the file.
   *
   * @throws IOException If an I/O error occurs.
   */
  @Test
  void writeSrFile_shouldWriteLinesWithTripleColon() throws IOException {
    // Arrange
    File file = createTempFile();
    ArrayList<String> contents = new ArrayList<>();
    contents.add("[[Question1:::Answer1]]");
    contents.add("This line should not be written.");
    contents.add("[[Question2:::Answer2]]");
    SrFileWriter srFileWriter = new SrFileWriter();

    // Act
    FileWriter fileWriter = srFileWriter.writeSrFile(file, contents);

    // Assert
    assertNotNull(fileWriter, "The FileWriter object should not be null.");
    assertTrue(file.exists(), "The .sr file should be created.");
    assertEquals(2, countLines(file), "Only lines containing ':::' should be written to the file.");
  }

  /**
   * Helper method to create a temporary file for testing.
   *
   * @return The created temporary file.
   * @throws IOException If an I/O error occurs.
   */
  private File createTempFile() throws IOException {
    return File.createTempFile("test", ".sr");
  }

  /**
   * Helper method to create a temporary file with a specified extension for testing.
   *
   * @param extension The file extension.
   * @return The created temporary file.
   * @throws IOException If an I/O error occurs.
   */
  private File createTempFileWithExtension(String extension) throws IOException {
    File file = createTempFile();
    String newPath = file.getAbsolutePath().replace(".sr", "." + extension);
    file.renameTo(new File(newPath));
    return new File(newPath);
  }

  /**
   * Helper method to count the number of lines in a file.
   *
   * @param file The file to count lines from.
   * @return The number of lines in the file.
   * @throws IOException If an I/O error occurs.
   */
  private int countLines(File file) throws IOException {
    return (int) Files.lines(Path.of(file.getPath())).count();
  }
}

