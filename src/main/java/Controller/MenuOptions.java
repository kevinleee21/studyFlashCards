package Controller;
import java.util.Scanner;

/**
 * The MenuOptions class provides methods for reading and interpreting user menu options.
 */
public class MenuOptions {
  private Readable input;
  private Scanner scanner;
  public MenuOptions(Readable input, Scanner scan) {
    this.input = input;
    this.scanner = scan;
  }
  /**
   * Reads and interprets the user-selected option from the input source.
   *
   * @return The corresponding Options value representing the user-selected option.
   * @throws IllegalArgumentException If an invalid option is entered.
   */
  public Options readOption() {
      String command = scanner.next();
      if (command.equals("hard")) {
        return Options.HARD;
      } else if (command.equals("easy")) {
        return Options.EASY;
      } else if (command.equals("show")) {
        return Options.SHOW;
      } else if (command.equals("exit")) {
        return Options.EXIT;
      }
    throw new IllegalArgumentException("Please enter one of the 4 options!");
  }
}

