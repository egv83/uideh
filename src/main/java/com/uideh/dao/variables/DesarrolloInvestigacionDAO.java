package com.uideh.dao.variables;

import com.uideh.model.Desainvestigacion;
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
public class DesarrolloInvestigacionDAO {
    @PersistenceContext(unitName = "com.uideh_uideh_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public void crear(Desainvestigacion desaInvestigacion) {
        em.persist(desaInvestigacion);
    }

    public void modificar(Desainvestigacion desaInvestigacion) {
        em.merge(desaInvestigacion);
    }

    public void eliminar(Desainvestigacion desaInvestigacion) {
        em.remove(em.find(Desainvestigacion.class, desaInvestigacion.getIddesainvestigacion()));
    }

    public List<Desainvestigacion> allDesainvestigacion() {
        Query query = em.createNamedQuery("Desainvestigacion.findAll");
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            return query.getResultList();
        }
        return null;
    }
    
    public Desainvestigacion desaInvestigacionId(Long id){
        Query query = em.createNamedQuery("Desainvestigacion.findByIddesainvestigacion");
        query.setParameter("iddesainvestigacion", id);
        if(query.getResultList()!=null && !query.getResultList().isEmpty()){
            return (Desainvestigacion)query.getResultList().get(0);
        }
        return null;
    }
}
