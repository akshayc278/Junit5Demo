package io.javabrains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

//@TestInstance(TestInstance.Lifecycle.PER_METHOD)///create class object per method
@TestInstance(TestInstance.Lifecycle.PER_CLASS)//once
class MathUtilsTest {
	static MathUtils mathUtils;
	
	@BeforeAll 
	static void  init() {
		mathUtils = new MathUtils();
	}
	
	@BeforeEach
	void each() {
		System.out.println("each");
	}

	@Test
	@DisplayName("Add method test")
	void testAdd() {
		//MathUtils mathUtils = new MathUtils();
		System.out.println(mathUtils);
		int expected = 2;
		int actual = mathUtils.add(1, 1);
		assertEquals(expected,actual,"The add method should add two number");
	}
	
	@Test 
	void testComputeCircleArea() {
		//MathUtils mathUtils = new MathUtils();
		System.out.println(mathUtils);
		double actual=mathUtils.computeCircleArea(3);
		assertEquals(28.274333882308138,actual,"Calculate area of circle");
	}
	
	@Test 
	void testDevide() {
		System.out.println(mathUtils);
		//MathUtils mathUtils = new MathUtils();
					//expected                   //actual
		assertThrows(ArithmeticException.class	, ()-> mathUtils.devide(1, 1),"invalid operation devide by 0");
	}
	@Test
	@Disabled
	void testDisable() {
		System.out.println("disable");
	}
	@Test
	void assumeTest() {
		boolean flag=true;
		Assumptions.assumeTrue(flag);
		System.out.println("hi");
		flag=false;
		Assumptions.assumeTrue(flag);
		System.out.println("hi2");
	}
}
