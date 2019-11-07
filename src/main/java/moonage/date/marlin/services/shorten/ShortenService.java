package moonage.date.marlin.services.shorten;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import moonage.date.marlin.core.entities.UrlRecord;
import moonage.date.marlin.core.exceptions.InvalidUrlException;
import moonage.date.marlin.core.exceptions.UrlRecordNotFoundException;
import moonage.date.marlin.repository.shorten.ShortenRepository;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShortenService {
  @Autowired private ShortenRepository shortenRepository;

  public UrlRecord shorten(String original) {
    UrlRecord record = this.shortenRepository.findFirstByOriginal(original);
    if (record != null) {
      log.info("url record found in database, return from system.");
      return record;
    }

    String shortenUrl = getShortenFromUrl(original);
    UrlRecord urlRecord = new UrlRecord();
    urlRecord.setOriginal(original);
    urlRecord.setShorten(shortenUrl);
    this.shortenRepository.save(urlRecord);

    return urlRecord;
  }

  @Cacheable("url_records")
  public String getShortenFromUrl(String url) {
    final UrlValidator urlValidator = new UrlValidator(new String[] {"http", "https"});
    if (urlValidator.isValid(url)) {
      return Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
    }
    throw new InvalidUrlException("Invalid url.");
  }

  @Cacheable("url_redirects")
  public UrlRecord getRedirectUrl(String shorten) {
    UrlRecord record = this.shortenRepository.findFirstByShorten(shorten);
    if (record != null) {
      return record;
    } else {
      throw new UrlRecordNotFoundException("No url found in our record.");
    }
  }
}
