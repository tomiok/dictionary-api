package org.tomi.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.tomi.user.usecase.user.SaveUserNameServiceImpl;
import org.tomi.user.web.UserController;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private SaveUserNameServiceImpl saveUserNameService;

  @Test
  public void verify() {

  }

}
