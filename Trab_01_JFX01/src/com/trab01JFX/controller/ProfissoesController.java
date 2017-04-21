package com.trab01JFX.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ProfissoesController implements Initializable{
	@FXML
    private Tab tabCadastro;

    @FXML
    private Pane paneCadastro;

    @FXML
    private TextField txtCodProfissao;

    @FXML
    private TextField txtDesProfissao;

    @FXML
    private Button btnIncluirProfissao;

    @FXML
    private Button btnEditarProfissao;

    @FXML
    private Button btnExcluirProfissao;

    @FXML
    private Button btnLimparProfissao;

    @FXML
    private Tab tabPesquisa;

    @FXML
    private Pane panePesquisa;

    @FXML
    private TextField txtPesquisaProfissao;

    @FXML
    private Button btnConsultarProfissao;

    @FXML
    private TableColumn<?, ?> colCodProfissao;

    @FXML
    private TableColumn<?, ?> colNomeProfissao;

    @FXML
    void onActionBtnConsultarProfissao(ActionEvent event) {

    }

    @FXML
    void onActionBtnEditarProfissao(ActionEvent event) {

    }

    @FXML
    void onActionBtnExcluirProfissao(ActionEvent event) {

    }

    @FXML
    void onActionBtnIncluirProfissao(ActionEvent event) {

    }

    @FXML
    void onActionLimparProfissao(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
