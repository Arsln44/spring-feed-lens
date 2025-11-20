package sphynx.springfeedlens.mapper;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sphynx.springfeedlens.domain.FeedItem;

@Mapper(componentModel = "spring")
public interface FeedMapper {

    @Mapping(source = "entry.title", target = "title")
    @Mapping(source = "entry.link", target = "link")
    @Mapping(source = "entry.description.value", target = "description")
    @Mapping(source = "entry.publishedDate", target = "pubDate")
    @Mapping(source = "feed.title", target = "source")
    @Mapping(target = "id", ignore = true)
    FeedItem rssToFeedItem(SyndEntry entry, SyndFeed feed);
}
