package com.trab01JFX.controller;

import java.net.URL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.trab01JFX.dao.ProfissaoDao;
import com.trab01JFX.modelo.Pessoas;
import com.trab01JFX.modelo.Profissoes;
import com.trab01JFX.util.Util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class PessoasController implements Initializable{
	@FXML
    private TabPane tabPane;
	
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
    private ComboBox<String> cmbProfPessoa;

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
    private TableView<Pessoas> tabView;

    @FXML
    private TableColumn<Pessoas, Integer> colCodPessoa;

    @FXML
    private TableColumn<Pessoas, String> colNomePessoa;

    @FXML
    void onActionBtnAtualizarCmb(ActionEvent event) {
    	preencheCmbBox();
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
	
	public void preencheCmbBox() {
		this.cmbProfPessoa.getItems().remove(0, this.cmbProfPessoa.getItems().size());
		ProfissaoDao pD = new ProfissaoDao();
		ArrayList<Profissoes> alP = new ArrayList<Profissoes>();
		try{
			alP = pD.lista();
		}catch (Exception e) {
			Util.mensagemErro("Erro ao alimentar combobox: "+e.getMessage());
		}
		for(int i=0; i<alP.size(); i++){
			this.cmbProfPessoa.getItems().add(alP.get(i).getDescricao());
		}
	}
	
	public void limpaTela(){
		this.txtCodPessoa.setText("");
		this.txtCPFPessoa.setText("");
		this.txtDataNascPessoa.setText("");
		this.txtNomePesoa.setText("");
		this.txtPesquisaPessoa.setText("");
		preencheCmbBox();
		this.cmbProfPessoa.setPromptText(null);
		ArrayList<Pessoas> a = new ArrayList<Pessoas>();
		ObservableList<Pessoas> o = FXCollections.observableArrayList(a);
		this.tabView.setItems(o);
	}
	
	public void preencheTabCadastro(Pessoas p)throws SQLException{
		Profissoes pf = new Profissoes();
		ProfissaoDao pfD = new ProfissaoDao();
		pf = pfD.consultaPorId(p.getCod_pessoa());
		String descricao = "*";
		if(pf != null){
			descricao = pf.getDescricao();
		}
		for(int i=0; i<this.cmbProfPessoa.getItems().size(); i++){
			String s = this.cmbProfPessoa.getItems().get(i).toString();
			if(s.equals(descricao)){
				this.cmbProfPessoa.setPromptText(descricao);
			}
		}
		this.txtCodPessoa.setText(Integer.toString(p.getCod_pessoa()));
		this.txtCPFPessoa.setText(p.getCpf());
		this.txtNomePesoa.setText(p.getNome_pessoa());
		//this.txtDataNascPessoa.setText(Date.parse(p.getData_nascimento()));
	}


}
