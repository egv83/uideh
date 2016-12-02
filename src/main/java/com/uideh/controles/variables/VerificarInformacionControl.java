package com.uideh.controles.variables;

import com.uideh.dao.variables.VerificarInformacionDAO;
import com.uideh.model.Verifinformacion;
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
public class VerificarInformacionControl extends Utilidades implements Serializable {

    @EJB
    VerificarInformacionDAO verificarInformacionDAO;

    private Verifinformacion verifinformacion;
    private Verifinformacion verifinformacionSelect;
    private List<Verifinformacion> listaVerifinformacion;
    private Boolean accionBoton;
    private String lblAccion;

    public VerificarInformacionControl() {
    }

    @PostConstruct
    public void inicio() {
        this.verifinformacion = new Verifinformacion();
        this.listaVerifinformacion = null;
    }

    public List<Verifinformacion> getListaVerifinformacions() {
        if (listaVerifinformacion == null) {
            this.listaVerifinformacion = verificarInformacionDAO.allVerifinformacion();
        }
        return listaVerifinformacion;
    }
    
    public String grabar() {
        if (this.getVerifinformacion()!= null) {
            try {
                verificarInformacionDAO.crear(this.getVerifinformacion());
                this.verifinformacion = new Verifinformacion();
                this.listaVerifinformacion = null;
                addSuccessMessage(RegistroGuardado);
                execute("PF('dlVerificar').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }
    
    public String modificar() {
        if (this.getVerifinformacion() != null) {
            try {
                verificarInformacionDAO.modificar(this.getVerifinformacion());
                this.verifinformacion = new Verifinformacion();
                this.listaVerifinformacion = null;
                this.verifinformacionSelect = null;
                addSuccessMessage(RegistroModificado);
                execute("PF('dlVerificar').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }
    
     public void eliminar() {
        if (this.getVerifinformacionSelect()!= null) {
            verificarInformacionDAO.eliminar(this.getVerifinformacionSelect());
            this.verifinformacionSelect = null;
            this.listaVerifinformacion = null;
            this.verifinformacion = new Verifinformacion();
            addSuccessMessage(RegistroEliminado);
        }
    }
     
    public void seleciondo() {
        this.setVerifinformacion(this.getVerifinformacionSelect());
    }

    public void estadoVerificaInfor(String accion) {
        if ("n".equals(accion)) {
            this.setAccionBoton(Boolean.TRUE);
            this.setLblAccion("Nueva Variable");
        } else {
            this.setAccionBoton(Boolean.FALSE);
            this.setLblAccion("Modificar Variable");
        }
    }
    
    public Verifinformacion getVerInformacionId(Long id){
        return verificarInformacionDAO.verifInformacion(id);
    }

    public Verifinformacion getVerifinformacion() {
        return verifinformacion;
    }

    public void setVerifinformacion(Verifinformacion verifinformacion) {
        this.verifinformacion = verifinformacion;
    }

    public Verifinformacion getVerifinformacionSelect() {
        return verifinformacionSelect;
    }

    public void setVerifinformacionSelect(Verifinformacion verifinformacionSelect) {
        this.verifinformacionSelect = verifinformacionSelect;
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
