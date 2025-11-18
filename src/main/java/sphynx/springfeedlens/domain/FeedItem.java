package sphynx.springfeedlens.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "feeds")
public class FeedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(unique = true, nullable = false)
    private String link;

    @Column(nullable = false)
    private LocalDate pubDate;

    @Column(nullable = false)
    private String source;
}
