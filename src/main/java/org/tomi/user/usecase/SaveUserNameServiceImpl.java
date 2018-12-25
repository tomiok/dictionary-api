package org.tomi.user.usecase;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.text.similarity.LevenshteinDistance;
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

    // find all the user names
    Stream<String> names = userRepository
        .findByUserName(userName)
        .stream()
        .map(User::getUserName);

    // check if exist userName
    if (names.noneMatch(u -> u.equalsIgnoreCase(userName))) {
      userRepository.save(new User(userName));
      return true;
    }

    return false;
  }

  private boolean verityOnDictionary(String userName) {
    Iterable<Dictionary> restricted = dictionaryRepository.findAll();
    return StreamSupport.stream(restricted.spliterator(), false).anyMatch(d -> userName.contains(d.getWord()));
  }

  /**
   * Lookup and see if a username is "similar" with any word in the restricted dictionary.
   *
   * @param local  All the restricted words in the System.
   * @param target The current username
   */
  private void getDistance(String local, String target) {
    LevenshteinDistance distance = new LevenshteinDistance();
    int dis = distance.apply(local, target);
    if (dis < distance.getThreshold()) {
      throw new IllegalArgumentException();
    }
  }
}
