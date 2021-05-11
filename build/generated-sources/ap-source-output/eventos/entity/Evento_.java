package eventos.entity;

import eventos.entity.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-11T19:33:59")
@StaticMetamodel(Evento.class)
public class Evento_ { 

    public static volatile SingularAttribute<Evento, Integer> aforo;
    public static volatile SingularAttribute<Evento, Date> fecha;
    public static volatile SingularAttribute<Evento, Integer> numasientosporfila;
    public static volatile ListAttribute<Evento, Usuario> usuarioList;
    public static volatile SingularAttribute<Evento, Integer> entradas;
    public static volatile SingularAttribute<Evento, Integer> idEvento;
    public static volatile SingularAttribute<Evento, Date> fechares;
    public static volatile SingularAttribute<Evento, String> titulo;
    public static volatile SingularAttribute<Evento, Character> asientosfijos;
    public static volatile SingularAttribute<Evento, Integer> idCreador;
    public static volatile SingularAttribute<Evento, Integer> numfilas;
    public static volatile SingularAttribute<Evento, Integer> coste;

}