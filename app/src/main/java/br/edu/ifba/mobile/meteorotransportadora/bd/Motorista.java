package br.edu.ifba.mobile.meteorotransportadora.bd;

public class Motorista {
	private long codigo = -1;
	private String nome;
	private String telefone;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getTelefone() { return telefone; }

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() { return nome; }

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString () { return nome + "\n" + " Telefone: " + telefone; }

}
