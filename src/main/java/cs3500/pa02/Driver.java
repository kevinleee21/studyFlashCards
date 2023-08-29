package cs3500.pa02;

import Controller.ControllerImp;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

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
            Controller controller = new ControllerImp(new InputStreamReader(System.in), System.out);
            controller.run();

        } else if (args.length == 3) {
            String notesRootPath = args[0];
            String orderingFlag = args[1];
            String outputFilePath = args[2];

            Path directory = Path.of(notesRootPath);
            FileTreeWalkVisitor traverse = new FileTreeWalkVisitor();
            Files.walkFileTree(directory, traverse);
            ArrayList<File> markdownFiles = traverse.getValidFiles();

            if (markdownFiles.isEmpty()) {
                // for pushing
                throw new IllegalArgumentException("Directory cannot be empty");
            }

            /**
             * determine which comparater to use based on
             * what is typed in the second command line
             */
            if (orderingFlag.equals("filename")) {
                Collections.sort(markdownFiles, new FileNameComparator());
            } else if (orderingFlag.equals("create")) {
                Collections.sort(markdownFiles, new CreationTimeComparator());
            } else if (orderingFlag.equals("modified")) {
                Collections.sort(markdownFiles, new ModifiedComparator());
            } else {
                throw new IllegalArgumentException();
            }

            /**
             * Write out the study guide and the question bank
             */
            ArrayList<String> organized = StudyGuide.convertFile(markdownFiles);
            ArrayList<String> bank = SrFileReader.convertFile(markdownFiles);
            System.out.println(organized);
            File outputFile = new File(outputFilePath);
            MdFileWriter outputMd = new MdFileWriter();
            SrFileWriter outputSr = new SrFileWriter();
            outputMd.writeFile(outputFile, organized);
            outputSr.writeSrFile(outputFile, bank);

        }
    }
}