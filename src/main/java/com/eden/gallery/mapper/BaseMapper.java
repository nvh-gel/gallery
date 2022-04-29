package com.eden.gallery.mapper;

import com.eden.gallery.model.BaseModel;
import com.eden.gallery.viewmodel.BaseVM;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Abstract mapper.
 *
 * @param <M> model type
 * @param <V> view model type
 */
public interface BaseMapper<M extends BaseModel, V extends BaseVM> {

    /**
     * Map single model to view model.
     *
     * @param model model to map
     * @return view model
     */
    V toViewModel(M model);

    /**
     * Map single view model to model
     *
     * @param viewModel view model to map
     * @return model
     */
    @Mapping(target = "deleted", ignore = true)
    M toModel(V viewModel);

    /**
     * Map list of models to view models.
     *
     * @param models models to map
     * @return list of view models
     */
    List<V> toViewModel(List<M> models);

    /**
     * Map list of view models.
     *
     * @param viewModels view models to map
     * @return list of models
     */
    List<M> toModel(List<V> viewModels);

    /**
     * Map updated field to an article model.
     *
     * @param model  article to update
     * @param update update data
     */
    void mapUpdate(@MappingTarget M model, M update);
}
