package com.trab01JFX.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class PessoasController implements Initializable{
    @FXML
    private Tab tabCadastro;

    @FXML
    private Pane paneCadastro;

    @FXML
    private TextField txtCodPessoa;

    @FXML
    private TextField txtNomePesoa;

    @FXML
    private TextField txtCPFPessoa;

    @FXML
    private TextField txtDataNascPessoa;

    @FXML
    private ComboBox<?> cmbProfPessoa;

    @FXML
    private Button btnAtualizarCmb;

    @FXML
    private Button btnIncluirPessoa;

    @FXML
    private Button btnEditarPessoa;

    @FXML
    private Button btnExcluirPessoa;

    @FXML
    private Button btnLimparPessoa;

    @FXML
    private Tab tabPesquisa;

    @FXML
    private Pane panePesquisa;

    @FXML
    private TextField txtPesquisaPessoa;

    @FXML
    private Button btnConsultarPessoa;

    @FXML
    private TableColumn<?, ?> colCodPessoa;

    @FXML
    private TableColumn<?, ?> colNomePessoa;

    @FXML
    void onActionBtnAtualizarCmb(ActionEvent event) {

    }

    @FXML
    void onActionBtnEditarPessoa(ActionEvent event) {

    }

    @FXML
    void onActionBtnExcluirPessoa(ActionEvent event) {

    }

    @FXML
    void onActionBtnIncluirPessoa(ActionEvent event) {

    }

    @FXML
    void onActionLimparPessoa(ActionEvent event) {

    }
    
    @FXML
    void onActionBtnConsultarPessoa(ActionEvent event) {

    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

}
