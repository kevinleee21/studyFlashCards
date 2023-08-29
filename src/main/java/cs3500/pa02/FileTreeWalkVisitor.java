package cs3500.pa02;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Traverse through the directory and output a list of markdown files that will be sorted
 * for the study guide.sasa.
 */
public class FileTreeWalkVisitor implements FileVisitor<Path> {
    ArrayList<File> validFiles = new ArrayList<>();

    /**
     * Invoked before visiting a directory.
     *
     * @param dir   The directory being visited
     * @param attrs The basic attributes of the directory
     * @return The visit result indicating the desired action for the directory
     * @throws IOException If an I/O error occurs
     */
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.format("Starting Directory: %s%n", dir);
        return CONTINUE;
    }

    /**
     * Invoked for a file visit.
     *
     * @param file  The file being visited
     * @param attrs The basic attributes of the file
     * @return The visit result indicating the desired action for the file
     * @throws IOException If an I/O error occurs
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.getFileName().toString();
        //fileAttr(file, attrs);
        if (fileName.endsWith(".md")) {
            validFiles.add(file.toFile());
        }
        return CONTINUE;
    }

    /**
     * Invoked when a file visit fails.
     *
     * @param file The file that failed to be visited
     * @param exc  The exception that occurred during the visit
     * @return The visit result indicating the desired action when a visit fails
     * @throws IOException If an I/O error occurs
     */
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.err.println(exc);
        return CONTINUE;
    }

    /**
     * Invoked after visiting a directory.
     *
     * @param dir The directory being visited
     * @param exc The exception that occurred during the visit, or null if no exception occurred
     * @return The visit result indicating the desired action after visiting a directory
     * @throws IOException If an I/O error occurs
     */
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        System.out.format("Finishing Directory: %s%n", dir);
        return CONTINUE;
    }

    /**
     * Returns the list of valid files, which consists of a list of markdown files.
     *
     * @return The list of valid files
     */
    ArrayList<File> getValidFiles() {
        return validFiles;
    }
}

