//package Controller;
//
//import java.util.Scanner;
//import org.junit.Test;
//import org.mockito.Mockito;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class ControllerImpTest {
//
//  // ...
//
//  @Test
//  public void testReadDesiredFile_ValidFilePath_ReturnsFile() throws IOException {
//    // Mock objects
//    Readable input = Mockito.mock(Readable.class);
//    Appendable output = Mockito.mock(Appendable.class);
//    Scanner scan = Mockito.mock(Scanner.class);
//    DesiredFile desiredFileInput = Mockito.mock(DesiredFile.class);
//
//    // Mock behavior
//    when(scan.nextLine()).thenReturn("output/output.sr");
//    when(desiredFileInput.readFile()).thenReturn(new File("output/output.sr"));
//
//    // Create controller
//    ControllerImp controller = new ControllerImp(input, output, scan, desiredFileInput);
//
//    // Call the method
//    File result = controller.readDesiredFile();
//
//    // Verify method call and result
//    verify(output).append("Hello, please start the study session by typing in the filepath to the .sr file\n");
//    verify(desiredFileInput).readFile();
//    assertEquals(new File("output/output.sr"), result);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testReadDesiredFile_InvalidFilePath_ThrowsException() throws IOException {
//    // Mock objects
//    Readable input = Mockito.mock(Readable.class);
//    Appendable output = Mockito.mock(Appendable.class);
//    Scanner scan = Mockito.mock(Scanner.class);
//    DesiredFile desiredFileInput = Mockito.mock(DesiredFile.class);
//
//    // Mock behavior
//    when(scan.nextLine()).thenReturn("invalid/path");
//
//    // Create controller
//    ControllerImp controller = new ControllerImp(input, output, scan, desiredFileInput);
//
//    // Call the method (should throw an exception)
//    controller.readDesiredFile();
//  }
//
//  @Test
//  public void testReadNumberofQuestions_ValidNumber_ReturnsNumber() throws IOException {
//    // Mock objects
//    Readable input = Mockito.mock(Readable.class);
//    Appendable output = Mockito.mock(Appendable.class);
//    Scanner scan = Mockito.mock(Scanner.class);
//    DesiredQuestions desiredQuestionsInput = Mockito.mock(DesiredQuestions.class);
//
//    // Mock behavior
//    when(scan.nextLine()).thenReturn("5");
//    when(desiredQuestionsInput.readNumber()).thenReturn(5);
//
//    // Create controller
//    ControllerImp controller = new ControllerImp(input, output, scan, null, desiredQuestionsInput, null, null, null, null);
//
//    // Call the method
//    int result = controller.readNumberofQuestions();
//
//    // Verify method call and result
//    verify(output).append("Please enter the number of questions you want to be asked!\n");
//    verify(desiredQuestionsInput).readNumber();
//    assertEquals(5, result);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testReadNumberofQuestions_InvalidNumber_ThrowsException() throws IOException {
//    // Mock objects
//    Readable input = Mockito.mock(Readable.class);
//    Appendable output = Mockito.mock(Appendable.class);
//    Scanner scan = Mockito.mock(Scanner.class);
//    DesiredQuestions desiredQuestionsInput = Mockito.mock(DesiredQuestions.class);
//
//    // Mock behavior
//    when(scan.nextLine()).thenReturn("invalidNumber");
//    when(desiredQuestionsInput.readNumber()).thenThrow(InputMismatchException.class);
//
//    // Create controller
//    ControllerImp controller = new ControllerImp(input, output, scan  desiredQuestionsInput);
//
//    // Call the method (should throw an exception)
//    controller.readNumberofQuestions();
//  }
//
//  // ...
//}
