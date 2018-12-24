package org.tomi.dictionary;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "dictionary", path = "dictionary")
public interface DictionaryRepository extends CrudRepository<Dictionary, Long> {

}
