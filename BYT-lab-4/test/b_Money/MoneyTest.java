package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}


	//Sprawdza, czy metoda getAmount() zwraca poprawne wartości dla różnych instancji Money.
	@Test
	public void testGetAmount() {
		assertEquals(10000, (int) SEK100.getAmount());
		assertEquals(1000, (int) EUR10.getAmount());
		assertEquals(20000, (int) SEK200.getAmount());
		assertEquals(2000, (int) EUR20.getAmount());
		assertEquals(0, (int) SEK0.getAmount());
		assertEquals(0, (int) EUR0.getAmount());
		assertEquals(-10000, (int) SEKn100.getAmount());
	}

	//Upewnia się, że metoda getCurrency() zwraca poprawne obiekty Currency dla różnych instancji Money.
	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SEK100.getCurrency());
		assertEquals(EUR, EUR10.getCurrency());
		assertEquals(SEK, SEK200.getCurrency());
		assertEquals(EUR, EUR20.getCurrency());
		assertEquals(SEK, SEK0.getCurrency());
		assertEquals(EUR, EUR0.getCurrency());
		assertEquals(SEK, SEKn100.getCurrency());
	}

	//Testuje, czy metoda toString() generuje poprawne ciągi znaków dla różnych instancji Money, uwzględniając formatowanie kwoty i nazwy waluty.
	@Test
	public void testToString() {
		assertEquals("100,00 SEK", SEK100.toString());
		assertEquals("10,00 EUR", EUR10.toString());
		assertEquals("200,00 SEK", SEK200.toString());
		assertEquals("20,00 EUR", EUR20.toString());
		assertEquals("0,00 SEK", SEK0.toString());
		assertEquals("0,00 EUR", EUR0.toString());
		assertEquals("-100,00 SEK", SEKn100.toString());

	}


	//Sprawdza, czy metoda universalValue() zwraca poprawne wartości dla różnych instancji Money, uwzględniając kurs wymiany walut.
	@Test
	public void testGlobalValue() {
		assertEquals(1500, SEK100.universalValue(), 0.001);
		assertEquals(1500, EUR10.universalValue(), 0.001);
		assertEquals(3000, SEK200.universalValue(), 0.001);
		assertEquals(3000, EUR20.universalValue(), 0.001);
		assertEquals(0, SEK0.universalValue(), 0.001);
		assertEquals(0, EUR0.universalValue(), 0.001);
		assertEquals(-1500, SEKn100.universalValue(), 0.001);


	}


	//Testuje, czy metoda equals(Money other) poprawnie porównuje dwie instancje Money, uwzględniając ich uniwersalną wartość.
	@Test
	public void testEqualsMoney() {
		assertTrue(SEK100.equals(new Money(10000, SEK)));
		assertTrue(EUR0.equals(SEK0));
		assertFalse(EUR10.equals(new Money(9999, EUR)));
		assertFalse(SEK200.equals(new Money(-20000, SEK)));
		assertFalse(SEKn100.equals(new Money(10000, SEK)));
	}


	//Sprawdza, czy metoda add(Money other) poprawnie dodaje dwie instancje Money, zachowując odpowiednią walutę.
	@Test
	public void testAdd() {
		Money moneyAdd = SEK200.add(SEKn100);
		assertEquals(10000, (int)moneyAdd.getAmount());
		assertEquals(SEK, moneyAdd.getCurrency());
	}


	//Testuje, czy metoda sub(Money other) poprawnie odejmuje dwie instancje Money, zachowując odpowiednią walutę.
	@Test
	public void testSub() {
		Money moneySub = EUR20.sub(EUR10);
		assertEquals(1000, (int)moneySub.getAmount());
		assertEquals(EUR, moneySub.getCurrency());
	}

	// Sprawdza, czy metoda isZero() zwraca poprawne wartości dla różnych instancji Money.
	@Test
	public void testIsZero() {
		assertFalse(SEK200.isZero());
		assertFalse(SEKn100.isZero());
		assertFalse(SEK100.isZero());
		assertTrue(SEK0.isZero());
		assertFalse(EUR20.isZero());
		assertFalse(EUR10.isZero());
		assertTrue(EUR0.isZero());
	}


	//Testuje, czy metoda negate() poprawnie neguje kwotę instancji Money.
	@Test
	public void testNegate() {
		Money moneyNegate = SEK100.negate();
		assertEquals(-10000, (int)moneyNegate.getAmount());
		assertEquals(SEK, moneyNegate.getCurrency());
	}


	//Porównuje różne instancje Money za pomocą metody compareTo(Object other) zgodnie z ich uniwersalną wartością, sprawdzając poprawność porównań.
	@Test
	public void testCompareTo() {
		assertEquals(0, EUR0.compareTo(SEK0));
		assertFalse(SEK100.compareTo(SEKn100) < 0);
		assertFalse(SEK200.compareTo(SEK100) < 0);
		assertTrue(EUR10.compareTo(EUR20) < 0);
	}
}
