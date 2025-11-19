package sphynx.springfeedlens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sphynx.springfeedlens.domain.FeedItem;

@Repository
public interface FeedRepository extends JpaRepository<FeedItem,Long> {
}
