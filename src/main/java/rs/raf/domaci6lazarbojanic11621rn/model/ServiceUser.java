package rs.raf.domaci6lazarbojanic11621rn.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ServiceUser {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("pass")
    private String pass;
}
