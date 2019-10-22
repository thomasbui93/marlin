package moonage.date.marlin.services.shorten;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moonage.date.marlin.core.entities.UrlRecord;
import moonage.date.marlin.core.exceptions.InvalidUrlException;
import moonage.date.marlin.core.exceptions.UrlRecordNotFoundException;
import moonage.date.marlin.repository.shorten.ShortenRepository;

@Service
public class ShortenService {
  @Autowired
  private ShortenRepository shortenRepository;

  public String shorten(String original) {
    UrlRecord record = this.shortenRepository.findFirstByOriginal(original);
    if (record != null) {
      return record.getShorten();
    }
    String shortenUrl = getShortenFromUrl(original);
    UrlRecord urlRecord = new UrlRecord();
    urlRecord.setOriginal(original);
    urlRecord.setShorten(shortenUrl);

    this.shortenRepository.save(urlRecord);
    return shortenUrl;
  }

  public String getShortenFromUrl(String url) {
    final UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
    if (urlValidator.isValid(url)) {
      return Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
    }
    throw new InvalidUrlException("Invalid url.");
  }

  public String getRedirectUrl(String shorten) {
    UrlRecord record = this.shortenRepository.findFirstByShorten(shorten);
    if (record != null) {
      return record.getOriginal();
    } else {
      throw new UrlRecordNotFoundException("No url found in our record.");
    }
  }
}