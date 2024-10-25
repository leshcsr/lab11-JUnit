package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import domain.Article;
import domain.Purchase;
import domain.PurchasedArticle;
import exceptions.PurchaseException;

public class PurchaseTest {
	// Common variables for all test.
		private Purchase basket= new Purchase();
		private double price;
		private int quantity=1;
		private Article article;

		// Initialization of the variables before the execution of each test
		@Before
		public void initialize() {
			System.out.println("Initialize  ...");
			
			// Price 234.99 real value
			price= 234.99;	
			quantity = 1;
			article = new Article("404", "MASK PINK", price, false, quantity);
		} 

		@Test
		public void testAddBasket1() {
			quantity = 1;
			price = 234.99;

			//1. expected value
			double expected = quantity*price;

			//2. invoke the method and get the result
			double obtained = basket.addBasket(article, quantity);
			
			//3. check
			//expected always 1st param
			assertEquals(expected, obtained,0);
			    
	                       
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
				basket.addBasket(article, 3);
		        fail("Expected a RuntimeException for insufficient stock");
			} catch (RuntimeException e) {
				assertEquals("There is not enough stock", e.getMessage());
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
					basket.addBasket(article, quantity);
					fail("Expected a RuntimeException because the purchase is already closed");
				} catch(RuntimeException e){
					assertEquals("The purchase is closed. No articles can be added", e.getMessage());					
				}
	
			} catch (ParseException e) {
				fail("The date format should be correct");
			}
		}
		
		@Test
		public void testAddBasket4() {
			try {
				quantity = 1;
				basket.addBasket(null, quantity);
				fail("The code must not continue !!");
			} catch (NullPointerException e) {
				fail("Null pointer exception not handled");
			} catch (RuntimeException e) {
				assertTrue(true);
			}
		}
		
		@Test
		public void testAddBasket5() {
		    quantity = 1;
		    price = 100.00;
		    article = new Article("101", "BLUE MASK", price, false, 10);

		    basket.addBasket(article, quantity);
		    
		    // Se vuelve a agregar el mismo artículo (debería actualizar la cantidad)
		    int additionalQuantity = 2;
		    double expectedCost = (quantity + additionalQuantity) * price;
		    
		    // Se agrega más unidades del mismo artículo
		    double obtainedCost = basket.addBasket(article, additionalQuantity);
		    
		    // Verificar que la cantidad del artículo en el carrito ha sido actualizada
		    assertEquals(expectedCost, obtainedCost, 0);
		    

		}



}
