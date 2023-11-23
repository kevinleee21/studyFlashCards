package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class SimpleView {

    private FXMLLoader loader;

    public SimpleView() {
        this.loader = new FXMLLoader();
        this.loader.setLocation(getClass().getClassLoader().getResource("ankilite.fxml"));
    }

    public Scene load() {
        // load the layout
        try {
            return new Scene(this.loader.load());
        } catch (IOException exc) {
            throw new IllegalStateException("Unable to load layout.", exc);
        }
    }
}

