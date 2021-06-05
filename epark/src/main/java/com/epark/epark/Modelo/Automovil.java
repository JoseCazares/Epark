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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "automovil")
public class Automovil implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** Primary key. */
    protected static final String PK = "idAutomovil";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_automovil", unique = true, nullable = false, precision = 10)
    private int idAutomovil;
    @Column(name = "Modelo", nullable = false, length = 40)
    private String modelo;
    @Column(name = "Marca", nullable = false, length = 35)
    private String marca;
    @Column(name = "Placas", length = 7)
    private String placas;
    @Column(name = "Color", nullable = false, length = 20)
    private String color;
    @Column(name = "Descripcion", nullable = false, length = 50)
    private String descripcion;
    @OneToMany(mappedBy = "automovil")
    private Set<Registro> registro;
    @ManyToOne(optional = false)
    @JoinColumn(name = "Id_cliente", nullable = false)
    private Cliente cliente;

    /** Default constructor. */
    public Automovil() {
        super();
    }

    /**
     * Access method for idAutomovil.
     *
     * @return the current value of idAutomovil
     */
    public int getIdAutomovil() {
        return idAutomovil;
    }

    /**
     * Setter method for idAutomovil.
     *
     * @param aIdAutomovil the new value for idAutomovil
     */
    public void setIdAutomovil(int aIdAutomovil) {
        idAutomovil = aIdAutomovil;
    }

    /**
     * Access method for modelo.
     *
     * @return the current value of modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Setter method for modelo.
     *
     * @param aModelo the new value for modelo
     */
    public void setModelo(String aModelo) {
        modelo = aModelo;
    }

    /**
     * Access method for marca.
     *
     * @return the current value of marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Setter method for marca.
     *
     * @param aMarca the new value for marca
     */
    public void setMarca(String aMarca) {
        marca = aMarca;
    }

    /**
     * Access method for placas.
     *
     * @return the current value of placas
     */
    public String getPlacas() {
        return placas;
    }

    /**
     * Setter method for placas.
     *
     * @param aPlacas the new value for placas
     */
    public void setPlacas(String aPlacas) {
        placas = aPlacas;
    }

    /**
     * Access method for color.
     *
     * @return the current value of color
     */
    public String getColor() {
        return color;
    }

    /**
     * Setter method for color.
     *
     * @param aColor the new value for color
     */
    public void setColor(String aColor) {
        color = aColor;
    }

    /**
     * Access method for descripcion.
     *
     * @return the current value of descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Setter method for descripcion.
     *
     * @param aDescripcion the new value for descripcion
     */
    public void setDescripcion(String aDescripcion) {
        descripcion = aDescripcion;
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
     * Access method for cliente.
     *
     * @return the current value of cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Setter method for cliente.
     *
     * @param aCliente the new value for cliente
     */
    public void setCliente(Cliente aCliente) {
        cliente = aCliente;
    }

    /**
     * Compares the key for this instance with another Automovil.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Automovil and the key
     *         objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Automovil)) {
            return false;
        }
        Automovil that = (Automovil) other;
        if (this.getIdAutomovil() != that.getIdAutomovil()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Automovil.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Automovil))
            return false;
        return this.equalKeys(other) && ((Automovil) other).equalKeys(this);
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
        i = getIdAutomovil();
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
        StringBuffer sb = new StringBuffer("[Automovil |");
        sb.append(" idAutomovil=").append(getIdAutomovil());
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
        ret.put("idAutomovil", Integer.valueOf(getIdAutomovil()));
        return ret;
    }

}
