package model;

import javafx.scene.control.TextField;

public class Corrector {

    /*De texto*/
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

    /*Validacion de entradas*/
    public boolean validarNumero(TextField input) {
        try {
            String str = input.getText().trim();
            str = str.replaceAll(",", "");
            Double.parseDouble(str);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public boolean validarPotencia(TextField inputBase, TextField inputExp) {
        String decimal = "" + inputBase.getText().trim();
        if (decimal == null || decimal.length() == 0) {
            return false;
        }
        int exponencial;
        try {
            exponencial = Integer.parseInt(inputExp.getText());
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        decimal = decimal.replaceAll(",", "");
        if (decimal.contains(".")) {
            if (decimal.split("\\.").length == 1) {
                decimal += "0";
            } else if (decimal.split("\\.")[1].length() > 2) {
                exponencial -= (decimal.split("\\.")[1].length() - 2);
                decimal = decimal.split("\\.")[0] + "." + decimal.split("\\.")[1].substring(0, 2);
            }
        }
        inputBase.setText(decimal);
        inputExp.setText("" + exponencial);
        return true;
    }
}
