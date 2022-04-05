package travel.backend.recommend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import travel.backend.recommend.dto.recommendGetDto;
import travel.backend.recommend.dto.recommendPostDto;
import travel.backend.recommend.mapper.recommendMapper;
import travel.backend.recommend.model.recommend;
import travel.backend.recommend.repository.recommendRepo;

import java.util.List;
import java.util.Optional;

@Service
public class recommendService {
    @Autowired
    private recommendRepo recommendRepo;

    @Autowired
    recommendMapper recommendMapper;

//    public Mono<recommend> createRecommend(recommend recommend){
//        return recommendRepo.insert(recommend);
//
//    }
    public Mono<recommend> createRecommend(recommend recommend){
          return recommendRepo.save(recommend);
    }
    public Flux<recommend> getAllRecommends(){
        return recommendRepo.findAll();
        //return recommendRepo.findAll();
    }

    public Mono<recommend> findById(String id){
        //return recommendMapper.recommendToRecommendGetDto(recommendRepo.findById(id));
        //return recommendRepo.findById(id).map(recommendMapper.recommendToRecommendGetDto());
        return recommendRepo.findById(id);
    }
    public Flux<recommend> findByAuthor(String Author){
        return recommendRepo.findByAuthor(Author);
    }
    public Flux<recommend> findByDestinationId(String id){
        return recommendRepo.findByDestinationId(id);
    }

    public Mono<recommend> updateRecommendation(String recommendationId, recommend recommend){
        return recommendRepo.findById(recommendationId)
                            .map((c) -> {
                                c.setAuthor(recommend.getAuthor());
                                c.setRate(recommend.getRate());
                                c.setContent(recommend.getContent());
                                return c;
                        }).flatMap(c -> recommendRepo.save(c));
    }

    public Mono<Void> deleteById(String recommendationId){
        return recommendRepo.deleteById(recommendationId);
    }

}
