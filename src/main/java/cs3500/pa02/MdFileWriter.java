package cs3500.pa02;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Write out the contents of the organized study guide.
 */
public class MdFileWriter extends Writer {

    /**
     * writes everything to a file path in order to create the final studyguide with everything we want
     *
     * @param file the file that the content will be written to
     * @param contents a list of all the contents that needs to be written to the output file
     */
    @Override
    public FileWriter writeFile(File file, ArrayList<String> contents) {
        String filePath = file.getPath();
        if (!filePath.toLowerCase().endsWith(".md")) {
            filePath += ".md";
        }
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (String str : contents) {
                if (str.startsWith("#")) {
                    fileWriter.append(System.lineSeparator());
                } else {
                    fileWriter.append(str).append(System.lineSeparator());
                }
            }

            fileWriter.close();
            return fileWriter;
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
