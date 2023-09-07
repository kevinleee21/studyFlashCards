package cs3500.pa02;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

abstract class Writer {
    public abstract FileWriter writeFile(File file, ArrayList<String> content) ;
}
