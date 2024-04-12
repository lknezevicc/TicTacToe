package hr.application.scenemanagement;

import hr.application.tictactoe.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class SceneManager {
    private static final URL STYLE_URL = SceneManager.class.getResource("/style.css");
    private static SceneManager instance;
    private final Properties scenesPath;
    private Stage stage;
    private SceneType currentScene;
    private SceneType previousScene;

    private SceneManager() {
        scenesPath = new Properties();
        try {
            scenesPath.load(Main.class.getResourceAsStream("/scenes.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void setScene(SceneType sceneType) {
        if (stage == null) {
            return;
        }

        if (currentScene != null) previousScene = currentScene;
        currentScene = sceneType;

        String scenePath = scenesPath.getProperty(sceneType.name());
        if (scenePath != null) {
            try {
                Scene scene = loadScene(scenePath);
                scene.getStylesheets().add(STYLE_URL.toExternalForm());
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void show() {
        stage.show();
    }

    private Scene loadScene(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
        return new Scene(loader.load());
    }

    public void setPreviousScene() {
        if (previousScene != null)
            setScene(previousScene);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("TicTacToe");
    }

    public void setResizable(boolean resizable) {
        stage.setResizable(resizable);
    }
}
