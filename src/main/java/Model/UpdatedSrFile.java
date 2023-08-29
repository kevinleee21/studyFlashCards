package Model;
import java.util.ArrayList;

/**
 * The UpdatedSrFile class manages the updating of question difficulty in the study session.
 * It keeps track of the number of conversions from hard to easy and from easy to hard.
 */
public class UpdatedSrFile {
  /**
   * Counter for the number of conversions from hard to easy.
   */
  private int hardToeasy;
  /**
   * Counter for the number of conversions from easy to hard.
   */
  private int easyTohard;

  public UpdatedSrFile() {
    this.hardToeasy = 0;
    this.easyTohard = 0;
  }

  /**
   * Updates the difficulty of a question line based on the provided input.
   * If the line is marked as "***Hard" and the input is "easy", it converts the line to "***Easy"
   * and increments the hardToeasy counter.
   * If the line is marked as "***Easy" and the input is "hard" or "show", it converts the line to "***Hard"
   * and increments the easyTohard counter.
   *
   * @param line  the question line to update
   * @param input the user input indicating the desired difficulty change
   */
  public void updateDifficulty(ArrayList<String> sorted, String line, String input) {
    int index = sorted.indexOf(line); // Get the index of the line in the list
    if (index >= 0) {
      if (line.contains("***Hard") && input.equals("easy")) {
        sorted.set(index, line.replace("***Hard", "***Easy"));
        hardToeasy++;
      } else if (line.contains("***Easy") && (input.equals("hard") || input.equals("show"))) {
        sorted.set(index, line.replace("***Easy", "***Hard"));
        easyTohard++;
      }
    }
  }

  public int getEasyTohard() {
    return easyTohard;
  }

  public int getHardToeasy() {
    return hardToeasy;
  }
}
