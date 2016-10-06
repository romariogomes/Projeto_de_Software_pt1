package model;

import java.util.Date;

public class Produto {

	private Integer codigo;
	private String nome;
	private String descricao;
	private Date dataIni;
	private Date dataFim;
	
	public Produto() {
		// TODO Auto-generated constructor stub
	}

	public Produto(Integer codigo, String nome, String descricao, Date dataIni, Date dataFim) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.dataIni = dataIni;
		this.dataFim = dataFim;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + ", dataIni=" + dataIni
				+ ", dataFim=" + dataFim + "]";
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataIni() {
		return dataIni;
	}

	public void setDataIni(Date dataIni) {
		this.dataIni = dataIni;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
}
