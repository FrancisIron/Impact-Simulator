package controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ControllerOld {
    private Timeline timeline = new Timeline();

    @FXML
    private Circle particula;


    public void moverParticula()  {
      timeline.setCycleCount(Timeline.INDEFINITE); //Con Timeline.INDEFINITE, se hace infinito
      //Esto hace que regrese
      /*timeline.setAutoReverse(true);*/

      //170 limite de la derecha, -160 limite de la izquierda
      /*if (textIzquierda.getText().startsWith("+")&&textDerecha.getText().startsWith("|")){
          final KeyValue kv = new KeyValue(particula.translateXProperty(),170);
          final KeyFrame kf = new KeyFrame(Duration.millis(1000),kv);
          timeline.getKeyFrames().add(kf);
      } else if(textIzquierda.getText().startsWith("|")&&textDerecha.getText().startsWith("+")){
          final KeyValue kv = new KeyValue(particula.translateXProperty(),-160);
          final KeyFrame kf = new KeyFrame(Duration.millis(1000),kv);
          timeline.getKeyFrames().add(kf);
      }*/

      final KeyValue kv = new KeyValue(particula.translateXProperty(),330); //330 es el maximo para que choque;
      final KeyFrame kf = new KeyFrame(Duration.millis(1000),kv);
      timeline.getKeyFrames().add(kf);
      timeline.play();
    }





}
