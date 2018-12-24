package org.tomi.dictionary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Dictionary {

  @Id
  @GeneratedValue
  private Long id;

  private String word;

  public Dictionary() {}

  public Dictionary(String word) {
    this.word = word;
  }
}
