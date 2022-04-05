package travel.backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import travel.backend.recommend.model.recommend;
import travel.backend.recommend.repository.recommendRepo;

@SpringBootTest
//@ExtendWith(SpringExtension.class)
public class recommendRepoTests {
    @Autowired
    private recommendRepo recommendRepo;

    @Test
    void createRecommend() {
        recommend temp = new recommend("00","00", "Creator00", "-0", "Void-0", "Creator00-0");
        Mono<recommend> test1 = recommendRepo.save(temp);
        //recommend temp2 = new recommend("01","01", "Creator01", "-1", "Void-1", "Creator01-1");
        StepVerifier
                .create(test1)
                //.expectNextCount(1)
                .expectNext(temp)
                .verifyComplete();
    }
    @Test
    void findById() {
        recommend temp = new recommend("01","01", "Creator01", "-1", "Void-1", "Creator01-1");
        Mono<recommend> test0 = recommendRepo.save(temp);
        Mono<recommend> test1 = recommendRepo.findById("01");
        //Mono<recommend> test2 = recommendRepo.save(temp);
        StepVerifier
                .create(test0)
                .expectNextCount(1)
                .verifyComplete();
        StepVerifier
                .create(test1)
                .expectNext(temp)
//                .assertNext(rec ->{
//                    assertEquals(temp.getRecommendationId(), rec.getRecommendationId());
//                    assertEquals(temp.getDestinationId(), rec.getDestinationId());
//                    assertEquals(temp.getAuthor(), rec.getAuthor());
//                    assertEquals(temp.getRate(), rec.getRate());
//                    assertEquals(temp.getContent(), rec.getContent());
//                    assertEquals(temp.getTestCombo(), rec.getTestCombo());
//                })
                .verifyComplete();
    }
    @Test
    void findByAuthor() {
        recommend temp = new recommend("020", "02", "Creator02a", "-2", "Void-2", "Creator02-2");
        recommend temp2 = new recommend("021", "02", "Creator02a", "-2", "Void-2", "Creator02-2");
        Mono<recommend> test1 = recommendRepo.save(temp);
        Mono<recommend> test2 = recommendRepo.save(temp2);
        Flux<recommend> test3 = Flux.concat(test1, test2);
        StepVerifier
                .create(test3)
                .expectNext(temp)
                .expectNext(temp2)
                .expectComplete()
                .verify();
        Flux<recommend> test4 = recommendRepo.findByAuthor("Creator02a");
        StepVerifier
                .create(test4)
                .expectNext(temp)
                .expectNext(temp2)
                .expectComplete()
                .verify();
    }
    @Test
    void findByDestination(){
        recommend temp = new recommend("03","03", "Creator03", "-3", "Void-3", "Creator03-3");
        Mono<recommend> test1 = recommendRepo.save(temp);
        StepVerifier
                .create(test1)
                .expectNext(temp)
                .expectComplete()
                .verify();
        Flux<recommend> test2 = recommendRepo.findByDestinationId("03");
        StepVerifier
                .create(test1)
                .expectNext(temp)
                .verifyComplete();
    }
    @Test
    void deleteRecommend(){
        recommend temp = new recommend("04","04", "Creator04", "-4", "Void-4", "Creator04-4");
        Mono<recommend> test0 = recommendRepo.save(temp);
        Mono<Void> test1 = recommendRepo.deleteById("04");
        StepVerifier
                .create(test0)
                .expectNext(temp)
                .expectComplete()
                .verify();
        StepVerifier
                .create(test1)
                .verifyComplete();
    }
}
