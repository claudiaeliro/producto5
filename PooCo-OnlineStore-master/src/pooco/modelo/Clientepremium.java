package pooco.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clientepremium")
@NamedQueries({
    @NamedQuery(name = "Clientepremium.findAll", query = "SELECT c FROM Clientepremium c"),
    @NamedQuery(name = "Clientepremium.findByIdeMailPremium", query = "SELECT c FROM Clientepremium c WHERE c.ideMailPremium = :ideMailPremium"),
    @NamedQuery(name = "Clientepremium.findByDescuento", query = "SELECT c FROM Clientepremium c WHERE c.descuento = :descuento"),
    @NamedQuery(name = "Clientepremium.findByTarifaAnual", query = "SELECT c FROM Clientepremium c WHERE c.tarifaAnual = :tarifaAnual")})
public class Clientepremium implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_eMailPremium")
    private String ideMailPremium;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Descuento")
    private Float descuento;
    @Column(name = "TarifaAnual")
    private Float tarifaAnual;
    @JoinColumn(name = "id_eMailPremium", referencedColumnName = "id_eMail", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Cliente cliente;
        
    public Clientepremium() {
    }

    public Clientepremium(String ideMailPremium) {
        this.ideMailPremium = ideMailPremium;       
        this.descuento = descuentoEnv();
        this.tarifaAnual = calcAnual();      
    }

    public String getIdeMailPremium() {
        return ideMailPremium;
    }

    public void setIdeMailPremium(String ideMailPremium) {
        this.ideMailPremium = ideMailPremium;
    }

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public Float getTarifaAnual() {
        return tarifaAnual;
    }

    public void setTarifaAnual(Float tarifaAnual) {
        this.tarifaAnual = tarifaAnual;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideMailPremium != null ? ideMailPremium.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientepremium)) {
            return false;
        }
        Clientepremium other = (Clientepremium) object;
        if ((this.ideMailPremium == null && other.ideMailPremium != null) || (this.ideMailPremium != null && !this.ideMailPremium.equals(other.ideMailPremium))) {
            return false;
        }
        return true;
    }

     
 
    public float calcAnual() {
        return tarifaAnual=30f; 
    } 
    
    
 
    public float descuentoEnv() {    
        return descuento=20f;    
    }
    
}
