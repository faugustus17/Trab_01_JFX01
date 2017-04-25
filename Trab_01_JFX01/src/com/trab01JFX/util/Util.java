package com.trab01JFX.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	public static String dataBarra(Date data){
		String s;
		Format formatter;
		
		formatter = new SimpleDateFormat("dd/MM/yyyy");
		s = formatter.format(data);
		return s;
		
	}
	
	public static String dataTraco(Date data){
		String s;
		Format formatter;
		
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		s = formatter.format(data);
		return s;
		
	}
	
	public static Date dataF(String data){
		try{
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date dataaux = formato.parse(data);
			return dataaux;
		}catch (ParseException e) {
			Util.mensagemErro("Erro: "+e.getMessage());
			return null;
		}
	}
	
	public static Date dataBD(String data){
		try{
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date dataaux = formato.parse(data);
			return dataaux;
		}catch (ParseException e) {
			Util.mensagemErro("Erro: "+e.getMessage());
			return null;
		}
	}
	
	public static String data(String data){
        return (data.substring(6,10)+"-"+data.substring(3,5)+"-"+data.substring(0,2));
	}
	
	public static String data2(String data){
        return (data.substring(8,10)+"/"+data.substring(5,7)+"/"+data.substring(0,4));
	}
	
}
