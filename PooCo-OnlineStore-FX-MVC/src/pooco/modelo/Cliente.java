package pooco.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdeMail", query = "SELECT c FROM Cliente c WHERE c.ideMail = :ideMail"),
    @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cliente.findByDomicilio", query = "SELECT c FROM Cliente c WHERE c.domicilio = :domicilio"),
    @NamedQuery(name = "Cliente.findByNif", query = "SELECT c FROM Cliente c WHERE c.nif = :nif")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_eMail")
    private String ideMail;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Domicilio")
    private String domicilio;
    @Column(name = "Nif")
    private String nif;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Clientepremium clientepremium;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Clienteestandard clienteestandard;
    @OneToMany(mappedBy = "ideMailPedido")
    private List<Pedido> pedidoList;
    

    public Cliente() {
    }
    
    public Cliente(String eMail, String nombre, String domicilio, String nif) {
        this.ideMail = eMail;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
    }

    public Cliente(String ideMail) {
        this.ideMail = ideMail;
    }

    public String getIdeMail() {
        return ideMail;
    }

    public void setIdeMail(String ideMail) {
        this.ideMail = ideMail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Clientepremium getClientepremium() {
        return clientepremium;
    }

    public void setClientepremium(Clientepremium clientepremium) {
        this.clientepremium = clientepremium;
    }

    public Clienteestandard getClienteestandard() {
        return clienteestandard;
    }

    public void setClienteestandard(Clienteestandard clienteestandard) {
        this.clienteestandard = clienteestandard;
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
        hash += (ideMail != null ? ideMail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.ideMail == null && other.ideMail != null) || (this.ideMail != null && !this.ideMail.equals(other.ideMail))) {
            return false;
        }
        return true;
    } 
    
    @Override
    public String toString(){
        return " eMail: " + this.ideMail + 
              " Nombre: " + this.nombre +            
              " Domicilio: " + this.domicilio + 
              " NIF: " + this.nif ;       
    }
    
}
