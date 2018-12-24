package org.tomi.user.web;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tomi.dictionary.Dictionary;
import org.tomi.dictionary.DictionaryRepository;
import org.tomi.user.model.Result;
import org.tomi.user.model.User;
import org.tomi.user.model.UserRepository;

@RestController
public class UserController {

  private final UserRepository userRepository;

  private final DictionaryRepository dictionaryRepository;

  public UserController(final UserRepository userRepository, final DictionaryRepository dictionaryRepository) {
    this.userRepository = userRepository;
    this.dictionaryRepository = dictionaryRepository;
  }

  @RequestMapping(path = "/verify", method = RequestMethod.GET)
  public HttpEntity<Result> verify(@RequestParam(value = "userName") String userName) {
    // validate length
    if (userName.length() < 6) {
      return status(HttpStatus.BAD_REQUEST).body(new Result(Boolean.FALSE));
    }

    // validate words from dict if is a forbidden word.
    if (this.verityOnDictionary(userName)) {
      return status(HttpStatus.BAD_REQUEST).body(new Result(Boolean.FALSE));
    }

    // check if exist userName
    Stream<String> names = userRepository
        .findByUserName(userName)
        .stream()
        .map(User::getUserName);

    if (names.anyMatch(u -> u.equalsIgnoreCase(userName))) {
      return status(HttpStatus.BAD_REQUEST).body(new Result(Boolean.FALSE));
    }
    return ok(new Result(Boolean.TRUE));
  }

  private boolean verityOnDictionary(String userName) {
    Iterable<Dictionary> restricted = dictionaryRepository.findAll();
    return StreamSupport.stream(restricted.spliterator(), false).anyMatch(d -> userName.contains(d.getWord()));
  }

}
