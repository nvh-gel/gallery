package com.eden.gallery.mapper;

import com.eden.data.mapper.BaseMapper;
import com.eden.data.model.BaseModel;
import com.eden.gallery.model.Album;
import com.eden.gallery.model.Article;
import com.eden.gallery.model.Model;
import com.eden.gallery.model.Nickname;
import com.eden.gallery.viewmodel.AlbumVM;
import com.eden.gallery.viewmodel.ArticleVM;
import com.eden.gallery.viewmodel.ModelVM;
import com.eden.gallery.viewmodel.NicknameVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapstruct mapper for article.
 */
@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArticleMapper extends BaseMapper<Article, ArticleVM> {

    @Mapping(target = "albums", ignore = true)
    ModelVM mapToModelVM(Model model);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "albums", ignore = true)
    Model mapToModel(ModelVM modelVM);

    @Mapping(target = "modelId", source = "model", qualifiedByName = "mapModelToModelId")
    NicknameVM toNickNameVM(Nickname nickname);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "model", ignore = true)
    Nickname toNickName(NicknameVM nicknameVM);

    @Mapping(target = "publisherId", source = "publisher", qualifiedByName = "mapModelToModelId")
    AlbumVM toAlbumVM(Album album);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    Album toAlbum(AlbumVM albumVM);

    @Named("mapModelToModelId")
    default Long mapModelToModelId(BaseModel model) {

        return model == null ? null : model.getId();
    }
}
