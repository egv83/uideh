package com.uideh.dao.variables;

import com.uideh.model.Verifinformacion;
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
public class VerificarInformacionDAO {

    @PersistenceContext(unitName = "com.uideh_uideh_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public void crear(Verifinformacion verifinformacion) {
        em.persist(verifinformacion);
    }

    public void modificar(Verifinformacion verifinformacion) {
        em.merge(verifinformacion);
    }

    public void eliminar(Verifinformacion verifinformacion) {
        em.remove(em.find(Verifinformacion.class, verifinformacion.getIdverifinformacion()));
    }

    public List<Verifinformacion> allVerifinformacion() {
        Query query = em.createNamedQuery("Verifinformacion.findAll");
        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            //lista = query.getResultList();
            return query.getResultList();
        }
        //return lista;
        return null;
    }
    
    public Verifinformacion verifInformacion(Long id){
        Query query = em.createNamedQuery("Verifinformacion.findByIdverifinformacion");
        query.setParameter("idverifinformacion", id);
        if(query.getResultList()!=null && !query.getResultList().isEmpty()){
            return (Verifinformacion)query.getResultList().get(0);
        }
        return null;
    }
}
