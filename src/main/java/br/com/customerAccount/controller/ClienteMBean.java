package br.com.customerAccount.controller;

import br.com.customerAccount.dao.ClienteDAO;
import br.com.customerAccount.model.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miguel
 * <p>
 * Mbean que consome as funções do ClienteDAO
 */
public class ClienteMBean {
    private static final Double MIN_SALDO = 560.00;
    List<Cliente> clientes;

    public ClienteMBean() {
        clientes = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        ClienteDAO cDAO = new ClienteDAO();
        cDAO.salvar(cliente);
    }

    public List<Cliente> listagemCliente() {
        return new ClienteDAO().listAll();
    }

    public List<Cliente> listagemIntervalo(int min, int max) {
        return new ClienteDAO().listaByIntervalId(min, max);
    }

    public Cliente removerCliente(Cliente cliente) {
        return new ClienteDAO().remover(cliente.getId());
    }

    public double mediaClienteAplicacao(int min, int max) {

        double mediaCliente = 0;
        for (Cliente cliente : listagemIntervalo(min, max)) {
            if (cliente.getSaldo() < MIN_SALDO) {
                continue;
            }
            this.clientes.add(cliente);
            mediaCliente += cliente.getSaldo();
        }
        int qtdClientes = clientes.size();
        return (qtdClientes != 0) ? mediaCliente / qtdClientes : 0;
    }

    public double mediaClienteBD(int min, int max) {
        return new ClienteDAO().mediaSaldoCliente(min, max, MIN_SALDO);
    }

    /**
     * Função para inserir no banco n registros, não há validação de cpf, ou seja os
     * valores do cpf são gerados por inteiros sequenciais, já que não podem ser
     * repetidos
     *
     * @param n quantidade de registros a serem inseridos
     */
    public void adicionarClienteLote(int n) {
        ClienteDAO dao = new ClienteDAO();

        Cliente cliente = new Cliente();

        for (int i = 0; i < n; i++) {

            cliente.setCpf_cnpj(i + 1);
            cliente.setSaldo(i * 100);
            cliente.setNome("Cliente_Teste_Lote");

            dao.salvar(cliente);
            cliente = new Cliente();
        }
    }

    public List<Cliente> participantesMedia() {
        return clientes;
    }

}
