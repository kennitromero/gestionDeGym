package modelo.dtos;

/**
 * @author kennross
 */
public class ReservaDto {

    private int idReserva = 0;
    private int idEspacio = 0;
    private int idPersona = 0;
    private int estado = 0;

    @Override
    public String toString() {
        return "ReservaDto{" + "idReserva=" + idReserva + ", idEspacio=" + idEspacio + ", idPersona=" + idPersona + ", estado=" + estado + '}';
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(int idEspacio) {
        this.idEspacio = idEspacio;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
