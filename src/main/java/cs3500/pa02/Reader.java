package cs3500.pa02;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Reader {
    public abstract ArrayList<String> convertFiles(ArrayList<File> sortedFiles);
    public abstract void finishLine(String line, StringBuilder bracketedStatement, Scanner scanner, ArrayList<String> organizedGuide);
}
