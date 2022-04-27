package com.eden.gallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface SoftDeleteRepository<T, ID> extends JpaRepository<T, ID> {

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.isDeleted = false")
    @NonNull
    List<T> findAll();

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.isDeleted = false and e.id = ?1")
    @NonNull
    Optional<T> findById(@NonNull ID id);

    @Transactional
    @Query("update #{#entityName} e set e.isDeleted = true, e.updatedAt = NOW() where e.id = ?1")
    @Modifying
    void softDelete(@NonNull ID id);
}
