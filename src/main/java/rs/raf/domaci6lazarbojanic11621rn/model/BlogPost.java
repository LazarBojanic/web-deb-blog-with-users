package rs.raf.domaci6lazarbojanic11621rn.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class BlogPost {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("date_published")
    private Date date_published;
    @JsonProperty("author")
    private String author;
    @JsonProperty("content")
    private String content;
}
