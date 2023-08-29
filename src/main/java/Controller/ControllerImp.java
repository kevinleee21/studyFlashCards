package Controller;

import Model.AccessQuestionBank;
import Model.QuestionSortComparator;
import Model.UpdatedSrFile;
import View.Result;
import cs3500.pa02.Controller;
import cs3500.pa02.SrFileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class ControllerImp implements Controller {
  private Readable input;
  private Appendable output;
  private Scanner scan;

  public ControllerImp(Readable input, Appendable output) {
    this.input = Objects.requireNonNull(input);
    this.output = Objects.requireNonNull(output);
    this.scan = new Scanner(input);
  }
  @Override
  public void run() {
    try {
      File desiredFile = readDesiredFile();
      ArrayList<String> sorted = AccessQuestionBank.randomize(desiredFile);
      if (desiredFile.isFile()) {
        sorted.sort(new QuestionSortComparator());
      }

      int numberofQues = readNumberofQuestions();
      MenuOptions options = new MenuOptions(this.input, this.scan);
      UpdatedSrFile updated = new UpdatedSrFile();

      if (numberofQues > 0) {
        for (int i = 0; i < numberofQues && i < sorted.size(); i++) {
          String question = sorted.get(i).substring(0, sorted.get(i).indexOf("?"));
          String answer = sorted.get(i).substring(sorted.get(i).lastIndexOf(":::") + ":::".length(), sorted.get(i).lastIndexOf("***"));
          String menu = "Please enter one of the 4 options: hard - easy - show - exit";
          output.append(question).append(System.lineSeparator());
          output.append(menu).append(System.lineSeparator());
          Options userOption = options.readOption();
          handleUserOption(userOption, sorted, i, answer, updated, desiredFile);
        }
        Result.displayFinal(output, numberofQues, UpdatedSrFile.hardToeasy, UpdatedSrFile.easyTohard, sorted);
        SrFileWriter writer = new SrFileWriter();
        writer.writeSrFile(desiredFile, sorted);
      }
    } catch (IOException e) {
      System.err.println("An error occurred while processing the input/output.");
      e.printStackTrace();
    } catch (InputMismatchException e) {
      System.err.println("Invalid number input. Please provide a valid number.");
      e.printStackTrace();
    } catch (NoSuchElementException e) {
      System.err.println("Invalid input. Please provide valid input.");
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      System.err.println("Invalid arguement.Please re-read the directions.");
      e.printStackTrace();
    }
  }
  private File readDesiredFile() throws IOException {
    DesiredFile fileInput = new DesiredFile(this.input, this.scan);
    output.append("Hello, please start the study session by typing in the filepath to the .sr file").append(System.lineSeparator());
    File desiredFile;
    try {
      desiredFile = fileInput.readFile();
    } catch (NoSuchElementException e) {
      throw new IllegalArgumentException("Invalid file input. Please provide a valid file path.");
    }
    return desiredFile;
  }

  private int readNumberofQuestions() throws IOException {
    DesiredQuestions numberInput = new DesiredQuestions(this.input, this.scan);
    output.append("Please enter the number of questions you want to be asked!").append(System.lineSeparator());
    int numberofQues;
    try {
      numberofQues = numberInput.readNumber();
    } catch (InputMismatchException e) {
      throw new IllegalArgumentException("Invalid number input. Please provide a valid number.");
    }
    return numberofQues;
  }

  private void handleUserOption(Options userOption, ArrayList<String> sorted, int index, String answer, UpdatedSrFile updated, File desiredFile) throws IOException {
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
        Result.displayFinal(output, index, UpdatedSrFile.hardToeasy, UpdatedSrFile.easyTohard, sorted);
        writer.writeSrFile(desiredFile, sorted);
        break;
      default:
        throw new IllegalArgumentException("Invalid menu option. Please choose a valid option.");
    }
  }
}

