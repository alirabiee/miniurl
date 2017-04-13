package com.alirabiee.miniurl.rest.minify;

import com.alirabiee.miniurl.Configuration;
import com.alirabiee.miniurl.Main;
import com.alirabiee.sys.constant.SystemErrorCode;
import com.alirabiee.sys.utility.captcha.CaptchaAPI;
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
 * Created by ali.
 *
 * Functionality test of {@link MinifyController}
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

    /**
     * Test the system correctly validates an invalid URL
     * @throws Exception
     */
    @Test
    public void testInvalidURL() throws Exception {
        mvc
            .perform(
                MockMvcRequestBuilders.post( "/minify" )
                                      .accept( MediaType.ALL )
                                      .contentType( MediaType.ALL )
                                      .param( "url", "invalid-url" )
                                      .param( "g-recaptcha-response", "" )
            )
            .andExpect( status().is3xxRedirection() )
            .andExpect( MockMvcResultMatchers.redirectedUrlPattern( "/*error=" + SystemErrorCode.VALIDATION_ERROR ) );
    }

    /**
     * Test the system generates the correct shortened URL for a specific URL
     * @throws Exception
     */
    @Test
    public void testNewUrl() throws Exception {
        mvc
            .perform(
                MockMvcRequestBuilders.post( "/minify" )
                                      .accept( MediaType.ALL )
                                      .contentType( MediaType.ALL )
                                      .param( "url", "http://goo.gl/" )
                                      .param( "g-recaptcha-response", "" )
            )
            .andExpect( status().isOk() )
            .andExpect( MockMvcResultMatchers.model().attribute( "minified", "eLY" ) );
    }

    /**
     * Test the system generates the correct shortened URL for the same URL
     * @throws Exception
     */
    @Test
    public void testRepeatedUrl() throws Exception {
        mvc
            .perform(
                MockMvcRequestBuilders.post( "/minify" )
                                      .accept( MediaType.ALL )
                                      .contentType( MediaType.ALL )
                                      .param( "url", "http://goo.gl/" )
                                      .param( "g-recaptcha-response", "" )
            )
            .andExpect( status().isOk() )
            .andExpect( MockMvcResultMatchers.model().attribute( "minified", "eLY" ) );
    }
}
