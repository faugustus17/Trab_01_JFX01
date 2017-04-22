package com.trab01JFX.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.trab01JFX.modelo.Profissoes;
import com.trab01JFX.util.Util;

public class ProfissaoDao {
	Conexao conexao = new Conexao();
	Connection conn = conexao.abreConexaoBD();
	ResultSet rs = null;
	Statement st = null;
	
	//Consulta se Profissão ja existe no BD
	public int consultaProfissao(String profissao){
		int retorno = 0;
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_profissoes WHERE descricao LIKE '%"+profissao+"'");
			if(rs.next()){
				//Profissão ja cadastrada
				retorno = 1;
			}else{
				//Profissão não encontrada
				retorno = 2;
			}
			return retorno;
		}catch (SQLException e){
			Util.mensagemErro("Erro: "+e.getMessage()+"   Cod: "+e.getSQLState());
			//Retorna 0 = erro na busca.
			return retorno;
		}
	}
	
	//Lista todas as Profissões
	public ArrayList<Profissoes> listaTudo(String descricao){
		Profissoes p;
		ArrayList<Profissoes> alP = new ArrayList<Profissoes>();
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_profissoes WHERE descricao LIKE '%"+descricao+"' ORDER BY descricao");
			while(rs.next()){
				p = new Profissoes();
				p.setCod_profissao(rs.getInt("cod_profissao"));
				p.setDescricao(rs.getString("descricao"));
				alP.add(p);
			}
		}catch (Exception e) {
			Util.mensagemErro("Erro: "+e.getMessage());
		}
		return alP;
	}
	
	//Lista Profissão por Nome
	public Profissoes consultaPorProfissao(String profissao){
		Profissoes p = new Profissoes();
		String sql = null;
		try{
			st = conn.createStatement();
			sql = "SELECT * FROM tb_profissoes WHERE descricao LIKE '%"+profissao+"'";
			rs = st.executeQuery(sql);
			if(rs.next()){
				p.setCod_profissao(rs.getInt("cod_profissao"));
				p.setDescricao(rs.getString("descricao"));
			}else{
				p = null;
			}
		}catch (Exception e) {
			Util.mensagemErro("Erro: "+e.getMessage());
		}
		return p;
	}
	
	//Lista Profissão por Id
	public Profissoes consultaPorCodProfissao(int codigo){
		Profissoes p = new Profissoes();
		String sql = null;
		try{
			st = conn.createStatement();
			sql = "SELECT * FROM tb_profissoes WHERE cod_profissao ="+codigo;
			rs = st.executeQuery(sql);
			if(rs.next()){
				p.setCod_profissao(rs.getInt("cod_profissao"));
				p.setDescricao(rs.getString("descricao"));
			}else{
				p = null;
			}
		}catch (Exception e) {
			Util.mensagemErro("Erro: "+e.getMessage());
		}
		return p;
	}
	
	//Inclusão de Profissão no BD
	public int incluiProfissao(Profissoes profissao){
		int retorno = 0;
		String sql = null;
		if(this.consultaProfissao(profissao.getDescricao())==1){
			Util.mensagemErro("Profissão já cadastrada!");
			retorno = 2;
		}else{
			try{
				sql = "INSERT INTO tb_profissoes(descricao) VALUES('"+profissao.getDescricao()+"')";
				st = conn.createStatement();
				int rst = st.executeUpdate(sql);
				if (rst == 1){
					retorno = 1;
				}else{
					retorno = 0;
				}
				
			}catch (SQLException e){
				Util.mensagemErro("Erro ao cadastrar profissão: "+e.getMessage()+"   Cod: "+e.getSQLState());
				return retorno;
			}
		}
		return retorno;		
	}
	
	//Altera uma Profissão no BD
	public boolean alteraProfissao(Profissoes profissao){
		boolean retorno = false;
		String sql = null;
		try{
			sql = "UPDATE tb_profissoes SET descricao = '"+profissao.getDescricao()+"'";
			sql+= "WHERE cod_profissao = "+profissao.getCod_profissao();
			st = conn.createStatement();
			int rst = st.executeUpdate(sql);
			if (rst == 1){
				retorno = true;
			}else{
				retorno = false;
			}
		}catch (SQLException e){
			Util.mensagemErro("Erro ao cadastrar profissão: "+e.getMessage()+"   Cod: "+e.getSQLState());
			return retorno;
		}
		return retorno;
	}
	
	//Exclusão de uma Profissão no BD
	public boolean excluiProfissao(Profissoes profissao){
		boolean retorno = false;
		String sql = null;
		try{
			sql = "DELETE FROM tb_profissoes WHERE cod_profissao = "+profissao.getCod_profissao();
			st = conn.createStatement();
			int rst = st.executeUpdate(sql);
			if (rst == 1){
				retorno = true;
			}else{
				retorno = false;
			}
		}catch (SQLException e){
			Util.mensagemErro("Erro ao cadastrar profissão: "+e.getMessage()+"   Cod: "+e.getSQLState());
			return retorno;
		}
		return retorno;
	}
}
