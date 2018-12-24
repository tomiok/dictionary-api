package org.tomi.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tomi.dictionary.Dictionary;
import org.tomi.dictionary.DictionaryRepository;

@RestController
public class UserController {

  @Autowired
  UserRepository userRepository;
  @Autowired
  DictionaryRepository dictionaryRepository;

  @RequestMapping(path = "/verify", method = RequestMethod.GET)
  public HttpEntity<Result> verify(@RequestParam(value = "userName") String userName) {
    Result result = new Result();
    // validate lenght
    if (userName.length() < 6) {
      return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // validate words from dict
    if (this.verityOnDictionary(userName)) {
      return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // check if exist userName
    result = new Result(userRepository.findByUserName(userName));
    if (result.getUsers() == null || result.getUsers().size() == 0) {
      result.setValid(true);
    } else {
      result.setUsers(this.alternateUserName(userName));
    }

    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  private boolean verityOnDictionary(String userName) {
    Iterable<Dictionary> restricted = dictionaryRepository.findAll();
    return StreamSupport.stream(restricted.spliterator(), false).anyMatch(d -> userName.contains(d.getWord()));
  }

  private List<User> alternateUserName(String userName) {
    List alternates = new ArrayList(14);
    int i = 0;
    do {
      String altUserName = userName + i++;
      if (!this.verityOnDictionary(altUserName)) {
        // check if exist to avoid repeated suggested
        List<User> users = userRepository.findByUserName(altUserName);
        if (users == null || users.size() == 0) {
          alternates.add(altUserName);
        }
      }
    } while (alternates.size() < 14);

    return alternates;
  }
}
