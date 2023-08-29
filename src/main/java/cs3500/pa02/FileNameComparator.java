package cs3500.pa02;

import java.io.File;
import java.util.Comparator;

/**
 * Used to sort a list of markdownfiles in the order of alphabetical order of their file names.
 */
public class FileNameComparator implements Comparator<File> {

    /**
     *
     * @param f1 the first object to be compared.
     * @param f2 the second object to be compared.
     * @return either a positive int, negative int, or 0
     */
    @Override
    public int compare(File f1, File f2) {
        return f1.compareTo(f2);
    }
}
