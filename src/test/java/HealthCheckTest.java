import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={com.retailer.rewardspoints.RewardPointsApplication.class})
@AutoConfigureMockMvc
public class HealthCheckTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHealthAllCustomerData() throws Exception {
        this.mockMvc.perform(get("/rewardPoints/customers")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void testCustomerNotFound() throws Exception {
        this.mockMvc.perform(get("/rewardPoints/points/customer/1018")).andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(content().string(CoreMatchers.containsString("CustomerID: 1018 Sorry We could not find this customer")));
    }
}