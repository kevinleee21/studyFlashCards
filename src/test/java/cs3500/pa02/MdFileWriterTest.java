//package cs3500.pa02;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//import org.junit.jupiter.api.Test;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import static org.junit.jupiter.api.Assertions.*;
//
//class MdFileWriterTest {
//
//    @Test
//    void writeFile_shouldWriteContentsToFile() {
//        // Arrange
//        ArrayList<String> contents = new ArrayList<>();
//        contents.add("# Study Guide");
//        contents.add("This is a sample study guide.");
//
//        // Create a temporary file
//        File tempFile;
//        try {
//            tempFile = File.createTempFile("test", ".md");
//        } catch (IOException e) {
//            fail("Failed to create temporary file.");
//            return;
//        }
//
//        // Act
//        MdFileWriter fileWriter = new MdFileWriter();
//        try {
//            fileWriter.writeFile(tempFile, contents);
//        } catch (IOException e) {
//            fail("Failed to write contents to file.");
//            return;
//        }
//
//        // Assert
//        assertTrue(tempFile.exists(), "File should exist.");
//        assertTrue(tempFile.length() > 0, "File should not be empty.");
//
//        // Clean up
//        tempFile.delete();
//    }
//}


