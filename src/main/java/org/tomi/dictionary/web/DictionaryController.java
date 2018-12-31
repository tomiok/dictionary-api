package org.tomi.dictionary.web;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tomi.dictionary.model.Dictionary;
import org.tomi.dictionary.usecase.FindWordsInDicService;
import org.tomi.dictionary.usecase.SaveWordService;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

  private final SaveWordService saveWordService;

  private final FindWordsInDicService findWordsInDicService;

  public DictionaryController(final SaveWordService saveWordService,
                              final FindWordsInDicService findWordsInDicService) {
    this.saveWordService = saveWordService;
    this.findWordsInDicService = findWordsInDicService;
  }

  @PostMapping
  public ResponseEntity<DicHttpResponse> saveWord(@RequestParam("word") String word) {
    String saved = saveWordService.saveWordInDic(word);
    return ok(new DicHttpResponse(saved, Status.SAVED));
  }
    
  @GetMapping
  public ResponseEntity<Page<Dictionary>> getAll
    (@RequestParam("page") int page, @RequestParam("number") int number) {
    return ok(findWordsInDicService.findAll(page, number));
  }
}
