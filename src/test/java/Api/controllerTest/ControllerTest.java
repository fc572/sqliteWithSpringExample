package Api.controllerTest;

import Api.model.User;
import Api.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserService userService;

    @Before
    public void setUp(){

        List<User> users = new ArrayList<User>();
        users.add(new User());
        users.add(new User());

        users.get(0).setId(1);
        users.get(0).setName("Fra");
        users.get(0).setPassword("fraPass");

        users.get(1).setId(2);
        users.get(1).setName("Jack");
        users.get(1).setPassword("jackPass");

        given(this.userService.findById(1)).willReturn(users.get(0));
        given(this.userService.findByName("Jack")).willReturn(users.get(1).getId());
        given(this.userService.findAllUsers()).willReturn(users);

    }

    @Test
    public void getUserByIdTest(){
        ResponseEntity<User> user = this.restTemplate.getForEntity("/api/user/{id}", User.class, 1);
        assertThat("Http status success", user.getStatusCode().is2xxSuccessful());
        assertThat(user.getBody().getName(), equalTo("Fra"));
    }

    @Test @Ignore
    public void getUserByNameTest(){
        ResponseEntity<User> user = this.restTemplate.getForEntity("/api/user?name={name}", User.class, "Jack");
        assertThat("Http status success", user.getStatusCode().is2xxSuccessful());
        assertThat(user.getBody().getName(), equalTo("Jack"));
    }

    @Test
    public void getAllUsers(){
        User[] users = restTemplate.getForObject("/api/users/", User[].class);
        assertThat(Arrays.asList(users), hasSize(2));
    }
}


