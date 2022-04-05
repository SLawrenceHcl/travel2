package travel.backend.recommend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class recommendPostDto{
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
    @JsonProperty("testCombo")
    private String testCombo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        recommendPostDto that = (recommendPostDto) o;
        return Objects.equals(getRecommendationId(), that.getRecommendationId()) && Objects.equals(getDestinationId(), that.getDestinationId()) && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getRate(), that.getRate()) && Objects.equals(getContent(), that.getContent()) && Objects.equals(getTestCombo(), that.getTestCombo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRecommendationId(), getDestinationId(), getAuthor(), getRate(), getContent(), getTestCombo());
    }
}
