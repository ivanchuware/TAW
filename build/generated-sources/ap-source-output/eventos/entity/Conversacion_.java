package eventos.entity;

import eventos.entity.Mensaje;
import eventos.entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-25T16:42:27")
@StaticMetamodel(Conversacion.class)
public class Conversacion_ { 

    public static volatile SingularAttribute<Conversacion, Usuario> idUsuario1;
    public static volatile SingularAttribute<Conversacion, Usuario> idUsuario2;
    public static volatile ListAttribute<Conversacion, Mensaje> mensajeList;
    public static volatile SingularAttribute<Conversacion, Integer> idConversacion;

}