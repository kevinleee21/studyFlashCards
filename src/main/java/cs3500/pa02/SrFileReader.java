package cs3500.pa02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The SrFileReader class provides methods for reading and converting .sr files into an organized guide.
 */
public class SrFileReader extends Reader {
  /**
   * Converts a list of sorted .sr files into an organized guide
   * represented as a list of Strings.
   *
   * @param sortedFiles The list of sorted .sr files.
   * @return An ArrayList of Strings representing the organized guide.
   */
  @Override
  public ArrayList<String> convertFiles(ArrayList<File> sortedFiles) {
    ArrayList<String> organizedGuide = new ArrayList<>();

    for (File file: sortedFiles) {
      try {
        Scanner scanner = new Scanner(file);
        StringBuilder bracketedStatement = new StringBuilder();
        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          if (line.contains("[[") && line.contains(":::") && line.contains("]]")) {
            organizedGuide.add("- " + (line.substring(line.indexOf("[[") + 2, line.indexOf("]]"))));
          } else if(line.contains("[[") && !line.contains("]]")) {
            finishLine(line, bracketedStatement, scanner, organizedGuide);
          }
        }
        scanner.close();
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    } return organizedGuide;
  }

  /**
   * when bracketed statement takes up more than one line in notes
   * @param line scanner read text line
   * @param bracketedStatement
   * @param scanner string builder for final statement
   * @param organizedGuide scan the file
   */
  @Override
  public void finishLine(String line, StringBuilder bracketedStatement, Scanner scanner, ArrayList<String> organizedGuide) {
    bracketedStatement.append("- ").append(line.substring(line.indexOf("[[") + 2));
    boolean endline = false;
    while (scanner.hasNextLine() && !endline) {
      line = scanner.nextLine();
      if (line.contains("]]") && line.contains(":::")) {
        bracketedStatement.append(line.substring(0, line.indexOf("]]")));
        endline = true;
      } else if (!line.contains(":::") && line.contains("]]")){
        bracketedStatement.setLength(0);
        return;
      } else {
        bracketedStatement.append(line);
      }
    }
    organizedGuide.add(bracketedStatement.toString().trim());
    bracketedStatement.setLength(0);
  }
}
