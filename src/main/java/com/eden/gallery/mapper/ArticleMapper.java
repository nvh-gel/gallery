package com.eden.gallery.mapper;

import com.eden.gallery.model.Article;
import com.eden.gallery.viewmodel.ArticleVM;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ArticleMapper extends BaseMapper<Article, ArticleVM> {
}
