package pl.pieter.utils;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.util.Duration;

public class AnimationUtils {

    public static Animation setTimeLineAnimation(double seconds, DoubleProperty startValue, double endValue) {
        Animation animation = new Timeline(
                new KeyFrame(Duration.seconds(seconds),
                        new KeyValue(startValue, endValue)));
        return animation;
    }

    public static Animation scrollNextAnimation(ScrollPane scrollPane, double scrollStep, double timeInSeconds) {
        double scrollHValue = scrollPane.getHvalue() + scrollStep;

        return setTimeLineAnimation(timeInSeconds, scrollPane.hvalueProperty(), scrollHValue);
    }

    public static Animation scrollPrevAnimation(ScrollPane scrollPane, double scrollStep, double timeInSeconds) {
        double scrollHValue = scrollPane.getHvalue() - scrollStep;

        return setTimeLineAnimation(timeInSeconds, scrollPane.hvalueProperty(), scrollHValue);
    }
}
