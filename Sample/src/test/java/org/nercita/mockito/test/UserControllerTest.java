package org.nercita.mockito.test;


import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


/**
 *
 */
public class UserControllerTest extends AbstractControllerTestNGTest {

	// tested controller
	@Autowired
	private TestUserController testUserController;
	
	// mocked service (被依赖的服务)
	@Autowired
	private TestUserService testUserService;
	
	@Test(dataProvider = "getUserName")
	public void getUserName(Object[] params, String userName, String expectedContent) throws Exception {
		// 1. 定义"被依赖的服务"的方法行为
		when(this.testUserService.getUserName(anyLong())).thenReturn(userName);
		
		this.getMock("/user/getUserName?id={id}", params, expectedContent);
	}
	
	@DataProvider(name = "getUserName")
	protected static final Object[][] getUserNameTestData() {
		Object[][] testData = new Object[][] {
				{ new Object[] { "23" }, "Bert Lee", "{\"name\":\"Bert Lee\"}" },
		};
		return testData;
	}
	
	@Test(dataProvider = "updateUserName")
	public void updateUserName(String paramsJson, Boolean result, String expectedContent) throws Exception {
		// 1. 定义"被依赖的服务"的方法行为
		when(this.testUserService.updateUserName(anyLong(), anyString())).thenReturn(result);
		
		this.postMock("/user/updateUserName", paramsJson, expectedContent);
	}
	
	
	@DataProvider(name = "updateUserName")
	protected static final Object[][] updateUserNameTestData() {
		Object[][] testData = new Object[][] {
				{ "{\"id\":23,\"name\":\"Bert Lee\"}", 0, "{\"ret\":0,\"ret_msg\":\"ok\"}" },
		};
		return testData;
	}
	
	@Override
	public Object getController() {
		return this.testUserController;
	}

}

