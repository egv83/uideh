package com.uideh.dao.variables;

import com.uideh.model.Actproductivas;
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
public class ActividadesProductivasDAO {
    
    @PersistenceContext(unitName = "com.uideh_uideh_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public void crear(Actproductivas actproductivas) {
        em.persist(actproductivas);
    }

    public void modificar(Actproductivas actproductivas) {
        em.merge(actproductivas);
    }

    public void eliminar(Actproductivas actproductivas) {
        em.remove(em.find(Actproductivas.class, actproductivas.getIdactproductivas()));
    }

    public List<Actproductivas> allResultados() {
        Query query = em.createNamedQuery("Actproductivas.findAll");
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            return query.getResultList();
        }
        return null;
    }
    
    public Actproductivas actProductivasId(Long id){
        Query query = em.createNamedQuery("Actproductivas.findByIdactproductivas");
        query.setParameter("idactproductivas", id);
        if(query.getResultList()!=null && !query.getResultList().isEmpty()){
            return (Actproductivas)query.getResultList().get(0);
        }
        return null;
    }
}
