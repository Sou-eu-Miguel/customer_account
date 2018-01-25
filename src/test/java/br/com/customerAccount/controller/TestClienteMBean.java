package br.com.customerAccount.controller;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


public class TestClienteMBean {

	ClienteMBean cBean;

	@Before
	public void init() {
		cBean = new ClienteMBean();
	}

	@Test
	public void testMediaSaldo() {
		assertEquals(cBean.mediaClienteAplicacao(1500,2700), cBean.mediaClienteBD(1500, 2700),4);
	}
	

}
