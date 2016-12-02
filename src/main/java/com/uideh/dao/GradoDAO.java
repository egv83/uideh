package com.uideh.dao;

import com.uideh.model.Grado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Esteban Vallejo
 */
@Stateless
public class GradoDAO {

    @PersistenceContext(unitName = "com.uideh_uideh_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    //List<Agencia> lista;

    public void crear(Grado grado) {
        em.persist(grado);
    }

    public void modificar(Grado grado) {
        em.merge(grado);
    }

    public void eliminar(Grado grado) {
        em.remove(em.find(Grado.class, grado.getIdgrado()));
    }

    public List<Grado> allGrados() {
        Query query = em.createNamedQuery("Grado.findAll");
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            //lista = query.getResultList();
            return query.getResultList();
        }
        //return lista;
        return null;
    }
}
