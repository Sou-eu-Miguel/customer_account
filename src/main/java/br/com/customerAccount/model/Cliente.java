package br.com.customerAccount.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author miguel
 * 
 *         POJO contendo atributos da tabela cliente
 *
 */
@Entity
@Table(name = "tb_customer_account", schema = "public")
public class Cliente {

	@Id
	@Column(name = "id_customer")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientes_seq_gen")
	@SequenceGenerator(name = "clientes_seq_gen", sequenceName = "cliente_id_seq")
	private int id;

	@Column(name = "cpf_cnpj", unique = true)
	private int cpf_cnpj;

	@Column(name = "nm_customer")
	private String nome;

	@Column(name = "is_active")
	private boolean ativo;

	@Column(name = "vl_total")
	private double saldo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(int cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		StringBuilder cliente = new StringBuilder();

		cliente.append("\nCliente.: " + id);
		cliente.append("\nNome....: " + nome);
		cliente.append("\nCPF.....: " + cpf_cnpj);
		cliente.append("\nSaldo...: " + saldo);

		return cliente.toString();
	}
}
