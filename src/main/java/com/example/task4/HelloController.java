package com.example.task4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.control.TextField;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    public ConcreteAggregate conaggr = new ConcreteAggregate("img");
    public Iterator iter = conaggr.getIterator();

    public Timeline time = new Timeline();

    private boolean isPlaying = false;

    @FXML
    private ImageView screen;

    @FXML
    private TextField delayField;


    public void initialize() {
        // ��������� ���������� ����������
        time.setCycleCount(Timeline.INDEFINITE);
        updateTimeline(1000);

        screen.setPreserveRatio(false);
    }

    // ���������� ������� ��� ������ ������
    private class EvHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            screen.setImage((Image) iter.next());
        }
    }


    @FXML
    public void toggleAnimation() {
        if (isPlaying) {
            time.pause();
        } else {
            time.play();
        }
        isPlaying = !isPlaying;
    }

    // ����� ��� ���������� ��������� ����� � ����� ���������
    @FXML
    public void updateDelay() {
        int newDelay = Integer.parseInt(delayField.getText());
        updateTimeline(newDelay);
    }

    // ����� ��� ���������� ��������� �����
    private void updateTimeline(int delayMillis) {
        time.stop();
        time.getKeyFrames().clear();
        time.getKeyFrames().add(new KeyFrame(Duration.millis(delayMillis), new EvHandler()));
        if (isPlaying) {
            time.play(); // ������������
        }
    }

    @FXML
    public void next() {
        screen.setImage((Image) iter.next());
    }
    @FXML
    public void preview(){
        screen.setImage((Image) iter.preview());
    }

}