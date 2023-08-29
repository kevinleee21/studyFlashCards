package cs3500.pa02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a student's study-guide
 */
public class StudyGuide {
    /**
     * initializes the list of strings that will later have all the organized string contents from
     * each markdown file.
     */
    private ArrayList<String> organizedGuide;

    StudyGuide() {
        this.organizedGuide = new ArrayList<>();
    }

    /**
     * Organizes a list of student notes and makes sure the headings are properly ordered and the phrases inside
     * double brackets are properly nested in each heading
     *
     * @param sortedFiles all markdown files sorted in the preferred order
     * @return a list of these files with their headings and highlighted organized
     */
    public static ArrayList<String> convertFile(ArrayList<File> sortedFiles) {
        ArrayList<String> organizedGuide = new ArrayList<>();

        for (File file : sortedFiles) {
            try {
                Scanner scanner = new Scanner(file);
                StringBuilder bracketedStatement = new StringBuilder();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.contains("[[") && line.contains("]]") && !line.contains(":::")) {
                        organizedGuide.add("- " + (line.substring(line.indexOf("[[") + 2, line.indexOf("]]"))));
                    } else if (line.contains("[[") && !line.contains("]]") && !line.contains(":::")) {
                        bracketedStatement.append("- ").append(line.substring(line.indexOf("[[") + 2));
                        boolean endLine = false;
                        while (scanner.hasNextLine() && !endLine) {
                            line = scanner.nextLine();
                            if (line.contains("]]") && !line.contains(":::")) {
                                bracketedStatement.append("- ").append(line, 0, line.indexOf("]]"));
                                endLine = true;
                            } else if (line.contains("]]") && line.contains(":::")){
                                bracketedStatement.setLength(0);
                            } else {
                                bracketedStatement.append("- ").append(line);
                            }
                        }
                        organizedGuide.add(bracketedStatement.toString().trim());
                        bracketedStatement.setLength(0);
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
}
