package entidades;

public class Quarto {

	private int numQuarto;
	private String andar;
	private String tipo;
	private Cliente cliente;
	
	public Quarto(String andar, String tipo, Cliente cliente) {
		this.andar = andar;
		this.tipo = tipo;
		this.cliente = cliente;
	}
	
	public Quarto(int numQuarto, String andar, String tipo, Cliente cliente) {
		this.numQuarto = numQuarto;
		this.andar = andar;
		this.tipo = tipo;
		this.cliente = cliente;
	}

	public int getNumQuarto() {
		return numQuarto;
	}

	public void setNumQuarto(int numQuarto) {
		this.numQuarto = numQuarto;
	}

	public String getAndar() {
		return andar;
	}

	public void setAndar(String andar) {
		this.andar = andar;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	

}
