package cs3500.pa02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * The SrFileWriter class provides methods for writing contents to an .sr file.
 */
public class SrFileWriter {
  /**
   * Writes the contents to the specified .sr file.
   *
   * @param file     The file to write the contents to.
   * @param contents The contents to write to the file.
   * @throws RuntimeException If an error occurs while writing to the file.
   */
  public FileWriter writeSrFile(File file, ArrayList<String> contents) {
    String filePath = file.getPath();
    if (!filePath.toLowerCase().endsWith(".sr")) {
      filePath += ".sr";
    }

    Path path = Paths.get(filePath);

    try {
      FileWriter fileWriter = new FileWriter(filePath);

      for (String str : contents) {
        if (str.contains(":::")) {
          fileWriter.write(str);
          fileWriter.write(System.lineSeparator());
        }
      }

      fileWriter.close();
      return fileWriter;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
