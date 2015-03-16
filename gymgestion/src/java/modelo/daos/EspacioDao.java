package modelo.daos;

/**
 * @author Kennit David Ruz Romero Fecha: 4 de de Enero de 2015
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.dtos.EspacioDto;
import modelo.util.Conexion;

public class EspacioDao {

    private Connection miCon = null;
    PreparedStatement pstm = null;
    int rtdo;
    ResultSet rs = null;
    String mensaje = "";
    String sqlTemp = "";

    public EspacioDao() {
        miCon = Conexion.getInstance();
    }

    public List readEspacios() {
        ArrayList<EspacioDto> espacios = null;
        sqlTemp = "SELECT `idEspacio`, `NumeroCupos`, DATE_FORMAT(`FechaApertura`, '%d de %M del %Y') as FechaApertura, `Estado` FROM `espacios`";        
        try {
            pstm = miCon.prepareStatement(sqlTemp);
            rs = pstm.executeQuery();

            espacios = new ArrayList();
            while (rs.next()) {
                EspacioDto temp = new EspacioDto();
                temp.setIdEspacio(rs.getInt("idEspacio"));
                temp.setNumeroCupos(rs.getInt("NumeroCupos"));
                temp.setFechaApertura(rs.getString("FechaApertura"));
                temp.setEstado(rs.getInt("Estado"));
                espacios.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("Error, detalle: " + ex.getMessage());
        }
        return espacios;
    }

    public String obtenerFechaPorId(int idEspacio) {
        try {
            String sqlInsert = "SELECT DATE_FORMAT(`FechaApertura`, '%d de %M del %Y') as FechaApertura FROM `espacios` WHERE `idEspacio` = ?";
            pstm = miCon.prepareStatement(sqlInsert);

            pstm.setInt(1, idEspacio);
            rs = pstm.executeQuery();

            while (rs.next()) {
                mensaje = rs.getString("FechaApertura");
            }
        } catch (SQLException sqle) {
            mensaje = "Error, detalle " + sqle.getMessage();
        }
        return mensaje;
    }
    
    public boolean validarFechaDeAnticipacion(int idEspacio) {
        boolean poder = false;
        try {
            int resultado = 0;            
            String sqlInsert = "SELECT DATEDIFF(FechaApertura, now()) as Diferencia FROM espacios WHERE idEspacio = ?";
            pstm = miCon.prepareStatement(sqlInsert);

            pstm.setInt(1, idEspacio);
            rs = pstm.executeQuery();

            while (rs.next()) {
                resultado = rs.getInt("Diferencia");
            }
            
            if (resultado >= 7) {
                poder = true;
            }
        } catch (SQLException sqle) {
            mensaje = "Error, detalle " + sqle.getMessage();
        }
        return poder;
    }
}
