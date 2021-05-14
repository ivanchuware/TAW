/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos.dao;

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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "EventosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    public Usuario findById (Integer id) {
        Query q;
        List<Usuario> lista;
        
        q = this.em.createNamedQuery("Usuario.findByIdUsuario");
        q.setParameter("id_usuario", id);
        lista = q.getResultList();
        if (lista != null && lista.isEmpty() == false) {
            return lista.get(0);
        } else {
            return null;
        }                                
    }
    
    public Usuario findByEmail (String email) {
        Query q;
        List<Usuario> lista;
        
        q = this.em.createNamedQuery("Usuario.findByEmail");
        q.setParameter("email", email);
        lista = q.getResultList();
        if (lista != null && lista.isEmpty() == false) {
            return lista.get(0);
        } else {
            return null;
        }                                
    }
}