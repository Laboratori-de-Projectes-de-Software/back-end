package developers.iasuperleague.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @param <E> Type of the entity
 * @param <PK> Type of the entity's primary key
 */
public abstract class AbstractCrudRepository<E, PK> implements CrudRepository<E, PK> {

    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * Saves the entity type class;
     */
    private final Class<E> entityClass;


    protected AbstractCrudRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(E entity) {
        entityManager.persist(entity);
        entityManager.flush();
    }

    public void update(E entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    public E findById(PK id) {
        return entityManager.find(entityClass, id);
    }
}
