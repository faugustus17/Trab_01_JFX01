package com.trab01JFX.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;
import com.trab01JFX.modelo.Pessoas;
import com.trab01JFX.util.Util;

public class PessoaDao {
	Conexao conexao = new Conexao();
	Connection conn = conexao.abreConexaoBD();
	ResultSet rs = null;
	Statement st = null;
	
	//Consulta se Pessoa ja existe no BD
	public int consultaPessoa(String nome){
		int retorno = 0;
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_pessoas WHERE nome_pessoa LIKE '"+nome+"'");
			if(rs.next()){
				//Pessoa já cadastrada
				retorno = 1;
			}else{
				//Pessoa não encontrada
				retorno = 2;
			}
			return retorno;
		}catch (SQLException e) {
			Util.mensagemErro("Erro: "+e.getMessage()+"    Cod: "+e.getErrorCode());
			return retorno;
		}
	}
	
	//Consulta se CPF ja existe no BD
	public int consultaCPF(String cpf){
		int retorno = 0;
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_pessoas WHERE cpf = '"+cpf+"'");
			if(rs.next()){
				//CPF já cadastrado
				retorno = 1;
			}else{
				//CPF não encontrado
				retorno = 2;
			}
			return retorno;
		}catch (SQLException e) {
			Util.mensagemErro("Erro: "+e.getMessage()+"    Cod: "+e.getErrorCode());
			return retorno;
		}
	}
	
	//Consulta e retorna uma coleção de pessoas cadastradas
	public ArrayList<Pessoas> consultaPorNome(String nome){
		Pessoas p;
		String sql = null;
		
		if(nome.equals("") || nome.length() == 0 || nome.isEmpty()){
			sql = "SELECT * FROM tb_pessoas ORDER BY nome_pessoa";
		}else{
			sql = "SELECT * FROM tb_pessoas WHERE nome_pessoa LIKE '%";
			sql = nome+"%' ORDER BY nome_pessoa";	
		}
		ArrayList<Pessoas> alP = new ArrayList<Pessoas>();
		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				p = new Pessoas();
				p.setCod_pessoa(rs.getInt("cod_pessoa"));
				p.setCpf(rs.getString("cpf"));
				p.setNome_pessoa(rs.getString("nome_pessoa"));
				p.setData_nascimento(Util.rsData(rs.getString("data_nascimento")));
				p.setCod_profissao(rs.getInt("cod_profissao"));
				alP.add(p);
			}
		}catch (SQLException e) {
			Util.mensagemErro("Erro: "+e.getMessage());
		}
		return alP;
	}
	
	//Consulta e retorna uma coleção de pessoas cadastradas
	public ArrayList<Pessoas> consultaPorId(int id){
		Pessoas p;
		String sql = null;
		ArrayList<Pessoas> alP = new ArrayList<Pessoas>();
		try{
			sql = "SELECT * FROM tb_pessoas WHERE cod_profissao= "+id;
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				p = new Pessoas();
				p.setCod_pessoa(rs.getInt("cod_pessoa"));
				p.setCpf(rs.getString("cpf"));
				p.setNome_pessoa(rs.getString("nome_pessoa"));
				p.setData_nascimento(Util.rsData(rs.getString("data_nascimento")));
				p.setCod_profissao(rs.getInt("cod_profissao"));
				alP.add(p);
			}
		}catch (SQLException e) {
			Util.mensagemErro("Erro: "+e.getMessage());
		}
		return alP;
	}
	
	//Inclui pessoa no BD
	public int incluiPessoa(Pessoas pessoa){
		int retorno = 0;
		String sql = null;
		if(this.consultaPessoa(pessoa.getNome_pessoa().trim()) == 1){
			Util.mensagemErro("Pessoa já cadastrada!");
			retorno = 2;
		}else{
			try{
				sql = "INSERT INTO tb_pessoas(cpf, nome_pessoa, data_nascimento, cod_profissao) VALUES ( '";
				sql += pessoa.getCpf()+"', '"+pessoa.getNome_pessoa()+"', '"+Util.rsDataBD(pessoa.getData_nascimento())+"' ,";
				sql += pessoa.getCod_profissao()+")";
				st = conn.createStatement();
				int rst = st.executeUpdate(sql);
				if (rst == 1){
					//Cadastro efetuado
					retorno = 1;
				}else{
					//Cadastro não realizado
					retorno = 0;
				}
			}catch (SQLException e) {
				Util.mensagemErro("Erro ao cadastrar pessoa: "+e.getMessage()+"   Cod: "+e.getErrorCode());
				return retorno;
			}
		}
		return retorno;
	}
	
	//Altera uma Pessoa no BD
	public boolean alteraPessoa(Pessoas pessoa){
		boolean retorno = false;
		String sql= null;
		try{
			sql = "UPDATE tb_pessoas SET cpf = '"+pessoa.getCpf()+"', ";
			sql += " nome_pessoa= '"+pessoa.getNome_pessoa()+"', ";
			sql += " data_nascimento= '"+Util.rsDataBD(pessoa.getData_nascimento())+"', ";
			sql += " cod_profissao= "+pessoa.getCod_profissao() + " WHERE cod_pessoa = "+pessoa.getCod_pessoa();
			st = conn.createStatement();
			int rst = st.executeUpdate(sql);
			if (rst == 1){
				//Alteração efetuada
				retorno = true;
			}else{
				//Alteração não realizada
				retorno = false;
			}
		}catch (SQLException e) {
			Util.mensagemErro("Erro ao alterar pessoa: "+e.getMessage()+"   Cod: "+e.getErrorCode());
			return retorno;
		}
		return retorno;	
	}
	
	//Exclui uma pessoa do BD
	public boolean excluiPessoa(Pessoas pessoa){
		boolean retorno = false;
		String sql = null;
		try{
			sql = "DELETE FROM tb_pessoas WHERE cod_pessoa = "+pessoa.getCod_pessoa();
			st = conn.createStatement();
			int rst = st.executeUpdate(sql);
			if (rst == 1){
				//Exclusão efetuada
				retorno = true;
			}else{
				//Exclusão não realizada
				retorno = false;
			}
		}catch (SQLException e) {
			Util.mensagemErro("Erro ao excluir pessoa: "+e.getMessage()+"   Cod: "+e.getErrorCode());
			return retorno;
		}	
		return retorno;
	}
}
