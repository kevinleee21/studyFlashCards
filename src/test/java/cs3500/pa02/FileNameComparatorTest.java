package cs3500.pa02;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This tests the filename comparator
 */

class FileNameComparatorTest {
    @Test
    public void testFileNameCompare() {
        File file1 = new File("sample_files/arrays.md");
        File file2= new File("sample_files/vectors.md");
        File file3= new File("hashcode.md");
        File file4= new File("hashcode.md");

        FileNameComparator comparator = new FileNameComparator();
        assertTrue(comparator.compare(file1, file2) < 0);
        assertTrue(comparator.compare(file4, file3) == 0);
        assertTrue(comparator.compare(file2, file3) > 0);
        assertTrue(comparator.compare(file1, file3) > 0);
    }
}
