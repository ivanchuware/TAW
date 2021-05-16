/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos.dao;

import eventos.entity.Evento;
import eventos.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author luilo
 */
@Stateless
public class EventoFacade extends AbstractFacade<Evento> {

    @PersistenceContext(unitName = "EventosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventoFacade() {
        super(Evento.class);
    }
    
    public List<Evento> findByIdCreador (Usuario idCreador) {
        Query q;
        List<Evento> lista;
        
        q = this.em.createQuery("SELECT e FROM Evento e WHERE e.idCreador = :idCreador");
        q.setParameter("idCreador", idCreador);
        lista = q.getResultList();
        return lista;                                        
    }
    
    public List<Evento> findBySimilarTitulo (String filtro) {
        Query q;        
        q = em.createQuery("SELECT e FROM Evento e WHERE e.titulo LIKE :titulo");
        q.setParameter("titulo", "%" + filtro + "%");
        return q.getResultList();        
    }
    
    public List<Evento> findBySimilarDescripcion (String filtro) {
        Query q;        
        q = em.createQuery("SELECT e FROM Evento e WHERE e.descripcion LIKE :descripcion");
        q.setParameter("descripcion", "%" + filtro + "%");
        return q.getResultList();        
    }
  
    public List<Evento> findByRangoMinPrecio(String filtroMin) {
        Query q;        
        q = em.createQuery("SELECT e FROM Evento e WHERE e.coste >= :filtroMin");
        q.setParameter("filtroMin", Integer.parseInt(filtroMin));
        return q.getResultList();        
    }
    
    public List<Evento> findByRangoMaxPrecio(String filtroMax) {
        Query q;        
        q = em.createQuery("SELECT e FROM Evento e WHERE e.coste <= :filtroMax");
        q.setParameter("filtroMax", Integer.parseInt(filtroMax));
        return q.getResultList();        
    }

    public List<Evento> findByFiltro(Usuario user, String filtroTitulo, String filtroDescripcion, String filtroPrecioMin, String filtroPrecioMax) {
        Query q;
        List<Evento> lista = findByIdCreador(user);
        List<Evento> listaAux;
        if(filtroTitulo!=null && filtroTitulo.length()>0){
            listaAux = findBySimilarTitulo(filtroTitulo);
            lista.retainAll(listaAux);
        }
        if(filtroDescripcion!=null && filtroDescripcion.length()>0){
            listaAux = findBySimilarDescripcion(filtroDescripcion);
            lista.retainAll(listaAux);
        }
        if(filtroPrecioMin!=null && filtroPrecioMin.length()>0){
            listaAux = findByRangoMinPrecio(filtroPrecioMin);
            lista.retainAll(listaAux);
        }
        if(filtroPrecioMax!=null && filtroPrecioMax.length()>0){
            listaAux = findByRangoMaxPrecio(filtroPrecioMax);
            lista.retainAll(listaAux);
        }
        return lista;  
    }
    
}
