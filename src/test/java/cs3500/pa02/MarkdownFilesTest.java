package cs3500.pa02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

import static java.nio.file.FileVisitResult.*;
import static org.junit.jupiter.api.Assertions.*;

//test
public class MarkdownFilesTest {

    private FileTreeWalkVisitor markdownFiles;
    private ArrayList<File> validFiles;
    static final String SAMPLE_INPUTS_DIRECTORY = "src/test/resources/sample_files";


    @BeforeEach
    public void setup() {
        markdownFiles = new FileTreeWalkVisitor();
        validFiles = new ArrayList<>();
    }

    @Test
    public void testPreVisitDirectory() throws IOException {
        Path directory = Paths.get("path/to/your/directory"); // Replace with the actual directory path you want to test

        FileVisitResult result = markdownFiles.preVisitDirectory(directory, null);

        assertEquals(CONTINUE, result);
    }

    @Test
    public void testVisitFile() throws IOException {
        Path file = Paths.get("path/to/your/file.md"); // Replace with the actual file path you want to test

        FileTreeWalkVisitor markdownFiles = new FileTreeWalkVisitor(); // Create a new instance of MarkdownFiles
        markdownFiles.visitFile(file, null); // Call the visitFile method

        ArrayList<File> validFiles = markdownFiles.validFiles; // Access the validFiles list from the MarkdownFiles instance

        assertEquals(1, validFiles.size());
        assertEquals("file.md", validFiles.get(0).getName());
    }


    @Test
    public void testVisitFileFailed() throws IOException {
        Path file = Paths.get("path/to/your/file.md"); // Replace with the actual file path you want to test
        IOException exception = new IOException("File visit failed");

        FileVisitResult result = markdownFiles.visitFileFailed(file, exception);

        assertEquals(CONTINUE, result);
    }

    @Test
    public void testPostVisitDirectory() throws IOException {
        Path directory = Paths.get("path/to/your/directory"); // Replace with the actual directory path you want to test

        FileVisitResult result = markdownFiles.postVisitDirectory(directory, null);

        assertEquals(CONTINUE, result);
    }
    @Test
    public void testGetValidFiles() {
        FileTreeWalkVisitor mfv = new FileTreeWalkVisitor();
        try {
            Files.walkFileTree(Path.of(SAMPLE_INPUTS_DIRECTORY), mfv);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        ArrayList<File> expectedFiles = new ArrayList<>();
        expectedFiles.add(new File(SAMPLE_INPUTS_DIRECTORY + "/sample_file.md"));
        expectedFiles.add(new File(SAMPLE_INPUTS_DIRECTORY + "/arrays.md"));
        expectedFiles.add(new File(SAMPLE_INPUTS_DIRECTORY + "/vectors.md"));


        ArrayList<File> actualFiles = mfv.getValidFiles();

        assertEquals(3, actualFiles.size());
        assertArrayEquals(expectedFiles.toArray(), actualFiles.toArray());
    }
}