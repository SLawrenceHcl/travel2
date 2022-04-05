package travel.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;
import travel.backend.recommend.dto.recommendGetDto;
import travel.backend.recommend.model.recommend;
import travel.backend.recommend.repository.recommendRepo;
import travel.backend.recommend.service.recommendService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
class BackendApplicationTests {

//	@Mock
	@Autowired
	recommendService recommendService;

	@Autowired
	recommendRepo recommendRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void repoTest(){
		recommend temp = new recommend("43","55", "steven", "7", "it was pretty cool", "steven7");
		recommendRepo.save(temp);
		Mono<recommend> rec = recommendRepo.findById("43");
		StepVerifier
				.create(rec)
//				.assertNext(rect ->{
//					assertEquals(temp.getRecommendationId(), rect.getRecommendationId());
//					assertEquals(temp.getDestinationId(), rect.getDestinationId());
//					assertEquals(temp.getAuthor(), rect.getAuthor());
//					assertEquals(temp.getRate(), rect.getRate());
//					assertEquals(temp.getContent(), rect.getContent());
//					assertEquals(temp.getTestCombo(), rect.getTestCombo());
//				})
				.expectNext(temp)
				.expectComplete();


	}

	@Test
	void getRecommend() {
		recommend temp = new recommend("43","55", "steven", "7", "it was pretty cool", "steven7");
		Mono<recommend> sampleData = Mono.just(temp);

		Mono<recommend> test1 = recommendService.findById("43");

		StepVerifier
				.create(test1)
				.expectNext(temp)
				.expectComplete();

//		StepVerifier.create(test1).consumeNextWith(r -> {
//			assertEquals(temp, r);
//		}).verifyComplete();


	}


}
//java.lang.AssertionError: expectation
//		"expectNext(recommend(recommendationId=43, destinationId=55, author=steven, rate=7, content=it was pretty cool, testCombo=steven7))"
//		failed (
//				expected value: recommend(recommendationId=43, destinationId=55, author=steven, rate=7, content=it was pretty cool, testCombo=steven7);
//				actual value: recommend(recommendationId=43, destinationId=55, author=steven, rate=7, content=it was pretty cool, testCombo=steven7))
