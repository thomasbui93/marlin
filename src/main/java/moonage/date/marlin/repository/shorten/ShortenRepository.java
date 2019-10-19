package moonage.date.marlin.repository.shorten;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import moonage.date.marlin.core.entities.UrlRecord;

@Repository
public interface ShortenRepository extends CrudRepository<UrlRecord, UUID> {}