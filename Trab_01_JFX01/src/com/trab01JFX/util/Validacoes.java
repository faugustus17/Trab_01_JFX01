package com.trab01JFX.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;

public class Validacoes {
	public boolean valData(String data){
        String datePattern = "^(?:(31)(\\D)(0?[13578]|1[02])\\2|(29|30)(\\D)(0?[13-9]|1[0-2])\\5|" +
                "(0?[1-9]|1\\d|2[0-8])(\\D)(0?[1-9]|1[0-2])\\8)((?:1[6-9]|[2-9]\\d)?\\d{2})$|" +
                "^(29)(\\D)(0?2)\\12((?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|" +
                "(?:16|[2468][048]|[3579][26])00)$";
        boolean isDataValida = false;
        if(data != null && data.length() > 0) {
            if (data.matches(datePattern)) {
                isDataValida = true;
            }
        }
        return isDataValida;
    }

    public boolean verificaVencimentoData(String data) throws ParseException {
        boolean isDataValida;
        Calendar calendar = new GregorianCalendar();
        Date dataAtual = new Date();
        calendar.setTime(dataAtual);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dtAtual = calendar.getTime();
        Date dtInfo = new Date(dateFormat.parse(data).getTime());

        if(dtInfo.before(dtAtual)){
            isDataValida = true;
        }else if (dtInfo.after(dtAtual)){
            isDataValida =false;
        }else {
            isDataValida = true;
        }
        return isDataValida;
    }
    
    public String limpaCPF(String CPF){
    	String vCPF = CPF.replace(".", "");
    	CPF = vCPF.replace("-", "");
    	return CPF;
    }
    
    public boolean validaCPF (String CPF){
    	String vCPF = CPF.replace(".", "");
    	CPF = vCPF.replace("-", "");
    	if(CPF.equals("00000000000")||CPF.equals("11111111111")||
    	   CPF.equals("22222222222")||CPF.equals("33333333333")||
    	   CPF.equals("44444444444")||CPF.equals("55555555555")||
    	   CPF.equals("66666666666")||CPF.equals("77777777777")||
    	   CPF.equals("88888888888")||CPF.equals("99999999999")||
    	   CPF.equals("12345678909")||(CPF.length() != 11)){
    		return false;
    	}
    	char d10, d11;
    	int soma, r, num, peso;
    	try{
    	// Calculo do 1o. Digito Verificador
    		soma = 0;
    		peso = 10;
    		for (int i=0; i<9; i++){
    			num = (int)(CPF.charAt(i) - 48);
    			soma = soma + (num * peso);
    			peso = peso - 1;
    		}
    		r = 11 - (soma % 11);
    		if ((r == 10) || (r == 11)){
    			d10 = '0';
    		}else{
    			d10 = (char)(r + 48); // converte no respectivo caractere numerico
    		}
    		// Calculo do 2o. Digito Verificador
    		soma = 0;
    		peso = 11;
    		for(int i=0; i<10; i++){
    			num = (int)(CPF.charAt(i) - 48);
    			soma = soma + (num * peso);
    			peso = peso - 1;
    		}
    		r = 11 - (soma % 11);
    		if ((r == 10) || (r == 11)){
    			d11 = '0';
    		}else{
    			d11 = (char)(r + 48);// converte no respectivo caractere numerico
    		}
    		// Verifica se os digitos calculados conferem com os digitos informados.
    		if ((d10 == CPF.charAt(9)) && (d11 == CPF.charAt(10))){
    			return true;
    		}else{
    			return false ;
    		}
    	}catch (InputMismatchException erro){
    		return false ;
    	}
	}
    
    public int somaIdade(Date data){
    	Calendar cData = Calendar.getInstance();
    	Calendar cHoje = Calendar.getInstance();
    	cData.setTime(data);
    	cData.set(Calendar.YEAR, cHoje.get(Calendar.YEAR));
    	int idade = cData.after(cHoje) ? -1 : 0;
    	cData.setTime(data);
    	idade += cHoje.get(Calendar.YEAR) - cData.get(Calendar.YEAR); 	
		return idade; 	
    }
}

