package br.com.customerAccount.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.customerAccount.model.Cliente;

/**
 * 
 * @author miguel
 * 
 *         Classe de persistencia da classe Cliente, extende as funcionalidade
 *         da GenericDAO
 *
 */
public class ClienteDAO extends GenericDAO<Cliente> {

	public ClienteDAO() {
		super(Cliente.class);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listaByIntervalId(int min, int max) {
		reset();
		StringBuilder hql = new StringBuilder();

		hql.append(" SELECT c ");
		hql.append(" FROM ");
		hql.append(" 	Cliente c ");
		hql.append(" WHERE ");
		hql.append(" 	 c.id >= :min ");
		hql.append(" AND c.id <= :max ");
		hql.append(" ORDER BY c.saldo DESC");

		Query q = manager.createQuery(hql.toString());
		q.setParameter("min", min);
		q.setParameter("max", max);

		return q.getResultList();
	}

	public double mediaSaldoCliente(int idMin, int idMax, double minSaldo) {
		reset();
		StringBuilder hql = new StringBuilder();

		hql.append(" SELECT COALESCE(AVG(c.saldo),0) ");
		hql.append(" FROM ");
		hql.append(" 	Cliente c ");
		hql.append(" WHERE ");
		hql.append(" 	 c.id >= :min ");
		hql.append(" AND c.id <= :max ");
		hql.append(" AND c.saldo > :minSaldo ");

		Query q = manager.createQuery(hql.toString());
		
		q.setParameter("min", idMin)
		.setParameter("max", idMax)
		.setParameter("minSaldo", minSaldo);

		return (double) q.getSingleResult();
	}
}
