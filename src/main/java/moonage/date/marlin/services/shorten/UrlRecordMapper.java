package moonage.date.marlin.services.shorten;

import moonage.date.marlin.core.entities.UrlRecord;
import moonage.date.marlin.core.entities.UrlRecordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UrlRecordMapper {
  UrlRecordMapper INSTANCE = Mappers.getMapper(UrlRecordMapper.class);

  UrlRecordDTO toUrlRecordDTO(UrlRecord urlRecord);
}
