package org.tomi.user.usecase;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;
import org.tomi.dictionary.Dictionary;
import org.tomi.dictionary.DictionaryRepository;
import org.tomi.user.model.User;
import org.tomi.user.model.UserRepository;

@Service
public class SaveUserNameServiceImpl implements SaveUserNameService {

  private static final int MIN_USER_NAME_LENGTH = 4;

  private final UserRepository userRepository;

  private final DictionaryRepository dictionaryRepository;

  public SaveUserNameServiceImpl(final UserRepository userRepository,
                                 final DictionaryRepository dictionaryRepository) {
    this.userRepository = userRepository;
    this.dictionaryRepository = dictionaryRepository;
  }

  @Override
  public String saveUserName(final String userName) {
    // validate length
    if (userName.length() < MIN_USER_NAME_LENGTH) {
      throw new IllegalArgumentException("The username cannot be shorter than 6 chars.");
    }

    // validate words from dict if is a forbidden word.
    this.verityOnDictionary(userName);

    // find all the user names
    Stream<String> names = userRepository
        .findByUserName(userName)
        .stream()
        .map(User::getUserName);

    // check if exist userName
    if (names.noneMatch(u -> u.equalsIgnoreCase(userName))) {
      userRepository.save(new User(userName));
      return userName;
    }

    throw new IllegalArgumentException("The username " + userName + " is already in use.");
  }

  private void verityOnDictionary(String userName) {
    Iterable<Dictionary> restricted = dictionaryRepository.findAll();
    Stream<Dictionary> dic = StreamSupport.stream(restricted.spliterator(), false);
    dic.forEach(d -> getDistance(d.getWord(), userName));
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
    if (dis < 4) {
      throw new IllegalArgumentException("You need to change your username, it is probably hurting someone's "
                                         + "susceptibility. ");
    }
  }
}
