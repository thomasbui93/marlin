package moonage.date.marlin.repository.shorten;

import java.util.UUID;
import moonage.date.marlin.core.entities.UrlRecord;
import org.springframework.data.repository.CrudRepository;

public interface ShortenRepository extends CrudRepository<UrlRecord, UUID> {
  UrlRecord findFirstByOriginal(String original);

  UrlRecord findFirstByShorten(String shorten);
}
