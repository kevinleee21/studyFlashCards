package View;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The Result class provides methods for displaying the final results of a study session.
 */
public class Result {
  /**
   * Displays the final results of the study session.
   *
   * @param answered The number of questions answered.
   * @param hard     The number of questions that went from easy to hard.
   * @param easy     The number of questions that went from hard to easy.
   * @param sorted   The sorted list of questions.
   */
  public static void displayFinal(Appendable output, int answered, int hard, int easy, ArrayList<String> sorted)
          throws IOException {
    String ln = System.lineSeparator();
    output.append("Good Job!").append(ln);
    output.append("You answered ").append(String.valueOf(answered)).append(" questions").append(ln);
    output.append(String.valueOf(hard)).append(" questions went from hard to easy").append(ln);
    output.append(String.valueOf(easy)).append(" questions went from easy to hard").append(ln);
    output.append(String.valueOf(easyCount(sorted))).append(" Easy Questions").append(ln);
    output.append(String.valueOf(hardCount(sorted))).append(" Hard Questions").append(ln);
  }


  /**
   * Counts the number of easy questions in the sorted list.
   *
   * @param sorted The sorted list of questions.
   * @return The number of easy questions.
   */
  static int easyCount(ArrayList<String> sorted) {
    int count = 0;
    for (String s : sorted) {
      if (s.contains("***Easy")) {
        count++;
      }
    }
    return count;
  }

  /**
   * Counts the number of hard questions in the sorted list.
   *
   * @param sorted The sorted list of questions.
   * @return The number of hard questions.
   */
  static int hardCount(ArrayList<String> sorted) {
    int count = 0;
    for (String s : sorted) {
      if (s.contains("***Hard")) {
        count++;
      }
    }
    return count;
  }
}
