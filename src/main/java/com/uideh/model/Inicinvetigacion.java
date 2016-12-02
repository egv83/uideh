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
@Table(name = "inicinvetigacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inicinvetigacion.findAll", query = "SELECT i FROM Inicinvetigacion i"),
    @NamedQuery(name = "Inicinvetigacion.findByIdinicinvetigacion", query = "SELECT i FROM Inicinvetigacion i WHERE i.idinicinvetigacion = :idinicinvetigacion"),
    @NamedQuery(name = "Inicinvetigacion.findByVariable", query = "SELECT i FROM Inicinvetigacion i WHERE i.variable = :variable"),
    @NamedQuery(name = "Inicinvetigacion.findByPonderacion", query = "SELECT i FROM Inicinvetigacion i WHERE i.ponderacion = :ponderacion")})
public class Inicinvetigacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinicinvetigacion")
    private Long idinicinvetigacion;
    @Size(max = 100)
    @Column(name = "variable")
    private String variable;
    @Column(name = "ponderacion")
    private Long ponderacion;
    @OneToMany(mappedBy = "idinicinvetigacion")
    private Collection<Investigacion> investigacionCollection;

    public Inicinvetigacion() {
    }

    public Inicinvetigacion(Long idinicinvetigacion) {
        this.idinicinvetigacion = idinicinvetigacion;
    }

    public Long getIdinicinvetigacion() {
        return idinicinvetigacion;
    }

    public void setIdinicinvetigacion(Long idinicinvetigacion) {
        this.idinicinvetigacion = idinicinvetigacion;
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
        hash += (idinicinvetigacion != null ? idinicinvetigacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inicinvetigacion)) {
            return false;
        }
        Inicinvetigacion other = (Inicinvetigacion) object;
        if ((this.idinicinvetigacion == null && other.idinicinvetigacion != null) || (this.idinicinvetigacion != null && !this.idinicinvetigacion.equals(other.idinicinvetigacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uideh.model.Inicinvetigacion[ idinicinvetigacion=" + idinicinvetigacion + " ]";
    }
    
}
