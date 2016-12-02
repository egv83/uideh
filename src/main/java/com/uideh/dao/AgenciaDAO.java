package com.uideh.dao;

import com.uideh.model.Agencia;
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
public class AgenciaDAO {

    @PersistenceContext(unitName = "com.uideh_uideh_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    //List<Agencia> lista;

    public void crear(Agencia agencia) {
        em.persist(agencia);
    }

    public void modificar(Agencia agencia) {
        em.merge(agencia);
    }

    public void eliminar(Agencia agencia) {
        em.remove(em.find(Agencia.class, agencia.getIdagencia()));
    }

    public List<Agencia> allAgencias() {
        Query query = em.createNamedQuery("Agencia.findAll");
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            //lista = query.getResultList();
            return query.getResultList();
        }
        //return lista;
        return null;
    }
}
