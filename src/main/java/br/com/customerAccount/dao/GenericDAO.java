package br.com.customerAccount.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.customerAccount.util.JPAUtil;

/**
 * 
 * @author miguel
 * 
 *         Classe generica utilizada para funcionalidades comuns entre as
 *         entidades.
 *
 * @param <T>
 */
public abstract class GenericDAO<T> {
	protected EntityManager manager;

	private Class<T> persistedClass;

	/**
	 * Funcao que adiciona a manager a instancia atual do entity manager
	 */
	protected void reset() {
		manager = new JPAUtil().getEntityManager();
	}

	protected GenericDAO() {
	}

	protected GenericDAO(Class<T> persistedClass) {
		this();
		this.persistedClass = persistedClass;
	}

	public T salvar(T entity) {
		reset();
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.getTransaction().commit();

		return entity;
	}

	public T atualizar(T entity) {
		reset();
		manager.getTransaction().begin();
		manager.merge(entity);
		manager.getTransaction().commit();

		return entity;
	}

	public T buscar(int id) {
		reset();
		return manager.find(persistedClass, id);
	}

	public T remover(int id) {
		reset();
		manager.getTransaction().begin();

		T obj = buscar(id);
		manager.merge(obj);
		manager.remove(obj);
		manager.getTransaction().commit();

		return obj;

	}

	/**
	 * 
	 * @return Lista de todos os registros do banco referente a classe passada ao
	 *         construtor
	 */
	@SuppressWarnings("unchecked")
	public List<T> listAll() {
		reset();

		StringBuilder hql = new StringBuilder();

		hql.append("	FROM ");
		hql.append("		" + persistedClass.getSimpleName());

		Query q = manager.createQuery(hql.toString());

		return q.getResultList();
	}
}
