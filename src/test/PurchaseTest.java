package test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import domain.Article;
import domain.Purchase;
import exceptions.PurchaseException;

public class PurchaseTest {
	    // Common variables for all test cases.
		private Purchase basket= new Purchase();
		private double price;
		private int quantity=1;
		private Article article;	

	       // Initialization before each execution of test cases
		@Before
		public  void initialize() {
			System.out.println("Initialize and check ...");
			assertNull(basket.getDate());
			assertEquals(basket.getCost(), 0,0);
			price= 234.99;	// 234.99 real value
			quantity = 1;
			article = new Article("404", "MASK PINK", price, false, quantity);
		} 


		@Test
		public void testAddBasket1() {
			quantity = 1;
			price = 234.99;

			//1. expected value
			double expected = quantity*price;
			try {
				//2. invoke the method and get the result
				double obtained = basket.addBasket(article, quantity);
				//3. check
				assertEquals(expected, obtained,0); // expected always 1st parameter
			} catch (RuntimeException e1) {
				fail();
			}           
	        //4. stablish the initial state 
			try {
				basket.removeBasket(article, quantity);
				assertTrue(true);
			} catch (PurchaseException e) {
				fail("Impossible!!");
			}
		}

		@Test
		public void testAddBasket2() {
			
			quantity = 3;
			price = 234.99;
			
			try {
				double obtained = basket.addBasket(article, quantity);
				fail();
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
				assertTrue(e.getMessage().compareTo("There is not enough stock")==0);
			}
		}
		
		@Test
		public void testAddBasket3() {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date date = format.parse("23/12/2020");
				basket.buy(date);
				assertNotNull(basket.getDate());
				
				quantity = 3;				
				
				try {
					double obtained = basket.addBasket(article, quantity);
					fail();
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
					assertTrue(e.getMessage().compareTo("The purchase is closed. No articles can be added")==0);
				}
								
			} catch (ParseException e) {
				fail("The date 23/12/2020 is correct");
			}
		}


}
