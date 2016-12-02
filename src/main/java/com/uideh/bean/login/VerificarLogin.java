/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uideh.bean.login;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Esteban Vallejo
 */
@Named
@ViewScoped
public class VerificarLogin implements Serializable {

    public void verificarSesion() {
        System.out.println("ENTRO EN VERIFICAR SESION");
        try {
            System.out.println("ENTRO EN TRY");
            FacesContext context = FacesContext.getCurrentInstance();
            String usuario = (String) context.getExternalContext().getSessionMap().get("usuario");
            System.out.println("USUARIO: "+usuario);
            if (usuario == null) {
                System.out.println("ENTRO EN IF");
                context.getExternalContext().redirect("./../index.xhtml");
            }
        } catch (Exception e) {

        }
    }
}
