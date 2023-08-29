package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class QuestionSortComparatorTest {

  @Test
  public void testCompare_HardComesBeforeNonHard_ReturnsNegative() {
    QuestionSortComparator comparator = new QuestionSortComparator();
    String question1 = "Which city is located on two continents?::: Istanbul, Turkey. It is located on both Europe and Asia.***Hard";
    String question2 = "Which continent is known as the Roof of the World?:::Asia.***Easy";
    int result = comparator.compare(question1, question2);
    assertEquals(-1, result);
  }

  @Test
  public void testCompare_NonHardComesBeforeHard_ReturnsPositive() {
    QuestionSortComparator comparator = new QuestionSortComparator();
    String question1 = "Which continent is known as the Roof of the World?:::Asia.***Easy";
    String question2 = "Which city is located on two continents?::: Istanbul, Turkey. It is located on both Europe and Asia.***Hard";
    int result = comparator.compare(question1, question2);
    assertEquals(1, result);
  }

  @Test
  public void testCompare_SameDifficulty_ReturnsZero() {
    QuestionSortComparator comparator = new QuestionSortComparator();
    String question1 = "What is the largest lake in Africa? ::: The largest lake is Lake Victoria.***Easy";
    String question2 = "Which continent is known as the Roof of the World?:::Asia.***Easy";
    int result = comparator.compare(question1, question2);
    assertEquals(0, result);
  }
}