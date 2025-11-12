package LAB7;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public record Carte(
        @JsonProperty("titlul") String titlu,
        @JsonProperty("autorul") String autor,
        @JsonProperty("anul") int anAparitie
) {
    @Override
    public String toString() {
        return "Carte{" +
                "titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                ", anAparitie=" + anAparitie +
                '}';
    }
}