package io.javabrains;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;

//@TestInstance(TestInstance.Lifecycle.PER_METHOD)///create class object per method
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // once
class MathUtilsTest {
	// beforeall requre static
	// before each requre normal declaration
	static MathUtils mathUtils;
	TestInfo testInfo;
	TestReporter testReporter;

	@BeforeAll
	static void init() {
		mathUtils = new MathUtils();

	}

	@BeforeEach
	void each(TestInfo testInfo, TestReporter testReporter) {
		// System.out.println("each");
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with name " + testInfo.getTestMethod());
	}

	@Test
	@DisplayName("Add method test")
	void testAdd() {
		// MathUtils mathUtils = new MathUtils();
		System.out.println(mathUtils);
		int expected = 2;
		int actual = mathUtils.add(1, 1);
		assertEquals(expected, actual, "The add method should add two number");
	}

	@Test
	void testComputeCircleArea() {
		// MathUtils mathUtils = new MathUtils();
		System.out.println(mathUtils);
		double actual = mathUtils.computeCircleArea(3);
		assertEquals(28.274333882308138, actual, "Calculate area of circle");
	}

	@Test
	void testDevide() {
		System.out.println(mathUtils);
		// MathUtils mathUtils = new MathUtils();
		// expected //actual
		assertThrows(ArithmeticException.class, () -> mathUtils.devide(1, 1), "invalid operation devide by 0");
	}

	@Test
	@Disabled
	void testDisable() {
		System.out.println("disable");
	}

	@Test
	void assumeTest() {
		boolean flag = true;
		Assumptions.assumeTrue(flag);
		System.out.println("hi");
		flag = false;
		Assumptions.assumeTrue(flag);
		System.out.println("hi2");
	}

	@Test
	void testmultiply() {
		// assertEquals(4, mathUtils.multiply(1, 4));
		assertAll(() -> assertEquals(4, mathUtils.multiply(1, 4)), () -> assertEquals(8, mathUtils.multiply(2, 4)));
	}

	@Nested
	@DisplayName("Add Nested Class Test")
	class AddTest {
		@Test
		@DisplayName("Adding two positive numbers")
		void testAdd() {
			assertEquals(2, mathUtils.add(1, 2));
		}

		@Test
		@DisplayName("Add two negative numbers")
		void testAddN(TestInfo testInfo, TestReporter testReporter) {
			testReporter
					.publishEntry("Running from nested class" + testInfo.getDisplayName() + " with name " + testInfo.getTestMethod());
			assertEquals(-2, mathUtils.add(-1, -1));

		}

		@Test
		@DisplayName("Add negative and positive")
		void testAddP() {
			int actual = mathUtils.add(3, -1);
			// lazy loading of message
			assertEquals(2, actual, () -> "this gets calculated if test case failed resourse will not wasted");
		}
	}

	@RepeatedTest(value = 3)
	@DisplayName("Add two negative numbers 2")
	void testAddN2() {
		assertEquals(-2, mathUtils.add(-1, -1));
	}

	@RepeatedTest(value = 3)
	@DisplayName("Add two negative numbers 3")
	void testAddN3(RepetitionInfo repetitionInfo) {
		if (repetitionInfo.getCurrentRepetition() == 1)
			assertEquals(-2, mathUtils.add(-1, -1));
	}
}
