package pooco.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pooco.modelo.Clientepremium;
import pooco.modelo.Clienteestandard;
import pooco.modelo.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pooco.modelo.Cliente;
import pooco.persistencia.exceptions.IllegalOrphanException;
import pooco.persistencia.exceptions.NonexistentEntityException;
import pooco.persistencia.exceptions.PreexistingEntityException;

public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
      public ClienteJpaController(){
        emf= Persistence.createEntityManagerFactory("PooCo-OnlineStorePU");
    }


    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getPedidoList() == null) {
            cliente.setPedidoList(new ArrayList<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientepremium clientepremium = cliente.getClientepremium();
            if (clientepremium != null) {
                clientepremium = em.getReference(clientepremium.getClass(), clientepremium.getIdeMailPremium());
                cliente.setClientepremium(clientepremium);
            }
            Clienteestandard clienteestandard = cliente.getClienteestandard();
            if (clienteestandard != null) {
                clienteestandard = em.getReference(clienteestandard.getClass(), clienteestandard.getIdeMailestandard());
                cliente.setClienteestandard(clienteestandard);
            }
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : cliente.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdPedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            cliente.setPedidoList(attachedPedidoList);
            em.persist(cliente);
            if (clientepremium != null) {
                Cliente oldClienteOfClientepremium = clientepremium.getCliente();
                if (oldClienteOfClientepremium != null) {
                    oldClienteOfClientepremium.setClientepremium(null);
                    oldClienteOfClientepremium = em.merge(oldClienteOfClientepremium);
                }
                clientepremium.setCliente(cliente);
                clientepremium = em.merge(clientepremium);
            }
            if (clienteestandard != null) {
                Cliente oldClienteOfClienteestandard = clienteestandard.getCliente();
                if (oldClienteOfClienteestandard != null) {
                    oldClienteOfClienteestandard.setClienteestandard(null);
                    oldClienteOfClienteestandard = em.merge(oldClienteOfClienteestandard);
                }
                clienteestandard.setCliente(cliente);
                clienteestandard = em.merge(clienteestandard);
            }
            for (Pedido pedidoListPedido : cliente.getPedidoList()) {
                Cliente oldIdeMailPedidoOfPedidoListPedido = pedidoListPedido.getIdeMailPedido();
                pedidoListPedido.setIdeMailPedido(cliente);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldIdeMailPedidoOfPedidoListPedido != null) {
                    oldIdeMailPedidoOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldIdeMailPedidoOfPedidoListPedido = em.merge(oldIdeMailPedidoOfPedidoListPedido);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getIdeMail()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdeMail());
            Clientepremium clientepremiumOld = persistentCliente.getClientepremium();
            Clientepremium clientepremiumNew = cliente.getClientepremium();
            Clienteestandard clienteestandardOld = persistentCliente.getClienteestandard();
            Clienteestandard clienteestandardNew = cliente.getClienteestandard();
            List<Pedido> pedidoListOld = persistentCliente.getPedidoList();
            List<Pedido> pedidoListNew = cliente.getPedidoList();
            List<String> illegalOrphanMessages = null;
            if (clientepremiumOld != null && !clientepremiumOld.equals(clientepremiumNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Clientepremium " + clientepremiumOld + " since its cliente field is not nullable.");
            }
            if (clienteestandardOld != null && !clienteestandardOld.equals(clienteestandardNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Clienteestandard " + clienteestandardOld + " since its cliente field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clientepremiumNew != null) {
                clientepremiumNew = em.getReference(clientepremiumNew.getClass(), clientepremiumNew.getIdeMailPremium());
                cliente.setClientepremium(clientepremiumNew);
            }
            if (clienteestandardNew != null) {
                clienteestandardNew = em.getReference(clienteestandardNew.getClass(), clienteestandardNew.getIdeMailestandard());
                cliente.setClienteestandard(clienteestandardNew);
            }
            List<Pedido> attachedPedidoListNew = new ArrayList<Pedido>();
            for (Pedido pedidoListNewPedidoToAttach : pedidoListNew) {
                pedidoListNewPedidoToAttach = em.getReference(pedidoListNewPedidoToAttach.getClass(), pedidoListNewPedidoToAttach.getIdPedido());
                attachedPedidoListNew.add(pedidoListNewPedidoToAttach);
            }
            pedidoListNew = attachedPedidoListNew;
            cliente.setPedidoList(pedidoListNew);
            cliente = em.merge(cliente);
            if (clientepremiumNew != null && !clientepremiumNew.equals(clientepremiumOld)) {
                Cliente oldClienteOfClientepremium = clientepremiumNew.getCliente();
                if (oldClienteOfClientepremium != null) {
                    oldClienteOfClientepremium.setClientepremium(null);
                    oldClienteOfClientepremium = em.merge(oldClienteOfClientepremium);
                }
                clientepremiumNew.setCliente(cliente);
                clientepremiumNew = em.merge(clientepremiumNew);
            }
            if (clienteestandardNew != null && !clienteestandardNew.equals(clienteestandardOld)) {
                Cliente oldClienteOfClienteestandard = clienteestandardNew.getCliente();
                if (oldClienteOfClienteestandard != null) {
                    oldClienteOfClienteestandard.setClienteestandard(null);
                    oldClienteOfClienteestandard = em.merge(oldClienteOfClienteestandard);
                }
                clienteestandardNew.setCliente(cliente);
                clienteestandardNew = em.merge(clienteestandardNew);
            }
            for (Pedido pedidoListOldPedido : pedidoListOld) {
                if (!pedidoListNew.contains(pedidoListOldPedido)) {
                    pedidoListOldPedido.setIdeMailPedido(null);
                    pedidoListOldPedido = em.merge(pedidoListOldPedido);
                }
            }
            for (Pedido pedidoListNewPedido : pedidoListNew) {
                if (!pedidoListOld.contains(pedidoListNewPedido)) {
                    Cliente oldIdeMailPedidoOfPedidoListNewPedido = pedidoListNewPedido.getIdeMailPedido();
                    pedidoListNewPedido.setIdeMailPedido(cliente);
                    pedidoListNewPedido = em.merge(pedidoListNewPedido);
                    if (oldIdeMailPedidoOfPedidoListNewPedido != null && !oldIdeMailPedidoOfPedidoListNewPedido.equals(cliente)) {
                        oldIdeMailPedidoOfPedidoListNewPedido.getPedidoList().remove(pedidoListNewPedido);
                        oldIdeMailPedidoOfPedidoListNewPedido = em.merge(oldIdeMailPedidoOfPedidoListNewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cliente.getIdeMail();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdeMail();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Clientepremium clientepremiumOrphanCheck = cliente.getClientepremium();
            if (clientepremiumOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Clientepremium " + clientepremiumOrphanCheck + " in its clientepremium field has a non-nullable cliente field.");
            }
            Clienteestandard clienteestandardOrphanCheck = cliente.getClienteestandard();
            if (clienteestandardOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Clienteestandard " + clienteestandardOrphanCheck + " in its clienteestandard field has a non-nullable cliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pedido> pedidoList = cliente.getPedidoList();
            for (Pedido pedidoListPedido : pedidoList) {
                pedidoListPedido.setIdeMailPedido(null);
                pedidoListPedido = em.merge(pedidoListPedido);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cliente findCliente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
