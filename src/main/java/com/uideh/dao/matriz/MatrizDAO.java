package com.uideh.dao.matriz;

import com.uideh.model.Agente;
import com.uideh.model.Matriz;
import java.util.Calendar;
import java.util.Date;
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
    
    public Long promedioPorAgencia(Agente agente, String ano, String mes){

        StringBuilder consulta = new StringBuilder();
        consulta.append("SELECT SUM(m.total)/COUNT(m) FROM Matriz m ");
        consulta.append(" WHERE m.idagente.idagencia.idagencia=?1");
        consulta.append(" AND m.idagente.jefe=false");
        consulta.append(" AND m.fecha>=DATE(?2)");
        consulta.append(" AND m.fecha<=DATE(?3)");

        Query query = em.createQuery(consulta.toString());
        query.setParameter(1, agente.getIdagencia().getIdagencia());
        query.setParameter(2, "'"+ano+"-"+mes+"-01'");
        query.setParameter(3, "'"+ano+"-"+mes+"-31'");

        if(query.getSingleResult() != null){
            return (Long)query.getSingleResult();
        }
        return null;
    }
}
