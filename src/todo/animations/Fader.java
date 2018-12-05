package todo.animations;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Fader {

    private FadeTransition fadeTransition;

    public Fader (Node node, float startValue, float toValue) {

        fadeTransition = new FadeTransition(Duration.millis(1000), node);
        fadeTransition.setFromValue(startValue);
        fadeTransition.setToValue(toValue);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);

    }

    public void start() {
        fadeTransition.play();
    }

}
