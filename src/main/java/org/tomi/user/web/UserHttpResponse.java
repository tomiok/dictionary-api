package org.tomi.user.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
final class UserHttpResponse {

  private String username;

  private UserNameStatus status;

  private String message;

  UserHttpResponse(final String username, final UserNameStatus status, final String message) {
    this.username = username;
    this.status = status;
    this.message = message;
  }

  UserHttpResponse(final String username, final UserNameStatus status) {
    this.username = username;
    this.status = status;
  }
}

enum UserNameStatus {
  SAVED, PROBLEM_FOUND
}
