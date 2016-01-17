package com.x.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;


public class TestAnnaotaion {
	@Test
	public void testMark() {
		try {
			TestModel newInstance = TestModel.class.newInstance();
//			try {
//				Field declaredField = TestModel.class.getDeclaredField("name");
//				String name = declaredField.getName();
//				String value = declaredField.getAnnotation(Mark.class).value();
//				System.out.println(value);
//				System.out.println(name);
//			} catch (NoSuchFieldException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//获得这个 变量
			for(Field f: TestModel.class.getDeclaredFields()){
				System.out.println(f.getName());
//				String value = f.getAnnotation(Mark.class).value();
//				System.out.println(value);
				System.out.println(1);
			}
			for(Method m:TestModel.class.getDeclaredMethods()){
				System.out.println(m.getName());
			}
			
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
