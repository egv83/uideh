package com.uideh.dao.matriz;

import com.uideh.model.Investigacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Esteban VAllejo
 */
@Stateless
public class InvestigacionDAO {
    
    @PersistenceContext(unitName = "com.uideh_uideh_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
     public void crear(Investigacion investigacion) {
        em.persist(investigacion);
    }

    public void modificar(Investigacion investigacion) {
        em.merge(investigacion);
    }

    public void eliminar(Investigacion investigacion) {
        em.remove(em.find(Investigacion.class, investigacion.getIdinvestigacion()));
    }
    
    public List<Investigacion> allAgentes() {
        Query query = em.createNamedQuery("Flagrancia.findAll");
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            return query.getResultList();
        }
        return null;
    }
}
