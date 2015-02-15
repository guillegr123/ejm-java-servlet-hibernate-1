/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploh2.persistencia.dao;

import ejemploh2.persistencia.HibernateUtil;
import ejemploh2.persistencia.Libro;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author java
 */
public class LibroDao implements AutoCloseable {

    private Session sesion = null;
    private Transaction transaccion = null;
    
    private Session getSesionHibernate() throws Throwable {
        if (sesion == null) {
            sesion = HibernateUtil.getSessionFactory().openSession();
            transaccion = sesion.beginTransaction();
        }
        return sesion;
    }
    
    public List<Libro> obtenerTodos() throws Throwable {
        Session s = getSesionHibernate();
        return s.createCriteria(Libro.class).list();
    }
    
    public Libro obtenerPorId(int id) throws Throwable {
        Session s = getSesionHibernate();
        return (Libro)s.get(Libro.class, id);
    }
    
    public List<Libro> buscarPorTitulo(String tituloBusq) throws Throwable {
        Criteria q
            = getSesionHibernate().createCriteria(Libro.class)
                .add(Restrictions.ilike("titulo", "%" + tituloBusq + "%"));
        return q.list();
    }
    
    public void eliminar(Libro libro) throws Throwable {
        getSesionHibernate().delete(libro);
    }
    
    public void guardar(Libro libro) throws Throwable {
        Session s = getSesionHibernate();
        s.saveOrUpdate(libro);
    }
    
    @Override
    public void close() throws Exception {
        if (sesion != null) {
            if (sesion.isOpen()) {
                if (transaccion != null) {
                    if (transaccion.isActive()) {
                        try {
                            transaccion.commit();
                        } catch(Throwable t) {
                            transaccion.rollback();
                        }
                    }
                    transaccion = null;
                }
                sesion.close();
            }
            sesion = null;
        }
    }
    
}
