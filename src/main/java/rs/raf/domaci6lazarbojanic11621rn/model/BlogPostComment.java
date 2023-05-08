package rs.raf.domaci5lazarbojanic11621rn.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BlogPostComment {
    private Integer id;
    private Integer blog_post_id;
    private String author;
    private String content;
}
