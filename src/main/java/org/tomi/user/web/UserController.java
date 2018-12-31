package org.tomi.user.web;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tomi.user.usecase.SaveUserNameService;

@RestController
public class UserController {

  private final SaveUserNameService saveUserNameService;

  public UserController(final SaveUserNameService saveUserNameService) {
    this.saveUserNameService = saveUserNameService;
  }

  @RequestMapping(path = "/verify", method = RequestMethod.GET)
  public HttpEntity<UserHttpResponse> verify(@RequestParam(value = "userName") String userName) {
    String userNameSaved = saveUserNameService.saveUserName(userName);

    return ok(new UserHttpResponse(userNameSaved, UserNameStatus.SAVED));
  }
}
