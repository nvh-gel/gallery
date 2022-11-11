package com.eden.gallery.model;

import com.eden.data.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Data entity for publisher.
 */
@Entity
@Getter
@Setter
@SQLDelete(sql = "update publisher set is_deleted = true, updated_at = CURRENT_TIMESTAMP where id = ?")
@Where(clause = "is_deleted = false")
public class Publisher extends BaseModel {

    private String name;

    @OneToMany(targetEntity = Album.class, fetch = FetchType.LAZY, mappedBy = "publisher")
    private List<Album> albums;
}
