package com.name.checkusername.service;

import com.name.checkusername.entity.RestrictedWords;
import com.name.checkusername.entity.User;
import com.name.checkusername.repository.RestrictedWordsRepository;
import com.name.checkusername.repository.UserRepository;
import com.name.checkusername.util.ApplicationUtil;
import com.name.checkusername.vo.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by rafae on 08/07/2017.
 */
@Service
public class UsernameService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestrictedWordsRepository restrictedWordsRepository;

    public static String NOT_CONTAINS_6_CHARACTERS = "Username not contains 6 characters";
    public static String CONTAINS_RESTRICTED_WORD = "User name contains a restricted word";
    public static String EXIST_ON_DATABASE = "User name exist on database";
    public static String USER_NAME_IS_VALID = "User name is valid";
    public static String USER_NAME_IS_NOT_VALID = "User name is not valid";
    StringBuffer sb;

    public Result checkUsername(String username) throws Exception {
        Result result = new Result();
        List<String> errors = new ArrayList<String>();

        if (StringUtils.isBlank(username) || username.length() < 6) {
            throw new Exception(NOT_CONTAINS_6_CHARACTERS);
        }

        if (StringUtils.isNotBlank(userNameHasRestrictedWord(username))) {
            errors.add(CONTAINS_RESTRICTED_WORD);
            username = removeRestrictedWords(username);
        }

        if (existUserNameOnDatabase(username)) {
            errors.add(EXIST_ON_DATABASE);
        }

        if (errors.isEmpty()) {
            result.setMessage(USER_NAME_IS_VALID);
            result.setProcessResult(true);
        } else {
            result.setMessage(USER_NAME_IS_NOT_VALID);
            result.setProcessResult(false);
            result.setSuggestedUserNames(generateUsernames(removeRestrictedWords(username)));
        }
        return result;
    }

    public TreeSet<String> generateUsernames(String baseName) {
        TreeSet<String> userNamesReturn = new TreeSet<String>();
        while (userNamesReturn.size() < 14) {
            sb = new StringBuffer();
            sb.append(baseName);
            boolean added = false;
            while (added == false) {
                sb = new StringBuffer();
                sb.append(baseName);
                while (sb.length() < 6) {
                    sb.append("_").append(baseName);
                }
                sb.append(ApplicationUtil.generateValue(3));
                if (checkIsValidUserNameGenerated(sb.toString(), userNamesReturn))
                    userNamesReturn.add(sb.toString());
                added = true;
            }
        }
        return userNamesReturn;
    }

    public boolean existUserNameOnDatabase(String userName) {
        List<User> userOnDb = userRepository.findByUserName(userName);
        if (userOnDb == null || userOnDb.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public String userNameHasRestrictedWord(String userName) {
        List<RestrictedWords> restrictedWordsOnDb = (List<RestrictedWords>) restrictedWordsRepository.findAll();
        String notAllowed = StringUtils.EMPTY;
        if (restrictedWordsOnDb == null || restrictedWordsOnDb.isEmpty()) {
            System.out.println("Restricted words no exist on db");
        } else {
            for (RestrictedWords restrictedWord : restrictedWordsOnDb) {
                if (StringUtils.contains(userName, restrictedWord.getWord())) {
                    System.out.println("Restricted word found in username: " + restrictedWord.getWord());
                    notAllowed = restrictedWord.getWord();
                    break;
                }
            }
        }
        return notAllowed;
    }

    public String removeRestrictedWords(String currentUsername) {
        String notAllowed = userNameHasRestrictedWord(currentUsername);
        if (StringUtils.isBlank(notAllowed)) {
            return currentUsername;
        } else {
            return StringUtils.remove(currentUsername, notAllowed);
        }
    }

    public boolean checkIsValidUserNameGenerated(String suggestion, TreeSet<String> suggestions) {
        if (existUserNameOnDatabase(suggestion) || suggestions.contains(suggestion)) {
            return false;
        } else {
            return true;
        }
    }

}
