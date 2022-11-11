package com.eden.gallery.viewmodel;

import com.eden.common.viewmodel.BaseVM;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * View model of publisher data.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PublisherVM extends BaseVM {

    private String name;
    private List<AlbumVM> albums;
}
