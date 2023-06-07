package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.DaoQuarto;
import dao.DaoCliente;
import entidades.Quarto;
import entidades.Cliente;

public class Programa {

	private static DaoQuarto daoQuarto = new DaoQuarto();
	private static DaoCliente daoCliente = new DaoCliente();
	
	public static void main(String[] args) throws SQLException {
		
		Scanner scanner = new Scanner(System.in);
		int opcao;
		
		do {
			System.out.println("Digite:");
			System.out.println("1 - Cadastrar Quarto");
			System.out.println("2 - Atualizar Quarto");
			System.out.println("3 - Buscar Quarto");
			System.out.println("4 - Excluir Quarto");
			System.out.println("5 - Listar Quartos");
			System.out.println("6 - Pesquisar Quartos");
			System.out.println("7 - Cadastrar Cliente");
			System.out.println("8 - Listar Clientes");
			System.out.println("9 - Listar quarto por cliente");
			System.out.println("0 - Sair");
			
			opcao = Integer.parseInt( scanner.nextLine() );
			
			switch(opcao) {
				case 1:
					cadastrarQuarto();
					break;
				case 2:
					atualizarQuarto();
					break;
				case 3:
					buscarQuarto();
					break;
				case 4:
					excluirQuarto();
					break;
				case 5:
					listarQuartos();
					break;
				case 6:
					pesquisarQuartos();
					break;
				case 7:
					cadastrarCliente();
					break;
				case 8:
					listarClientes();
					break;
				case 9:
					quartoPorCliente();
					break;
				case 0:
					System.out.println("Até mais.");
					break;
				default:
					System.out.println("Opção inválida!");
			}
			
		}while(opcao != 0);
	}
	
	public static void cadastrarQuarto() throws SQLException {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite o andar: ");
		String andar = scanner.nextLine();
		
		System.out.println("Informe o tipo: ");
		String tipo = scanner.nextLine();
		
		System.out.println("Informe o ID do Usuário: ");
		int idCli = Integer.parseInt(scanner.nextLine());

		Cliente c = daoCliente.buscarPorId(idCli);
		
		if(c != null) {
			Quarto q = new Quarto(andar, tipo, c);
	
			System.out.println( daoQuarto.inserir( q ) ? "Cadastrou!" : "Falha do cadastro...");
	
			System.out.println("Tarefa cadastrada sob o ID " + q.getNumQuarto());
		}else {
			System.out.println("Não existe usuário com o ID informado!");
		}
	}
	
	public static void atualizarQuarto() throws SQLException{
		System.out.println("##### Atualizando Quarto #####");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o numero do quarto: ");
		int numQuarto = Integer.parseInt(scanner.nextLine());

		Quarto quarto = daoQuarto.buscar(numQuarto);
		
		if(quarto != null) {
			
			System.out.println("Andar atual do quarto: " + quarto.getAndar());
			System.out.println("Informe o novo andar ou pressione enter:");
			
			String and = scanner.nextLine();
			
			if(and.length() > 0) {
				quarto.setAndar(and);
			}
			
			System.out.println("Tipo atual do quarto: " + quarto.getTipo());
			System.out.println("Informe o novo tipo do  ou pressione enter:");
			
			String tip = scanner.nextLine();
			
			if(tip.length() > 0) {
				quarto.setTipo(tip);
			}
			
			if( daoQuarto.atualizar(quarto) ) {
				System.out.println("Quarto atualizada!");
			}else {
				System.out.println("Houve um erro ao atualizar.");
			}
			
		}else {
			System.out.println("Erro ao localizar tarefa. A tarefa "+ numQuarto +" existe?");
		}
	}
	
	public static void buscarQuarto() throws SQLException {
		System.out.println("\n##### Buscando Quarto por numero ######");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o numero do quarto: ");
		int numQuarto = Integer.parseInt(scanner.nextLine());

		Quarto q = daoQuarto.buscar(numQuarto);
		
		if(q != null) {
			System.out.println("ID: " + q.getNumQuarto());
			System.out.println("Andar: " + q.getAndar());
			System.out.println("tipo: " + q.getTipo());
			System.out.println("Cliente: " + q.getCliente().getNome() +"\n");
		}else {
			System.out.println("Quarto não existe...");
		}
	}

	public static void excluirQuarto() throws SQLException{
		System.out.println("\n##### Excluindo Quarto pelo numero ######");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o numero do quarto: ");
		int numQuarto = Integer.parseInt(scanner.nextLine());

		boolean r = daoQuarto.excluir(numQuarto);
		
		if( r ) {
			System.out.println("Quarto excluído!");
		}else {
			System.out.println("Houve um erro ao excluir. O quarto "+ numQuarto +" existe?");
		}
	}
	
	public static void listarQuartos() throws SQLException {
		
		System.out.println("\n##### Listar Quartos #####\n");
		
		List<Quarto> tasks = daoQuarto.buscarTodas();

		Scanner scanner = new Scanner(System.in);
		
		for(Quarto q : tasks) {
			System.out.println("Numero do Quarto: " + q.getNumQuarto());
			System.out.println("Andar: " + q.getAndar());
			System.out.println("Tipo: " + q.getTipo());
			System.out.println("Cliente: " + q.getCliente().getNome() +"\n");

			
			scanner.nextLine();
		}
	}

	public static void pesquisarQuartos() throws SQLException {
		System.out.println("\n##### Buscando Quarto por andar ######");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o andar: ");
		String pesquisa = scanner.nextLine();

		List<Quarto> tasks = daoQuarto.pesquisarQuarto(pesquisa);
		
		for(Quarto q : tasks) {
			System.out.println("Numero do Quarto: " + q.getNumQuarto());
			System.out.println("Andar: " + q.getAndar());
			System.out.println("Tipo: " + q.getTipo());
			System.out.println("Cliente: " + q.getCliente().getNome()+"\n");
			
			scanner.nextLine();
		}
	}

	public static void cadastrarCliente() throws SQLException{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite seu nome: ");
		String nome = scanner.nextLine();
		

		System.out.println("Digite seu email: ");
		String email = scanner.nextLine();
		
		System.out.println("Informe sua senha: ");
		String senha = scanner.nextLine();

		Cliente cliente = new Cliente(nome, email, senha);

		System.out.println( daoCliente.inserir( cliente ) ? "Cadastrou!" : "Falha do cadastro...");

		System.out.println("Cliente cadastrado sob o ID " + cliente.getId());
	}
	
	public static void listarClientes() throws SQLException {
		
		System.out.println("\n##### Listar Clientes #####\n");
		
		List<Cliente> cli = daoCliente.buscarTodos();
		
		for(Cliente c : cli) {
			System.out.println("ID: " + c.getId());
			System.out.println("Nome "+ c.getNome());
		}
	}
	
	public static void quartoPorCliente() throws SQLException{
		System.out.println("\n##### Listar Quarto por cliente #####\n");
		
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite o nome do cliente: ");
		String nome = scanner.nextLine();
		
		List<Quarto> tasks = daoQuarto.quartoPorCliente(nome);
		
		for(Quarto q : tasks) {
			System.out.println("numero do Quarto: " + q.getNumQuarto());
			System.out.println("Andar: " + q.getAndar());
			System.out.println("Tipo:"  + q.getTipo());
			System.out.println("Cliente: " + q.getCliente().getNome() +"\n");
			
			scanner.nextLine();
		}
	}
}