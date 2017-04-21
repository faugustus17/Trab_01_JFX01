package com.trab01JFX.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.Connection;
import com.trab01JFX.modelo.Usuarios;
import com.trab01JFX.util.Util;

public class UsuarioDao {
	Conexao conexao = new Conexao();
	Connection conn = conexao.abreConexaoBD();
	ResultSet rs = null;
	Statement st = null;
			
	//Consulta se o usuario existe no banco de dados
	public int consultaUsuario(String usuario){
		int retorno = 0;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_usuarios WHERE usuario = '"+usuario+"'");
			if(rs.next()){
				retorno = 1;
			}else{
				retorno = 2;
			}
			return retorno;
		}catch (SQLException e){
			Util.mensagemErro("Erro: "+e.getMessage()+"   Cod: "+e.getSQLState());
			return retorno;
		}
	}

	//Consulta se senha existe no banco de dados
	public int consultaSenha(String senha){
		int retorno = 0;
		
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_usuarios WHERE senha = '"+senha+"'");
			if(rs.next()){
				retorno = 1;
			}else{
				retorno = 2;
			}
			return retorno;
		}catch (SQLException e){
			Util.mensagemErro("Erro: "+e.getMessage()+"   Cod: "+e.getSQLState());
			return retorno;
		}
	}
	
	//Cadastra usuario e senha
	public int cadastraUsuario(Usuarios usuario){
		int retorno = 0;
		String sql=null;
		if(this.consultaUsuario(usuario.getUsuario())==1){
			Util.mensagemErro("Usuário já cadastrado!");
			retorno = 2;
		}else{
			try{
				sql = "INSERT INTO tb_usuarios(nome_usuario, usuario, senha) VALUES(";
				sql+="'"+usuario.getNome_usuario()+"','"+usuario.getUsuario()+"','"+usuario.getSenha()+"')";
				st = conn.createStatement();
				int rst = st.executeUpdate(sql);
				if (rst == 1){
					retorno = 1;
				}else{
					retorno = 0;
				}
				
			}catch (SQLException e){
				Util.mensagemErro("Erro ao cadastrar usuário: "+e.getMessage()+"   Cod: "+e.getSQLState());
				return retorno;
			}
		}
		return retorno;
	}
	
	//Mostra senha e usuario se tiver esquecido
	public Usuarios esqueceuUsuario(String nome){
		Usuarios usuario = new Usuarios();

		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_usuarios WHERE nome_usuario = '"+nome+"'");
			if(rs.next()){
				usuario.setNome_usuario(rs.getString(2));
				usuario.setUsuario(rs.getString(3));
				usuario.setSenha(rs.getString(4));
			}else{
				usuario = null;
			}
			return usuario;
		}catch (SQLException e){
			Util.mensagemErro("Erro: "+e.getMessage()+"   Cod: "+e.getSQLState());
			return usuario = null;
		}
	}

}
