package moonage.date.marlin.services.shorten;

import java.util.zip.Adler32;
import java.util.zip.Checksum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moonage.date.marlin.repository.shorten.ShortenRepository;

@Service
public class ShortenService {
  @Autowired
  private ShortenRepository repository;

  public String shorten(String original) {
    String shortenUrl = getShortenFromUrl(original);
    return shortenUrl;
  }

  public String getShortenFromUrl(String url) {
    if (url.length() == 0) {
      return "";
    }
    byte bytes[] = url.getBytes();
    Checksum checksum = new Adler32();
    checksum.update(bytes,0,bytes.length);
	
    return Long.toHexString(checksum.getValue());
  }
}