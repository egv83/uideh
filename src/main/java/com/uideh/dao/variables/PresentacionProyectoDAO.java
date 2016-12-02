package com.uideh.dao.variables;

import com.uideh.model.Presproyecto;
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
public class PresentacionProyectoDAO {
    
    @PersistenceContext(unitName = "com.uideh_uideh_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public void crear(Presproyecto presproyecto) {
        em.persist(presproyecto);
    }

    public void modificar(Presproyecto presproyecto) {
        em.merge(presproyecto);
    }

    public void eliminar(Presproyecto presproyecto) {
        em.remove(em.find(Presproyecto.class, presproyecto.getIdpresproyecto()));
    }

    public List<Presproyecto> allResultados() {
        Query query = em.createNamedQuery("Presproyecto.findAll");
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            return query.getResultList();
        }
        return null;
    }
    
    public Presproyecto presProyectId(Long id){
        Query query = em.createNamedQuery("Presproyecto.findByIdpresproyecto");
        query.setParameter("idpresproyecto", id);
        if(query.getResultList()!=null && !query.getResultList().isEmpty()){
            return (Presproyecto)query.getResultList().get(0);
        }
        return null;
    }
}
