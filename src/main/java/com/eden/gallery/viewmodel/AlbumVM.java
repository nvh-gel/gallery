package com.eden.gallery.viewmodel;

import com.eden.common.viewmodel.BaseVM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for album.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AlbumVM extends BaseVM {

    private String name;
    private String thumbnail;
    private String url;
    private String tags;
    private Boolean favourite;
    private Long publisherId;
    private List<ModelVM> models;
}
