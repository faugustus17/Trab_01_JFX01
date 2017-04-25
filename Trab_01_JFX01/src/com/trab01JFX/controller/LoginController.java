package com.trab01JFX.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.trab01JFX.application.Main;
import com.trab01JFX.dao.UsuarioDao;
import com.trab01JFX.modelo.Usuarios;
import com.trab01JFX.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable{
	@FXML
    private Button btnAcessar;

    @FXML
    private Button btnCadastrar;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnEsquecer;

    @FXML
    void onActionBtnAcessar(ActionEvent event) {
    	if(Util.stringVaziaOuNula(this.txtLogin.getText())){
    		Util.mensagemErro("Informe um usuario!");
    	}else if(Util.stringVaziaOuNula(this.txtSenha.getText())){
    		Util.mensagemErro("Informe uma senha!");
    	}else{
    		
    		UsuarioDao usuDao = new UsuarioDao();    		
    		
    		int retornoUsuario = usuDao.consultaUsuario(this.txtLogin.getText());
    		int retornoSenha = usuDao.consultaSenha(this.txtSenha.getText());
    		
    		if (retornoUsuario == 1 && retornoSenha == 1){
    			try{
    	    		Main.loadScene("/com/trab01JFX/view/Menu.fxml", "Menu Principal");
    	       	}catch(Exception e){
    	    		Util.mensagemErro(e.getMessage());
    	    	}
    		}else if(retornoUsuario == 2 || retornoSenha == 2){
    			Util.mensagemInformacao("Usuario e/ou Senha não conferem,"
    					+"\nou Usiário não cadastrado no banco de dados!");
    		}else{
    			Util.mensagemErro("Erro ao logar!");
    		}
    	}
    }

    @FXML
    void onActionbtnCadastrar(ActionEvent event) throws IOException {
    	try{
    		Main.loadScene("/com/trab01JFX/view/CadastroUsuario.fxml", "Cadastro de Usuários");
       	}catch(Exception e){
    		Util.mensagemErro(e.getMessage());
    	}
    }

    @FXML
    void onActionbtnEsquecer(ActionEvent event) {
    	String nome = Util.entradaString("Informe um nome para consulta", "Consulta de Usuário e Senha");
    	if (Util.stringVaziaOuNula(nome)){
    		Util.mensagemErro("Informe um nome para consulta!");
    	}else{
    		UsuarioDao usuDao = new UsuarioDao();
    		Usuarios u = new Usuarios();
    		u = usuDao.esqueceuUsuario(nome);
    		if(u != null){
    			String dados ="NOME: "+u.getNome_usuario()+"\nUSUARIO: "+u.getUsuario()+"\nSENHA: "+u.getSenha();
    			Util.mensagem(dados,"Recuperação de Dados");
    		}else{
    			Util.mensagemInformacao("Nome: "+nome+", não encontrado!");
    		}
    	}
    }
    
    public void limpaTela(){
    	this.txtLogin.setText("");
    	this.txtSenha.setText("");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
