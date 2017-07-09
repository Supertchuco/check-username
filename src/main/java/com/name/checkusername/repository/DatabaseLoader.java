package com.name.checkusername.repository;

import com.name.checkusername.entity.RestrictedWords;
import com.name.checkusername.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by rafae on 08/07/2017.
 */
@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RestrictedWordsRepository restrictedWordsRepository;

    @Autowired
    public DatabaseLoader(UserRepository userRep, RestrictedWordsRepository restrictedWordsRep) {
        this.userRepository = userRep;
        this.restrictedWordsRepository = restrictedWordsRep;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.userRepository.save(new User("cervantes"));
        this.userRepository.save(new User("jhon_jhon"));

        this.restrictedWordsRepository.save(new RestrictedWords("NotAllowed"));
    }
}
