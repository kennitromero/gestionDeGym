package modelo.dtos;
/**
 * @author kennross
 */
public class EspacioDto {

    private int idEspacio = 0;
    private int numeroCupos = 0;
    private String fechaApertura = "";
    private int estado = 0;

    @Override
    public String toString() {
        return "EspacioDto{" + "idEspacio=" + idEspacio + ", numeroCupos=" + numeroCupos + ", fechaApertura=" + fechaApertura + ", estado=" + estado + '}';
    }

    public int getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(int idEspacio) {
        this.idEspacio = idEspacio;
    }

    public int getNumeroCupos() {
        return numeroCupos;
    }

    public void setNumeroCupos(int numeroCupos) {
        this.numeroCupos = numeroCupos;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
