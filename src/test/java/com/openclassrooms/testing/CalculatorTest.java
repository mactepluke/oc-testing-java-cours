package com.openclassrooms.testing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorTest {

	private Calculator calculatorUnderTest;
	private static Instant startedAt;

	@BeforeAll
	static void initStartingTime() {
		System.out.println("Appel avant tous les tests");
		startedAt = Instant.now();
	}

	@AfterAll
	static void showTestDuration() {
		System.out.println("Appel après tous les tests");
		Instant endedAt = Instant.now();
		long duration = Duration.between(startedAt, endedAt).toMillis();
		System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
	}

	@BeforeEach
	void initCalculator() {
		calculatorUnderTest = new Calculator();
		System.out.println("Appel avant chaque test");
	}

	@AfterEach
	void undefCalculator() {
		System.out.println("Appel après chaque test");
	}

	@Test
	void testAddTwoPositiveNumbers() {
		// ARRANGE
		int a = 2;
		int b = 3;

		// ACT
		int somme = calculatorUnderTest.add(a, b);

		// ASSERT
		// assertEquals(5, somme);
		assertThat(somme).isEqualTo(5);
	}

	@Test
	void multiply_shouldReturnTheProduct_ofTwoIntegers() {
		// ARRANGE
		int a = 42;
		int b = 11;

		// ACT
		int produit = calculatorUnderTest.multiply(a, b);

		// ASSERT
		assertEquals(462, produit);
	}

	@ParameterizedTest(name = "{0} x 0 doit être égal à 0")
	@ValueSource(ints = { 1, 2, 42, 1011, 5089 })
	void multiply_shouldReturnZero_ofZeroWithMultipleIntegers(int arg) {
		// Arrange -- All is ready!

		// Act -- Multiply by zero
		int actualResult = calculatorUnderTest.multiply(arg, 0);

		// Assert -- It should always equal zero
		assertEquals(0, actualResult);
	}

	@ParameterizedTest(name = "{0} + {1} should equal to {2}")
	@CsvSource({ "1,1,2", "2,3,5", "42,57,99" })
	void add_shouldReturnTheSum_ofMultipleIntegers(int arg1, int arg2, int expectResult) {
		// Arrange -- All is ready!

		// Act
		int actualResult = calculatorUnderTest.add(arg1, arg2);

		// Assert
		assertEquals(expectResult, actualResult);
	}

	@Test
	void listDigits_shouldReturnTheListOfDigits_ofPositiveInteger() {
		// GIVEN
		int number = 95897;

		// WHEN
		Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

		// THEN
		// Set<Integer> expectedDigits = Stream.of(5, 7, 8,
		// 9).collect(Collectors.toSet());
		// assertEquals(expectedDigits, actualDigits);

		assertThat(actualDigits).containsExactlyInAnyOrder(9, 5, 8, 7);
	}
}
