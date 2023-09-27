package cs3500.pa02;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents an abstract class that focuses on reading files,
 * more specifically Markdown and Sr files
 */
abstract class Reader {
    /**
     * Converts a list of sorted files into an organized guide
     * represented as a list of Strings.
     * @param sortedFiles The list of sorted files.
     * @return An ArrayList of Strings representing the organized guide.
     */
    public abstract ArrayList<String> convertFiles(ArrayList<File> sortedFiles);

    /**
     * when bracketed statement takes up more than one line in notes
     * @param line scanner read text line
     * @param bracketedStatement
     * @param scanner string builder for final statement
     * @param organizedGuide scan the file
     */
    public abstract void finishLine(String line, StringBuilder bracketedStatement, Scanner scanner, ArrayList<String> organizedGuide);
}
