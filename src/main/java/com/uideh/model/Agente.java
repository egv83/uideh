package com.uideh.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "agente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agente.findAll", query = "SELECT a FROM Agente a"),
    @NamedQuery(name = "Agente.findByIdagente", query = "SELECT a FROM Agente a WHERE a.idagente = :idagente"),
    @NamedQuery(name = "Agente.findByCedula", query = "SELECT a FROM Agente a WHERE a.cedula = :cedula"),
    @NamedQuery(name = "Agente.findByNombres", query = "SELECT a FROM Agente a WHERE a.nombres = :nombres"),
    @NamedQuery(name = "Agente.findByApellidos", query = "SELECT a FROM Agente a WHERE a.apellidos = :apellidos"),
    @NamedQuery(name = "Agente.findByJefe", query = "SELECT a FROM Agente a WHERE a.jefe = :jefe")})
public class Agente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idagente")
    private Long idagente;
    @Size(max = 10)
    @Column(name = "cedula")
    private String cedula;
    @Size(max = 50)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 50)
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "jefe")
    private Boolean jefe;
    @JoinColumn(name = "idagencia", referencedColumnName = "idagencia")
    @ManyToOne(fetch = FetchType.EAGER)
    private Agencia idagencia;
    @JoinColumn(name = "idgrado", referencedColumnName = "idgrado")
    @ManyToOne(fetch = FetchType.EAGER)
    private Grado idgrado;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idagente")
    private Collection<Matriz> matrizCollection;

    public Agente() {
        this.idagencia = new Agencia();
        this.idgrado = new Grado();
    }

    public Agente(Long idagente) {
        this.idagente = idagente;
    }

    public Long getIdagente() {
        return idagente;
    }

    public void setIdagente(Long idagente) {
        this.idagente = idagente;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Boolean getJefe() {
        return jefe;
    }

    public void setJefe(Boolean jefe) {
        this.jefe = jefe;
    }

    public Agencia getIdagencia() {
        return idagencia;
    }

    public void setIdagencia(Agencia idagencia) {
        this.idagencia = idagencia;
    }

    public Grado getIdgrado() {
        return idgrado;
    }

    public void setIdgrado(Grado idgrado) {
        this.idgrado = idgrado;
    }

    @XmlTransient
    public Collection<Matriz> getMatrizCollection() {
        return matrizCollection;
    }

    public void setMatrizCollection(Collection<Matriz> matrizCollection) {
        this.matrizCollection = matrizCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idagente != null ? idagente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agente)) {
            return false;
        }
        Agente other = (Agente) object;
        if ((this.idagente == null && other.idagente != null) || (this.idagente != null && !this.idagente.equals(other.idagente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uideh.model.Agente[ idagente=" + idagente + " ]";
    }
    
}
