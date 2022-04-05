package travel.backend.recommend.repository;



import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import travel.backend.recommend.dto.recommendGetDto;
import travel.backend.recommend.dto.recommendPostDto;
import travel.backend.recommend.model.recommend;

import java.util.Optional;

@Repository
public interface recommendRepo extends ReactiveMongoRepository<recommend, String> {
    @Query("{'author': ?0}")
    Flux<recommend> findByAuthor(String author);
    @Query("{'destinationId': ?0}")
    Flux<recommend> findByDestinationId(String destinationId);


//    @Query("{'recommendationId': ?0}")
//    Mono<recommendGetDto> findById2(String id);
}
