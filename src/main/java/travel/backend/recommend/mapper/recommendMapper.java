package travel.backend.recommend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import travel.backend.recommend.dto.recommendGetDto;
import travel.backend.recommend.dto.recommendPostDto;
import travel.backend.recommend.model.recommend;

@Mapper(componentModel = "spring")
public interface recommendMapper {
    recommendGetDto modelToDto(recommend recommend);
    recommendPostDto modelToDto2(recommend recommend);
//    @Mapping(target = "a", source = "author")
//    @Mapping(target = "b", source = "rate")
    @Mapping(target = "testCombo", expression = "java(fuse(recommendPostDto.getAuthor(), recommendPostDto.getRate()))")
    recommend dtoToModel(recommendPostDto recommendPostDto);

    default String fuse(String a, String b){
        return a+b;
    }
}
