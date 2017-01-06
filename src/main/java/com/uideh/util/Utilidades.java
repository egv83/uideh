package com.uideh.util;

import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Esteban Vallejo
 */
public class Utilidades {
    
    public static final String RegistroGuardado = "Registro satisfacoriamente ingresado";
    public static final String RegistroModificado = "Registro satisfacoriamente modificado";
    public static final String RegistroEliminado = "Registro satisfacoriamente eliminado";
    
    private Date fechaActual = new Date();
    private Calendar calendario = Calendar.getInstance();
    
    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }
    
    public static void addWarningMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

     protected RequestContext getRequestContext(){
        return RequestContext.getCurrentInstance();
    }
    
    public void execute(String componente) {
        getRequestContext().execute(componente);
    }
    
    public String fechaActualSinHora()
    {
        //Calendar calendario = Calendar.getInstance();
        calendario.setTime(new Date());
        String fecha=calendario.get(Calendar.YEAR)+"/"+(calendario.get(Calendar.MONTH)+1)+"/"+calendario.get(Calendar.DAY_OF_MONTH);
        return fecha;
    }
    
    public String mesActual()
    {
        //Calendar calendario = Calendar.getInstance();
        calendario.setTime(new Date());
        String fecha=(calendario.get(Calendar.MONTH)+1)+"";
        return fecha;
    }
    
    public String añoActual(){
        //Calendar calendario = Calendar.getInstance();
        calendario.setTime(new Date());
        String fecha=calendario.get(Calendar.YEAR)+"";
        return fecha;
    }
    
    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }
}
