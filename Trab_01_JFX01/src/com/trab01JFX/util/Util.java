package com.trab01JFX.util;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Util {
	public static void mensagemInformacao(String msg)
	{
		Alert alert;
		alert = new Alert(AlertType.INFORMATION,msg,ButtonType.OK);
		alert.setTitle("Trabalho JavaFX");
		alert.setHeaderText("Informação");
		alert.show();
	}

	public static void mensagemErro(String msg)
	{
		Alert alert;
		alert = new Alert(AlertType.ERROR,msg,ButtonType.OK);
		alert.setTitle("Trabalho JavaFX");
		alert.setHeaderText("Erro");
		alert.show();
	}

	public static boolean stringVaziaOuNula(String s)
	{
		//retorna true se a string for nula ou vazia
		boolean retorno=false;
		if (s.equals("") || s.isEmpty() || s.length()==0)
			retorno=true;

		return retorno;
	}
	
	public static String entradaString(String msg, String titulo){
		return JOptionPane.showInputDialog(null, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void mensagem(String msg, String titulo){
		JOptionPane.showMessageDialog(null, msg, titulo,JOptionPane.INFORMATION_MESSAGE);
	}


}
