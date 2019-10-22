package moonage.date.marlin.web.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import moonage.date.marlin.services.shorten.ShortenService;

@RestController
public class ShortenController {
  @Autowired
  public ShortenService shortenService;

  @GetMapping(value = "/api/shorten")
  public Map<String, Object> createAction(@RequestParam("url") String url) {
    Map<String, Object> response = new HashMap<String, Object>();
    try {
      String shortenUrl = this.shortenService.shorten(url);
      response.put("url", shortenUrl);
    } catch (Error exception) {
      response.put("error", true);
    }
    return response;
  }

  @GetMapping(value = "/api/decode")
  public Map<String, Object> decodeAction(@RequestParam("url") String url) {
    Map<String, Object> response = new HashMap<String, Object>();
    try {
      String original = this.shortenService.getRedirectUrl(url);
      response.put("url", original);
    } catch (Error exception) {
      response.put("error", true);
    }
    return response;
  }
}