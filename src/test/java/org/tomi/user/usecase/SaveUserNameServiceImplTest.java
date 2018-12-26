package org.tomi.user.usecase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.tomi.dictionary.DictionaryRepository;
import org.tomi.user.model.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class SaveUserNameServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private DictionaryRepository dictionaryRepository;

  @Test
  public void saveUserName() {

  }
}