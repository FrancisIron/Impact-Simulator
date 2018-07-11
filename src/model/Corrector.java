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
            exponencial = Integer.parseInt(inputExp.getText());//llamer al expo.getText()
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
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
