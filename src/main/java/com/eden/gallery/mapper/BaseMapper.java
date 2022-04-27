package com.eden.gallery.mapper;

import com.eden.gallery.model.BaseModel;
import com.eden.gallery.viewmodel.BaseVM;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<M extends BaseModel, V extends BaseVM> {

    V toViewModel(M model);

    @Mapping(target = "deleted", ignore = true)
    M toModel(V viewModel);

    List<V> toViewModel(List<M> models);

    List<M> toModel(List<V> viewModels);

    void mapUpdate(@MappingTarget M model, M toUpdate);
}
