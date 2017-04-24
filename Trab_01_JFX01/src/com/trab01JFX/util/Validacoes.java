package com.trab01JFX.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    public String validaData(String data){
        String isDataValida=" ";
        if(data != null && data.length() > 0){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            try{
                isDataValida = String.valueOf((df.parse(data)));
            } catch (ParseException e) {
                isDataValida = ("Data inválida: "+e.getMessage());
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

}
