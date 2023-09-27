package Controller;
import java.io.IOException;

/**
 * The Controller interface is responsible for managing application flow
 * and handling tasks, involving receiving user interaction. The interface
 * also plays a major role in the interaction between the model and view
 */
public interface Controller {
  /**
   * Executes the main controller logic, managing application flow
   * and handling potential I/O exceptions.
   * @throws IOException
   */
  void run() throws IOException;
}
