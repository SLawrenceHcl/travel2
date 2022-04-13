package travel.backend;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import travel.backend.recommend.controller.recommendController;
import travel.backend.recommend.dto.recommendGetDto;
import travel.backend.recommend.dto.recommendPostDto;
import travel.backend.recommend.mapper.recommendMapper;
import travel.backend.recommend.model.recommend;
import travel.backend.recommend.service.recommendService;

import static org.mockito.ArgumentMatchers.any;

@WebFluxTest(controllers = recommendController.class)//, excludeAutoConfiguration = ReactiveSecurityAutoConfiguration.class)
class recommendControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    recommendService recommendService;

    @MockBean
    recommendMapper recommendMapper;


    @Test
    public void post(){
        recommend temp = new recommend("000","000", "Creator000", "-000", "Void-000", "Creator000-000");
        recommendPostDto temp2 = new recommendPostDto("000","000", "Creator000", "-000", "Void-000", "Creator000-000");

        //recommendService.createRecommend(recommendMapper.dtoToModel(recommendPostDto)).map(recommendMapper::modelToDto2);

//        Mockito.when(recommendMapper.modelToDto2(any(recommend.class))).thenReturn(temp2);
//        Mockito.when(recommendMapper.dtoToModel(any(recommendPostDto.class))).thenReturn(temp);
//        Mockito.when(recommendService.createRecommend(recommendMapper.dtoToModel(temp2))).thenReturn(Mono.just(temp));

        Mockito.when(recommendService.createRecommend(any(recommend.class))).thenReturn(Mono.just(temp));
        Mockito.when(recommendMapper.modelToDto2(any(recommend.class))).thenReturn(temp2);
        Mockito.when(recommendMapper.dtoToModel(any(recommendPostDto.class))).thenReturn(temp);


        webTestClient
                .post().uri("/recommend/add")
                .bodyValue(temp2)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(recommendPostDto.class)
                .isEqualTo(temp2);
    }

    @Test
    public void getById(){
        recommend temp = new recommend("01","01", "Creator01", "-1", "Void-1", "Creator01-1");
        recommendGetDto temp2 = new recommendGetDto("01","01", "Creator01", "-1", "Void-1");
        Mockito.when(recommendService.findById(any(String.class))).thenReturn(Mono.just(temp)); //
        Mockito.when(recommendMapper.modelToDto(any(recommend.class))).thenReturn(temp2);
        webTestClient
                .get().uri("/recommend/findById/01")//127.0.0.1:8081
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(recommendGetDto.class)
                .isEqualTo(temp2);
    }
    @Test
    public void getByAuthor(){
        recommend temp = new recommend("01","01", "Creator01", "-1", "Void-1", "Creator01-1");
        recommendGetDto temp2 = new recommendGetDto("01","01", "Creator01", "-1", "Void-1");
        Mockito.when(recommendService.findByAuthor(any(String.class))).thenReturn(Flux.just(temp));
        Mockito.when(recommendMapper.modelToDto(any(recommend.class))).thenReturn(temp2);
        webTestClient
                .get().uri("/recommend/findByAuthor/Creator01")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .equals(Flux.just(temp2));
    }

    @Test
    public void getByDestination(){
        recommend temp = new recommend("01","01", "Creator01", "-1", "Void-1", "Creator01-1");
        recommendGetDto temp2 = new recommendGetDto("01","01", "Creator01", "-1", "Void-1");
        Mockito.when(recommendService.findByDestinationId(any(String.class))).thenReturn(Flux.just(temp));
        Mockito.when(recommendMapper.modelToDto(any(recommend.class))).thenReturn(temp2);
        webTestClient
                .get().uri("/recommend/findByDestinationId/01")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .equals(Flux.just(temp2));
    }

    @Test
    public void updateRecommendation(){
        recommendPostDto temp0 = new recommendPostDto("01","01", "Creator01", "-1", "Void-1", "Creator01-1");
        recommend temp = new recommend("01","01", "Creator01", "-1", "Void-1", "Creator01-1");
        recommendGetDto temp2 = new recommendGetDto("01","01", "Creator01", "-1", "Void-1");


        Mockito.when(recommendMapper.dtoToModel(any(recommendPostDto.class))).thenReturn(temp);
        Mockito.when(recommendMapper.modelToDto(any(recommend.class))).thenReturn(temp2);

        Mockito.when(recommendService.updateRecommendation(any(String.class), any(recommend.class))).thenReturn(Mono.just(temp));

        webTestClient
                .put().uri("/recommend/updateRecommendation/01")
                .bodyValue(temp0)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(recommendGetDto.class)
                .isEqualTo(temp2);
    }

    @Test
    public void deleteRecommendation(){

        Mockito.when(recommendService.deleteById(any(String.class))).thenReturn(Mono.empty());
        webTestClient
                .delete().uri("/recommend/deleteById/01")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .isEmpty();
    }
}
