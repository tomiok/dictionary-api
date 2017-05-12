package org.tomi.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getVerifyFailByLenght() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/verify").param("userName", "john").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"valid\":false}")));
    }

    @Test
    public void getVerifyFailByRestricted() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/verify").param("userName", "johndamn").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"valid\":false}")));
    }

    // notice thats johnnn2 is skiped!
    @Test
    public void getVerifyFailByExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/verify").param("userName", "johnnn").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"valid\":false,\"users\":[\"johnnn0\",\"johnnn1\",\"johnnn3\",\"johnnn4\",\"johnnn5\",\"johnnn6\",\"johnnn7\",\"johnnn8\",\"johnnn9\",\"johnnn10\",\"johnnn11\",\"johnnn12\",\"johnnn13\",\"johnnn14\"]}")));
    }

    @Test
    public void getVerifySuccess() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/verify").param("userName", "luqita").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"valid\":true}")));
    }
}
