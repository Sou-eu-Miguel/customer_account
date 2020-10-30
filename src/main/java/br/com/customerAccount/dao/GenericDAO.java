package br.com.customerAccount.dao;

import br.com.customerAccount.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @param <T>
 * @author miguel
 * <p>
 * Classe generica utilizada para funcionalidades comuns entre as
 * entidades.
 */
public abstract class GenericDAO<T> {
    protected EntityManager manager;

    private Class<T> persistedClass;

    protected GenericDAO() {
    }

    protected GenericDAO(Class<T> persistedClass) {
        this();
        this.persistedClass = persistedClass;
    }

    /**
     * Funcao que adiciona a manager a instancia atual do entity manager
     */
    protected void reset() {
        manager = new JPAUtil().getEntityManager();
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
     * @return Lista de todos os registros do banco referente a classe passada ao
     * construtor
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
