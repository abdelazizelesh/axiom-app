package axiom.app.rest;

import axiom.app.rest.controller.HandsetController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
@ExtendWith(SpringExtension.class)
@WebMvcTest(HandsetController.class)
public class HandsetControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    /**
     * Test search all mobile handset database returns 105 devices
     * @throws Exception
     */
    @Test
    void testSearchAll() throws Exception {
        RequestBuilder request = get("/mobile/search");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(105)));
    }

    /**
     * Test search mobile handset database by price equals 200 returns 10 devices
     * @throws Exception
     */
    @Test
    void testSeachPriceEq200() throws Exception {
        RequestBuilder request = get("/mobile/search")
                .param("priceEur","200");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(10)));
    }

    /**
     * Test search mobile handset database by price equals 20 returns 0 device
     * @throws Exception
     */
    @Test
    void testSeachPriceEq20() throws Exception {
        RequestBuilder request = get("/mobile/search")
                .param("priceEur","20");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(0)));
    }


    /**
     * Test search mobile handset database by sim contains ignore-case eSim returns 18 devices
     * @throws Exception
     */
    @Test
    void testSeachSimContainseSim() throws Exception {
        RequestBuilder request = get("/mobile/search")
                .param("sim","eSim");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(18)));
    }

    /**
     * Test search mobile handset database by announceDate contains 1999 and price equals 200 returns 2 devices
     * @throws Exception
     */
    @Test
    void testSeachAnnounceDateContains1999AndPriceEq200() throws Exception {
        RequestBuilder request = get("/mobile/search")
                .param("announceDate","1999")
                .param("price","200");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)));
    }

//    /**
//     * Test search mobile handset database by invalid parameter return status 500 internal server error
//     * @throws Exception
//     */
//    @Test
//    void testSeachInvalidParamter() throws Exception {
//        RequestBuilder request = get("/mobile/search")
//                .param("abc","xyz");
//        mvc.perform(request)
//                .andExpect(status().isInternalServerError());
//    }
}
