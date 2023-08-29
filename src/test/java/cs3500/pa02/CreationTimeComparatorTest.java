package cs3500.pa02;

import Model.CreationTimeComparator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This tests the createdtime comparator
 */
class CreationTimeComparatorTest {
    @Test
    public void testCreationTimeComparator() {
        FileTime knownCreationTime = FileTime.from(Instant.parse("2023-05-14T12:00:00Z"));
        FileTime knownCreationTime2 = FileTime.from(Instant.parse("2022-05-14T12:00:00Z"));
        FileTime knownCreationTime3 = FileTime.from(Instant.parse("2022-05-14T11:00:00Z"));
        File file1 = new File("cs3500Notes/array.md");
        File file2= new File("cs3500Notes/vectors.md");
        File file3= new File("cs3500Notes/time.md");


        try {
            Files.setAttribute(file1.toPath(), "creationTime", knownCreationTime);
            Files.setAttribute(file2.toPath(), "creationTime", knownCreationTime2);
            Files.setAttribute(file3.toPath(), "creationTime", knownCreationTime3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CreationTimeComparator comparator = new CreationTimeComparator();
        assertTrue(comparator.compare(file2, file3) > 0);
        assertTrue(comparator.compare(file1, file2) < 0);
        assertTrue(comparator.compare(file2, file2) == 0);
    }
}


