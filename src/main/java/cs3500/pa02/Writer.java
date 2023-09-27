package cs3500.pa02;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * The Writer abstract class defines a common interface for classes that
 * write content to a file. Subclasses should implement the writeFile
 * method to specify the file-writing behavior.
 */
abstract class Writer {
    /**
     * Writes the provided content to the specified file.
     *
     * @param file     the target file where content will be written.
     * @param content  an Arraylist of strings representing the content to be written.
     * @return a FileWriter object associated with the written file.
     */
    public abstract FileWriter writeFile(File file, ArrayList<String> content) ;
}
