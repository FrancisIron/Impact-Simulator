package controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Calculista;
import model.Corrector;

public class ControllerVer2 {

    //FXID y atributos de animacion
    private TranslateTransition transition;

    @FXML
    private StackPane stackParticula;

    //FXID Entradas
    @FXML
    private TextField baseMasa;

    @FXML
    private TextField expMasa;

    @FXML
    private TextField baseCarga;

    @FXML
    private TextField expCarga;

    @FXML
    private TextField potencial;

    @FXML
    private TextField campoElectrico;

    @FXML
    private TextField distancia;

    //FXID Salidas
    @FXML
    private TextField vel;

    @FXML
    private TextField time;

    @FXML
    private TextField difPotencial;

    @FXML
    private TextField difEPotencial;

    //FXID Otros
    @FXML
    private Text error;

    private Calculista andrea;

    private Corrector juanito;


    /* Metodos main de simulacion */
    public ControllerVer2() {
        this.andrea = new Calculista();
        this.juanito = new Corrector();
        this.transition = new TranslateTransition();
    }

    public void onEnterPressed(KeyEvent key) {
        if (key.getCode() == KeyCode.ENTER) {
            simular();
        }
    }

    public void actionCalcular() {
        animationStop();
        error.setVisible(false);
        if (juanito.validarPotencia(baseMasa, expMasa) && juanito.validarPotencia(baseCarga, expCarga) && juanito.validarNumero(potencial) && juanito.validarNumero(campoElectrico) && juanito.validarNumero(distancia)) {
        } else {
            error.setVisible(true);
        }
    }

    public void simular() {
        animationStop();
        error.setVisible(false);
        if (juanito.validarPotencia(baseMasa, expMasa) && juanito.validarPotencia(baseCarga, expCarga) && juanito.validarNumero(potencial) && juanito.validarNumero(campoElectrico) && juanito.validarNumero(distancia)) {
            if (Double.parseDouble(baseCarga.getText()) > 0) {
                double t = andrea.calcular(potencial, baseCarga, expCarga, baseMasa, expMasa, campoElectrico, distancia, vel, time, difPotencial, difEPotencial);
                String num = "" + t;
                System.out.println(num);
                double frames = Double.parseDouble(num.split("E")[0]);
                System.out.println(frames);
                animationPlay(frames);
            }
        } else {
            error.setVisible(true);
        }
    }

    public void animationPlay(double frames) {
        transition.setDuration(Duration.millis(frames));
        transition.setNode(stackParticula);
        transition.setByX(194);
        transition.play();
    }

    public void animationStop() {
        transition.stop();
        stackParticula.setTranslateX(0);
    }

}
