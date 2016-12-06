package org.nercita.core.test.mock;

import static org.easymock.EasyMock.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * mock 帮助类
 * 
 * @author wangzz
 * @since 2009-03-25
 * 
 */
@SuppressWarnings("unchecked")
final public class MockTestHelper {
	public static void resetAll(Object testObject) {
        reset(getDeclaredMockedFields(testObject));
    }

    public static void verifyAll(Object testObject) {
        verify(getDeclaredMockedFields(testObject));
    }

    public static void replayAll(Object testObject) {
        replay(getDeclaredMockedFields(testObject));
    }

   
	@SuppressWarnings("rawtypes")
	private static Object[] getDeclaredMockedFields(Object testObject) {
        Field[] declaredFields = testObject.getClass().getDeclaredFields();
        List declaredMockedFields = new ArrayList();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                boolean isAccessible = field.isAccessible();
                try {
                    field.setAccessible(true);
                    Object value = field.get(testObject);
                    if (isClassProxy(value.getClass())) {
                        declaredMockedFields.add(value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                finally {
                    field.setAccessible(isAccessible);
                }
            }
        }
        return declaredMockedFields.toArray();
    }

	
	@SuppressWarnings("rawtypes")
	private static boolean isClassProxy(Class clazz) {
        String className = clazz.getName();
        return className.contains("$Proxy") || className.contains("$$EnhancerByCGLIB$$");
    }

}
