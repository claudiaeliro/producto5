package pooco.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "articulo")
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a"),
    @NamedQuery(name = "Articulo.findByIdArticulo", query = "SELECT a FROM Articulo a WHERE a.idArticulo = :idArticulo"),
    @NamedQuery(name = "Articulo.findByDescripcion", query = "SELECT a FROM Articulo a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Articulo.findByPvpVenta", query = "SELECT a FROM Articulo a WHERE a.pvpVenta = :pvpVenta"),
    @NamedQuery(name = "Articulo.findByGastosEnvio", query = "SELECT a FROM Articulo a WHERE a.gastosEnvio = :gastosEnvio"),
    @NamedQuery(name = "Articulo.findByTiempoPreparacion", query = "SELECT a FROM Articulo a WHERE a.tiempoPreparacion = :tiempoPreparacion")})
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idArticulo")
    private String idArticulo;
    @Column(name = "Descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PvpVenta")
    private Float pvpVenta;
    @Column(name = "GastosEnvio")
    private Float gastosEnvio;
    @Column(name = "TiempoPreparacion")
    private Integer tiempoPreparacion;
    @OneToMany(mappedBy = "idArticuloPedido")
    private List<Pedido> pedidoList;

    public Articulo() {
    }
    
    public Articulo(String codigo, String descripcion, float pvpVenta, float gastosEnvio, int tiempoPreparacion) {
        this.idArticulo = codigo;
        this.descripcion = descripcion;
        this.pvpVenta = pvpVenta;
        this.gastosEnvio = gastosEnvio;
        this.tiempoPreparacion = tiempoPreparacion;
    }
    
    public Articulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPvpVenta() {
        return pvpVenta;
    }

    public void setPvpVenta(Float pvpVenta) {
        this.pvpVenta = pvpVenta;
    }

    public Float getGastosEnvio() {
        return gastosEnvio;
    }

    public void setGastosEnvio(Float gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    public Integer getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(Integer tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArticulo != null ? idArticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.idArticulo == null && other.idArticulo != null) || (this.idArticulo != null && !this.idArticulo.equals(other.idArticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "ARTICULO" + "\n"
                + "Codigo             : " + this.idArticulo + "\n"
                + "Descripcion        : " + this.descripcion + "\n"
                + "PVP de Venta       : " + this.pvpVenta + "\n"
                + "Gastos de envio    : " + this.gastosEnvio + "\n"
                + "Tiempo preparacion : " + this.tiempoPreparacion + " minutos.\n";
    }
    
}
