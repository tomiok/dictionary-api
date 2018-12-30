package org.tomi.dictionary.web;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tomi.dictionary.usecase.SaveWordService;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

  private final SaveWordService saveWordService;

  public DictionaryController(final SaveWordService saveWordService) {
    this.saveWordService = saveWordService;
  }

  @PostMapping
  public ResponseEntity<?> saveWord(@RequestParam("word") String word) {
    String saved = saveWordService.saveWordInDic(word);

    return ok(saved);
  }
}
