package pooco.modelo;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "clienteestandard")
@NamedQueries({
    @NamedQuery(name = "Clienteestandard.findAll", query = "SELECT c FROM Clienteestandard c"),
    @NamedQuery(name = "Clienteestandard.findByIdeMailestandard", query = "SELECT c FROM Clienteestandard c WHERE c.ideMailestandard = :ideMailestandard"),
    @NamedQuery(name = "Clienteestandard.findByDescuento", query = "SELECT c FROM Clienteestandard c WHERE c.descuento = :descuento"),
    @NamedQuery(name = "Clienteestandard.findByTarifaAnual", query = "SELECT c FROM Clienteestandard c WHERE c.tarifaAnual = :tarifaAnual")})
public class Clienteestandard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_eMailestandard")
    private String ideMailestandard;    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Descuento")
    private Float descuento;
    @Column(name = "TarifaAnual")
    private Float tarifaAnual;
    @JoinColumn(name = "id_eMailestandard", referencedColumnName = "id_eMail", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Cliente cliente;
    
    
    public Clienteestandard() {
    }
    
    public Clienteestandard(String ideMailestandard) {
        this.ideMailestandard = ideMailestandard;
        this.descuento = descuentoEnv();
        this.tarifaAnual = calcAnual();        
    }

    public String getIdeMailestandard() {
        return ideMailestandard;
    }

    public void setIdeMailestandard(String ideMailestandard) {
        this.ideMailestandard = ideMailestandard;
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
        hash += (ideMailestandard != null ? ideMailestandard.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clienteestandard)) {
            return false;
        }
        Clienteestandard other = (Clienteestandard) object;
        if ((this.ideMailestandard == null && other.ideMailestandard != null) || (this.ideMailestandard != null && !this.ideMailestandard.equals(other.ideMailestandard))) {
            return false;
        }
        return true;
    }


    public float calcAnual() {
        return tarifaAnual=0f; 
    } 
      
  
    public float descuentoEnv() {    
        return descuento=0f;    
    }
    
}
