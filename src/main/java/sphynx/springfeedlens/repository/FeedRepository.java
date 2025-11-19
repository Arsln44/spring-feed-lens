package sphynx.springfeedlens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphynx.springfeedlens.domain.FeedItem;

import java.util.Optional;

public interface FeedRepository extends JpaRepository<FeedItem,Long> {

}
