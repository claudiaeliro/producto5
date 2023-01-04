package pooco.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pooco.modelo.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pooco.modelo.Clientepremium;
import pooco.persistencia.exceptions.IllegalOrphanException;
import pooco.persistencia.exceptions.NonexistentEntityException;
import pooco.persistencia.exceptions.PreexistingEntityException;

public class ClientepremiumJpaController implements Serializable {

    public ClientepremiumJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
   public ClientepremiumJpaController() {
          emf= Persistence.createEntityManagerFactory("PooCo-OnlineStorePU");
    }
    public void create(Clientepremium clientepremium) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Cliente clienteOrphanCheck = clientepremium.getCliente();
        if (clienteOrphanCheck != null) {
            Clientepremium oldClientepremiumOfCliente = clienteOrphanCheck.getClientepremium();
            if (oldClientepremiumOfCliente != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Cliente " + clienteOrphanCheck + " already has an item of type Clientepremium whose cliente column cannot be null. Please make another selection for the cliente field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = clientepremium.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getIdeMail());
                clientepremium.setCliente(cliente);
            }
            em.persist(clientepremium);
            if (cliente != null) {
                cliente.setClientepremium(clientepremium);
                cliente = em.merge(cliente);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClientepremium(clientepremium.getIdeMailPremium()) != null) {
                throw new PreexistingEntityException("Clientepremium " + clientepremium + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clientepremium clientepremium) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientepremium persistentClientepremium = em.find(Clientepremium.class, clientepremium.getIdeMailPremium());
            Cliente clienteOld = persistentClientepremium.getCliente();
            Cliente clienteNew = clientepremium.getCliente();
            List<String> illegalOrphanMessages = null;
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                Clientepremium oldClientepremiumOfCliente = clienteNew.getClientepremium();
                if (oldClientepremiumOfCliente != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Cliente " + clienteNew + " already has an item of type Clientepremium whose cliente column cannot be null. Please make another selection for the cliente field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getIdeMail());
                clientepremium.setCliente(clienteNew);
            }
            clientepremium = em.merge(clientepremium);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.setClientepremium(null);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.setClientepremium(clientepremium);
                clienteNew = em.merge(clienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = clientepremium.getIdeMailPremium();
                if (findClientepremium(id) == null) {
                    throw new NonexistentEntityException("The clientepremium with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientepremium clientepremium;
            try {
                clientepremium = em.getReference(Clientepremium.class, id);
                clientepremium.getIdeMailPremium();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientepremium with id " + id + " no longer exists.", enfe);
            }
            Cliente cliente = clientepremium.getCliente();
            if (cliente != null) {
                cliente.setClientepremium(null);
                cliente = em.merge(cliente);
            }
            em.remove(clientepremium);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clientepremium> findClientepremiumEntities() {
        return findClientepremiumEntities(true, -1, -1);
    }

    public List<Clientepremium> findClientepremiumEntities(int maxResults, int firstResult) {
        return findClientepremiumEntities(false, maxResults, firstResult);
    }

    private List<Clientepremium> findClientepremiumEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clientepremium.class));
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

    public Clientepremium findClientepremium(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clientepremium.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientepremiumCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clientepremium> rt = cq.from(Clientepremium.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
