package sphynx.springfeedlens.service;


import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sphynx.springfeedlens.config.RssProperties;
import sphynx.springfeedlens.domain.FeedItem;
import sphynx.springfeedlens.mapper.FeedMapper;
import sphynx.springfeedlens.repository.FeedRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RssService {

    private final RssProperties rssProperties;
    private final FeedRepository feedRepository;
    private final FeedMapper feedMapper;

    @Scheduled(fixedRate = 600000)
    public void fetchAndSaveFeeds() {
        List<URL> urls = rssProperties.getUrls();
        SyndFeedInput input = new SyndFeedInput();

        for (URL url : urls) {
            try {

                SyndFeed feed = input.build(new XmlReader(url));
                List<SyndEntry> entries = feed.getEntries();

                for (SyndEntry entry : entries) {
                    processAndSaveItem(entry, feed);
                }
            } catch (Exception e) {
                System.err.println("Hata: " + url + " adresi okunamadı. " + e.getMessage());
            }
        }
    }

    private void processAndSaveItem(SyndEntry entry, SyndFeed feed) {
        try {

            FeedItem feedItem = feedMapper.rssToFeedItem(entry, feed);

            if (!feedRepository.existsByLink(feedItem.getLink())) {
                feedRepository.save(feedItem);
            }
        } catch (DataIntegrityViolationException e) {
        } catch (Exception e) {
            System.err.println("Kayıt hatası: " + e.getMessage());
        }
    }
}
