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
@Table(name = "desoperativo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Desoperativo.findAll", query = "SELECT d FROM Desoperativo d"),
    @NamedQuery(name = "Desoperativo.findByIddesoperativo", query = "SELECT d FROM Desoperativo d WHERE d.iddesoperativo = :iddesoperativo"),
    @NamedQuery(name = "Desoperativo.findByVariable", query = "SELECT d FROM Desoperativo d WHERE d.variable = :variable"),
    @NamedQuery(name = "Desoperativo.findByPonderacion", query = "SELECT d FROM Desoperativo d WHERE d.ponderacion = :ponderacion")})
public class Desoperativo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddesoperativo")
    private Long iddesoperativo;
    @Size(max = 100)
    @Column(name = "variable")
    private String variable;
    @Column(name = "ponderacion")
    private Long ponderacion;
    @OneToMany(mappedBy = "iddesoperativo")
    private Collection<Flagrancia> flagranciaCollection;

    public Desoperativo() {
    }

    public Desoperativo(Long iddesoperativo) {
        this.iddesoperativo = iddesoperativo;
    }

    public Long getIddesoperativo() {
        return iddesoperativo;
    }

    public void setIddesoperativo(Long iddesoperativo) {
        this.iddesoperativo = iddesoperativo;
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
        hash += (iddesoperativo != null ? iddesoperativo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Desoperativo)) {
            return false;
        }
        Desoperativo other = (Desoperativo) object;
        if ((this.iddesoperativo == null && other.iddesoperativo != null) || (this.iddesoperativo != null && !this.iddesoperativo.equals(other.iddesoperativo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uideh.model.Desoperativo[ iddesoperativo=" + iddesoperativo + " ]";
    }
    
}
