package org.tomi.dictionary.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
final class DicHttpResponse {

  private String word;

  private Status status;
}

enum Status {
  SAVED, IN_REVIEW
}
