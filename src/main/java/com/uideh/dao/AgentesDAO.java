package com.uideh.dao;

import com.uideh.model.Agente;
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
public class AgentesDAO {

    @PersistenceContext(unitName = "com.uideh_uideh_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public void crear(Agente agente) {
        em.persist(agente);
    }

    public void modificar(Agente agente) {
        em.merge(agente);
    }

    public void eliminar(Agente agente) {
        em.remove(em.find(Agente.class, agente.getIdagente()));
    }

    /*public List<Agente> allAgentes() {
        StringBuilder consulta = new StringBuilder();
        consulta.append("SELECT a FROM Agente a");
        Query query = em.createQuery(consulta.toString());
        if(query.getResultList() != null && !query.getResultList().isEmpty()){
            return query.getResultList();
        }
        return null;
    }*/
    
    public List<Agente> allAgentes() {
        Query query = em.createNamedQuery("Agente.findAll");
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            return query.getResultList();
        }
        return null;
    }
    
    public Long totalAgentesPorAgencia(Agente agente){
        StringBuilder str = new StringBuilder();
        str.append("SELECT COUNT(a) FROM Agente a");
        str.append(" WHERE a.idagencia.idagencia=?1");
        str.append(" AND a.jefe=false");
        Query query = em.createQuery(str.toString());
        query.setParameter(1, agente.getIdagencia().getIdagencia());

        if(query.getSingleResult() != null ){
            System.out.println("CONSULTA SINGLE RESULT: "+query.getSingleResult());
            return (Long)query.getSingleResult();
        }

        return null;
    }
}
