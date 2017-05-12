package org.tomi.dictionary;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tomi.user.User;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "dictionary", path = "dictionary")
public interface DictionaryRepository extends CrudRepository<Dictionary, Long> {

}
