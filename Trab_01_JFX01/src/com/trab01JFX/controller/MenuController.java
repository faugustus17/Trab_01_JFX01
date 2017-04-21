package com.trab01JFX.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.trab01JFX.application.Main;
import com.trab01JFX.util.Util;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController implements Initializable{
	@FXML
	private Pane pane;

	@FXML
	private MenuItem menuItemPessoa;

	@FXML
	private MenuItem menuItemProfissao;
	
	@FXML
    private MenuItem menuItemSair;

	@FXML
	private ImageView img01;
	
	@FXML
	private Button btnSair;


	@FXML
	void onActionMenuItemPessoa(ActionEvent event) {
		try{
			AnchorPane rootx = new AnchorPane();
			rootx = FXMLLoader.load(getClass().getResource("/com/trab01JFX/view/Pessoas.fxml"));
			Scene scenex = new Scene(rootx);
			final Stage stagex = new Stage();
			stagex.setScene(scenex);
			stagex.setTitle("Cadastro de Pessoas");
			stagex.initModality(Modality.APPLICATION_MODAL);
			Main main = new Main();
			stagex.initOwner(main.stage);
			stagex.show();
		}catch (Exception e) {
			Util.mensagemErro("Erro: "+e.getMessage());
		}
		
	}

	@FXML
	void onActionMenuItemProfissao(ActionEvent event) {
		try{
			AnchorPane rootx = new AnchorPane();
			rootx = FXMLLoader.load(getClass().getResource("/com/trab01JFX/view/Profissoes.fxml"));
			Scene scenex = new Scene(rootx);
			final Stage stagex = new Stage();
			stagex.setScene(scenex);
			stagex.setTitle("Cadastro de Profissões");
			stagex.initModality(Modality.APPLICATION_MODAL);
			Main main = new Main();
			stagex.initOwner(main.stage);
			stagex.show();
		}catch (Exception e) {
			Util.mensagemErro("Erro: "+e.getMessage());
		}
	}
	
	@FXML
	void onActionMenuSair(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void onActionBtnSair(ActionEvent event) {
		Platform.exit();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
