package cs3500.pa02;


import Controller.Controller;
import Model.CreationTimeComparator;
import Model.FileNameComparator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Represents the creation of the Markdown and SR files
 * after the user selected a specific notesRootPath, orderingFlag, and outputFilePath
 *
 */
public class FileController implements Controller {

    private String rootPath;
    private String orderingFlag;
    private String outputFilePath;

    public FileController(String rootPath, String orderingFlag, String outputFilePath) {
        this.rootPath = rootPath;
        this.orderingFlag = orderingFlag;
        this.outputFilePath = outputFilePath;
    }

    /**
     *
     * @throws IOException
     */
    @Override
    public void run() throws IOException {
        Path directory = Path.of(rootPath);
        FileTreeWalkVisitor traverse = new FileTreeWalkVisitor();
        Files.walkFileTree(directory, traverse);
        ArrayList<File> markdownFiles = traverse.getValidFiles();

        if (markdownFiles.isEmpty()) {
            throw new IllegalArgumentException("Directory cannot be empty");
        }

        /**
         * determine which comparater to use based on
         * what is typed in the second command line
         */
        if (orderingFlag.equals("filename")) {
            markdownFiles.sort(new FileNameComparator());
        } else if (orderingFlag.equals("create")) {
            markdownFiles.sort(new CreationTimeComparator());
        } else if (orderingFlag.equals("modified")) {
            markdownFiles.sort(new ModifiedComparator());
        } else {
            throw new IllegalArgumentException();
        }

        /**
         * Write out the study guide and the question bank
         */
        MdFileReader md = new MdFileReader();
        SrFileReader sr = new SrFileReader();
        ArrayList<String> organized = md.convertFiles(markdownFiles);
        ArrayList<String> bank = sr.convertFiles(markdownFiles);
        File outputFile = new File(outputFilePath);
        MdFileWriter outputMd = new MdFileWriter();
        SrFileWriter outputSr = new SrFileWriter();
        outputMd.writeFile(outputFile, organized);
        outputSr.writeFile(outputFile, bank);
    }


}
