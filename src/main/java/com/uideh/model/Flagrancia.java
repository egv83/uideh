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
@Table(name = "flagrancia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flagrancia.findAll", query = "SELECT f FROM Flagrancia f"),
    @NamedQuery(name = "Flagrancia.findByIdflagrancia", query = "SELECT f FROM Flagrancia f WHERE f.idflagrancia = :idflagrancia")})
public class Flagrancia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idflagrancia")
    private Long idflagrancia;

    @JoinColumn(name = "iddesoperativo", referencedColumnName = "iddesoperativo")
    @ManyToOne
    private Desoperativo iddesoperativo;
    @JoinColumn(name = "idresultados", referencedColumnName = "idresultados")
    @ManyToOne
    private Resultados idresultados;
    @JoinColumn(name = "idverifinformacion", referencedColumnName = "idverifinformacion")
    @ManyToOne
    private Verifinformacion idverifinformacion;
    /*@OneToMany(mappedBy = "flagrancia")
    private Collection<Matriz> matrizCollection;*/

    public Flagrancia() {
    }

    public Flagrancia(Long idflagrancia) {
        this.idflagrancia = idflagrancia;
    }

    public Long getIdflagrancia() {
        return idflagrancia;
    }

    public void setIdflagrancia(Long idflagrancia) {
        this.idflagrancia = idflagrancia;
    }

    public Desoperativo getIddesoperativo() {
        return iddesoperativo;
    }

    public void setIddesoperativo(Desoperativo iddesoperativo) {
        this.iddesoperativo = iddesoperativo;
    }

    public Resultados getIdresultados() {
        return idresultados;
    }

    public void setIdresultados(Resultados idresultados) {
        this.idresultados = idresultados;
    }

    public Verifinformacion getIdverifinformacion() {
        return idverifinformacion;
    }

    public void setIdverifinformacion(Verifinformacion idverifinformacion) {
        this.idverifinformacion = idverifinformacion;
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
        hash += (idflagrancia != null ? idflagrancia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flagrancia)) {
            return false;
        }
        Flagrancia other = (Flagrancia) object;
        if ((this.idflagrancia == null && other.idflagrancia != null) || (this.idflagrancia != null && !this.idflagrancia.equals(other.idflagrancia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uideh.model.Flagrancia[ idflagrancia=" + idflagrancia + " ]";
    }
    
}
