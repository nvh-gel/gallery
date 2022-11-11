package com.eden.gallery.model;


import com.eden.data.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Data entity for model nickname.
 */
@Entity
@Getter
@Setter
@SQLDelete(sql = "update nickname set is_deleted = true, updated_at = CURRENT_TIMESTAMP where id = ?")
@Where(clause = "is_deleted = false")
public class Nickname extends BaseModel {

    private String nick;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;
}
