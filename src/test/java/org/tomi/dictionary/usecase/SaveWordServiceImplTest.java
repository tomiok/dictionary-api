package org.tomi.dictionary.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.tomi.dictionary.model.Dictionary;
import org.tomi.dictionary.model.DictionaryRepository;

@RunWith(MockitoJUnitRunner.class)
public class SaveWordServiceImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private DictionaryRepository dictionaryRepository;

  @InjectMocks
  private SaveWordServiceImpl saveWordService;

  @Test
  public void saveWordInDic() {
    String word = "abuse";

    when(dictionaryRepository.save(any(Dictionary.class))).thenReturn(new Dictionary(word));
    String actual = saveWordService.saveWordInDic(word);
    assertThat(actual).isEqualTo(word);
  }

  @Test
  public void shouldFailWithShortWord() {
    thrown.expect(IllegalArgumentException.class);
    String word = "s";
    saveWordService.saveWordInDic(word);
  }
}