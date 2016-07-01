package br.edu.ifba.mobile.meteorotransportadora.bd;

public class Destino {
	private long codigo = -1;
	private String nome;
	private int quantidade;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public int getQuantidade() { return quantidade; }

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getNome() { return nome; }

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString () { return nome + "\n" + " Quantidade: " + quantidade; }

}
