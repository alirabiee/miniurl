package com.alirabiee.miniurl.rest.minify;

import com.alirabiee.miniurl.Configuration;
import com.alirabiee.miniurl.Main;
import com.alirabiee.sys.constant.SystemErrorCode;
import com.alirabiee.sys.utility.CaptchaAPI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by A on 2017-04-12.
 */
@RunWith( SpringRunner.class )
@SpringBootTest( classes = { Main.class, Configuration.class } )
@AutoConfigureMockMvc
@ActiveProfiles( value = "test" )
public class MinifyControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CaptchaAPI captchaAPI;

    @Before
    public void setup() {
        BDDMockito.given( captchaAPI.verify( "" ) ).willReturn( true );
    }

    @Test
    public void testInvalidURL() throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.post( "/minify" )
                                  .accept( MediaType.ALL )
                                  .contentType( MediaType.ALL )
                                  .param( "url", "invalid-url" )
                                  .param( "g-recaptcha-response", "" )
        ).andExpect( status().is3xxRedirection() )
           .andExpect( MockMvcResultMatchers.redirectedUrlPattern( "/*error=" + SystemErrorCode.VALIDATION_ERROR ) );
    }

    @Test
    public void testNewUrl() throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.post( "/minify" )
                                  .accept( MediaType.ALL )
                                  .contentType( MediaType.ALL )
                                  .param( "url", "http://goo.gl/" )
                                  .param( "g-recaptcha-response", "" )
        ).andExpect( status().isOk() ).andExpect( MockMvcResultMatchers.model().attribute( "minified", "r" ) );
    }

    @Test
    public void testRepeatedUrl() throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.post( "/minify" )
                                  .accept( MediaType.ALL )
                                  .contentType( MediaType.ALL )
                                  .param( "url", "http://goo.gl/" )
                                  .param( "g-recaptcha-response", "" )
        ).andExpect( status().isOk() ).andExpect( MockMvcResultMatchers.model().attribute( "minified", "r" ) );
    }
}
