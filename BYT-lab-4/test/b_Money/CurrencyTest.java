package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}


	//Sprawdza, czy metoda getName() zwraca poprawne nazwy walut dla różnych instancji Currency.
	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}


	//Upewnia się, że metoda getRate() zwraca poprawne kursy wymiany walut dla różnych instancji Currency.
	@Test
	public void testGetRate() {
		assertEquals(0.15, SEK.getRate(), 0.0001);
		assertEquals(0.20, DKK.getRate(), 0.0001);
		assertEquals(1.5, EUR.getRate(), 0.0001);
	}


	//Testuje, czy metoda setRate(Double rate) poprawnie aktualizuje kurs wymiany waluty.
	@Test
	public void testSetRate() {
		SEK.setRate(0.25);
		DKK.setRate(0.30);
		EUR.setRate(1.8);

		assertEquals(0.25, SEK.getRate(), 0.001);
		assertEquals(0.30, DKK.getRate(), 0.001);
		assertEquals(1.8, EUR.getRate(), 0.001);
	}


	//Sprawdza, czy metoda universalValue(Integer amount) zwraca poprawne wartości dla różnych kwot w danej walucie, konwertując je na wartość w uniwersalnej walucie.
	@Test
	public void testGlobalValue() {
		assertEquals(15,(int)SEK.universalValue(10000));
		assertEquals(20,(int)DKK.universalValue(10000));
		assertEquals(15,(int)EUR.universalValue(1000));
	}


	//Testuje, czy metoda valueInThisCurrency(Integer amount, Currency otherCurrency) poprawnie konwertuje kwoty z innych walut na wartość w danej walucie.
	@Test
	public void testValueInThisCurrency() {
		assertEquals(22,(int)SEK.valueInThisCurrency(220,EUR));
	}

}
