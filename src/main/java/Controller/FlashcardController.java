package Controller;
import Model.QuestionSortComparator;
import Model.UpdatedSrFile;
import View.Result;
import cs3500.pa02.SrFileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represent the flow of the application when the user decides to
 * study with their flashcards by spaced repetition
 */
public class FlashcardController implements Controller {
  private Readable input;
  private Appendable output;
  private Scanner scan;

  public FlashcardController(Readable input, Appendable output) {
    this.input = Objects.requireNonNull(input);
    this.output = Objects.requireNonNull(output);
    this.scan = new Scanner(input);
  }

  /**
   * Represents the flow of the flashcard component of the application, managing user input
   * and updating both the model and view aspects accordingly.
   * @throws IOException IOException if an I/O error occurs during execution.
   */
  @Override
  public void run() throws IOException {
    File desiredFile = readDesiredFile();
    ArrayList<String> sorted = randomize(desiredFile);
    sorted.sort(new QuestionSortComparator());
    int numberofQues = readNumberofQuestions(sorted);
    UpdatedSrFile updated = new UpdatedSrFile();

    if (numberofQues > 0) {
      for (int i = 0; i < numberofQues && i < sorted.size(); i++) {
        String question = sorted.get(i).substring(0, sorted.get(i).indexOf("?"));
        String answer = sorted.get(i).substring(sorted.get(i).lastIndexOf(":::")
                + ":::".length(), sorted.get(i).lastIndexOf("***"));
        String menu = "Please enter one of the 4 options: hard - easy - show - exit";
        output.append(question).append(System.lineSeparator());
        output.append(menu).append(System.lineSeparator());
        handleUserOption(readOption(), sorted, i, answer, updated, desiredFile);
      }
      Result.displayFinal(output, numberofQues, updated.getHardToeasy(), updated.getEasyTohard(), sorted);
      SrFileWriter writer = new SrFileWriter();
      writer.writeFile(desiredFile, sorted);
    }
  }

  /**
   * Converts the question bank lines from the Sr File to Strings and randomizes them
   * @param file Sr file that contains the questions, answers, and difficulty
   * @return an randomized ArrayList of Strings where a single string
   * represents a question, answer, and difficulty
   */
  private ArrayList<String> randomize(File file) {
    boolean validFile = false;
    ArrayList<String> output = new ArrayList<>();
    Scanner scanner;
    /**
     * continue loop until a user types in a valid file
     */
    while (!validFile) {
      try {
        // read the file
        scanner = new Scanner(file);
        // process of creating list of string from the file
        while(scanner.hasNext()) {
          String line = scanner.nextLine();
          output.add(line);
        }
        validFile = true;
        // randomizes the list
        Collections.shuffle(output);
      } catch(FileNotFoundException e) {
        System.out.println("Not a file. Try again!");
        Scanner inputScanner = new Scanner(System.in);
        String newPath = inputScanner.nextLine();
        file = new File(newPath);
      }
    }
    return output;
  }

  /**
   * Reads the file/path from the user's input
   * @return the valid file from an existing directoru
   * @throws IOException IOException if an I/O error occurs during execution.
   */
  private File readDesiredFile() throws IOException {
    output.append("Hello, please start the study session " +
            "by typing in the filepath to the .sr file").append(System.lineSeparator());
    String filePath = scan.next();
    return new File(filePath);
  }

  /**
   * Reads the number of questions from the user's input
   * @param sorted the all Q&A blocks in the form of a string, all in a list
   * @return an integer representing the number of questions to use.
   * @throws IOException
   */
  private int readNumberofQuestions(ArrayList<String> sorted) throws IOException {
    output.append("Please enter the number of questions you want to be asked!").append(System.lineSeparator());
    int numbofQues = sorted.size();
    int desiredQues = readNumber();

    if (desiredQues > numbofQues) {
      return numbofQues;
    } else {
      return desiredQues;
    }
  }

  /**
   * Reads the number that the user types in
   * @return the number that the user typed
   */
  private int readNumber() {
    int value = 0;
    boolean isValid = false;

    while(!isValid) {
      try {
        value = scan.nextInt();
        isValid = true;

      } catch (InputMismatchException e) {
        System.out.println("Invalid number input. Please provide a valid number.");
        scan.next();
      }
    }
    return value;
  }


  /**
   * Updates the difficulty of questions in the Sr File and results of the flashcard application
   * from the user's input
   * @param userOption all the possible options that the user can select
   * @param sorted the question bank
   * @param index which question the user is at in the question bank
   * @param answer the answer to the question
   * @param updated the question bank being updated from the user's response
   * @param desiredFile the original file that contains all the Q&A block, before being
   * converted to an Arraylist of Strings
   * @throws IOException
   */
  private void handleUserOption(Options userOption, ArrayList<String> sorted,
                                int index, String answer, UpdatedSrFile updated, File desiredFile) throws IOException {
    SrFileWriter writer = new SrFileWriter();
    switch (userOption) {
      case HARD:
        updated.updateDifficulty(sorted, sorted.get(index), "hard");
        break;
      case EASY:
        updated.updateDifficulty(sorted, sorted.get(index), "easy");
        break;
      case SHOW:
        updated.updateDifficulty(sorted, sorted.get(index), "show");
        output.append(answer).append(System.lineSeparator());
        break;
      case EXIT:
        Result.displayFinal(output, index, updated.getHardToeasy(), updated.getEasyTohard(), sorted);
        writer.writeFile(desiredFile, sorted);
        System.exit(0);
        break;
      default:
        throw new IllegalArgumentException("Invalid menu option. Please choose a valid option.");
    }
  }

  /**
   * Reads the option that the user typed in
   * @return the option that the user typed in
   * enum form
   */
  private Options readOption() {
    String command = scan.next();
    if (command.equalsIgnoreCase("hard")) {
      return Options.HARD;
    } else if (command.equalsIgnoreCase("easy")) {
      return Options.EASY;
    } else if (command.equalsIgnoreCase("show")) {
      return Options.SHOW;
    } else if (command.equalsIgnoreCase("exit")) {
      return Options.EXIT;
    } else {
      System.out.println("Invalid option. Please provide a valid option.");
      return readOption();
    }
  }
}

