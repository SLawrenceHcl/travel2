package travel.backend.recommend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import travel.backend.recommend.dto.recommendGetDto;
import travel.backend.recommend.dto.recommendPostDto;
import travel.backend.recommend.mapper.recommendMapper;
import travel.backend.recommend.model.recommend;
import travel.backend.recommend.repository.recommendRepo;
import travel.backend.recommend.service.recommendService;



@RestController
@RequestMapping("/recommend")
public class recommendController {

   @Autowired
   recommendService recommendService;

   @Autowired
   recommendMapper recommendMapper;

   @GetMapping(value="/test", produces = MediaType.TEXT_PLAIN_VALUE)
   public String index() {

      return "Test text for testing";
   }
   @PostMapping("/add")
//   public Mono<recommend> createRecommend(String destinationId, String author, String rate, String content){
   public Mono<recommendPostDto> createRecommend(@RequestBody recommendPostDto recommendPostDto){

      return recommendService.createRecommend(recommendMapper.dtoToModel(recommendPostDto)).map(recommendMapper::modelToDto2);
   }
   @GetMapping("/findAll")
   public Flux<recommend> getAllRecommends(){
      return recommendService.getAllRecommends();
   }

   @GetMapping("/findById/{id}")
   public Mono<recommendGetDto> findById(@PathVariable("id") String recommendationId){  //recommendGetDto
      return recommendService.findById(recommendationId).map(recommendMapper::modelToDto);
   }

   @GetMapping("/findByAuthor/{author}")
   public Flux<recommendGetDto> findByAuthor(@PathVariable("author") String author){
      return recommendService.findByAuthor(author).map(recommendMapper::modelToDto);
   }

   @GetMapping("/findByDestinationId/{id}")
   public Flux<recommendGetDto> findByDestinationId(@PathVariable("id") String destId){
      return recommendService.findByDestinationId(destId).map(recommendMapper::modelToDto);
   }

   @PutMapping("/updateRecommendation/{id}")
   public Mono<recommendGetDto> updateRecommendation(@PathVariable("id") String recommendationId, @RequestBody recommendPostDto recommendPostDto){
      return recommendService.updateRecommendation(recommendationId, recommendMapper.dtoToModel(recommendPostDto)).map(recommendMapper::modelToDto);
   }

   @DeleteMapping("/deleteById/{id}")
   public Mono<Void> deleteRecommendation(@PathVariable("id") String recommendationId){
      return recommendService.deleteById(recommendationId);
   }

}
