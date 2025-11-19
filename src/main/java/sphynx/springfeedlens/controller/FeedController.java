package sphynx.springfeedlens.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sphynx.springfeedlens.domain.FeedItem;
import sphynx.springfeedlens.service.FeedService;

import java.util.List;

@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @GetMapping
    public List<FeedItem> findAll() {
        return feedService.getAllFeeds();
    }
}
