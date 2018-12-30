package org.tomi.user.usecase;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.tomi.dictionary.model.Dictionary;
import org.tomi.dictionary.model.DictionaryRepository;
import org.tomi.user.model.User;
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

    when(dictionaryRepository.findAll()).thenReturn(Arrays.asList(dic, dic2));

    saveUserNameService.saveUserName("abuse");
  }

  @Test
  public void shouldFail_GivenRepeatedName() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("is already in use");
    String name = "repeated";
    when(userRepository.findByUserName(name)).thenReturn(Collections.singletonList(new User(name)));

    saveUserNameService.saveUserName(name);
  }
}