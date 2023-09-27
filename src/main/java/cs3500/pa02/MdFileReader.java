package cs3500.pa02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a student's study-guide
 */
public class MdFileReader extends Reader {

    /**
     * Organizes a list of student notes and makes sure the headings are properly ordered and the phrases inside
     * double brackets are properly nested in each heading
     *
     * @param sortedFiles all markdown files sorted in the preferred order
     * @return a list of these files with their headings and highlighted organized
     */
    @Override
    public ArrayList<String> convertFiles(ArrayList<File> sortedFiles) {
        ArrayList<String> organizedGuide = new ArrayList<>();

        for (File file : sortedFiles) {
            try {
                // read the file
                Scanner scanner = new Scanner(file);
                StringBuilder bracketedStatement = new StringBuilder();
                while (scanner.hasNextLine()) {
                    // text read from file
                    String line = scanner.nextLine();
                    // check if the text has property for studyguide i.e. [[, ]], :::
                    if (line.contains("[[") && line.contains("]]") && !line.contains(":::")) {
                        organizedGuide.add("- " + (line.substring(line.indexOf("[[") + 2, line.indexOf("]]"))));
                    } else if (line.contains("[[") && !line.contains("]]") && !line.contains(":::")) {
                        finishLine(line, bracketedStatement, scanner, organizedGuide);
                    } else if (line.startsWith("#")) {
                        organizedGuide.add(line);
                    }
                }

                scanner.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return organizedGuide;
    }

    /**
     * when bracketed statement takes up more than one line in notes
     * @param line scanner read text line
     * @param bracketedStatement string builder for final statement
     * @param scanner scan the file
     */
    @Override
    public void finishLine(String line, StringBuilder bracketedStatement, Scanner scanner, ArrayList<String> organizedGuide) {
        bracketedStatement.append("- ").append(line.substring(line.indexOf("[[") + 2));
        boolean endLine = false;
        while (scanner.hasNextLine() && !endLine) {
            line = scanner.nextLine();
            if (line.contains("]]") && !line.contains(":::")) {
                bracketedStatement.append(line, 0, line.indexOf("]]"));
                endLine = true;
            } else if (line.contains("]]") && line.contains(":::")) {
                // clear the text as it doesn't belong in .md file
                bracketedStatement.setLength(0);
            } else {
                bracketedStatement.append(line);
            }
        }
        organizedGuide.add(bracketedStatement.toString().trim());
        bracketedStatement.setLength(0);
    }
}
