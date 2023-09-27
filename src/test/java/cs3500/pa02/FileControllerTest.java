package cs3500.pa02;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileControllerTest {

    @Test
    void FileNamerun() throws IOException {
        String outputFilePath = "Output/test.md";
        String filename = "filename";
        String rootpath = "/Users/kevinslee/IdeaProjects/studyFlashCards/cs3500Notes";
        FileController controller = new FileController(rootpath, filename, outputFilePath);
        controller.run();
        File temp = new File(outputFilePath);

        assertTrue(temp.exists());
    }

    @Test
    void createrun() throws IOException {
        String outputFilePath = "Output/test.md";
        String filename = "filename";
        String modified = "modified";
        String create = "create";
        String rootpath = "/Users/kevinslee/IdeaProjects/studyFlashCards/cs3500Notes";
        FileController controller = new FileController(rootpath, create, outputFilePath);
        controller.run();
        File temp = new File(outputFilePath);

        assertTrue(temp.exists());
    }

    @Test
    void modifiedrun() throws IOException {
        String outputFilePath = "Output/test.md";
        String filename = "filename";
        String modified = "modified";
        String create = "create";
        String rootpath = "/Users/kevinslee/IdeaProjects/studyFlashCards/cs3500Notes";
        FileController controller= new FileController(rootpath, modified, outputFilePath);
        controller.run();
        File temp = new File(outputFilePath);

        assertTrue(temp.exists());
    }

    @Test
    void nonexistentDir() throws IOException {
        String outputFilePath = "Output/test.md";
        String orderingFlag = "modified";
        String temp = "/Users/kevinslee/IdeaProjects/studyFlashCards/doesntexist";
        FileController controller = new FileController(temp, orderingFlag, outputFilePath);
        File temp1 = new File(outputFilePath);
        assertThrows(IllegalArgumentException.class, ()->controller.run());
    }
}