package eventos.entity;

import eventos.entity.Conversacion;
import eventos.entity.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-25T16:42:27")
@StaticMetamodel(Mensaje.class)
public class Mensaje_ { 

    public static volatile SingularAttribute<Mensaje, Integer> idMensaje;
    public static volatile SingularAttribute<Mensaje, Date> fecha;
    public static volatile SingularAttribute<Mensaje, Integer> minuto;
    public static volatile SingularAttribute<Mensaje, Integer> hora;
    public static volatile SingularAttribute<Mensaje, Usuario> idUsuario;
    public static volatile SingularAttribute<Mensaje, Conversacion> idConversacion;
    public static volatile SingularAttribute<Mensaje, String> mensaje;

}