package modelo.dtos;

/**
 * @author kennross
 */
public class PersonaDto {

    private int idPersona = 0;
    private String nombres = "";
    private String apellidos = "";
    private int edad = 0;
    private int sexo = 0;
    private String correoElectronico = "";

    @Override
    public String toString() {
        return "PersonaDto{" + "idPersona=" + idPersona + ", nombres=" + nombres + ", apellidos=" + apellidos + ", edad=" + edad + ", sexo=" + sexo + ", correoElectronico=" + correoElectronico + '}';
    }
    
    public int getIdPersona() {
        return idPersona;
    }
    
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}