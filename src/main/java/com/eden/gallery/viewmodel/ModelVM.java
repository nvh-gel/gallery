package com.eden.gallery.viewmodel;

import com.eden.common.viewmodel.BaseVM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO of model.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ModelVM extends BaseVM {

    private String name;
    private String nativeName;
    private String thumbnail;
    private String url;
    private Boolean favourite;
    private List<NicknameVM> nicks;
    private List<AlbumVM> albums;
}
