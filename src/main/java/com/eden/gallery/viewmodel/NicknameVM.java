package com.eden.gallery.viewmodel;

import com.eden.common.viewmodel.BaseVM;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO of model nickname
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NicknameVM extends BaseVM {

    private String nick;
    private Long modelId;
}
