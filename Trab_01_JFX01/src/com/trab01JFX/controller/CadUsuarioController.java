package com.trab01JFX.controller;

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
import javafx.scene.control.TextField;

public class CadUsuarioController implements Initializable{
	@FXML
	private Button btnCadUsuario;
	
	@FXML
    private Button btnVoltar;

	@FXML
	private TextField txtCadNomeUsuario;

	@FXML
	private TextField txtCadUsuario;

	@FXML
	private TextField txtCadSenha;

	@FXML
	void onActionBtnCadUsuario(ActionEvent event) {
		String msg = "";
		Usuarios u = new Usuarios();
		UsuarioDao uD = new UsuarioDao();
		if(Util.stringVaziaOuNula(this.txtCadNomeUsuario.getText())){
			msg ="Informe um NOME";
		}
		if(Util.stringVaziaOuNula(this.txtCadUsuario.getText())){
			msg +="Informe um USUARIO";
		}
		if(Util.stringVaziaOuNula(this.txtCadSenha.getText())){
			msg +="Informe uma SENHA";
		}
		if(msg.equals("")){
			u.setNome_usuario(this.txtCadNomeUsuario.getText());
			u.setUsuario(this.txtCadUsuario.getText());
			u.setSenha(this.txtCadSenha.getText());

			int retorno = uD.cadastraUsuario(u);

			if(retorno == 1){
				Util.mensagemInformacao("Usuário cadastrado com sucesso!");
				this.txtCadNomeUsuario.setText("");
				this.txtCadUsuario.setText("");
				this.txtCadSenha.setText("");
				Main.loadScene("/com/trab01JFX/view/Login.fxml", "Tela de Login");
			}
			if(retorno == 0){
				Util.mensagemErro("Erro na inclusão!");
			}
			if(retorno == 2){
				Util.mensagemInformacao("Usuário ja cadastrado");
			}
		}else{
			Util.mensagemErro(msg);
		}
	}
	
	@FXML
    void onActionBtnVoltar(ActionEvent event) {
		Main.loadScene("/com/trab01JFX/view/Login.fxml", "Tela de Login");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
