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
import pooco.modelo.Clienteestandard;
import pooco.persistencia.exceptions.IllegalOrphanException;
import pooco.persistencia.exceptions.NonexistentEntityException;
import pooco.persistencia.exceptions.PreexistingEntityException;

public class ClienteestandardJpaController implements Serializable {

    public ClienteestandardJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
        public ClienteestandardJpaController(){
        emf= Persistence.createEntityManagerFactory("PooCo-OnlineStorePU");
    }

    public void create(Clienteestandard clienteestandard) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Cliente clienteOrphanCheck = clienteestandard.getCliente();
        if (clienteOrphanCheck != null) {
            Clienteestandard oldClienteestandardOfCliente = clienteOrphanCheck.getClienteestandard();
            if (oldClienteestandardOfCliente != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Cliente " + clienteOrphanCheck + " already has an item of type Clienteestandard whose cliente column cannot be null. Please make another selection for the cliente field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = clienteestandard.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getIdeMail());
                clienteestandard.setCliente(cliente);
            }
            em.persist(clienteestandard);
            if (cliente != null) {
                cliente.setClienteestandard(clienteestandard);
                cliente = em.merge(cliente);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClienteestandard(clienteestandard.getIdeMailestandard()) != null) {
                throw new PreexistingEntityException("Clienteestandard " + clienteestandard + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clienteestandard clienteestandard) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clienteestandard persistentClienteestandard = em.find(Clienteestandard.class, clienteestandard.getIdeMailestandard());
            Cliente clienteOld = persistentClienteestandard.getCliente();
            Cliente clienteNew = clienteestandard.getCliente();
            List<String> illegalOrphanMessages = null;
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                Clienteestandard oldClienteestandardOfCliente = clienteNew.getClienteestandard();
                if (oldClienteestandardOfCliente != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Cliente " + clienteNew + " already has an item of type Clienteestandard whose cliente column cannot be null. Please make another selection for the cliente field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getIdeMail());
                clienteestandard.setCliente(clienteNew);
            }
            clienteestandard = em.merge(clienteestandard);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.setClienteestandard(null);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.setClienteestandard(clienteestandard);
                clienteNew = em.merge(clienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = clienteestandard.getIdeMailestandard();
                if (findClienteestandard(id) == null) {
                    throw new NonexistentEntityException("The clienteestandard with id " + id + " no longer exists.");
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
            Clienteestandard clienteestandard;
            try {
                clienteestandard = em.getReference(Clienteestandard.class, id);
                clienteestandard.getIdeMailestandard();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clienteestandard with id " + id + " no longer exists.", enfe);
            }
            Cliente cliente = clienteestandard.getCliente();
            if (cliente != null) {
                cliente.setClienteestandard(null);
                cliente = em.merge(cliente);
            }
            em.remove(clienteestandard);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clienteestandard> findClienteestandardEntities() {
        return findClienteestandardEntities(true, -1, -1);
    }

    public List<Clienteestandard> findClienteestandardEntities(int maxResults, int firstResult) {
        return findClienteestandardEntities(false, maxResults, firstResult);
    }

    private List<Clienteestandard> findClienteestandardEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clienteestandard.class));
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

    public Clienteestandard findClienteestandard(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clienteestandard.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteestandardCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clienteestandard> rt = cq.from(Clienteestandard.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
