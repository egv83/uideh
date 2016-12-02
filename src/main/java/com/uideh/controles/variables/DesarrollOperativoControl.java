package com.uideh.controles.variables;

import com.uideh.dao.variables.DesarrollOperativoDAO;
import com.uideh.model.Desoperativo;
import com.uideh.util.Utilidades;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Esteban Vallejo
 */
@ManagedBean
@SessionScoped
public class DesarrollOperativoControl extends Utilidades implements Serializable{

    @EJB
    DesarrollOperativoDAO desarrollOperativoDAO;
    
    private Desoperativo desOperativo;
    private Desoperativo desOperativoSelect;
    private List<Desoperativo> listaDesoperativos;
    private Boolean accionBoton;
    private String lblAccion;
    
    public DesarrollOperativoControl() {
    }
    
    @PostConstruct
    public void inicio(){
        this.desOperativo = new Desoperativo();
        this.desOperativoSelect = null;
    }
    
    public List<Desoperativo> getListaDesOperativo(){
        if(this.listaDesoperativos == null){
            this.listaDesoperativos = this.desarrollOperativoDAO.allDesarrollOperativo();
        }
        return listaDesoperativos;
    }
    
    public String grabar() {
        if (this.getDesOperativo()!= null) {
            try {
                desarrollOperativoDAO.crear(this.getDesOperativo());
                this.desOperativo = new Desoperativo();
                this.listaDesoperativos = null;
                addSuccessMessage(RegistroGuardado);
                execute("PF('dlDesOper').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }
    
    public String modificar() {
        if (this.getDesOperativo() != null) {
            try {
                desarrollOperativoDAO.modificar(this.getDesOperativo());
                this.desOperativo = new Desoperativo();
                this.listaDesoperativos = null;
                this.desOperativoSelect = null;
                addSuccessMessage(RegistroModificado);
                execute("PF('dlDesOper').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }
    
     public void eliminar() {
        if (this.getDesOperativoSelect()!= null) {
            desarrollOperativoDAO.eliminar(this.getDesOperativoSelect());
            this.desOperativoSelect = null;
            this.listaDesoperativos = null;
            this.desOperativo = new Desoperativo();
            addSuccessMessage(RegistroEliminado);
        }
    }
    
    public void seleciondo() {
        this.setDesOperativo(this.getDesOperativoSelect());
    }

    public void estadoBoton(String accion) {
        if ("n".equals(accion)) {
            this.setAccionBoton(Boolean.TRUE);
            this.setLblAccion("Nueva Variable");
        } else {
            this.setAccionBoton(Boolean.FALSE);
            this.setLblAccion("Modificar Variable");
        }
    }

    public Desoperativo getDesOperativo() {
        return desOperativo;
    }

    public void setDesOperativo(Desoperativo desOperativo) {
        this.desOperativo = desOperativo;
    }

    public Desoperativo getDesOperativoSelect() {
        return desOperativoSelect;
    }
    
    public void setDesOperativoSelect(Desoperativo desOperativoSelect) {
        this.desOperativoSelect = desOperativoSelect;
    }

    public Boolean getAccionBoton() {
        return accionBoton;
    }

    public void setAccionBoton(Boolean accionBoton) {
        this.accionBoton = accionBoton;
    }

    public String getLblAccion() {
        return lblAccion;
    }

    public void setLblAccion(String lblAccion) {
        this.lblAccion = lblAccion;
    }

}
