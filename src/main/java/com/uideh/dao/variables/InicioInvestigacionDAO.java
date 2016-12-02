package com.uideh.dao.variables;

import com.uideh.model.Inicinvetigacion;
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
public class InicioInvestigacionDAO {
    @PersistenceContext(unitName = "com.uideh_uideh_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public void crear(Inicinvetigacion inicinvetigacion) {
        em.persist(inicinvetigacion);
    }

    public void modificar(Inicinvetigacion inicinvetigacion) {
        em.merge(inicinvetigacion);
    }

    public void eliminar(Inicinvetigacion inicinvetigacion) {
        em.remove(em.find(Inicinvetigacion.class, inicinvetigacion.getIdinicinvetigacion()));
    }

    public List<Inicinvetigacion> allResultados() {
        Query query = em.createNamedQuery("Inicinvetigacion.findAll");
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            return query.getResultList();
        }
        return null;
    }
    
    public Inicinvetigacion inicInvetigacionId(Long id){
        Query query = em.createNamedQuery("Inicinvetigacion.findByIdinicinvetigacion");
        query.setParameter("idinicinvetigacion", id);
        if(query.getResultList()!=null && !query.getResultList().isEmpty()){
            return (Inicinvetigacion)query.getResultList().get(0);
        }
        return null;
    }
}
