package pooco.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pooco.modelo.Articulo;
import pooco.modelo.Cliente;
import pooco.modelo.Pedido;
import pooco.persistencia.exceptions.NonexistentEntityException;

public class PedidoJpaController implements Serializable {

    public PedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
       public PedidoJpaController() {
          emf= Persistence.createEntityManagerFactory("PooCo-OnlineStorePU");
    }

    public void create(Pedido pedido) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo idArticuloPedido = pedido.getIdArticuloPedido();
            if (idArticuloPedido != null) {
                idArticuloPedido = em.getReference(idArticuloPedido.getClass(), idArticuloPedido.getIdArticulo());
                pedido.setIdArticuloPedido(idArticuloPedido);
            }
            Cliente ideMailPedido = pedido.getIdeMailPedido();
            if (ideMailPedido != null) {
                ideMailPedido = em.getReference(ideMailPedido.getClass(), ideMailPedido.getIdeMail());
                pedido.setIdeMailPedido(ideMailPedido);
            }
            em.persist(pedido);
            if (idArticuloPedido != null) {
                idArticuloPedido.getPedidoList().add(pedido);
                idArticuloPedido = em.merge(idArticuloPedido);
            }
            if (ideMailPedido != null) {
                ideMailPedido.getPedidoList().add(pedido);
                ideMailPedido = em.merge(ideMailPedido);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedido pedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido persistentPedido = em.find(Pedido.class, pedido.getIdPedido());
            Articulo idArticuloPedidoOld = persistentPedido.getIdArticuloPedido();
            Articulo idArticuloPedidoNew = pedido.getIdArticuloPedido();
            Cliente ideMailPedidoOld = persistentPedido.getIdeMailPedido();
            Cliente ideMailPedidoNew = pedido.getIdeMailPedido();
            if (idArticuloPedidoNew != null) {
                idArticuloPedidoNew = em.getReference(idArticuloPedidoNew.getClass(), idArticuloPedidoNew.getIdArticulo());
                pedido.setIdArticuloPedido(idArticuloPedidoNew);
            }
            if (ideMailPedidoNew != null) {
                ideMailPedidoNew = em.getReference(ideMailPedidoNew.getClass(), ideMailPedidoNew.getIdeMail());
                pedido.setIdeMailPedido(ideMailPedidoNew);
            }
            pedido = em.merge(pedido);
            if (idArticuloPedidoOld != null && !idArticuloPedidoOld.equals(idArticuloPedidoNew)) {
                idArticuloPedidoOld.getPedidoList().remove(pedido);
                idArticuloPedidoOld = em.merge(idArticuloPedidoOld);
            }
            if (idArticuloPedidoNew != null && !idArticuloPedidoNew.equals(idArticuloPedidoOld)) {
                idArticuloPedidoNew.getPedidoList().add(pedido);
                idArticuloPedidoNew = em.merge(idArticuloPedidoNew);
            }
            if (ideMailPedidoOld != null && !ideMailPedidoOld.equals(ideMailPedidoNew)) {
                ideMailPedidoOld.getPedidoList().remove(pedido);
                ideMailPedidoOld = em.merge(ideMailPedidoOld);
            }
            if (ideMailPedidoNew != null && !ideMailPedidoNew.equals(ideMailPedidoOld)) {
                ideMailPedidoNew.getPedidoList().add(pedido);
                ideMailPedidoNew = em.merge(ideMailPedidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedido.getIdPedido();
                if (findPedido(id) == null) {
                    throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedido;
            try {
                pedido = em.getReference(Pedido.class, id);
                pedido.getIdPedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.", enfe);
            }
            Articulo idArticuloPedido = pedido.getIdArticuloPedido();
            if (idArticuloPedido != null) {
                idArticuloPedido.getPedidoList().remove(pedido);
                idArticuloPedido = em.merge(idArticuloPedido);
            }
            Cliente ideMailPedido = pedido.getIdeMailPedido();
            if (ideMailPedido != null) {
                ideMailPedido.getPedidoList().remove(pedido);
                ideMailPedido = em.merge(ideMailPedido);
            }
            em.remove(pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedido> findPedidoEntities() {
        return findPedidoEntities(true, -1, -1);
    }

    public List<Pedido> findPedidoEntities(int maxResults, int firstResult) {
        return findPedidoEntities(false, maxResults, firstResult);
    }

    private List<Pedido> findPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedido.class));
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

    public Pedido findPedido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedido> rt = cq.from(Pedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
