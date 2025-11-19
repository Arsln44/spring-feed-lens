package sphynx.springfeedlens.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "rss")
@Getter
public class RssProperties {
    private List<String> urls;
}
