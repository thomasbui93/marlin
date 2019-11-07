package moonage.date.marlin.web.api;

import moonage.date.marlin.core.entities.UrlRecord;
import moonage.date.marlin.core.entities.UrlRecordDTO;
import moonage.date.marlin.core.exceptions.InvalidUrlException;
import moonage.date.marlin.core.exceptions.UrlRecordNotFoundException;
import moonage.date.marlin.services.shorten.ShortenService;
import moonage.date.marlin.services.shorten.UrlRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/shorten")
public class ShortenController {
  @Autowired public ShortenService shortenService;

  @GetMapping(value = "/encode")
  public ResponseEntity<UrlRecordDTO> encodeAction(@RequestParam("url") String url) {
    try {
      UrlRecord urlRecord = this.shortenService.shorten(url);
      UrlRecordDTO urlRecordDTO = UrlRecordMapper.INSTANCE.toUrlRecordDTO(urlRecord);
      return ResponseEntity.status(HttpStatus.CREATED).body(urlRecordDTO);
    } catch (NullPointerException exception) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Encoding data failed.");
    } catch (InvalidUrlException exception) {
      throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid url.");
    }
  }

  @GetMapping(value = "/decode")
  public ResponseEntity<UrlRecordDTO> decodeAction(@RequestParam("url") String url) {
    try {
      UrlRecord urlRecord = this.shortenService.getRedirectUrl(url);
      UrlRecordDTO urlRecordDTO = UrlRecordMapper.INSTANCE.toUrlRecordDTO(urlRecord);
      return ResponseEntity.status(HttpStatus.CREATED).body(urlRecordDTO);
    } catch (UrlRecordNotFoundException exception) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid shorten url.");
    }
  }
}
