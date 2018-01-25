package br.com.customerAccount.aplicacao;

import br.com.customerAccount.controller.ClienteMBean;
import br.com.customerAccount.model.Cliente;

public class Aplicacao {
	public static void main(String[] args) {
		ClienteMBean cMbean = new ClienteMBean();

		// Adicionando n clientes em base de dados
		cMbean.adicionarClienteLote(3000);
		System.out.println("\n========Média de Salário===========\n");
		System.out.println("Média de salario gerada pela Ap: " + cMbean.mediaClienteAplicacao(1500, 2700));
		System.out.println("Média de salario gerada pelo BD: " + cMbean.mediaClienteBD(1500, 2700));

		System.out.println("\n========Participantes do calculo===========\n");
		for (Cliente cliente : cMbean.participantesMedia()) {
			System.out.println(cliente);
		}

	}
}
