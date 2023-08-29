package cs3500.pa02;


import Controller.Controller;
import Model.CreationTimeComparator;
import Model.FileNameComparator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownSRFileController implements Controller {

    private String rootPath;
    private String orderingFlag;
    private String outputFilePath;

    public MarkdownSRFileController(String rootPath, String orderingFlag, String outputFilePath) {
        this.rootPath = rootPath;
        this.orderingFlag = orderingFlag;
        this.outputFilePath = outputFilePath;
    }

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
        ArrayList<String> organized = MdFileReader.convertFile(markdownFiles);
        ArrayList<String> bank = SrFileReader.convertFiles(markdownFiles);
        File outputFile = new File(outputFilePath);
        MdFileWriter outputMd = new MdFileWriter();
        SrFileWriter outputSr = new SrFileWriter();
        outputMd.writeFile(outputFile, organized);
        outputSr.writeSrFile(outputFile, bank);
    }


}