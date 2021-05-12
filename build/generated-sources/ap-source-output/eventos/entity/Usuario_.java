package eventos.entity;

import eventos.entity.Conversacion;
import eventos.entity.Evento;
import eventos.entity.Roles;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-12T18:08:39")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> apellidos;
    public static volatile ListAttribute<Usuario, Conversacion> conversacionList;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile ListAttribute<Usuario, Evento> eventoList;
    public static volatile ListAttribute<Usuario, Evento> eventoList1;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, Roles> rol;
    public static volatile SingularAttribute<Usuario, Date> nacimiento;
    public static volatile SingularAttribute<Usuario, String> password;
    public static volatile SingularAttribute<Usuario, String> domicilio;
    public static volatile ListAttribute<Usuario, Conversacion> conversacionList1;
    public static volatile SingularAttribute<Usuario, String> ciudad;
    public static volatile SingularAttribute<Usuario, Character> genero;
    public static volatile SingularAttribute<Usuario, String> email;

}