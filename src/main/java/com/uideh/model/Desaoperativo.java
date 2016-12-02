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
@Table(name = "desaoperativo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Desaoperativo.findAll", query = "SELECT d FROM Desaoperativo d"),
    @NamedQuery(name = "Desaoperativo.findByIddesaoperativo", query = "SELECT d FROM Desaoperativo d WHERE d.iddesaoperativo = :iddesaoperativo"),
    @NamedQuery(name = "Desaoperativo.findByVariable", query = "SELECT d FROM Desaoperativo d WHERE d.variable = :variable"),
    @NamedQuery(name = "Desaoperativo.findByPonderacion", query = "SELECT d FROM Desaoperativo d WHERE d.ponderacion = :ponderacion")})
public class Desaoperativo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddesaoperativo")
    private Long iddesaoperativo;
    @Size(max = 100)
    @Column(name = "variable")
    private String variable;
    @Column(name = "ponderacion")
    private Long ponderacion;
    @OneToMany(mappedBy = "iddesaoperativo")
    private Collection<Investigacion> investigacionCollection;

    public Desaoperativo() {
    }

    public Desaoperativo(Long iddesaoperativo) {
        this.iddesaoperativo = iddesaoperativo;
    }

    public Long getIddesaoperativo() {
        return iddesaoperativo;
    }

    public void setIddesaoperativo(Long iddesaoperativo) {
        this.iddesaoperativo = iddesaoperativo;
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
        hash += (iddesaoperativo != null ? iddesaoperativo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Desaoperativo)) {
            return false;
        }
        Desaoperativo other = (Desaoperativo) object;
        if ((this.iddesaoperativo == null && other.iddesaoperativo != null) || (this.iddesaoperativo != null && !this.iddesaoperativo.equals(other.iddesaoperativo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uideh.model.Desaoperativo[ iddesaoperativo=" + iddesaoperativo + " ]";
    }
    
}
