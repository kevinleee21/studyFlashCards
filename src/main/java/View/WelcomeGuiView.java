package View;
import javafx.scene.Scene;

/**
 * Represents a welcome GUI view.
 */
public interface WelcomeGuiView {
    /**
     * Loads a scene from a welcome GUI layout.
     *
     * @return the layout
     */
    Scene load() throws IllegalStateException;
}
