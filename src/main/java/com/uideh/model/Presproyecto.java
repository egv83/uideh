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
@Table(name = "presproyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Presproyecto.findAll", query = "SELECT p FROM Presproyecto p"),
    @NamedQuery(name = "Presproyecto.findByIdpresproyecto", query = "SELECT p FROM Presproyecto p WHERE p.idpresproyecto = :idpresproyecto"),
    @NamedQuery(name = "Presproyecto.findByVariable", query = "SELECT p FROM Presproyecto p WHERE p.variable = :variable"),
    @NamedQuery(name = "Presproyecto.findByPonderacion", query = "SELECT p FROM Presproyecto p WHERE p.ponderacion = :ponderacion")})
public class Presproyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpresproyecto")
    private Long idpresproyecto;
    @Size(max = 100)
    @Column(name = "variable")
    private String variable;
    @Column(name = "ponderacion")
    private Long ponderacion;
    @OneToMany(mappedBy = "idpresproyecto")
    private Collection<Investigacion> investigacionCollection;

    public Presproyecto() {
    }

    public Presproyecto(Long idpresproyecto) {
        this.idpresproyecto = idpresproyecto;
    }

    public Long getIdpresproyecto() {
        return idpresproyecto;
    }

    public void setIdpresproyecto(Long idpresproyecto) {
        this.idpresproyecto = idpresproyecto;
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
        hash += (idpresproyecto != null ? idpresproyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Presproyecto)) {
            return false;
        }
        Presproyecto other = (Presproyecto) object;
        if ((this.idpresproyecto == null && other.idpresproyecto != null) || (this.idpresproyecto != null && !this.idpresproyecto.equals(other.idpresproyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uideh.model.Presproyecto[ idpresproyecto=" + idpresproyecto + " ]";
    }
    
}
