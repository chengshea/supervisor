package cs;


import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs.Application;
import com.cs.model.UserInfo;
import com.cs.service.UserInfoService;
import com.cs.service.WXService;
import com.cs.util.OAuth;
import com.google.gson.Gson;
import com.google.gson.JsonObject;



@SpringBootTest(classes=Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public  class UserInfoServiceTest {
 
	
	 
	 @Autowired
	 private WXService wx;
	 @Autowired
	  private UserInfoService userInfo;

	    @Test
	    public void setUp() { 
	       String token =  wx.token();
	       System.err.println("token : "+token);
	    } 
	    
	    
	    @Test
	    public void save(){
	    	
	    	Page<UserInfo> page = userInfo.getpage(0,10);
	    	for (UserInfo userInfo : page) {
				
	    		System.err.println(new Gson().toJson(userInfo));
			}
	    	System.err.println(page.getNumberOfElements());
	    }
	    
	   
}
