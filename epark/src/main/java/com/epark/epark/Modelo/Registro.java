// Generated with g9.

package com.epark.epark.Modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "registro")
public class Registro implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** Primary key. */
    protected static final String PK = "idRegistro";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_registro", unique = true, nullable = false, precision = 10)
    private int idRegistro;
    @Column(name = "Fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;
    @Column(name = "Fecha_salida", nullable = false)
    private LocalDate fechaSalida;
    @Column(name = "Hora_entrada", nullable = false)
    private LocalTime horaEntrada;
    @Column(name = "Hora_salida", nullable = false)
    private LocalTime horaSalida;
    @Column(name = "Monto_a_pagar", nullable = false, precision = 6, scale = 2)
    private BigDecimal montoAPagar;
    @Column(name = "Estatus", nullable = false, length = 1)
    private String estatus;
    @ManyToOne(optional = false)
    @JoinColumn(name = "Id_automovil", nullable = false)
    private Automovil automovil;
    @ManyToOne(optional = false)
    @JoinColumn(name = "Id_cliente", nullable = false)
    private Cliente cliente;

    /** Default constructor. */
    public Registro() {
        super();
    }

    /**
     * Access method for idRegistro.
     *
     * @return the current value of idRegistro
     */
    public int getIdRegistro() {
        return idRegistro;
    }

    /**
     * Setter method for idRegistro.
     *
     * @param aIdRegistro the new value for idRegistro
     */
    public void setIdRegistro(int aIdRegistro) {
        idRegistro = aIdRegistro;
    }

    /**
     * Access method for fechaIngreso.
     *
     * @return the current value of fechaIngreso
     */
    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Setter method for fechaIngreso.
     *
     * @param aFechaIngreso the new value for fechaIngreso
     */
    public void setFechaIngreso(LocalDate aFechaIngreso) {
        fechaIngreso = aFechaIngreso;
    }

    /**
     * Access method for fechaSalida.
     *
     * @return the current value of fechaSalida
     */
    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    /**
     * Setter method for fechaSalida.
     *
     * @param aFechaSalida the new value for fechaSalida
     */
    public void setFechaSalida(LocalDate aFechaSalida) {
        fechaSalida = aFechaSalida;
    }

    /**
     * Access method for horaEntrada.
     *
     * @return the current value of horaEntrada
     */
    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    /**
     * Setter method for horaEntrada.
     *
     * @param aHoraEntrada the new value for horaEntrada
     */
    public void setHoraEntrada(LocalTime aHoraEntrada) {
        horaEntrada = aHoraEntrada;
    }

    /**
     * Access method for horaSalida.
     *
     * @return the current value of horaSalida
     */
    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    /**
     * Setter method for horaSalida.
     *
     * @param aHoraSalida the new value for horaSalida
     */
    public void setHoraSalida(LocalTime aHoraSalida) {
        horaSalida = aHoraSalida;
    }

    /**
     * Access method for montoAPagar.
     *
     * @return the current value of montoAPagar
     */
    public BigDecimal getMontoAPagar() {
        return montoAPagar;
    }

    /**
     * Setter method for montoAPagar.
     *
     * @param aMontoAPagar the new value for montoAPagar
     */
    public void setMontoAPagar(BigDecimal aMontoAPagar) {
        montoAPagar = aMontoAPagar;
    }

    /**
     * Access method for estatus.
     *
     * @return the current value of estatus
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * Setter method for estatus.
     *
     * @param aEstatus the new value for estatus
     */
    public void setEstatus(String aEstatus) {
        estatus = aEstatus;
    }

    /**
     * Access method for automovil.
     *
     * @return the current value of automovil
     */
    public Automovil getAutomovil() {
        return automovil;
    }

    /**
     * Setter method for automovil.
     *
     * @param aAutomovil the new value for automovil
     */
    public void setAutomovil(Automovil aAutomovil) {
        automovil = aAutomovil;
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
     * Compares the key for this instance with another Registro.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Registro and the key
     *         objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Registro)) {
            return false;
        }
        Registro that = (Registro) other;
        if (this.getIdRegistro() != that.getIdRegistro()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Registro.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Registro))
            return false;
        return this.equalKeys(other) && ((Registro) other).equalKeys(this);
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
        i = getIdRegistro();
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
        StringBuffer sb = new StringBuffer("[Registro |");
        sb.append(" idRegistro=").append(getIdRegistro());
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
        ret.put("idRegistro", Integer.valueOf(getIdRegistro()));
        return ret;
    }

}
