package com.eden.gallery.model;

import com.eden.data.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Data entity for album.
 */
@Entity
@Getter
@Setter
@SQLDelete(sql = "update album set is_deleted = true, updated_at = CURRENT_TIMESTAMP where id = ?")
@Where(clause = "is_deleted = false")
public class Album extends BaseModel {

    private String name;
    private String thumbnail;
    private String url;
    private String tags;
    private boolean favourite;

    @ManyToOne(targetEntity = Publisher.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToMany(targetEntity = Model.class, fetch = FetchType.LAZY)
    @JoinTable(name = "album_model")
    private List<Model> models;
}
