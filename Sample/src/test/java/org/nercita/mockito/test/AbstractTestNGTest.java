package org.nercita.mockito.test;

import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeSuite;

@ContextConfiguration("classpath:test-context.xml")
public class AbstractTestNGTest extends AbstractTestNGSpringContextTests {
	    /** 
	     * Initializes the test context. 
	     */  
       @BeforeSuite(alwaysRun = true)  
       public void init() {  
          MockitoAnnotations.initMocks(this); // 基于Spring自动装配注解，这里不再需要初始化  
       }

}
