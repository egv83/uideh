package com.uideh.controles;

import com.uideh.dao.GradoDAO;
import com.uideh.model.Agencia;
import com.uideh.model.Grado;
import com.uideh.util.Utilidades;
import static com.uideh.util.Utilidades.RegistroGuardado;
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
public class GradoControl extends Utilidades implements Serializable{

    @EJB
    GradoDAO gradoDAO;

    private Grado grado;
    private Grado gradoSelect;
    private List<Grado> listaGrado;
    private Boolean accionBoton;
    private String lblAccion;

    @PostConstruct
    public void inicio(){
        this.grado = new Grado();
        this.listaGrado = null;
    }
    
    public List<Grado> getListaGrado(){
        if (this.listaGrado == null) {
            this.listaGrado = gradoDAO.allGrados();
        }
        return this.listaGrado;
    }
    
    public String grabar() {
        if (this.getGrado() != null) {
            try {
                gradoDAO.crear(this.getGrado());
                this.listaGrado = null;
                this.grado = new Grado();
                addSuccessMessage(RegistroGuardado);
                execute("PF('dlGrado').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }
    
    public String modificar() {
        if (this.getGrado() != null) {
            try {
                gradoDAO.modificar(this.getGrado());
                this.grado = new Grado();
                this.listaGrado = null;
                this.gradoSelect = null;
                addSuccessMessage(RegistroModificado);
                execute("PF('dlGrado').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }
    
    public void eliminar() {
        if (this.getGradoSelect() != null) {
            gradoDAO.eliminar(this.getGradoSelect());
            this.gradoSelect = null;
            this.listaGrado = null;
            this.grado = new Grado();
            addSuccessMessage(RegistroEliminado);
        }
    }
    
    public void seleciondo() {
        this.setGrado(this.getGradoSelect());
    }

    public void estadoBoton(String accion) {
        if ("n".equals(accion)) {
            this.setAccionBoton(Boolean.TRUE);
            this.setLblAccion("Nueva Grado");
        } else {
            this.setAccionBoton(Boolean.FALSE);
            this.setLblAccion("Modificar Grado");
        }
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

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public Grado getGradoSelect() {
        return gradoSelect;
    }

    public void setGradoSelect(Grado gradoSelect) {
        this.gradoSelect = gradoSelect;
    }

    public void setListaGrado(List<Grado> listaGrado) {
        this.listaGrado = listaGrado;
    }

}
