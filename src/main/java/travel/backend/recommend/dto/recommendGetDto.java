package travel.backend.recommend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import travel.backend.recommend.model.recommend;

@Getter
@Setter
@AllArgsConstructor
public class recommendGetDto{
    @JsonProperty("recommendationId")
    private String recommendationId;
    @JsonProperty("destinationId")
    private String destinationId;
    @JsonProperty("author")
    private String author;
    @JsonProperty("rate")
    private String rate;
    @JsonProperty("content")
    private String content;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        final recommendGetDto other = (recommendGetDto) obj;
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
        return true;
    }
}
