package com.trab01JFX.controller;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.trab01JFX.dao.PessoaDao;
import com.trab01JFX.dao.ProfissaoDao;
import com.trab01JFX.modelo.Pessoas;
import com.trab01JFX.modelo.Profissoes;
import com.trab01JFX.util.Util;
import com.trab01JFX.util.Validacoes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class PessoasController implements Initializable{
	int OK=0;
	
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
    void onActionBtnEditarPessoa(ActionEvent event)throws ParseException {
    	String msg = "";
    	Validacoes v = new Validacoes();
    	PessoaDao pD = new PessoaDao();
    	if(Util.stringVaziaOuNula(this.txtNomePesoa.getText())){
    		msg = "Informe o NOME da pessoa";
    	}
    	if(Util.stringVaziaOuNula(this.txtCPFPessoa.getText())){
    		msg += "\nInforme o CPF da pessoa";
    	}
    	if(Util.stringVaziaOuNula(this.txtDataNascPessoa.getText())){
    		msg += "\nInforme a DATA DE NASCIMENTO da pessoa";
    	}
    	if(!v.validaCPF(this.txtCPFPessoa.getText())){
    		msg += "\nInforme um CPF válido";
    	}
    	if(!v.verificaVencimentoData(this.txtDataNascPessoa.getText())){
    		msg += "\nInforme uma DATA VÁLIDA";
    	}
    	if((v.somaIdade(Util.dataF(this.txtDataNascPessoa.getText()))) < 18){
    		msg +="\nCadastro permitido somente para maiores de 18 anos";
    	}
    	if(msg.equals("")){
    		Profissoes pf = new Profissoes();
    		Pessoas p = new Pessoas();
    		//PessoaDao pD = new PessoaDao();
    		if (!Util.stringVaziaOuNula(this.txtCodPessoa.getText())){
    			p.setCod_pessoa(Integer.parseInt(this.txtCodPessoa.getText()));
    		}
    		p.setNome_pessoa(this.txtNomePesoa.getText());
    		p.setCpf(v.limpaCPF(this.txtCPFPessoa.getText()));
    		p.setData_nascimento(Util.dataF(this.txtDataNascPessoa.getText()));
    		String s = this.cmbProfPessoa.getPromptText();
    		pf = this.objProfissao(s);
    		p.setCod_profissao(pf.getCod_profissao());
    		boolean retorno = pD.alteraPessoa(p);
    		if(retorno){
    			Util.mensagemInformacao("Alteração realizada com sucesso!");
    			OK = 1;
    		}else{
    			Util.mensagemErro("Erro, alteração não pode ser feita!");
    			OK = 0;
    		}
    	}else{
    		Util.mensagemErro(msg);
    		OK = 0;
    	}
    	if(OK == 1){
    		this.limpaTela();
    	}
    }

    @FXML
    void onActionBtnExcluirPessoa(ActionEvent event) {
    	if(Util.stringVaziaOuNula(this.txtCodPessoa.getText())){
    		Util.mensagemErro("Selecione uma pessoa!");
    	}else{
    		Pessoas p = new Pessoas();
    		PessoaDao pD = new PessoaDao();
    		if(!Util.stringVaziaOuNula(this.txtCodPessoa.getText())){
    			p.setCod_pessoa(Integer.parseInt(this.txtCodPessoa.getText()));
    		}
    		boolean retorno = pD.excluiPessoa(p);
    		if(retorno){
    			Util.mensagemInformacao("Exclusão realizada com sucesso!");
    		}else{
    			Util.mensagemErro("Erro durante exclusão!");
    		}
    	}
    	this.limpaTela();
    }

    @FXML
    void onActionBtnIncluirPessoa(ActionEvent event)throws ParseException {
    	String msg = "";
    	Validacoes v = new Validacoes();
    	PessoaDao pD = new PessoaDao();
    	if(Util.stringVaziaOuNula(this.txtNomePesoa.getText())){
    		msg = "Informe o NOME da pessoa";
    	}
    	if(Util.stringVaziaOuNula(this.txtCPFPessoa.getText())){
    		msg += "\nInforme o CPF da pessoa";
    	}
    	if(Util.stringVaziaOuNula(this.txtDataNascPessoa.getText())){
    		msg += "\nInforme a DATA DE NASCIMENTO da pessoa";
    	}
    	if(!v.validaCPF(this.txtCPFPessoa.getText())){
    		msg += "\nInforme um CPF válido";
    	}
    	if(!v.verificaVencimentoData(this.txtDataNascPessoa.getText())){
    		msg += "\nInforme uma DATA VÁLIDA";
    	}
    	if((v.somaIdade(Util.dataF(this.txtDataNascPessoa.getText()))) < 18){
    		msg +="\nCadastro permitido somente para maiores de 18 anos";
    	}
    	if((pD.consultaCPF(this.txtCPFPessoa.getText())) == 1){
    		msg +="\nCPF já cadastrado";
    	}
    	if(msg.equals("")){
    		Pessoas p = new Pessoas();
    		//PessoaDao pD = new PessoaDao();
    		Profissoes pf = new Profissoes();
    		if (!Util.stringVaziaOuNula(this.txtCodPessoa.getText())){
    			p.setCod_pessoa(Integer.parseInt(this.txtCodPessoa.getText()));
    		}
    		p.setNome_pessoa(this.txtNomePesoa.getText());
    		p.setCpf(this.txtCPFPessoa.getText());
    		p.setData_nascimento(Util.dataF(this.txtDataNascPessoa.getText()));
    		String s = this.cmbProfPessoa.getSelectionModel().getSelectedItem();
    		if (s == null){
    			s = this.cmbProfPessoa.getPromptText();
    		}
    		pf = this.objProfissao(s);
    		p.setCod_profissao(pf.getCod_profissao());
    		int retorno = pD.incluiPessoa(p);
    		if(retorno == 0){
    			Util.mensagemErro("Erro na inclusão da pessoa!");
    			OK = 0;
    		}
    		if(retorno == 1){
    			Util.mensagemInformacao("Inclusão realizada com sucesso!");
    			OK = 1;
    		}
    		if(retorno == 2){
    			Util.mensagemInformacao("Pessoa já cadastrada!");
    			OK = 0;
    		}
    	}else{
    		Util.mensagemErro(msg);
    	}
    	if(OK == 1){
    		this.limpaTela();
    	}
    }

    @FXML
    void onActionLimparPessoa(ActionEvent event) {
    	this.limpaTela();
    }
    
    @FXML
    void onActionBtnConsultarPessoa(ActionEvent event) {
    	String consulta = this.txtNomePesoa.getText();
    	if(consulta == null){
    		consulta = "";
    	}else{
    		this.preencheCmbBox();
    		ArrayList<Pessoas> al = new ArrayList<Pessoas>();
    		ObservableList<Pessoas> ob = FXCollections.observableArrayList(al);
    		this.tabView.setItems(ob);
    		PessoaDao pD = new PessoaDao();
    		al = pD.consultaPorNome(consulta);
    		ob = FXCollections.observableArrayList(al);
    		this.tabView.setItems(ob);
    		this.colCodPessoa.setCellValueFactory(new PropertyValueFactory<Pessoas, Integer>("cod_pessoa"));
    		this.colNomePessoa.setCellValueFactory(new PropertyValueFactory<Pessoas, String>("nome_pessoa"));
    		if(al.size()==0){
    			Util.mensagemInformacao("Nome não encontrado ou não cadastrado!");
    		}
    	}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.preencheCmbBox();
		this.tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				if(newValue == tabCadastro){
					preencheCmbBox();
				}
			}	
		});
		
		this.cmbProfPessoa.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				Profissoes p = new Profissoes();
				ProfissaoDao pD = new ProfissaoDao();
				try{
					p = pD.consultaPorProfissao(newValue);
				}catch (Exception e) {
					Util.mensagemErro("Erro: "+e.getMessage());
				}
				if(p != null){
					
				}
			}
		});
		
		this.tabView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				if(tabView.getSelectionModel().selectedItemProperty() != null){
					Pessoas p = new Pessoas();
					p.setCod_pessoa(tabView.getSelectionModel().getSelectedItem().getCod_pessoa());
					p.setNome_pessoa(tabView.getSelectionModel().getSelectedItem().getNome_pessoa());
					p.setCpf(tabView.getSelectionModel().getSelectedItem().getCpf());
					p.setData_nascimento(tabView.getSelectionModel().getSelectedItem().getData_nascimento());
					p.setCod_profissao(tabView.getSelectionModel().getSelectedItem().getCod_profissao());
					try{
						preencheTabCadastro(p);
					}catch (Exception e) {
						Util.mensagemErro("Erro: "+e.getMessage());
					}
				}
			}
		});
		
		this.txtNomePesoa.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				txtNomePesoa.setText(newValue.toUpperCase());			
			}
		});
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
	
	public void preencheTabCadastro(Pessoas p)throws SQLException{
		Profissoes pf = new Profissoes();
		ProfissaoDao pfD = new ProfissaoDao();
		pf = pfD.consultaPorId(p.getCod_profissao());
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
		this.txtDataNascPessoa.setText(Util.dataBarra(p.getData_nascimento()));
		SingleSelectionModel<Tab> selectionModel = this.tabPane.getSelectionModel();
		selectionModel.select(this.tabCadastro);
	}
	
	public Profissoes objProfissao(String s){
		Profissoes p = new Profissoes();
		ProfissaoDao pD = new ProfissaoDao();
		try{
			p = pD.consultaPorProfissao(s);
		}catch (Exception e) {
			Util.mensagemErro("Erro: "+e.getMessage());
		}
		return p;
	}
	
	public void limpaTela(){
		this.txtCodPessoa.setText("");
		this.txtCPFPessoa.setText("");
		this.txtDataNascPessoa.setText("");
		this.txtNomePesoa.setText("");
		this.txtPesquisaPessoa.setText("");
		preencheCmbBox();
		this.cmbProfPessoa.setPromptText("");
		ArrayList<Pessoas> a = new ArrayList<Pessoas>();
		ObservableList<Pessoas> o = FXCollections.observableArrayList(a);
		this.tabView.setItems(o);
	}
	
	public class UpcaseDocument extends PlainDocument{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public UpcaseDocument(){
			super();
		}

		@Override
		public void insertString(int an_length, String as_character, AttributeSet ao_attribute)
		throws BadLocationException{
			super.insertString(an_length, as_character.toUpperCase(), ao_attribute);
		}
	}
}

