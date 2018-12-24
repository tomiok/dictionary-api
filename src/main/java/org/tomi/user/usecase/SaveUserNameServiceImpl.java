package org.tomi.user.usecase;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Component;
import org.tomi.dictionary.Dictionary;
import org.tomi.dictionary.DictionaryRepository;
import org.tomi.user.model.User;
import org.tomi.user.model.UserRepository;

@Component
public class SaveUserNameServiceImpl implements SaveUserNameService {

  private final UserRepository userRepository;

  private final DictionaryRepository dictionaryRepository;

  public SaveUserNameServiceImpl(final UserRepository userRepository,
                                 final DictionaryRepository dictionaryRepository) {
    this.userRepository = userRepository;
    this.dictionaryRepository = dictionaryRepository;
  }

  @Override
  public boolean saveUserName(final String userName) {
    // validate length
    if (userName.length() < 6) {
      return false;
    }

    // validate words from dict if is a forbidden word.
    if (this.verityOnDictionary(userName)) {
      return false;
    }

    // check if exist userName
    Stream<String> names = userRepository
        .findByUserName(userName)
        .stream()
        .map(User::getUserName);

    return names.noneMatch(u -> u.equalsIgnoreCase(userName));
  }

  private boolean verityOnDictionary(String userName) {
    Iterable<Dictionary> restricted = dictionaryRepository.findAll();
    return StreamSupport.stream(restricted.spliterator(), false).anyMatch(d -> userName.contains(d.getWord()));
  }
}
