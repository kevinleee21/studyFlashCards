package cs3500.pa02;
import Controller.QnAFlashcardController;
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
        if (args.length == 0) {
            QnAFlashcardController controller = new QnAFlashcardController(new InputStreamReader(System.in), System.out);
            controller.run();

        } else if (args.length == 3) {

            String notesRootPath = args[0];
            String orderingFlag = args[1];
            String outputFilePath = args[2];

            MarkdownSRFileController controllerQanA =
                    new MarkdownSRFileController(notesRootPath, orderingFlag, outputFilePath);

            controllerQanA.run();
        }
    }
}