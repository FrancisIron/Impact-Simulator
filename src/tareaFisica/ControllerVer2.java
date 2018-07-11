package tareaFisica;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

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

    /* Metodos main de simulacion */

    public void simular() {
        animationStop();
        particula.setTranslateX(0);
        error.setVisible(false);
        if (validarPotencia(baseMasa, expMasa) && validarPotencia(baseCarga, expCarga) && validarNumero(potencial) && validarNumero(campoElectrico) && validarNumero(distancia)) {
            double frames = calcular();
            System.out.println("frames inicial "+frames);
            animationPlay((1 / frames));
        } else {
            error.setVisible(true);
        }
    }

    public void animationPlay(double frames) {
        System.out.println("frames anim "+frames);
        transition.setDuration(Duration.millis(frames));
        transition.setNode(particula);
        transition.setByX(194);
        transition.play();
    }

    public void animationStop() {
        transition.stop();
    }

    /* Metodos para procesado del texto */

    public String createDouble(double shortdeciaml, int exp) {
        String num;
        if (exp < 0) {
            num = "0.";
            for (int i = 0; i > exp + 1; i--) {
                num += "0";
            }
            num += ("" + shortdeciaml).replaceAll("\\.", "");
        } else {
            num = ("" + shortdeciaml).split("\\.")[0];
            num += ("" + shortdeciaml).split("\\.")[1];
            int decimals = ("" + shortdeciaml).split("\\.")[1].length();
            for (int i = 0; i < exp - decimals; i++) {
                num += "" + 0;
            }
        }
        return num;
    }

    /* Metodos para calcular */

    public double calcular (){
        double charge, mass, auxBase, acc, resultTime, speed, EPot, difPot;
        int auxExp;
        auxBase = Double.parseDouble(baseCarga.getText());
        auxExp = Integer.parseInt(expCarga.getText());
        charge = Double.parseDouble(createDouble(auxBase, auxExp));
        auxBase = Double.parseDouble(baseMasa.getText());
        auxExp = Integer.parseInt(expMasa.getText());
        mass = Double.parseDouble(createDouble(auxBase, auxExp));
        acc = calculateAcceleration(charge, mass, Double.parseDouble(campoElectrico.getText()));
        resultTime = calculateTime(acc, Double.parseDouble(distancia.getText()));
        speed = calculateSpeed(acc, resultTime);
        EPot = calculateEnergyChange(mass, speed);
        difPot = calculateDifPoten(charge, EPot);
        vel.setText("" + speed);
        time.setText("" + resultTime);
        difPotencial.setText("" + difPot);
        difEPotencial.setText("" + EPot);
        //valor que retorna para la animacion
        return resultTime;
    }

    public double calculateAcceleration(double charge, double mass, double field) {
        //Ax * m = F
        //Ax * m = q * E
        //Ax = (q * E)/m
        return (charge * field) / mass;
    }

    public double calculateTime(double acc, double distance) {
        //(t^2 * Ax)/2 = d
        return sqrt((2 * distance) / acc);
    }

    public double calculateSpeed(double time, double acc) {
        // v = Ax * t
        return time * acc;
    }

    public double calculateDifPoten(double charge, double energyChange) {
        //ΔV = ΔU/q
        return energyChange / charge;
    }

    public double calculateEnergyChange(double mass, double speed) {
        //(m * Vf)/2 = -ΔU
        return -((mass * speed) / 2);
    }

    /*Metodos de validacion de input*/

    public boolean validarNumero(TextField input) {
        try {
            String str = input.getText();
            double num = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public boolean validarPotencia(TextField inputBase, TextField inputExp) {
        String decimal = "" + inputBase.getText();//llamar al input.getText()
        if (decimal == null || decimal.length() == 0) {
            return false;
        }
        int exponencial;
        try {
            exponencial= Integer.parseInt(inputExp.getText());//llamer al expo.getText()
        } catch (NumberFormatException e){
            return false;
        } catch (NullPointerException e){
            return false;
        }
        if (decimal.contains(".")) {
            if (decimal.split("\\.")[1].length() > 2) {
                exponencial -= (decimal.split("\\.")[1].length() - 2);
                decimal = decimal.split("\\.")[0] + "." + decimal.split("\\.")[1].substring(0, 2);
                inputBase.setText(decimal);
                inputExp.setText("" + exponencial);
            }
            return true;
        } else {
            return false;
        }
    }

}
