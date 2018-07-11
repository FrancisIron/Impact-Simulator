package model;

import javafx.scene.control.TextField;

import static java.lang.Math.sqrt;

public class Calculista {

    public double calcular (TextField baseCarga, TextField expCarga, TextField baseMasa, TextField expMasa, TextField campoElectrico, TextField distancia, TextField vel, TextField time, TextField difPotencial, TextField difEPotencial ){
        double charge, mass, auxBase, acc, resultTime, speed, EPot, difPot;
        int auxExp;
        Corrector juanito = new Corrector();
        auxBase = Double.parseDouble(baseCarga.getText());
        auxExp = Integer.parseInt(expCarga.getText());
        charge = Double.parseDouble(juanito.createDouble(auxBase, auxExp));
        auxBase = Double.parseDouble(baseMasa.getText());
        auxExp = Integer.parseInt(expMasa.getText());
        mass = Double.parseDouble(juanito.createDouble(auxBase, auxExp));
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

}
