package cs3500.pa02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.io.File.createTempFile;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MdFileReaderTest {

    @Test
    void convertFiles() throws IOException {
        // initialize the list of files then convert to list of string
        File f1 = createTempFile("temp1", ".md");
        File f2 = createTempFile("temp2", ".md");
        // append a list of lines, add new lines automatically
        List<String> content1 = Arrays.asList("- [[An **array** is a collection of variables of the same type]], referred to",
                "  by a common name.",
                "- In Java, arrays are objects, and must be created dynamically (at runtime). " +
                        "[[Which continent is known as the \"Roof of the World\"?:::Asia.***Hard]]\n",
                "- [[numberOfElements\n" +
                        " must be a posit\n" +
                        "ive Integer.]]");
        List<String> content2 = Arrays.asList("- [Which country is known as the Land of Fire and Ice?:::Iceland.]]",
                "- [[General Form:\n" +
                        " arrayName;]]",
                "- In Java, arrays are objects, and must be created dynamically (at runtime). ",
                "## Declaring an Array", "[[What is the official language of Japan?\n" +
                        ":::The official language is Japanese.***Hard]]");
        Files.write(f1.toPath(), content1, StandardOpenOption.APPEND);
        Files.write(f2.toPath(), content2, StandardOpenOption.APPEND);
        MdFileReader mdread = new MdFileReader();
        ArrayList<File> files = new ArrayList<>();
        files.add(f1);
        files.add(f2);
        ArrayList<String> study = mdread.convertFiles(files);

        // check the first string of the list
        Assertions.assertEquals(study.get(0), "- An **array** is a collection of variables of the same type");
        Assertions.assertEquals(study.get(1), "- numberOfElements must be a positive Integer.");
        Assertions.assertEquals(study.get(2), "- General Form: arrayName;");
        Assertions.assertEquals(study.get(3), "## Declaring an Array");
        // check edge case for finishline method, text belongs in .sr file, not .md
        Assertions.assertNotEquals("What is the official language of Japan?" +
                ":::The official language is Japanese.***Hard", study.get(3));
    }

    @Test
    public void testInvalidInput() {
        // Checking an invalid score produces the correct exception
        MdFileReader md = new MdFileReader();
        ArrayList<File> files = new ArrayList<>();
        // Add a non-existent file to the list
        files.add(new File("nonexistentfile.txt"));

        assertThrows(RuntimeException.class,
                () -> md.convertFiles(files));
    }
}
