package com.trab01JFX.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class Conexao {
	//usuario e senha
	private String login = "root";
	private String senha = "XXXX";
	// IP do servidor do banco
	private String host = "localhost";
	// Nome do banco de dados
	private String dbName = "db_trab_jfx_01";
	// URL de conexao do banco
	private String url = "jdbc:mysql://" + host + "/" + dbName+"?useSSL=false";
	// Objeto do tipo Connection para estabelecer a conexao
	public Connection conexao = null;
	
	//construtor
	public Conexao() { }

	public Connection abreConexaoBD()
	{
		try
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch (ClassNotFoundException ex)
			{
				return null;
			}
			try
			{
				// efetua conexao com o banco de dados
				this.conexao = (Connection) DriverManager.getConnection(url,login,senha);
			}
			catch (SQLException ex)
			{
				return null;
			}
			return this.conexao;
		}
		catch (Exception e)
		{
			return null;
		}
	}

}
