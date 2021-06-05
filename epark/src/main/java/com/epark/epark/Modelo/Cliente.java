// Generated with g9.

package com.epark.epark.Modelo;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente", indexes = { @Index(name = "cliente_correo_IX", columnList = "correo", unique = true),
        @Index(name = "cliente_usuario_IX", columnList = "usuario", unique = true) })
public class Cliente implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** Primary key. */
    protected static final String PK = "idCliente";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_cliente", unique = true, nullable = false, precision = 10)
    private int idCliente;
    @Column(name = "Nombre", nullable = false, length = 30)
    private String nombre;
    @Column(name = "Apellido_materno", nullable = false, length = 30)
    private String apellidoMaterno;
    @Column(name = "Apellido_paterno", nullable = false, length = 30)
    private String apellidoPaterno;
    @Column(unique = true, nullable = false, length = 100)
    private String correo;
    @Column(unique = true, nullable = false, length = 20)
    private String usuario;
    @Column(nullable = false, length = 20)
    private byte[] clave;
    @OneToMany(mappedBy = "cliente")
    private Set<Registro> registro;
    @OneToMany(mappedBy = "cliente")
    private Set<Automovil> automovil;

    /** Default constructor. */
    public Cliente() {
        super();
    }

    /**
     * Access method for idCliente.
     *
     * @return the current value of idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Setter method for idCliente.
     *
     * @param aIdCliente the new value for idCliente
     */
    public void setIdCliente(int aIdCliente) {
        idCliente = aIdCliente;
    }

    /**
     * Access method for nombre.
     *
     * @return the current value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter method for nombre.
     *
     * @param aNombre the new value for nombre
     */
    public void setNombre(String aNombre) {
        nombre = aNombre;
    }

    /**
     * Access method for apellidoMaterno.
     *
     * @return the current value of apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Setter method for apellidoMaterno.
     *
     * @param aApellidoMaterno the new value for apellidoMaterno
     */
    public void setApellidoMaterno(String aApellidoMaterno) {
        apellidoMaterno = aApellidoMaterno;
    }

    /**
     * Access method for apellidoPaterno.
     *
     * @return the current value of apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Setter method for apellidoPaterno.
     *
     * @param aApellidoPaterno the new value for apellidoPaterno
     */
    public void setApellidoPaterno(String aApellidoPaterno) {
        apellidoPaterno = aApellidoPaterno;
    }

    /**
     * Access method for correo.
     *
     * @return the current value of correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Setter method for correo.
     *
     * @param aCorreo the new value for correo
     */
    public void setCorreo(String aCorreo) {
        correo = aCorreo;
    }

    /**
     * Access method for usuario.
     *
     * @return the current value of usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Setter method for usuario.
     *
     * @param aUsuario the new value for usuario
     */
    public void setUsuario(String aUsuario) {
        usuario = aUsuario;
    }

    /**
     * Access method for clave.
     *
     * @return the current value of clave
     */
    public byte[] getClave() {
        return clave;
    }

    /**
     * Setter method for clave.
     *
     * @param aClave the new value for clave
     */
    public void setClave(byte[] aClave) {
        clave = aClave;
    }

    /**
     * Access method for registro.
     *
     * @return the current value of registro
     */
    public Set<Registro> getRegistro() {
        return registro;
    }

    /**
     * Setter method for registro.
     *
     * @param aRegistro the new value for registro
     */
    public void setRegistro(Set<Registro> aRegistro) {
        registro = aRegistro;
    }

    /**
     * Access method for automovil.
     *
     * @return the current value of automovil
     */
    public Set<Automovil> getAutomovil() {
        return automovil;
    }

    /**
     * Setter method for automovil.
     *
     * @param aAutomovil the new value for automovil
     */
    public void setAutomovil(Set<Automovil> aAutomovil) {
        automovil = aAutomovil;
    }

    /**
     * Compares the key for this instance with another Cliente.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Cliente and the key objects
     *         are equal
     */
    private boolean equalKeys(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Cliente)) {
            return false;
        }
        Cliente that = (Cliente) other;
        if (this.getIdCliente() != that.getIdCliente()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Cliente.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Cliente))
            return false;
        return this.equalKeys(other) && ((Cliente) other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = getIdCliente();
        result = 37 * result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[Cliente |");
        sb.append(" idCliente=").append(getIdCliente());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("idCliente", Integer.valueOf(getIdCliente()));
        return ret;
    }

}
