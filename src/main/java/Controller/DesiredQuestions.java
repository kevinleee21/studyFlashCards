package Controller;


import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * the number of questions the user wants to be asked
 */
public class DesiredQuestions{
  private final Readable readable;
  private Scanner scanner;
  public DesiredQuestions(Readable readable, Scanner scan) {
    this.readable = readable;
    this.scanner = scan;
  }

  /**
   * reads the number that the user types in
   * @return that number the user typed in and throws the exception when
   * it isnt a number
   */
  public int readNumber() {
      return scanner.nextInt();
  }
}
