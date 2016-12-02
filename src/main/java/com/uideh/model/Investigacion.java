package com.uideh.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "investigacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Investigacion.findAll", query = "SELECT i FROM Investigacion i"),
    @NamedQuery(name = "Investigacion.findByIdinvestigacion", query = "SELECT i FROM Investigacion i WHERE i.idinvestigacion = :idinvestigacion")})
public class Investigacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinvestigacion")
    private Long idinvestigacion;
    @JoinColumn(name = "idautoproyecto", referencedColumnName = "idautoproyecto")
    @ManyToOne
    private Autoproyecto idautoproyecto;
    @JoinColumn(name = "iddesainvestigacion", referencedColumnName = "iddesainvestigacion")
    @ManyToOne
    private Desainvestigacion iddesainvestigacion;
    @JoinColumn(name = "iddesaoperativo", referencedColumnName = "iddesaoperativo")
    @ManyToOne
    private Desaoperativo iddesaoperativo;
    @JoinColumn(name = "idinicinvetigacion", referencedColumnName = "idinicinvetigacion")
    @ManyToOne
    private Inicinvetigacion idinicinvetigacion;
    @JoinColumn(name = "idpresproyecto", referencedColumnName = "idpresproyecto")
    @ManyToOne
    private Presproyecto idpresproyecto;
    @JoinColumn(name = "idresultados", referencedColumnName = "idresultados")
    @ManyToOne
    private Resultados idresultados;
    /*@OneToMany(mappedBy = "investigacion")
    private Collection<Matriz> matrizCollection;*/

    public Investigacion() {
    }

    public Investigacion(Long idinvestigacion) {
        this.idinvestigacion = idinvestigacion;
    }

    public Long getIdinvestigacion() {
        return idinvestigacion;
    }

    public void setIdinvestigacion(Long idinvestigacion) {
        this.idinvestigacion = idinvestigacion;
    }

    public Autoproyecto getIdautoproyecto() {
        return idautoproyecto;
    }

    public void setIdautoproyecto(Autoproyecto idautoproyecto) {
        this.idautoproyecto = idautoproyecto;
    }

    public Desainvestigacion getIddesainvestigacion() {
        return iddesainvestigacion;
    }

    public void setIddesainvestigacion(Desainvestigacion iddesainvestigacion) {
        this.iddesainvestigacion = iddesainvestigacion;
    }

    public Desaoperativo getIddesaoperativo() {
        return iddesaoperativo;
    }

    public void setIddesaoperativo(Desaoperativo iddesaoperativo) {
        this.iddesaoperativo = iddesaoperativo;
    }

    public Inicinvetigacion getIdinicinvetigacion() {
        return idinicinvetigacion;
    }

    public void setIdinicinvetigacion(Inicinvetigacion idinicinvetigacion) {
        this.idinicinvetigacion = idinicinvetigacion;
    }

    public Presproyecto getIdpresproyecto() {
        return idpresproyecto;
    }

    public void setIdpresproyecto(Presproyecto idpresproyecto) {
        this.idpresproyecto = idpresproyecto;
    }

    public Resultados getIdresultados() {
        return idresultados;
    }

    public void setIdresultados(Resultados idresultados) {
        this.idresultados = idresultados;
    }

    /*@XmlTransient
    public Collection<Matriz> getMatrizCollection() {
        return matrizCollection;
    }

    public void setMatrizCollection(Collection<Matriz> matrizCollection) {
        this.matrizCollection = matrizCollection;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinvestigacion != null ? idinvestigacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Investigacion)) {
            return false;
        }
        Investigacion other = (Investigacion) object;
        if ((this.idinvestigacion == null && other.idinvestigacion != null) || (this.idinvestigacion != null && !this.idinvestigacion.equals(other.idinvestigacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uideh.model.Investigacion[ idinvestigacion=" + idinvestigacion + " ]";
    }
    
}
