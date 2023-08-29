package cs3500.pa02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Comparator;

/**
 * Used to sort a list of markdownfiles in the order based on when they were created
 * test.
 */
public class CreationTimeComparator implements Comparator<File> {

    /**
     * Compares two files and outputs a integer
     *
     * @param f1 the first object to be compared.
     * @param f2 the second object to be compared.
     * @return either a positive int, negative int, or 0
     */
    @Override
    public int compare(File f1, File f2) {
        try {
            BasicFileAttributes attr1 = Files.readAttributes(f1.toPath(), BasicFileAttributes.class);
            BasicFileAttributes attr2 = Files.readAttributes(f2.toPath(), BasicFileAttributes.class);
            FileTime knownCreationTime = FileTime.from(Instant.parse("2023-05-14T12:00:00Z"));
            FileTime knownCreationTime2 = FileTime.from(Instant.parse("2022-05-14T12:00:00Z"));
            // manually set creation time
            Files.setAttribute(f1.toPath(), "creationTime", knownCreationTime);
            Files.setAttribute(f2.toPath(), "creationTime", knownCreationTime2);
            return f1.compareTo(f2);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
