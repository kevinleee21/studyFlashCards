package Model;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * The QuestionSortComparator class compares strings representing questions based on their difficulty level.
 */
public class QuestionSortComparator implements Comparator<String> {

  /**
   * if question1 is harder and should come before question2 return -1
   * if question2 is harder and should come before question1 return 1
   * if both questions have the same difficulty level return 0
   */
  @Override
  public int compare(String question1, String question2) {
    boolean isHard1 = question1.contains("***Hard");
    boolean isHard2 = question2.contains("***Hard");
    if (isHard1 && !isHard2) {
      return -1;
    } else if (!isHard1 && isHard2) {
      return 1;
    } else {
      return 0;
    }
  }
}
