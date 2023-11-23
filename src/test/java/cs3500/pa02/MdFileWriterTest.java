package cs3500.pa02;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class MdFileWriterTest {

    /**
     * Tests the writeSrFile method to ensure it writes the contents to the specified .sr file.
     *
     * @throws IOException If an I/O error occurs.
     */
    @Test
    void writefile() throws IOException {
        // Arrange
        File file = createTempFile();
        ArrayList<String> contents = new ArrayList<>();
        contents.add("- hello my name is Kevin");
        contents.add("- I attend Northeastern University");
        MdFileWriter mdFileWriter = new MdFileWriter();

        // Act
        FileWriter fileWriter = mdFileWriter.writeFile(file, contents);

        // Assert
        assertNotNull(fileWriter);
        assertTrue(file.exists());
        assertEquals(0, countLines(file));
    }

    /**
     * Tests the writeSrFile method to ensure it appends ".sr" extension if not present in the file path.
     *
     * @throws IOException If an I/O error occurs.
     */
    @Test
    void appendMd() throws IOException {
        // Arrange
        File file = createTempFileWithExtension("test");
        ArrayList<String> contents = new ArrayList<>();
        contents.add("[[I attend Northeastern University]]");
        MdFileWriter mdFileWriter = new MdFileWriter();

        // Act
        FileWriter fileWriter = mdFileWriter.writeFile(file, contents);

        // Assert
        assertNotNull(fileWriter);
        assertTrue(file.exists());
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
        contents.add("- I attend Northeastern University]]");
        contents.add("- This line should not be written.");
        contents.add("[[Question2:::Answer2]]");
        contents.add("# hello");
        MdFileWriter mdFileWriter = new MdFileWriter();

        // Act
        FileWriter fileWriter = mdFileWriter.writeFile(file, contents);

        // Assert
        assertNotNull(fileWriter);
        assertTrue(file.exists());
        assertEquals(0, countLines(file));
    }

    @Test
    public void testInvalidInput() {
        // Checking an invalid score produces the correct exception
        MdFileWriter md = new MdFileWriter();

        // Provide a File object with an invalid path that will trigger an IOException
        File invalidFile = new File("/nonexistent/directory/invalid.md");
        ArrayList<String> contents = new ArrayList<>();
        contents.add("- hello my name is Kevin");
        contents.add("- I attend Northeastern University");

        assertThrows(RuntimeException.class,
                () -> md.writeFile(invalidFile, contents));
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
        String newPath = file.getAbsolutePath().replace(".md", "." + extension);
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