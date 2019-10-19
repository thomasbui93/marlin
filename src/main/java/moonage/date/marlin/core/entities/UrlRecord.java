package moonage.date.marlin.core.entities;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;


@Data
@Entity
public class UrlRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private final UUID id;

  @Column(name="original", nullable = false, unique=true)
  private String original;

  @Column(name="shorten", nullable = false, unique=true)
  private String shorten;

  @Column(name="created_at", nullable = false, updatable = false)
  @CreationTimestamp
  private Date createdAt;

  @Column(name="updated_at", nullable = false, updatable = false)
  @UpdateTimestamp
  private Date updatedAt;
}