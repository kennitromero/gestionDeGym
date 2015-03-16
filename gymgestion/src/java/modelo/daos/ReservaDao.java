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
import modelo.dtos.ReservaDto;
import modelo.util.Conexion;

public class ReservaDao {

    private Connection miCon = null;
    PreparedStatement pstm = null;
    int rtdo;
    ResultSet rs = null;
    String mensaje = "";
    String sqlTemp = "";

    public ReservaDao() {
        miCon = Conexion.getInstance();
    }

    public String insertReserva(ReservaDto nuevaReserva) {
        try {
            String sqlInsert = "INSERT INTO `reservas`(`idReserva`, `idEspacio`, `idPersona`, `Estado`) "
                    + "VALUES (null, ?, ?, ?)";
            pstm = miCon.prepareStatement(sqlInsert);

            pstm.setInt(1, nuevaReserva.getIdEspacio());
            pstm.setInt(2, nuevaReserva.getIdPersona());
            pstm.setInt(3, nuevaReserva.getEstado());
            rtdo = pstm.executeUpdate();

            if (rtdo != 0) {
                mensaje = "ok";
            } else {
                mensaje = "okno";
            }
        } catch (SQLException sqle) {
            mensaje = "Error, detalle " + sqle.getMessage();
        }
        return mensaje;
    }

    public List readReservasPorPersona(int idPersona) {
        ArrayList<ReservaDto> misReservas = null;
        sqlTemp = "SELECT r.idReserva, r.idEspacio, r.Estado FROM reservas as r "
                + "JOIN personas as p ON (p.idPersona = r.idPersona) "
                + "JOIN espacios as e ON (r.idEspacio = e.idEspacio) WHERE p.idPersona = ?";
        try {
            pstm = miCon.prepareStatement(sqlTemp);
            pstm.setInt(1, idPersona);
            rs = pstm.executeQuery();
            
                misReservas = new ArrayList();
                while (rs.next()) {
                    ReservaDto temp = new ReservaDto();
                    temp.setIdReserva(rs.getInt("idReserva"));
                    temp.setIdEspacio(rs.getInt("idEspacio"));
                    temp.setEstado(rs.getInt("Estado"));
                    misReservas.add(temp);
                }
            
        } catch (SQLException ex) {
            System.out.println("Error, detalle: " + ex.getMessage());
        }
        return misReservas;
    }

    public boolean validarNumeroReservasPorPersona(int idPersona) {
        boolean poder = true;
        try {
            int resultado = 0;
            String sqlInsert = "SELECT count(idReserva) as Cantidad FROM reservas WHERE idPersona = ? AND Estado = 1";
            pstm = miCon.prepareStatement(sqlInsert);

            pstm.setInt(1, idPersona);
            rs = pstm.executeQuery();

            while (rs.next()) {
                resultado = rs.getInt("Cantidad");
            }

            if (resultado > 0) {
                poder = false;
            }
        } catch (SQLException sqle) {
            mensaje = "Error, detalle " + sqle.getMessage();
        }
        return poder;
    }
    
    public boolean validarCancelacion(int idEspacio) {
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
            
            if (resultado >= 1) {
                poder = true;
            }
        } catch (SQLException sqle) {
            mensaje = "Error, detalle " + sqle.getMessage();
        }
        return poder;
    }
    
    public String cambiarEstado(int idReserva, int nuevoEstado) {        
        try {            
            String sqlInsert = "UPDATE `reservas` SET `Estado` = ? WHERE `idReserva` = ?";
            pstm = miCon.prepareStatement(sqlInsert);

            pstm.setInt(1, nuevoEstado);
            pstm.setInt(2, idReserva);
            
            rtdo = pstm.executeUpdate();

            if (rtdo != 0) {
                mensaje = "ok";
            } else {
                mensaje = "okno";
            }
        } catch (SQLException sqle) {
            mensaje = "Error, detalle " + sqle.getMessage();
        }
        return mensaje;
    }
}
