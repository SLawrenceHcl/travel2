package travel.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import travel.backend.recommend.model.recommend;
import travel.backend.recommend.service.recommendService;


@SpringBootTest
public class recommendServiceTest {

    @Autowired
    recommendService recommendService;

    @Test
    void createRecommend() {
        recommend temp = new recommend("00","00", "Creator00", "-0", "Void-0", "Creator00-0");
        Mono<recommend> test1 = recommendService.createRecommend(temp);
        StepVerifier
                .create(test1)
                .expectNext(temp)
                .expectComplete();
    }
    @Test
    void findById() {
        recommend temp = new recommend("01","01", "Creator01", "-1", "Void-1", "Creator01-1");
        Mono<recommend> test1 = recommendService.createRecommend(temp);
        StepVerifier
                .create(test1)
                .expectNextCount(1)
                .verifyComplete();
        Mono<recommend> test2 = recommendService.findById("01");
        StepVerifier
                .create(test2)
                .expectNext(temp)
                .verifyComplete();
    }
    @Test
    void findByAuthor() {
        recommend temp = new recommend("020", "02", "Creator02b", "-2", "Void-2", "Creator02-2");
        recommend temp2 = new recommend("021", "02", "Creator02b", "-2", "Void-2", "Creator02-2");
        Mono<Void> del = recommendService.deleteById("020");
        Mono<Void> del2 = recommendService.deleteById("021");
        Flux<Void> delMaster = Flux.concat(del,del2);
        StepVerifier
                .create(delMaster)
                .verifyComplete();
        Mono<recommend> test1 = recommendService.createRecommend(temp);
        Mono<recommend> test2 = recommendService.createRecommend(temp2);
        Flux<recommend> test3 = Flux.concat(test1, test2);
        StepVerifier
                .create(test3)
//                .expectNextCount(2)
                .expectNext(temp)
                .expectNext(temp2)
                .expectComplete()
                .verify();
        Flux<recommend> test4 = recommendService.findByAuthor("Creator02b");
        StepVerifier
                .create(test4)
                .expectNext(temp)
                .expectNext(temp2)
                .expectComplete()
                .verify();
    }
    @Test
    void findByDestinationId() {
        recommend temp = new recommend("03","03", "Creator03", "-3", "Void-3", "Creator03-3");
        Mono<recommend> test1 = recommendService.createRecommend(temp);
        StepVerifier
                .create(test1)
                .expectNextCount(1)
                .verifyComplete();
        Flux<recommend> test2 = recommendService.findByDestinationId("03");
        StepVerifier
                .create(test2)
                .expectNext(temp)
                .verifyComplete();
    }
    @Test
    void updateRecommendation() {
        recommend temp = new recommend("04","04", "Creator04", "-4", "Void-4", "Creator04-4");
        Mono<recommend> test1 = recommendService.createRecommend(temp);
        StepVerifier
                .create(test1)
                .expectNext(temp)
                .verifyComplete();
        recommend updatedTemp = new recommend("04","04", "Creator04", "-4", "Updated", "Creator04-4");
        Mono<recommend> test2 = recommendService.updateRecommendation("04", updatedTemp);
        StepVerifier
                .create(test2)
                .expectNext(updatedTemp)
                .verifyComplete();
    }
    @Test
    void deleteRecommendation() {
        recommend temp = new recommend("05", "05", "Creator05", "-5", "Void-5", "Creator05-5");
        Mono<recommend> test1 = recommendService.createRecommend(temp);
        StepVerifier
                .create(test1)
                .expectNext(temp)
                .verifyComplete();

        Mono<Void> test2 = recommendService.deleteById("05");
        StepVerifier
                .create(test2)
                .verifyComplete();
        Mono<recommend> test3 = recommendService.findById("05");
        StepVerifier
                .create(test3)
                .verifyComplete();
    }
}
