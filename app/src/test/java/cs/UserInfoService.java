package cs;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cs.Application;
import com.cs.service.WXService;
import com.cs.util.OAuth;



@SpringBootTest(classes=Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public  class UserInfoService {
 
	
	 
	 @Autowired
	 private WXService wx;


	    @Test
	    public void setUp() { 
	       String token =  wx.token();
	       System.err.println("token : "+token);
	    } 
	    
	   
}
