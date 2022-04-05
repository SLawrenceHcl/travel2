package travel.backend.recommend.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("recommend")
public class recommend {
    @Id
    private String recommendationId;
    private String destinationId;
    private String author;
    private String rate;
    private String content;
    private String testCombo;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        final recommend other = (recommend) obj;
        if(recommendationId == null){
           if(other.recommendationId != null) return false;
        }
        if(!destinationId.equals(other.destinationId)) return false;

        if(destinationId == null){
            if(other.destinationId != null) return false;
        }
        if(!destinationId.equals(other.destinationId)) return false;

        if(author == null){
            if(other.author != null) return false;
        }
        if(!author.equals(other.author)) return false;

        if(rate == null){
            if(other.rate != null) return false;
        }
        if(!rate.equals(other.rate)) return false;

        if(content == null){
            if(other.content != null) return false;
        }
        if(!content.equals(other.content)) return false;

        if(testCombo == null){
            if(other.testCombo != null) return false;
        }
        if(!testCombo.equals(other.testCombo)) return false;

        return true;
    }


}
