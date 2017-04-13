package com.alirabiee.miniurl.rest.miniurl;

import com.alirabiee.miniurl.Configuration;
import com.alirabiee.miniurl.Main;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ali.
 *
 * Functionality test of {@link MiniUrlController}
 */
@RunWith( SpringRunner.class )
@SpringBootTest( classes = { Main.class, Configuration.class } )
@AutoConfigureMockMvc
@ActiveProfiles( value = "test" )
public class MiniUrlControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testHome() throws Exception {
        mvc.perform( MockMvcRequestBuilders.get( "/" ).accept( MediaType.ALL ).contentType( MediaType.ALL ) )
           .andExpect( status().isOk() )
           .andExpect( content().string( new StringContains( "jumbotron" ) ) );
    }
}
