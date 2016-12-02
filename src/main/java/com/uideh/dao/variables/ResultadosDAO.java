package com.uideh.dao.variables;

import com.uideh.model.Resultados;
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
public class ResultadosDAO {
    
    @PersistenceContext(unitName = "com.uideh_uideh_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public void crear(Resultados resultados) {
        em.persist(resultados);
    }

    public void modificar(Resultados resultados) {
        em.merge(resultados);
    }

    public void eliminar(Resultados resultados) {
        em.remove(em.find(Resultados.class, resultados.getIdresultados()));
    }

    public List<Resultados> allResultados() {
        Query query = em.createNamedQuery("Resultados.findAll");
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            return query.getResultList();
        }
        return null;
    }
    
    public Resultados resultadosId(Long id){
        Query query = em.createNamedQuery("Resultados.findByIdresultados");
        query.setParameter("idresultados", id);
        if(query.getResultList() != null && !query.getResultList().isEmpty()){
            return (Resultados)query.getResultList().get(0);
        }
        return null;
    }
}
