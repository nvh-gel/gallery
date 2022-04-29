package com.eden.gallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Abstract JPA repository support soft deleting.
 *
 * @param <T>  model data type
 * @param <ID> model id data type
 */
@NoRepositoryBean
public interface SoftDeleteRepository<T, ID> extends JpaRepository<T, ID> {

    /**
     * Find all entities.
     *
     * @return list of entities
     */
    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.isDeleted = false")
    @NonNull
    List<T> findAll();

    /**
     * Find a single entity by id.
     *
     * @param id must not be {@literal null}. entity id to find
     * @return found entity
     */
    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.isDeleted = false and e.id = ?1")
    @NonNull
    Optional<T> findById(@NonNull ID id);

    /**
     * Soft delete an entity.
     *
     * @param id entity id to delete
     */
    @Transactional
    @Query("update #{#entityName} e set e.isDeleted = true, e.updatedAt = NOW() where e.id = ?1")
    @Modifying
    void softDelete(@NonNull ID id);
}
