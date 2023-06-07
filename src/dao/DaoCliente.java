package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Quarto;
import entidades.Cliente;

public class DaoCliente {
	
		
		public boolean inserir(Cliente cliente) throws SQLException {
			
			Connection conexao = ConexaoHotel.getConexao();
			
			String sql = "insert into cliente (nome, email, senha) values(? , ?, sha2( ? , 256 ));";
			PreparedStatement ps = conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getEmail());
			ps.setString(3, cliente.getSenha());

			int linhasAfetadas = ps.executeUpdate();
			
			ResultSet r = ps.getGeneratedKeys();
			
			if( r.next() ) {
				int id = r.getInt(1);	
				cliente.setId(id);
			}
			
			return (linhasAfetadas == 1 ? true : false);
		}
		
		public boolean atualizarDados(Cliente cliente) throws SQLException {
			return true;
		}
		
		public boolean atualizarSenha(Cliente cliente) throws SQLException {
			return true;
		}

		public boolean excluir(int id) throws SQLException {
			return true;
		}
		
		public Cliente buscarPorId(int idBuscado) throws SQLException {
			Connection con = ConexaoHotel.getConexao();
			
			String sql = "select * from cliente where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idBuscado);
			
			ResultSet result = ps.executeQuery();
			
			Cliente cliente = null;
			
			if( result.next() ) {
				int id = result.getInt("id");
				String nome = result.getString("nome");
				String email = result.getString("email");
				String senha = result.getString("senha");
				
				cliente = new Cliente(id, nome, email, senha);
			}
			
			return cliente;
		}
		
		public Cliente buscarPorEmail(String email) throws SQLException {
			return null;
		}
		
		public List<Cliente> buscarTodos() throws SQLException {
			Connection con = ConexaoHotel.getConexao();
			
			String sql = "select * from cliente";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet result = ps.executeQuery();
			
			List<Cliente> clientes = new ArrayList<Cliente>();
			
			while( result.next() ) {
				int id = result.getInt("id");
				String nome = result.getString("nome");
				String email = result.getString("email");
				String senha = result.getString("senha");
				
				Cliente cliente = new Cliente(id,nome, email, senha);
		
				clientes.add(cliente);
			}
			
			return clientes;
		}
	}


