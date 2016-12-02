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
@Table(name = "actproductivas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actproductivas.findAll", query = "SELECT a FROM Actproductivas a"),
    @NamedQuery(name = "Actproductivas.findByIdactproductivas", query = "SELECT a FROM Actproductivas a WHERE a.idactproductivas = :idactproductivas"),
    @NamedQuery(name = "Actproductivas.findByVariable", query = "SELECT a FROM Actproductivas a WHERE a.variable = :variable"),
    @NamedQuery(name = "Actproductivas.findByPonderacion", query = "SELECT a FROM Actproductivas a WHERE a.ponderacion = :ponderacion")})
public class Actproductivas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idactproductivas")
    private Long idactproductivas;
    @Size(max = 100)
    @Column(name = "variable")
    private String variable;
    @Column(name = "ponderacion")
    private Long ponderacion;
    /*@OneToMany(mappedBy = "actproductivas")
    private Collection<Matriz> matrizCollection;*/

    public Actproductivas() {
    }

    public Actproductivas(Long idactproductivas) {
        this.idactproductivas = idactproductivas;
    }

    public Long getIdactproductivas() {
        return idactproductivas;
    }

    public void setIdactproductivas(Long idactproductivas) {
        this.idactproductivas = idactproductivas;
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
        hash += (idactproductivas != null ? idactproductivas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actproductivas)) {
            return false;
        }
        Actproductivas other = (Actproductivas) object;
        if ((this.idactproductivas == null && other.idactproductivas != null) || (this.idactproductivas != null && !this.idactproductivas.equals(other.idactproductivas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uideh.model.Actproductivas[ idactproductivas=" + idactproductivas + " ]";
    }
    
}
