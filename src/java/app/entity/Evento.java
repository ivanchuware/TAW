/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EloyB
 */
@Entity
@Table(name = "EVENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")
    , @NamedQuery(name = "Evento.findByIdEvento", query = "SELECT e FROM Evento e WHERE e.idEvento = :idEvento")
    , @NamedQuery(name = "Evento.findByTitulo", query = "SELECT e FROM Evento e WHERE e.titulo = :titulo")
    , @NamedQuery(name = "Evento.findByFecha", query = "SELECT e FROM Evento e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "Evento.findByFechares", query = "SELECT e FROM Evento e WHERE e.fechares = :fechares")
    , @NamedQuery(name = "Evento.findByCoste", query = "SELECT e FROM Evento e WHERE e.coste = :coste")
    , @NamedQuery(name = "Evento.findByAforo", query = "SELECT e FROM Evento e WHERE e.aforo = :aforo")
    , @NamedQuery(name = "Evento.findByEntradas", query = "SELECT e FROM Evento e WHERE e.entradas = :entradas")
    , @NamedQuery(name = "Evento.findByAsientosfijos", query = "SELECT e FROM Evento e WHERE e.asientosfijos = :asientosfijos")
    , @NamedQuery(name = "Evento.findByNumfilas", query = "SELECT e FROM Evento e WHERE e.numfilas = :numfilas")
    , @NamedQuery(name = "Evento.findByNumasientosporfila", query = "SELECT e FROM Evento e WHERE e.numasientosporfila = :numasientosporfila")
    , @NamedQuery(name = "Evento.findByIdCreador", query = "SELECT e FROM Evento e WHERE e.idCreador = :idCreador")})
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EVENTO")
    private Integer idEvento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "FECHARES")
    @Temporal(TemporalType.DATE)
    private Date fechares;
    @Column(name = "COSTE")
    private Integer coste;
    @Column(name = "AFORO")
    private Integer aforo;
    @Column(name = "ENTRADAS")
    private Integer entradas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASIENTOSFIJOS")
    private Character asientosfijos;
    @Column(name = "NUMFILAS")
    private Integer numfilas;
    @Column(name = "NUMASIENTOSPORFILA")
    private Integer numasientosporfila;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CREADOR")
    private int idCreador;
    @JoinTable(name = "USUARIO_INSCRITO", joinColumns = {
        @JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID_EVENTO")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")})
    @ManyToMany
    private Collection<Usuario> usuarioCollection;

    public Evento() {
    }

    public Evento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Evento(Integer idEvento, String titulo, Character asientosfijos, int idCreador) {
        this.idEvento = idEvento;
        this.titulo = titulo;
        this.asientosfijos = asientosfijos;
        this.idCreador = idCreador;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechares() {
        return fechares;
    }

    public void setFechares(Date fechares) {
        this.fechares = fechares;
    }

    public Integer getCoste() {
        return coste;
    }

    public void setCoste(Integer coste) {
        this.coste = coste;
    }

    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    public Integer getEntradas() {
        return entradas;
    }

    public void setEntradas(Integer entradas) {
        this.entradas = entradas;
    }

    public Character getAsientosfijos() {
        return asientosfijos;
    }

    public void setAsientosfijos(Character asientosfijos) {
        this.asientosfijos = asientosfijos;
    }

    public Integer getNumfilas() {
        return numfilas;
    }

    public void setNumfilas(Integer numfilas) {
        this.numfilas = numfilas;
    }

    public Integer getNumasientosporfila() {
        return numasientosporfila;
    }

    public void setNumasientosporfila(Integer numasientosporfila) {
        this.numasientosporfila = numasientosporfila;
    }

    public int getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(int idCreador) {
        this.idCreador = idCreador;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvento != null ? idEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.idEvento == null && other.idEvento != null) || (this.idEvento != null && !this.idEvento.equals(other.idEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.Evento[ idEvento=" + idEvento + " ]";
    }
    
}
