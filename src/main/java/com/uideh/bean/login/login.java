package com.uideh.bean.login;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Esteban Vallejo
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class login implements Serializable {

    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ext = fc.getExternalContext();
    private static final long serialVersionUID = 1L;
    private String usuario;
    private String clave;

    /*public String loginProject() {
        boolean result = UserDAO.login(uname, password);
        if (result) {
            return "home";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));
            return "login";
        }*/
    public String iniciarSesion() {
        String redireccion = null;
        if (this.getUsuario().equals("admin") && this.getClave().equals("admin")) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.getUsuario());
            redireccion = "home";
        } else {
            if (this.getUsuario().equals("califica") && this.getClave().equals("califica")) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.getUsuario());
                redireccion = "calificacion";
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Login Error",
                                "Credenciales no válidas"));
            }
        }
        return redireccion;
    }

    public String registro() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;

        HttpSession httpSession;

        //System.out.println("Usuario: " + this.getUsuario());
        //System.out.println("Clave: " + this.getClave());
        if (this.getUsuario().equals("admin") && this.getClave().equals("admin")) {
            //System.out.println("ENTRO SI ES ADMIN");
            httpSession = getSession();
            httpSession.setAttribute("usuario", this.getUsuario());

            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", this.getUsuario());

            FacesContext.getCurrentInstance().addMessage(null, msg);
//            context.addCallbackParam("estaLogeado", true);

//            context.addCallbackParam("view", "index.xhtml");
            //System.out.println("ANTES DE VER: ");
            //System.out.println("USUARIO HTTP: " + httpSession.getAttribute("usuario"));

            //return "home.xhtml?faces-redirect=true";
            return "home";

        } else if (this.getUsuario().equals("califica") && this.getClave().equals("califica")) {
            System.out.println("ENTRO SI ES CALIFICA");
            httpSession = getSession();
            httpSession.setAttribute("usuario", this.getUsuario());

            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", this.getUsuario());

            FacesContext.getCurrentInstance().addMessage(null, msg);
//            context.addCallbackParam("estaLogeado", true);
//            context.addCallbackParam("view", "calificaion.xhtml");

            //return "calificacion.xhtml?faces-redirect=true";
            return "calificacion";
        } else {
            System.out.println("ENTRO ULTIMO ELSE");
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Login Error",
                            "Credenciales no válidas"));
            return "login";
        }
    }

    public void logout() throws IOException {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);

        //System.out.println("VALOR DE SESSION EN LOGOUT: " + session.getAttribute("usuario"));

        session.invalidate();
        //logeado = false;

        //getExternalContext().redirect(getRequest().getContextPath() + "/faces/index.xhtml");
        /*getExternalContext().redirect(getRequest().getContextPath() + "/index.xhtml");
        HttpSession session = getSession();
        session.invalidate();*/
    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    }

    public void verificarSesion2() {
        try {

            URI uri;
            StringBuilder ruta = new StringBuilder();
            HttpServletRequest servletRequest = (HttpServletRequest) ext.getRequest();
            //System.out.println("REQUEST URI: " + servletRequest.getRequestURI());

            //System.out.println("REQUEST SCHEMA: " + ext.getRequestScheme());
            uri = new URI(ext.getRequestScheme(), null, ext.getRequestServerName(), ext.getRequestServerPort(), ext.getRequestContextPath(), null, null);
            //System.out.println("URI: " + uri);

            ruta.append(servletRequest.getServerName());
            ruta.append(":");
            ruta.append(servletRequest.getServerPort());
            ruta.append(servletRequest.getContextPath());

            //System.out.println("RUTA: " + ruta);
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();

            ext.redirect(uri.toString());
        } catch (Exception e) {

        }
    }

    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext ext = context.getExternalContext();

            URI uri = new URI(ext.getRequestScheme(), null, ext.getRequestServerName(), ext.getRequestServerPort(), ext.getRequestContextPath(), null, null);

            String usuario = (String) context.getExternalContext().getSessionMap().get("usuario");

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

            if (usuario == null) {
                session.invalidate();
                //context.getExternalContext().redirect("./../index.xhtml");
                //context.getExternalContext().getSessionMap().remove("usuario");
                context.getExternalContext().redirect(uri.toString());
            }
        } catch (Exception e) {

        }
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    protected ExternalContext getExternalContext() {
        return getContext().getExternalContext();
    }

    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    protected static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}