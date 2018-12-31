package org.tomi.dictionary.usecase;

import org.springframework.data.domain.Page;
import org.tomi.dictionary.model.Dictionary;

public interface FindWordsInDicService {

    Page<Dictionary> findAll(int page, int number);
}
