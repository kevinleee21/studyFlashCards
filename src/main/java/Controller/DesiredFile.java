package Controller;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The DesiredFile class provides methods for reading the desired file path from an input source.
 */
public class DesiredFile{
  private final Readable input;
  private Scanner sc;
  public DesiredFile(Readable input, Scanner scan) {
    this.input = input;
    this.sc = scan;
  }
  /**
   * Reads the desired file path from the input source.
   *
   * @return The File object representing the desired file.
   * @throws NoSuchElementException If no file input is found.
   */
  public File readFile() throws NoSuchElementException, IOException {
    String filePath = sc.next();
    File file = new File(filePath);

    if (!file.isFile()) {
      throw new IOException("Invalid file path. Please enter a filepath");
    }
    return file;
  }

}
