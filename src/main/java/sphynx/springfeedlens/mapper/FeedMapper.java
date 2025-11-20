package sphynx.springfeedlens.mapper;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sphynx.springfeedlens.domain.FeedItem;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedMapper {

    @Mapping(target = "title", expression = "java(resolveTitle(entry))")
    @Mapping(target = "link", expression = "java(resolveLink(entry))")
    @Mapping(target = "description", expression = "java(resolveDescription(entry))")
    @Mapping(target = "pubDate", expression = "java(resolvePubDate(entry))")
    @Mapping(target = "source", expression = "java(resolveSource(feed))")
    @Mapping(target = "id", ignore = true)
    FeedItem rssToFeedItem(SyndEntry entry, SyndFeed feed);


    default String resolveTitle(SyndEntry entry) {
        if (entry == null) return "Başlıksız İçerik";
        if (entry.getTitle() != null && !entry.getTitle().isEmpty()) {
            return entry.getTitle();
        }
        return "Başlıksız İçerik";
    }

    default String resolveLink(SyndEntry entry) {
        if (entry == null) return "Link Bulunamadı";
        if (entry.getLink() != null && !entry.getLink().isEmpty()) {
            return entry.getLink();
        }
        return "Link Bulunamadı";
    }

    default String resolveDescription(SyndEntry entry) {
        if (entry == null) return "Açıklama Bulunamadı";
        if (entry.getDescription() != null && entry.getDescription().getValue() != null
                && !entry.getDescription().getValue().isBlank()) {
            return entry.getDescription().getValue();
        }
        List<SyndContent> contents = entry.getContents();
        if (contents != null && !contents.isEmpty()) {
            SyndContent c = contents.get(0);
            if (c != null && c.getValue() != null && !c.getValue().isBlank()) {
                return c.getValue();
            }
        }
        return "Açıklama Bulunamadı";
    }

    default LocalDate resolvePubDate(SyndEntry entry) {
        if (entry == null) {
            return LocalDate.now();
        }

        if (entry.getPublishedDate() != null) {
            return entry.getPublishedDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }

        if (entry.getUpdatedDate() != null) {
            return entry.getUpdatedDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }

        return LocalDate.now();
    }


    default String resolveSource(SyndFeed feed) {
        if (feed != null && feed.getTitle() != null && !feed.getTitle().isEmpty()) {
            return feed.getTitle();
        }
        return "Bilinmeyen Kaynak";
    }
}
