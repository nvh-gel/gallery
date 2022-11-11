package com.eden.gallery.model;

import com.eden.data.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Article model.
 */
@Entity
@Getter
@Setter
@SQLDelete(sql = "update article set is_deleted = true, updated_at = CURRENT_TIMESTAMP where id = ?")
@Where(clause = "is_deleted = false")
public class Article extends BaseModel {

    private String title;
    private String content;
    private String author;

    @OneToOne(targetEntity = Album.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    private Album album;
}
