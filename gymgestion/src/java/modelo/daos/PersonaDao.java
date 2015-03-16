package modelo.daos;

/**
 * @author Kennit David Ruz Romero Fecha: 4 de de Enero de 2015
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.dtos.PersonaDto;
import modelo.util.Conexion;

public class PersonaDao {

    private Connection miCon = null;
    PreparedStatement pstm = null;
    int rtdo;
    ResultSet rs = null;
    String mensaje = "";
    String sqlTemp = "";

    public PersonaDao() {
        miCon = Conexion.getInstance();
    }

    public PersonaDto readPersona(String correo) {
        sqlTemp = "SELECT `idPersona`, `Nombres`, `Apellidos`, `Edad`, `Sexo`, "
                + "`CorreoElectronico` FROM `personas` WHERE `CorreoElectronico` = ?";
        PersonaDto persona = null;
        try {
            pstm = miCon.prepareStatement(sqlTemp);
            pstm.setString(1, correo);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                persona = new PersonaDto();
                persona.setIdPersona(rs.getInt("idPersona"));
                persona.setNombres(rs.getString("Nombres"));
                persona.setApellidos(rs.getString("Apellidos"));
                persona.setEdad(rs.getInt("Edad"));
                persona.setSexo(rs.getInt("Sexo"));
                persona.setCorreoElectronico(rs.getString("CorreoElectronico"));
            }
        } catch (SQLException ex) {
            System.out.println("Error, detalle: " + ex.getMessage());
        }
        return persona;
    }

    public String obtenerNombrePorId(int idDepartamento) {
        try {
            String sqlInsert = "SELECT `Nombre` FROM `departamentos` WHERE `idDepartamento` = ?";
            pstm = miCon.prepareStatement(sqlInsert);

            pstm.setInt(1, idDepartamento);
            rs = pstm.executeQuery();

            while (rs.next()) {
                mensaje = rs.getString("Nombre");
            }
        } catch (SQLException sqle) {
            mensaje = "Error, detalle " + sqle.getMessage();
        }
        return mensaje;
    }
}
