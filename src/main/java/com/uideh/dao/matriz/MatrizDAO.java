package com.uideh.dao.matriz;

import com.uideh.model.Matriz;
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
public class MatrizDAO {
    
    @PersistenceContext(unitName = "com.uideh_uideh_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
     public void crear(Matriz matriz) {
        em.persist(matriz);
    }

    public void modificar(Matriz matriz) {
        em.merge(matriz);
    }

    public void eliminar(Matriz matriz) {
        em.remove(em.find(Matriz.class, matriz.getIdmatriz()));
    }
    
    public List<Matriz> allAgentes() {
        Query query = em.createNamedQuery("Flagrancia.findAll");
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            return query.getResultList();
        }
        return null;
    }
}
