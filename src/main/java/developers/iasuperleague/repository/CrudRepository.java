package developers.iasuperleague.repository;

/**
 *
 * @param <E> type of the entity
 * @param <PK> type of the primary key*
 */
public interface CrudRepository<E, PK> {

    void create(E entity);

    void update(E entity);

    E findById(PK id);
}
