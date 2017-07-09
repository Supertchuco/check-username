package com.name.checkusername.repository;

import com.name.checkusername.entity.RestrictedWords;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rafae on 08/07/2017.
 */
public interface RestrictedWordsRepository extends CrudRepository<RestrictedWords, Long> {
     List<RestrictedWords> findByWord(String word);
}
