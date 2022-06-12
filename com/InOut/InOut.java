
package com.InOut; //Nome pacote
import javax.swing.JOptionPane;
//Conhecendo e utilizando a classe JOptionPane.
// JOptionPane é uma classe que possibilita a criação de uma caixa de dialogo padrão
// que ou solicita um valor para o usuário ou retorna uma informação


public class InOut {

    //tipos primitivos de dados que podem ser recebidos nas caixas de dialagos

    public static int InInt(String titulo){
        int in = Integer.parseInt(JOptionPane.showInputDialog(titulo));
        return in;
    }

    public static float InFloat(String titulo){
        float in = Float.parseFloat(JOptionPane.showInputDialog(titulo));
        return in;
    }

    public static double InDouble(String titulo){
        double in = Double.parseDouble(JOptionPane.showInputDialog(titulo));
        return in;
    }

    public static String InString(String titulo){
        String in = JOptionPane.showInputDialog(titulo);
        return in;
    }

    public static void  OutMessage(String msg, String titulo) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void  OutMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

}