package controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Calculista;
import model.Corrector;

import static java.lang.Math.sqrt;

public class ControllerVer2 {
    TranslateTransition transition = new TranslateTransition();

    //FXID animacion
    @FXML
    private Circle particula;

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
    }

    public void simular() {
        animationStop();
        particula.setTranslateX(0);
        error.setVisible(false);
        if (juanito.validarPotencia(baseMasa, expMasa) && juanito.validarPotencia(baseCarga, expCarga) && juanito.validarNumero(potencial) && juanito.validarNumero(campoElectrico) && juanito.validarNumero(distancia)) {
            double frames = andrea.calcular(baseCarga,expCarga,baseMasa,expMasa,campoElectrico,distancia,vel,time,difPotencial,difEPotencial);
            animationPlay((frames));
        } else {
            error.setVisible(true);
        }
    }

    public void animationPlay(double frames) {
        transition.setDuration(Duration.seconds(frames));
        transition.setNode(particula);
        transition.setByX(194);
        transition.play();
    }

    public void animationStop() {
        transition.stop();
    }


}
