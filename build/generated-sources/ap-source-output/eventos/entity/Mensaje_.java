package eventos.entity;

import eventos.entity.Conversacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-13T13:36:36")
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-13T12:58:05")
@StaticMetamodel(Mensaje.class)
public class Mensaje_ { 

    public static volatile SingularAttribute<Mensaje, Integer> idMensaje;
    public static volatile SingularAttribute<Mensaje, Date> hora;
    public static volatile SingularAttribute<Mensaje, Conversacion> idConversacion;
    public static volatile SingularAttribute<Mensaje, String> mensaje;

}