package eventos.entity;

import eventos.entity.Conversacion;
import eventos.entity.Evento;
import eventos.entity.Roles;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-11T19:49:38")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> apellidos;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, Roles> rol;
    public static volatile SingularAttribute<Usuario, Date> nacimiento;
    public static volatile SingularAttribute<Usuario, String> password;
    public static volatile SingularAttribute<Usuario, String> domicilio;
    public static volatile SingularAttribute<Usuario, String> ciudad;
    public static volatile SingularAttribute<Usuario, Character> genero;
    public static volatile CollectionAttribute<Usuario, Conversacion> conversacionCollection;
    public static volatile SingularAttribute<Usuario, String> email;
    public static volatile CollectionAttribute<Usuario, Evento> eventoCollection;
    public static volatile CollectionAttribute<Usuario, Conversacion> conversacionCollection1;

}