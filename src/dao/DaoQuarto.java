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

public class DaoQuarto {

		
		public boolean inserir(Quarto quarto) throws SQLException {
					
			Connection conexao = ConexaoHotel.getConexao();
			
			String sql = "insert into quarto (numQuarto, andar, tipo, cliente_id) values(? , ? , ?, ?);";
			PreparedStatement ps = conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setInt(1, quarto.getNumQuarto());
			ps.setString(2, quarto.getAndar());
			ps.setString(3, quarto.getTipo());
			ps.setInt(4, quarto.getCliente().getId());

			int linhasAfetadas = ps.executeUpdate();
			
			ResultSet r = ps.getGeneratedKeys();
			
			if( r.next() ) {
				int numQuarto = r.getInt(1);	
				quarto.setNumQuarto(numQuarto);
			}
			
			return (linhasAfetadas == 1 ? true : false);
		}

		public boolean atualizar(Quarto quarto) throws SQLException {
			Connection con = ConexaoHotel.getConexao();
			
			String sql = "update quarto set andar = ?, tipo = ? where numQuarto = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, quarto.getAndar());
			ps.setString(2, quarto.getTipo());
			ps.setInt(3, quarto.getNumQuarto());
			
			if( ps.executeUpdate() == 1) {
				return true;
			}else {
				return false;
			}
		}

		public boolean excluir(int id) throws SQLException {
			Connection con = ConexaoHotel.getConexao();
			
			String sql = "delete from quarto where numQuarto = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			if( ps.executeUpdate() == 1) {
				return true;
			}else {
				return false;
			}
		}

		public Quarto buscar(int idBuscado) throws SQLException {
			
			Connection con = ConexaoHotel.getConexao();
			
			String sql = "select * from quarto where numQuarto = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idBuscado);
			
			ResultSet result = ps.executeQuery();
			
			Quarto quarto = null;
			
			if( result.next() ) {
				int numQuarto = result.getInt("numQuarto");
				String andar = result.getString("andar");
				String tipo = result.getString("tipo");
				int idCliente = result.getInt("cliente_id");
				
				DaoCliente daoCli = new DaoCliente();
				Cliente c = daoCli.buscarPorId(idCliente);
				
				quarto = new Quarto(numQuarto, andar, tipo, c);
			}
			
			return quarto;
		}

		public List<Quarto> buscarTodas() throws SQLException {
			Connection con = ConexaoHotel.getConexao();
			
			String sql = "select * from quarto";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet result = ps.executeQuery();
			
			List<Quarto> quartos = new ArrayList<Quarto>();
			
			DaoCliente daoCli = new DaoCliente();

			while( result.next() ) {
				int numQuarto = result.getInt("numQuarto");
				String andar = result.getString("andar");
				String tipo = result.getString("tipo");
				int idCliente = result.getInt("cliente_id");
				
				Cliente c = daoCli.buscarPorId(idCliente);
				
				Quarto t = new Quarto(numQuarto, andar, tipo, c);
		
				quartos.add(t);
			}
			
			return quartos;
		}

		public List<Quarto> pesquisarQuarto(String texto) throws SQLException {
			Connection con = ConexaoHotel.getConexao();
			
			String sql = "select * from quarto where andar like ? ";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+texto+"%");
			
			ResultSet result = ps.executeQuery();
			
			List<Quarto> quartos = new ArrayList<Quarto>();
			
			DaoCliente daoCli = new DaoCliente();
			
			while( result.next() ) {
				int numQuarto = result.getInt("numQuarto");
				String andar = result.getString("andar");
				String tipo = result.getString("tipo");
				int idCliente = result.getInt("cliente_id");
				
				Cliente c = daoCli.buscarPorId(idCliente);
				Quarto t = new Quarto(numQuarto, andar, tipo, c);
		
				quartos.add(t);
			}
			
			return quartos;
		}
		
		public List<Quarto> quartoPorCliente(String nome) throws SQLException {
			Connection con = ConexaoHotel.getConexao();
			
			String sql = "select * from quarto left join cliente on quarto.cliente_id = cliente.id where cliente.nome = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nome);
			
			ResultSet result = ps.executeQuery();
			
			List<Quarto> quartos = new ArrayList<Quarto>();
			
			
			if( result.next() ) {			
				int idCli= result.getInt("cliente_id");
				String email = result.getString("email");
				String senha = result.getString("senha");
				
				Cliente cliente = new Cliente(idCli, nome, email, senha);
				
				do {
					int numQuarto = result.getInt("numQuarto");
					String andar = result.getString("andar");
					String tipo = result.getString("tipo");
					
					Quarto q = new Quarto(numQuarto, andar, tipo, cliente);
			
					quartos.add(q);
				}while(result.next());
			}
			
			return quartos;
		}
	}

