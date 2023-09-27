package cs3500.pa02;
import Controller.FlashcardController;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * serves as the entry point for the application and coordinates the generation
 * of a study guide from markdown files based on command-line arguments..
 */
public class Driver {
    /**
     *
     * @param args three command line arguements, file directory, ordering flag, and output path
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // if there are no command line arguements then run thd FlashCard application
        if (args.length == 0) {
            FlashcardController controller = new FlashcardController(new InputStreamReader(System.in), System.out);
            controller.run();

        } else if (args.length == 3) {
            // if there are 3 command line args with a
            // notesRootPath, orderingFlag, outputFilePath then run the Markdown File application
            String notesRootPath = args[0];
            String orderingFlag = args[1];
            String outputFilePath = args[2];

            FileController controllerQanA =
                    new FileController(notesRootPath, orderingFlag, outputFilePath);

            controllerQanA.run();
        }
    }
}