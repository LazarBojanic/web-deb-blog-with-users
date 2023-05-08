package rs.raf.domaci6lazarbojanic11621rn.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Token {
    @JsonProperty("token")
    private String token;
}
