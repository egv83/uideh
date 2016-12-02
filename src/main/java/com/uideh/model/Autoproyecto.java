package com.uideh.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "autoproyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autoproyecto.findAll", query = "SELECT a FROM Autoproyecto a"),
    @NamedQuery(name = "Autoproyecto.findByIdautoproyecto", query = "SELECT a FROM Autoproyecto a WHERE a.idautoproyecto = :idautoproyecto"),
    @NamedQuery(name = "Autoproyecto.findByVariable", query = "SELECT a FROM Autoproyecto a WHERE a.variable = :variable"),
    @NamedQuery(name = "Autoproyecto.findByPonderacion", query = "SELECT a FROM Autoproyecto a WHERE a.ponderacion = :ponderacion")})
public class Autoproyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idautoproyecto")
    private Long idautoproyecto;
    @Size(max = 100)
    @Column(name = "variable")
    private String variable;
    @Column(name = "ponderacion")
    private Long ponderacion;
    @OneToMany(mappedBy = "idautoproyecto")
    private Collection<Investigacion> investigacionCollection;

    public Autoproyecto() {
    }

    public Autoproyecto(Long idautoproyecto) {
        this.idautoproyecto = idautoproyecto;
    }

    public Long getIdautoproyecto() {
        return idautoproyecto;
    }

    public void setIdautoproyecto(Long idautoproyecto) {
        this.idautoproyecto = idautoproyecto;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public Long getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(Long ponderacion) {
        this.ponderacion = ponderacion;
    }

    @XmlTransient
    public Collection<Investigacion> getInvestigacionCollection() {
        return investigacionCollection;
    }

    public void setInvestigacionCollection(Collection<Investigacion> investigacionCollection) {
        this.investigacionCollection = investigacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idautoproyecto != null ? idautoproyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autoproyecto)) {
            return false;
        }
        Autoproyecto other = (Autoproyecto) object;
        if ((this.idautoproyecto == null && other.idautoproyecto != null) || (this.idautoproyecto != null && !this.idautoproyecto.equals(other.idautoproyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uideh.model.Autoproyecto[ idautoproyecto=" + idautoproyecto + " ]";
    }
    
}
