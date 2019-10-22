package moonage.date.marlin.repository.shorten;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import moonage.date.marlin.core.entities.UrlRecord;

public interface ShortenRepository extends CrudRepository<UrlRecord, UUID> {
  UrlRecord findFirstByOriginal(String original);
  UrlRecord findFirstByShorten(String shorten);
}