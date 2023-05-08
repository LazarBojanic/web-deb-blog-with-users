package rs.raf.domaci6lazarbojanic11621rn.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BlogPostComment {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("blog_post_id")
    private Integer blog_post_id;
    @JsonProperty("author")
    private String author;
    @JsonProperty("content")
    private String content;
}
