package org.tomi.dictionary.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.tomi.dictionary.model.Dictionary;
import org.tomi.dictionary.model.DictionaryRepository;

@Component
public class FindWordInDicServiceImpl implements FindWordsInDicService {

  private final DictionaryRepository dictionaryRepository;

  public FindWordInDicServiceImpl(final DictionaryRepository dictionaryRepository) {
    this.dictionaryRepository = dictionaryRepository;
  }

  @Override
  public Page<Dictionary> findAll(int page, int number) {
    return dictionaryRepository.findAll(PageRequest.of(10, 5));
  }
}
