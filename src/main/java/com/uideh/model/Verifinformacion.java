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
@Table(name = "verifinformacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Verifinformacion.findAll", query = "SELECT v FROM Verifinformacion v"),
    @NamedQuery(name = "Verifinformacion.findByIdverifinformacion", query = "SELECT v FROM Verifinformacion v WHERE v.idverifinformacion = :idverifinformacion"),
    @NamedQuery(name = "Verifinformacion.findByVariable", query = "SELECT v FROM Verifinformacion v WHERE v.variable = :variable"),
    @NamedQuery(name = "Verifinformacion.findByPonderacion", query = "SELECT v FROM Verifinformacion v WHERE v.ponderacion = :ponderacion")})
public class Verifinformacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idverifinformacion")
    private Long idverifinformacion;
    @Size(max = 100)
    @Column(name = "variable")
    private String variable;
    @Column(name = "ponderacion")
    private Long ponderacion;
    @OneToMany(mappedBy = "idverifinformacion")
    private Collection<Flagrancia> flagranciaCollection;

    public Verifinformacion() {
    }

    public Verifinformacion(Long idverifinformacion) {
        this.idverifinformacion = idverifinformacion;
    }

    public Long getIdverifinformacion() {
        return idverifinformacion;
    }

    public void setIdverifinformacion(Long idverifinformacion) {
        this.idverifinformacion = idverifinformacion;
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
    public Collection<Flagrancia> getFlagranciaCollection() {
        return flagranciaCollection;
    }

    public void setFlagranciaCollection(Collection<Flagrancia> flagranciaCollection) {
        this.flagranciaCollection = flagranciaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idverifinformacion != null ? idverifinformacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Verifinformacion)) {
            return false;
        }
        Verifinformacion other = (Verifinformacion) object;
        if ((this.idverifinformacion == null && other.idverifinformacion != null) || (this.idverifinformacion != null && !this.idverifinformacion.equals(other.idverifinformacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uideh.model.Verifinformacion[ idverifinformacion=" + idverifinformacion + " ]";
    }
    
}
