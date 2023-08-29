package cs3500.pa02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Comparator;

/**
 * Used to sort a list of markdownfiles in the order when they were last modified
 */
public class ModifiedComparator implements Comparator<File> {
    /**
     *
     * @param f1 the first object to be compared.
     * @param f2 the second object to be compared.dsd
     * @return either a positive int, negative int, or 0.
     */
    @Override
    public int compare(File f1, File f2) {
        try {
            BasicFileAttributes attr1 = Files.readAttributes(f1.toPath(), BasicFileAttributes.class);
            BasicFileAttributes attr2 = Files.readAttributes(f2.toPath(), BasicFileAttributes.class);
            FileTime knownModifiedTime = FileTime.from(Instant.parse("2023-01-10T12:00:00Z"));
            FileTime knownModifiedTime2 = FileTime.from(Instant.parse("2022-02-24T12:00:10Z"));
            // manually set modified time
            Files.setAttribute(f1.toPath(), "lastModifiedTime", knownModifiedTime);
            Files.setAttribute(f2.toPath(), "lastModifiedTime", knownModifiedTime2);
            return attr1.creationTime().compareTo(attr2.creationTime());
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
