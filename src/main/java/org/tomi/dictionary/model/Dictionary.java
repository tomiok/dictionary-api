package org.tomi.dictionary.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dictionary {

  @Id
  @GeneratedValue
  private long id;

  private String word;

  public Dictionary(String word) {
    Validate.isTrue(word.length() > 2);
    this.word = word;
  }
}
