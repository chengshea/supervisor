package cs;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cs.Application;



@SpringBootTest(classes=Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public  class ControllerTest {
 
	
	   @Autowired
	  private WebApplicationContext context;
		
	  private MockMvc mvc; 
	 


	    @Before
	    public void setUp() throws Exception { 
	        mvc = MockMvcBuilders.webAppContextSetup(context).build();
	    } 
	    
	    
	    @Test
	    public  void test(){
	    	Map<String,String> m1= new HashMap<String,String>();
	    	m1.put("user", "123");
	    	m1.put("pwd", "456");
	       try {
				this.post("/api/login", m1);
			} catch (Exception e) {
				Assert.fail("验证");
			}
	      
	   
	   
	   }
	    
	    private  void   post(String url,Map<String,String> m) throws Exception{
	    	MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.post(url)
	        .contentType(MediaType.APPLICATION_JSON_UTF8);
	          	for (Entry<String, String>  k : m.entrySet()) {
	          		   rb.param(k.getKey(), k.getValue());
					}
				
	    	mvc.perform(rb.accept(MediaType.APPLICATION_JSON))
	                        .andExpect(MockMvcResultMatchers.status().isOk()) 
	                        .andDo(MockMvcResultHandlers.print()); 
	    }
	    
	    
}
