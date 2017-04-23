package com.trab01JFX.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.trab01JFX.dao.PessoaDao;
import com.trab01JFX.dao.ProfissaoDao;
import com.trab01JFX.modelo.Pessoas;
import com.trab01JFX.modelo.Profissoes;
import com.trab01JFX.util.Util;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProfissoesController implements Initializable{
	@FXML
    private TabPane tabPane;
	
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
    private TableView<Profissoes> tabViewPesqProfissao;

    @FXML
    private TableColumn<Profissoes, Integer> colCodProfissao;

    @FXML
    private TableColumn<Profissoes, String> colNomeProfissao;

    @FXML
    void onActionBtnConsultarProfissao(ActionEvent event) {
    	String descricao = this.txtPesquisaProfissao.getText();
    	if(descricao == null){
    		descricao = "";
    	}else{
    		try{
    			ProfissaoDao pD = new ProfissaoDao();
    			ArrayList<Profissoes> al = new ArrayList<Profissoes>();
    			ObservableList<Profissoes> ob = FXCollections.observableArrayList(al);
    			this.tabViewPesqProfissao.setItems(ob);
    			al = pD.listaTudo(descricao);
    			ob = FXCollections.observableArrayList(al);
    			this.tabViewPesqProfissao.setItems(ob);
    			this.colCodProfissao.setCellValueFactory(new PropertyValueFactory<Profissoes, Integer>("cod_profissao"));
    			this.colNomeProfissao.setCellValueFactory(new PropertyValueFactory<Profissoes, String>("descricao"));
    		}catch (Exception e) {
				Util.mensagemErro("Erro: "+e.getMessage());
			}
    	}
    }

	@FXML
    void onActionBtnEditarProfissao(ActionEvent event) {
    	if(Util.stringVaziaOuNula(this.txtDesProfissao.getText())){
    		Util.mensagemErro("Este campo não pode ser vazio");
    	}else{
    		Profissoes p = new Profissoes();
    		ProfissaoDao pD = new ProfissaoDao();
    		
    		if(!Util.stringVaziaOuNula(this.txtCodProfissao.getText())){
    			p.setCod_profissao(Integer.parseInt(this.txtCodProfissao.getText()));
    		}
    		p.setDescricao(this.txtDesProfissao.getText());
    		boolean retorno = pD.alteraProfissao(p);
    		if (retorno){
    			Util.mensagemInformacao("Alteração realizada com sucesso!");
    		}else{
    			Util.mensagemErro("Erro na alteração");
    		}
    	}
    	this.limpaTela();
    }

    @FXML
    void onActionBtnExcluirProfissao(ActionEvent event) {
    	if(Util.stringVaziaOuNula(this.txtCodProfissao.getText())){
    		Util.mensagemErro("Selecione uma profissão!");
    	}else{
    		Profissoes p = new Profissoes();
    		ProfissaoDao pD = new ProfissaoDao();
    		PessoaDao peD = new PessoaDao();
    		ArrayList<Pessoas> alPe = new ArrayList<Pessoas>();
    		alPe = peD.consultaPorId(p.getCod_profissao());
    		if(alPe.size()>0){
    			Util.mensagemErro("Essa profissão não pode ser excluída, pois possui vinculos!");
    		}else{
    			if(!Util.stringVaziaOuNula(this.txtCodProfissao.getText())){
    				p.setCod_profissao(Integer.parseInt(this.txtCodProfissao.getText()));
    			}
    			p.setDescricao(this.txtDesProfissao.getText());
    			boolean retorno = pD.excluiProfissao(p);
    			if (retorno){
    				Util.mensagemInformacao("Exclusão realizada com sucesso!");
    			}else{
    				Util.mensagemErro("Erro na exclusão");
    			}
    		}
    	}
    	this.limpaTela();
    }

    @FXML
    void onActionBtnIncluirProfissao(ActionEvent event) {
    	if(Util.stringVaziaOuNula(this.txtDesProfissao.getText())){
    		Util.mensagemErro("Informe uma profissão!");
    	}else{
    		Profissoes p = new Profissoes();
    		ProfissaoDao pD = new ProfissaoDao();
    		
    		p.setDescricao(this.txtDesProfissao.getText());
    		
    		int retorno = pD.incluiProfissao(p);
    		
    		if(retorno == 0){
    			Util.mensagemErro("Erro durante a inclusão!");
    		}
    		if(retorno == 1){
    			Util.mensagemInformacao("Inclusão feita com sucesso!");
    		}
    		if(retorno == 2){
    			Util.mensagemInformacao("Profissão já cadastrada anteriomente!");
    		}
    	}
    	this.limpaTela();
    }

    @FXML
    void onActionLimparProfissao(ActionEvent event) {
    	this.limpaTela();
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		this.tabViewPesqProfissao.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				if(tabViewPesqProfissao.getSelectionModel().getSelectedItem() != null){
					Profissoes p = new Profissoes();
					p.setCod_profissao(tabViewPesqProfissao.getSelectionModel().getSelectedItem().getCod_profissao());
					p.setDescricao(tabViewPesqProfissao.getSelectionModel().getSelectedItem().getDescricao());
					try{
						alimentaTabCadastro(p);
					}catch (SQLException e) {
						Util.mensagemErro("Erro: "+e.getMessage());
					}
				}
			}
		});		
	}
	
	public void alimentaTabCadastro(Profissoes p) throws SQLException{
		this.txtCodProfissao.setText(Integer.toString(p.getCod_profissao()));
		this.txtDesProfissao.setText(p.getDescricao());
		SingleSelectionModel<Tab> sl = tabPane.getSelectionModel();
		sl.select(tabCadastro);
	}
	
	public void limpaTela(){
		this.txtCodProfissao.setText("");
		this.txtDesProfissao.setText("");
		ArrayList<Profissoes> al = new ArrayList<Profissoes>();
		ObservableList<Profissoes> ob = FXCollections.observableArrayList(al);
		this.tabViewPesqProfissao.setItems(ob);
	}

}
