package sphynx.springfeedlens.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphynx.springfeedlens.domain.FeedItem;
import sphynx.springfeedlens.repository.FeedRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;

    public List<FeedItem> getAllFeeds(){
        return feedRepository.findAll();
    }
}
