package org.tomi.dictionary.usecase;

import org.springframework.stereotype.Component;
import org.tomi.dictionary.model.Dictionary;
import org.tomi.dictionary.model.DictionaryRepository;

@Component
public class SaveWordServiceImpl implements SaveWordService {

  private final DictionaryRepository dictionaryRepository;

  SaveWordServiceImpl(final DictionaryRepository dictionaryRepository) {
    this.dictionaryRepository = dictionaryRepository;
  }

  @Override
  public String saveWordInDic(final String word) {
    Dictionary dictionary = new Dictionary(word);
    Dictionary saved = dictionaryRepository.save(dictionary);

    return saved.getWord();
  }
}
