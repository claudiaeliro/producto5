package pooco.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pedido")
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByIdPedido", query = "SELECT p FROM Pedido p WHERE p.idPedido = :idPedido"),
    @NamedQuery(name = "Pedido.findByCantidad", query = "SELECT p FROM Pedido p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Pedido.findByFechaHora", query = "SELECT p FROM Pedido p WHERE p.fechaHora = :fechaHora")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idPedido")
    private Integer idPedido;
    @Column(name = "Cantidad")
    private Integer cantidad;
    @Column(name = "FechaHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @JoinColumn(name = "idArticuloPedido", referencedColumnName = "idArticulo")
    @ManyToOne
    private Articulo idArticuloPedido;
    @JoinColumn(name = "id_eMailPedido", referencedColumnName = "id_eMail")
    @ManyToOne
    private Cliente ideMailPedido;

    public Pedido() {
    }

    public Pedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
     public Pedido(int numPedido, Articulo articulo, int cantidad, Cliente cliente) {
        this.idPedido = numPedido;
        this.idArticuloPedido = articulo;
        this.cantidad = cantidad;
        this.ideMailPedido = cliente;
        this.fechaHora = new Date();
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Articulo getIdArticuloPedido() {
        return idArticuloPedido;
    }

    public void setIdArticuloPedido(Articulo idArticuloPedido) {
        this.idArticuloPedido = idArticuloPedido;
    }

    public Cliente getIdeMailPedido() {
        return ideMailPedido;
    }

    public void setIdeMailPedido(Cliente ideMailPedido) {
        this.ideMailPedido = ideMailPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return "\n" 
             + "Numero de Pedido    : " + this.idPedido + "\n"
             + "Fecha y hora        : " + this.fechaHora  + "\n"            
             + "eMail del Cliente   : " + this.ideMailPedido.getIdeMail() + "\n"
             + "Nombre Cliente      : " + this.ideMailPedido.getNombre() + "\n"
             + "Codigo Articulo     : " + this.idArticuloPedido.getIdArticulo() + "\n" 
             + "Descripcion Articulo: " + this.idArticuloPedido.getDescripcion() + "\n"
             + "Cantidad            : " + this.cantidad + "\n" 
             + "Pvp Articulo        : " + String.valueOf(this.idArticuloPedido.getPvpVenta())  + "\n"
             + "Total               : " + String.valueOf(cantidad*this.idArticuloPedido.getPvpVenta())  + "\n"
             + "Coste envio         : " + String.valueOf(this.idArticuloPedido.getGastosEnvio()) + "\n"
             + "\n";              
    }  
    
}
