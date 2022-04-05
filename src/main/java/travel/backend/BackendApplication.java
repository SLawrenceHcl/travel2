package travel.backend;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import travel.backend.recommend.repository.recommendRepo;

@EnableDiscoveryClient
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = recommendRepo.class)
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}


}
