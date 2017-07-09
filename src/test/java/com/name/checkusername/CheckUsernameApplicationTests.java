package com.name.checkusername;

import com.name.checkusername.service.UsernameService;
import com.name.checkusername.vo.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckUsernameApplicationTests {

    @Autowired
    UsernameService usernameService;

    private Result successResult;
    private Result failResult;

    @Before
    public void setup() {
        successResult = new Result();
        successResult.setProcessResult(true);
        successResult.setMessage("User name is valid");

        failResult = new Result();
        failResult.setProcessResult(true);
        failResult.setMessage("User name is valid");
    }

    @Test
    public void UserNameServiceSuccessTest() throws Exception {
        assertEquals(successResult, usernameService.checkUsername("jhonSmith"));
    }

    @Test(expected = Exception.class)
    public void UserNameServiceLess6CharacteresTest() throws Exception {
        usernameService.checkUsername("jhon");
    }

    @Test
    public void UserNameServiceExistUsernameOnDbTest() throws Exception {
        Result result = usernameService.checkUsername("cervantes");
        if (result.getSuggestedUserNames().size() != 14 && !result.getMessage().equals(UsernameService.USER_NAME_IS_NOT_VALID)) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void UserNameServiceWordNotAllowedTest() throws Exception {
        String[] userNames = {"vanNotAlloweds", "cervNotAllowedantes", "vNotAlloweds"};
        for (String userName : userNames) {
            Result result = usernameService.checkUsername(userName);
            if (result.getSuggestedUserNames().size() != 14 && !result.getMessage().equals(UsernameService.USER_NAME_IS_NOT_VALID)) {
                Assert.assertTrue(false);
            }
        }
    }
}
