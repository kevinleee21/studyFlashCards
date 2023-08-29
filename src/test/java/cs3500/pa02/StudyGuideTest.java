//package cs3500.pa02;
//
//import org.junit.jupiter.api.Test;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import static org.junit.jupiter.api.Assertions.*;
//
//class StudyGuideTest {
//
//    @Test
//    void convertFile_shouldOrganizeMarkdownFiles() {
//        // Arrange
//        ArrayList<File> sortedFiles = new ArrayList<>();
//        sortedFiles.add(createMarkdownFile("file1.md", "# Heading 1\n- [[Note 1]]\n- Note 2"));
//        sortedFiles.add(createMarkdownFile("file2.md", "# Heading 2\n- [[Note 3]]"));
//
//        // Act
//        ArrayList<String> organizedGuide = StudyGuide.convertFile(sortedFiles);
//
//        // Assert
//        assertEquals(4, organizedGuide.size(), "The organized guide should contain 6 lines.");
//
//        assertEquals("# Heading 1", organizedGuide.get(0));
//        assertEquals("- Note 1", organizedGuide.get(1));
//
//        assertEquals("# Heading 2", organizedGuide.get(2));
//        assertEquals("- Note 3", organizedGuide.get(3));
//
//    }
//
//    @Test
//    void convertFile_shouldFilterQuestionsAndAnswers() {
//        // Arrange
//        ArrayList<File> sortedFiles = new ArrayList<>();
//        sortedFiles.add(createMarkdownFile("file3.md", "# Heading\n- Note 1\n[[what time is it?::: 3]]"));
//
//        // Act
//        ArrayList<String> organizedGuide = StudyGuide.convertFile(sortedFiles);
//
//        // Assert
//        assertEquals(1, organizedGuide.size(), "The organized guide should contain 2 lines.");
//
//        assertEquals("# Heading", organizedGuide.get(0));
//    }
//
//    // Helper method to create a markdown file with the given content
//    private File createMarkdownFile(String filename, String content) {
//        try {
//            File file = new File(filename);
//            FileWriter writer = new FileWriter(file);
//            writer.write(content);
//            writer.close();
//            return file;
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to create markdown file: " + filename);
//        }
//    }
//}
//
