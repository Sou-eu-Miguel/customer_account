package br.com.customerAccount.model;

import br.com.customerAccount.dao.ClienteDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TestCliente {

    @Mock
    private ClienteDAO dao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        Cliente cliente_mock = new Cliente();
        cliente_mock.setAtivo(true);
        cliente_mock.setNome("Teste");

        when(dao.buscar(1)).thenReturn(cliente_mock);

        Cliente c = dao.buscar(1);
        assertEquals("Teste", c.getNome());
        assertTrue(c.isAtivo());
    }

}
