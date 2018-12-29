package org.tomi.user.usecase;

import java.util.Arrays;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.tomi.dictionary.Dictionary;
import org.tomi.dictionary.DictionaryRepository;
import org.tomi.user.model.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class SaveUserNameServiceImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private UserRepository userRepository;

  @Mock
  private DictionaryRepository dictionaryRepository;

  @InjectMocks
  private SaveUserNameServiceImpl saveUserNameService;

  @Test
  public void shouldFailWithShortUsername() {
    thrown.expect(IllegalArgumentException.class);
    String shortName = "s";
    saveUserNameService.saveUserName(shortName);
  }

  @Test
  public void shouldFailWithBadWord() {
    thrown.expect(IllegalArgumentException.class);
    Dictionary dic = new Dictionary("Abuse");
    Dictionary dic2 = new Dictionary("drugs");
    Iterable<Dictionary> words = Arrays.asList(dic, dic2);

    Mockito.when(dictionaryRepository.findAll()).thenReturn(words);

    saveUserNameService.saveUserName("abuse");
  }
}