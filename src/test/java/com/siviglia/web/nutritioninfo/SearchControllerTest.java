package com.siviglia.web.nutritioninfo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SearchControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @Test 
    public void shouldReturnNothing() throws Exception{

        String queryName = "vjrerevevnrke";
        
        mockMvc.perform(get("/api/v1/search/name/" + queryName))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.search_query").value(queryName))
            .andExpect(jsonPath("$.products").isArray())
            .andExpect(jsonPath("$.products.length()").value(0));
    }
    
    @Test 
    public void shouldReturnSearchResultsInArrayForPizza() throws Exception{

        String queryName = "pizza";
        
        mockMvc.perform(get("/api/v1/search/name/" + queryName))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.search_query").value(queryName))
            .andExpect(jsonPath("$.products").isArray())
            .andExpect(jsonPath("$.products.length()").value( greaterThan(0) ));
    }

    @Test 
    public void shouldReturnTotalResultsForPizza() throws Exception{

        String queryName = "pizza";
        
        mockMvc.perform(get("/api/v1/search/name/" + queryName))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.search_query").value(queryName))
            .andExpect(jsonPath("$.total_results").value( greaterThan(0) ));
    }

    @Test 
    public void shouldReturnMaxResultsForPizza() throws Exception{

        String queryName = "pizza";
        
        mockMvc.perform(get("/api/v1/search/name/" + queryName))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.search_query").value(queryName))
            .andExpect(jsonPath("$.max_results").value( greaterThan(0) ));
    }

    @Test 
    public void shouldReturnSizeOFArrayForPizza() throws Exception{

        String queryName = "pizza";
        
        mockMvc.perform(get("/api/v1/search/name/" + queryName))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.search_query").value(queryName))
            .andExpect(jsonPath("$.returned_results").value( greaterThan(0) ));
    }

    @Test 
    public void shouldReturnFirstResultIndexForPizza() throws Exception{

        String queryName = "pizza";
        String firstResult = "5";
        
        mockMvc.perform(get("/api/v1/search/name/" + queryName + "/?first_result=" + firstResult))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.search_query").value(queryName))
            .andExpect(jsonPath("$.first_result").value( Integer.parseInt(firstResult) ));
    }
}
