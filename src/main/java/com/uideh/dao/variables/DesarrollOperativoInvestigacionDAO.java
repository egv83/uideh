package com.uideh.dao.variables;

import com.uideh.model.Desaoperativo;
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
public class DesarrollOperativoInvestigacionDAO {
    
    @PersistenceContext(unitName = "com.uideh_uideh_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public void crear(Desaoperativo desaOperativo) {
        em.persist(desaOperativo);
    }

    public void modificar(Desaoperativo desaOperativo) {
        em.merge(desaOperativo);
    }

    public void eliminar(Desaoperativo desaOperativo) {
        em.remove(em.find(Desaoperativo.class, desaOperativo.getIddesaoperativo()));
    }

    public List<Desaoperativo> allDesarrollOperativoInv() {
        Query query = em.createNamedQuery("Desaoperativo.findAll");
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            return query.getResultList();
        }
        return null;
    }
    
    public Desaoperativo desaoperativoId(Long id){
        Query query = em.createNamedQuery("Desaoperativo.findByIddesaoperativo");
        query.setParameter("iddesaoperativo", id);
        if(query.getResultList()!=null && !query.getResultList().isEmpty()){
            return (Desaoperativo)query.getResultList().get(0);
        }
        return null;
    }
}
