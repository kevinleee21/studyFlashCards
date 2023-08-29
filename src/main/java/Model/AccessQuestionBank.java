package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * The AccessQuestionBank class provides methods for accessing and manipulating question banks.
 */
public class AccessQuestionBank {
  /**
   * Randomizes the content of the specified file and returns it as a list of strings.
   *
   * @param file The file containing the questions to be randomized.
   * @return A randomized list of strings representing the questions from the file.
   * @throws FileNotFoundException If the specified file is not found.
   */
  public static ArrayList<String> randomize(File file) throws FileNotFoundException {
    Scanner scanner = new Scanner(file);
    ArrayList<String> output = new ArrayList<>();
    while(scanner.hasNext()) {
      String line = scanner.nextLine();
      output.add(line);
    }
    Collections.shuffle(output);
    return output;
  }
}
