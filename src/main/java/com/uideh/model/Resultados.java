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
@Table(name = "resultados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultados.findAll", query = "SELECT r FROM Resultados r"),
    @NamedQuery(name = "Resultados.findByIdresultados", query = "SELECT r FROM Resultados r WHERE r.idresultados = :idresultados"),
    @NamedQuery(name = "Resultados.findByVariable", query = "SELECT r FROM Resultados r WHERE r.variable = :variable"),
    @NamedQuery(name = "Resultados.findByPonderacion", query = "SELECT r FROM Resultados r WHERE r.ponderacion = :ponderacion")})
public class Resultados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idresultados")
    private Long idresultados;
    @Size(max = 100)
    @Column(name = "variable")
    private String variable;
    @Column(name = "ponderacion")
    private Long ponderacion;
    @OneToMany(mappedBy = "idresultados")
    private Collection<Flagrancia> flagranciaCollection;
    @OneToMany(mappedBy = "idresultados")
    private Collection<Investigacion> investigacionCollection;

    public Resultados() {
    }

    public Resultados(Long idresultados) {
        this.idresultados = idresultados;
    }

    public Long getIdresultados() {
        return idresultados;
    }

    public void setIdresultados(Long idresultados) {
        this.idresultados = idresultados;
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
        hash += (idresultados != null ? idresultados.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultados)) {
            return false;
        }
        Resultados other = (Resultados) object;
        if ((this.idresultados == null && other.idresultados != null) || (this.idresultados != null && !this.idresultados.equals(other.idresultados))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uideh.model.Resultados[ idresultados=" + idresultados + " ]";
    }
    
}
