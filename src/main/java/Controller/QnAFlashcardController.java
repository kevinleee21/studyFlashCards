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

public class QnAFlashcardController implements Controller {
  private Readable input;
  private Appendable output;
  private Scanner scan;

  public QnAFlashcardController(Readable input, Appendable output) {
    this.input = Objects.requireNonNull(input);
    this.output = Objects.requireNonNull(output);
    this.scan = new Scanner(input);
  }

  /**
   * this method is way too big, cut down and try to make more efficient
   */
  @Override
  public void run() throws IOException{
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
      writer.writeSrFile(desiredFile, sorted);
    }
  }

  private ArrayList<String> randomize(File file) {
    boolean validFile = false;
    ArrayList<String> output = new ArrayList<>();
    Scanner scanner;
    // continue loop until a user types in a valid file
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
        // randomize the list
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

  private File readDesiredFile() throws IOException {
    output.append("Hello, please start the study session " +
            "by typing in the filepath to the .sr file").append(System.lineSeparator());
    String filePath = scan.next();
    return new File(filePath);
  }

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
        writer.writeSrFile(desiredFile, sorted);
        System.exit(0);
        break;
      default:
        throw new IllegalArgumentException("Invalid menu option. Please choose a valid option.");
    }
  }

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

