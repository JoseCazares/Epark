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
import javax.persistence.Version;

@Entity
public class Mensajes implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** Primary key. */
    protected static final String PK = "idMensajes";

    /**
     * The optimistic lock. Available via standard bean get/set operations.
     */
    @Version
    @Column(name = "LOCK_FLAG")
    private Integer lockFlag;

    /**
     * Access method for the lockFlag property.
     *
     * @return the current value of the lockFlag property
     */
    public Integer getLockFlag() {
        return lockFlag;
    }

    /**
     * Sets the value of the lockFlag property.
     *
     * @param aLockFlag the new value of the lockFlag property
     */
    public void setLockFlag(Integer aLockFlag) {
        lockFlag = aLockFlag;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_mensajes", unique = true, nullable = false, precision = 10)
    private int idMensajes;
    @Column(name = "Nombre_completo", nullable = false, length = 80)
    private String nombreCompleto;
    @Column(name = "Correo", nullable = false, length = 100)
    private String correo;
    @Column(name = "Mensaje", nullable = false, length = 200)
    private String mensaje;

    /** Default constructor. */
    public Mensajes() {
        super();
    }

    /**
     * Access method for idMensajes.
     *
     * @return the current value of idMensajes
     */
    public int getIdMensajes() {
        return idMensajes;
    }

    /**
     * Setter method for idMensajes.
     *
     * @param aIdMensajes the new value for idMensajes
     */
    public void setIdMensajes(int aIdMensajes) {
        idMensajes = aIdMensajes;
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
     * Compares the key for this instance with another Mensajes.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Mensajes and the key
     *         objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Mensajes)) {
            return false;
        }
        Mensajes that = (Mensajes) other;
        if (this.getIdMensajes() != that.getIdMensajes()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Mensajes.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Mensajes))
            return false;
        return this.equalKeys(other) && ((Mensajes) other).equalKeys(this);
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
        i = getIdMensajes();
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
        StringBuffer sb = new StringBuffer("[Mensajes |");
        sb.append(" idMensajes=").append(getIdMensajes());
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
        ret.put("idMensajes", Integer.valueOf(getIdMensajes()));
        return ret;
    }

}
