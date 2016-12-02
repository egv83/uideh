package com.uideh.dao.variables;

import com.uideh.model.Autoproyecto;
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
public class AutorizacionProyectoDAO {
    
    @PersistenceContext(unitName = "com.uideh_uideh_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public void crear(Autoproyecto autoproyecto) {
        em.persist(autoproyecto);
    }

    public void modificar(Autoproyecto autoproyecto) {
        em.merge(autoproyecto);
    }

    public void eliminar(Autoproyecto autoproyecto) {
        em.remove(em.find(Autoproyecto.class, autoproyecto.getIdautoproyecto()));
    }

    public List<Autoproyecto> allResultados() {
        Query query = em.createNamedQuery("Autoproyecto.findAll");
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            return query.getResultList();
        }
        return null;
    }
    
    public Autoproyecto autoProyectId(Long id){
        Query query = em.createNamedQuery("Autoproyecto.findByIdautoproyecto");
        query.setParameter("idautoproyecto", id);
        if(query.getResultList()!=null && !query.getResultList().isEmpty()){
            return (Autoproyecto)query.getResultList().get(0);
        }
        return null;
    }
}
