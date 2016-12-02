package com.uideh.dao.matriz;

import com.uideh.model.Flagrancia;
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
public class FlagranciasDAO {
    
    @PersistenceContext(unitName = "com.uideh_uideh_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
     public void crear(Flagrancia flagrancia) {
        em.persist(flagrancia);
    }

    public void modificar(Flagrancia flagrancia) {
        em.merge(flagrancia);
    }

    public void eliminar(Flagrancia flagrancia) {
        em.remove(em.find(Flagrancia.class, flagrancia.getIdflagrancia()));
    }
    
    public List<Flagrancia> allAgentes() {
        Query query = em.createNamedQuery("Flagrancia.findAll");
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            return query.getResultList();
        }
        return null;
    }
}
