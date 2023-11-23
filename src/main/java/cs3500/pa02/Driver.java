package cs3500.pa02;
import Controller.FlashcardController;
import Controller.Scene1Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStreamReader;


/**
 * serves as the entry point for the application and coordinates the generation
 * of a study guide from markdown files based on command-line arguments..
 */
public class Driver extends Application {
    /**
     *
     * @param args three command line arguements, file directory, ordering flag, and output path
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // if there are no command line arguements then run thd FlashCard application
        if (args.length == 0) {
//            FlashcardController controller = new FlashcardController(new InputStreamReader(System.in), System.out);
//            controller.run();
            launch();

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

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            // Load the initial scene (associated with Scene1Controller)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ankilite.fxml"));
            Parent root = loader.load();
            Scene1Controller scene1Controller = loader.getController();

            // Set up the primary stage
            primaryStage.setTitle("Flashcard Application");
            primaryStage.setScene(new Scene(root));

            // Show the primary stage
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
