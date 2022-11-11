package com.eden.gallery.model;

import com.eden.data.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Data entity for model.
 */
@Entity
@Getter
@Setter
@SQLDelete(sql = "update model set is_deleted = true, updated_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Model extends BaseModel {

    private String name;
    private String nativeName;
    private String thumbnail;
    private String url;
    private boolean favourite;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "model")
    private List<Nickname> nicks;

    @ManyToMany(targetEntity = Album.class, fetch = FetchType.LAZY, mappedBy = "models")
    private List<Album> albums;
}
