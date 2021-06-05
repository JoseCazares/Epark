// Generated with g9.

package com.epark.epark.Modelo;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "contacto")
public class Contacto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** Primary key. */
    protected static final String PK = "idContacto";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_contacto", unique = true, nullable = false, precision = 10)
    private int idContacto;
    @Column(name = "Nombre_completo", nullable = false, length = 80)
    private String nombreCompleto;
    @Column(name = "Correo", nullable = false, length = 100)
    private String correo;
    @Column(name = "Mensaje", nullable = false, length = 200)
    private String mensaje;

    /** Default constructor. */
    public Contacto() {
        super();
    }

    /**
     * Access method for idContacto.
     *
     * @return the current value of idContacto
     */
    public int getIdContacto() {
        return idContacto;
    }

    /**
     * Setter method for idContacto.
     *
     * @param aIdContacto the new value for idContacto
     */
    public void setIdContacto(int aIdContacto) {
        idContacto = aIdContacto;
    }

    /**
     * Access method for nombreCompleto.
     *
     * @return the current value of nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Setter method for nombreCompleto.
     *
     * @param aNombreCompleto the new value for nombreCompleto
     */
    public void setNombreCompleto(String aNombreCompleto) {
        nombreCompleto = aNombreCompleto;
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
     * Access method for mensaje.
     *
     * @return the current value of mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Setter method for mensaje.
     *
     * @param aMensaje the new value for mensaje
     */
    public void setMensaje(String aMensaje) {
        mensaje = aMensaje;
    }

    /**
     * Compares the key for this instance with another Contacto.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Contacto and the key
     *         objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Contacto)) {
            return false;
        }
        Contacto that = (Contacto) other;
        if (this.getIdContacto() != that.getIdContacto()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Contacto.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Contacto))
            return false;
        return this.equalKeys(other) && ((Contacto) other).equalKeys(this);
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
        i = getIdContacto();
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
        StringBuffer sb = new StringBuffer("[Contacto |");
        sb.append(" idContacto=").append(getIdContacto());
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
        ret.put("idContacto", Integer.valueOf(getIdContacto()));
        return ret;
    }

}
