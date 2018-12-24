package org.tomi.user.web;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tomi.user.model.Result;
import org.tomi.user.usecase.SaveUserNameService;

@RestController
public class UserController {

  private final SaveUserNameService saveUserNameService;

  public UserController(final SaveUserNameService saveUserNameService) {
    this.saveUserNameService = saveUserNameService;
  }

  @RequestMapping(path = "/verify", method = RequestMethod.GET)
  public HttpEntity<?> verify(@RequestParam(value = "userName") String userName) {
    boolean res = saveUserNameService.saveUserName(userName);

    return res ? ok().build() : status(HttpStatus.BAD_REQUEST).build();
  }
}
