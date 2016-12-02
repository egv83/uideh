package com.uideh.dao.variables;

import com.uideh.model.Desoperativo;
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
public class DesarrollOperativoDAO {
    
    @PersistenceContext(unitName = "com.uideh_uideh_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public void crear(Desoperativo desoperativo) {
        em.persist(desoperativo);
    }

    public void modificar(Desoperativo desoperativo) {
        em.merge(desoperativo);
    }

    public void eliminar(Desoperativo desoperativo) {
        em.remove(em.find(Desoperativo.class, desoperativo.getIddesoperativo()));
    }

    public List<Desoperativo> allDesarrollOperativo() {
        Query query = em.createNamedQuery("Desoperativo.findAll");
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            return query.getResultList();
        }
        return null;
    }
    
    public Desoperativo desOperativoId(Long id){
        Query query = em.createNamedQuery("Desoperativo.findByIddesoperativo");
        query.setParameter("iddesoperativo", id);
        if(query.getResultList()!=null && !query.getResultList().isEmpty()){
            return (Desoperativo)query.getResultList().get(0);
        }
        return null;
    }
}
