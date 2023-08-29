//package cs3500.pa02;
//
//import org.junit.Test;
//
//import java.io.File;
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertEquals;
//
//public class SrFileReaderTest {
//
//  @Test
//  public void testConvertFile_SingleFile_ReturnsOrganizedGuide() {
//    // Provide the actual file path
//    String filePath = "SampleFiles/file3.md";
//
//    // Create an ArrayList to store the file
//    ArrayList<File> sortedFiles = new ArrayList<>();
//    sortedFiles.add(new File(filePath));
//
//    // Call the method
//    ArrayList<String> result = SrFileReader.convertFile(sortedFiles);
//
//    // Verify the result
//    assertEquals(1, result.size());
//    assertEquals("What time is it?:::3", result.get(0));
//  }
//}
