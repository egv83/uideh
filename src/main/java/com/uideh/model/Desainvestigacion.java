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
@Table(name = "desainvestigacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Desainvestigacion.findAll", query = "SELECT d FROM Desainvestigacion d"),
    @NamedQuery(name = "Desainvestigacion.findByIddesainvestigacion", query = "SELECT d FROM Desainvestigacion d WHERE d.iddesainvestigacion = :iddesainvestigacion"),
    @NamedQuery(name = "Desainvestigacion.findByVariable", query = "SELECT d FROM Desainvestigacion d WHERE d.variable = :variable"),
    @NamedQuery(name = "Desainvestigacion.findByPonderacion", query = "SELECT d FROM Desainvestigacion d WHERE d.ponderacion = :ponderacion")})
public class Desainvestigacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddesainvestigacion")
    private Long iddesainvestigacion;
    @Size(max = 100)
    @Column(name = "variable")
    private String variable;
    @Column(name = "ponderacion")
    private Long ponderacion;
    @OneToMany(mappedBy = "iddesainvestigacion")
    private Collection<Investigacion> investigacionCollection;

    public Desainvestigacion() {
    }

    public Desainvestigacion(Long iddesainvestigacion) {
        this.iddesainvestigacion = iddesainvestigacion;
    }

    public Long getIddesainvestigacion() {
        return iddesainvestigacion;
    }

    public void setIddesainvestigacion(Long iddesainvestigacion) {
        this.iddesainvestigacion = iddesainvestigacion;
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
        hash += (iddesainvestigacion != null ? iddesainvestigacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Desainvestigacion)) {
            return false;
        }
        Desainvestigacion other = (Desainvestigacion) object;
        if ((this.iddesainvestigacion == null && other.iddesainvestigacion != null) || (this.iddesainvestigacion != null && !this.iddesainvestigacion.equals(other.iddesainvestigacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uideh.model.Desainvestigacion[ iddesainvestigacion=" + iddesainvestigacion + " ]";
    }
    
}
