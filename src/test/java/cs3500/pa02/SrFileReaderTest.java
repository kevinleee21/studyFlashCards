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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.internal.matchers.text.ValuePrinter.print;

class SrFileReaderTest {

    @Test
    void convertFiles() throws IOException {
        // initialize the list of files then convert to list of string
        File f1 = createTempFile("temp1", ".md");
        File f2 = createTempFile("temp2", ".md");
        // append a list of lines, add new lines automatically
        List<String> content1 = Arrays.asList("- [[An **array** is a collection of variables of the same type]], referred to",
                "  by a common name.",
                "- In Java, arrays are objects, and must be created dynamically (at runtime). " +
                        "[[Which continent is known as the \"Roof of the World\"?:::Asia.]]\n",
                "- [[numberOfElements\n" +
                        " must be a posit\n" +
                        "ive Integer.]]");
        List<String> content2 = new ArrayList<>();
        content2.add("- [[Which country is known as the Land of Fire and Ice?:::Iceland.]]");
        content2.add("- [[General Form:\n" + " arrayName;]]");
        content2.add("- In Java, arrays are objects, and must be created dynamically (at runtime). ");
        content2.add("- [[What is the official language of Japan?\n" + ":::The official language is Japanese.]]");

        Files.write(f1.toPath(), content1, StandardOpenOption.APPEND);
        Files.write(f2.toPath(), content2, StandardOpenOption.APPEND);
        SrFileReader srread = new SrFileReader();
        ArrayList<File> files = new ArrayList<>();
        files.add(f1);
        files.add(f2);
        ArrayList<String> study = srread.convertFiles(files);

        // check the first string of the list
        Assertions.assertEquals(study.get(0), "- Which continent is known as the \"Roof of the World\"?:::Asia.");
        Assertions.assertEquals(study.get(1), "- Which country is known as the Land of Fire and Ice?:::Iceland.");
        Assertions.assertEquals(study.get(2), "- What is the official language of Japan?:::The official language is Japanese.");
    }
    @Test
    public void testInvalidInput() {
        // Checking an invalid score produces the correct exception
        SrFileReader sr= new SrFileReader();
        ArrayList<File> files = new ArrayList<>();
        // Add a non-existent file to the list
        files.add(new File("nonexistentfile.txt"));

        assertThrows(RuntimeException.class,
                () -> sr.convertFiles(files));
    }
}
