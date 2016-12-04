package com.uideh.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "matriz")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matriz.findAll", query = "SELECT m FROM Matriz m"),
    @NamedQuery(name = "Matriz.findByIdmatriz", query = "SELECT m FROM Matriz m WHERE m.idmatriz = :idmatriz"),
    @NamedQuery(name = "Matriz.findByProducagencia", query = "SELECT m FROM Matriz m WHERE m.producagencia = :producagencia"),
    @NamedQuery(name = "Matriz.findByTotal", query = "SELECT m FROM Matriz m WHERE m.total = :total"),
    @NamedQuery(name = "Matriz.findByFecha", query = "SELECT m FROM Matriz m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Matriz.findByCaso", query = "SELECT m FROM Matriz m WHERE m.caso = :caso"),
    @NamedQuery(name = "Matriz.findByDetalle", query = "SELECT m FROM Matriz m WHERE m.detalle = :detalle"),
    @NamedQuery(name = "Matriz.findByFlagrancia", query = "SELECT m FROM Matriz m WHERE m.flagrancia = :flagrancia"),
    @NamedQuery(name = "Matriz.findByInvestigacion", query = "SELECT m FROM Matriz m WHERE m.investigacion = :investigacion"),
    @NamedQuery(name = "Matriz.findByActivProductivas", query = "SELECT m FROM Matriz m WHERE m.activProductivas = :activProductivas")})
public class Matriz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmatriz")
    private Long idmatriz;
    @Column(name = "flagrancia")
    private Long flagrancia;
    @Column(name = "investigacion")
    private Long investigacion;
    @Column(name = "actproduct")
    private Long activProductivas;
    @Column(name = "producagencia")
    private Long producagencia;
    @Column(name = "total")
    private Long total;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 100)
    @Column(name = "caso")
    private String caso;
    @Size(max = 200)
    @Column(name = "detalle")
    private String detalle;
    /*@JoinColumn(name = "idactproductivas", referencedColumnName = "idactproductivas")
    @ManyToOne
    private Actproductivas idactproductivas;*/
    @JoinColumn(name = "idagente", referencedColumnName = "idagente")
    @ManyToOne(fetch = FetchType.EAGER)
    private Agente idagente;
    /*@JoinColumn(name = "idflagrancia", referencedColumnName = "idflagrancia")
    @ManyToOne
    private Flagrancia idflagrancia;*/
    /*@JoinColumn(name = "idinvestigacion", referencedColumnName = "idinvestigacion")
    @ManyToOne
    private Investigacion idinvestigacion;*/

    public Matriz() {
        this.idagente = new Agente();
    }

    public Matriz(Long idmatriz) {
        this.idmatriz = idmatriz;
    }

    public Long getIdmatriz() {
        return idmatriz;
    }

    public void setIdmatriz(Long idmatriz) {
        this.idmatriz = idmatriz;
    }

    public Long getProducagencia() {
        return producagencia;
    }

    public void setProducagencia(Long producagencia) {
        this.producagencia = producagencia;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCaso() {
        return caso;
    }

    public void setCaso(String caso) {
        this.caso = caso;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /*public Actproductivas getIdActproductivas() {
        return idactproductivas;
    }

    public void setIdActproductivas(Actproductivas idactproductivas) {
        this.idactproductivas = idactproductivas;
    }*/

    public Agente getIdAgente() {
        return idagente;
    }

    public void setIdAgente(Agente idagente) {
        this.idagente = idagente;
    }

    /*public Flagrancia getIdFlagrancia() {
        return idflagrancia;
    }

    public void setIdFlagrancia(Flagrancia idflagrancia) {
        this.idflagrancia = idflagrancia;
    }*/

    /*public Investigacion getIdInvestigacion() {
        return idinvestigacion;
    }*/

    /*public void setIdInvestigacion(Investigacion idinvestigacion) {
        this.idinvestigacion = idinvestigacion;
    }*/
    
    public Long getFlagrancia() {
        return flagrancia;
    }

    public void setFlagrancia(Long flagrancia) {
        this.flagrancia = flagrancia;
    }

    public Long getInvestigacion() {
        return investigacion;
    }

    public void setInvestigacion(Long investigacion) {
        this.investigacion = investigacion;
    }

    public Long getActivProductivas() {
        return activProductivas;
    }

    public void setActivProductivas(Long activProductivas) {
        this.activProductivas = activProductivas;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmatriz != null ? idmatriz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matriz)) {
            return false;
        }
        Matriz other = (Matriz) object;
        if ((this.idmatriz == null && other.idmatriz != null) || (this.idmatriz != null && !this.idmatriz.equals(other.idmatriz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uideh.model.Matriz[ idmatriz=" + idmatriz + " ]";
    }
    
}
