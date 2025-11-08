package com.joseluisgs.walaspringboot.modelos;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class) // Esto es porque vamos a auditar la entidad date para que funcione correctamente
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Creamos la fecha para que la maneje internamente con TemporalType
    // Creandose e insertandose automaticamente las del sistema
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;

    // Indicamos que es una asociacion con el usuario muchos a uno
    // Muchas compras tiene un usuario, por eso propagamos el usuario a la de muchos
    @ManyToOne
    private Usuario propietario;

    public Compra() {
    }

    public Compra(Usuario propietario) {
        this.propietario = propietario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return id == compra.id &&
                Objects.equals(fechaCompra, compra.fechaCompra) &&
                Objects.equals(propietario, compra.propietario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaCompra, propietario);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", fechaCompra=" + fechaCompra +
                ", propietario=" + propietario +
                '}';
    }
}

