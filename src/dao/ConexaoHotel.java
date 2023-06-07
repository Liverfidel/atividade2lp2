package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoHotel {
		
		private ConexaoHotel() {}
		
		private static Connection conexao = null;
		
		public static Connection getConexao() throws SQLException {
			
			if(conexao == null) {
				String dbURL = "jdbc:mysql://localhost:3306/hotel";
				String username = "root";
				String senha = "";
				
				ConexaoHotel.conexao = DriverManager.getConnection(dbURL, username, senha);
			}
			
			return conexao;
		}
		
	}
