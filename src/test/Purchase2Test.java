package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import domain.Article;
import domain.Purchase;
import exceptions.PurchaseException;

public class Purchase2Test {

    // Variables comunes para todos los tests
    private Purchase basket;
    private Article article;
    private double price;
    private int quantity;

    // Inicialización de las variables antes de cada test
    @Before
    public void initialize() {
        System.out.println("Inicializando objetos para el test...");
        
        basket = new Purchase();
        price = 100.50;
        quantity = 5;
        article = new Article("001", "Artículo de prueba", price, true, 10);
    }
    
 // Test para el método addBasket
    @Test
    public void testAddBasket() {
        // 1. expected value
        double expected = quantity * price;

        // 2. invoke the method and get the result
        double obtained = basket.addBasket(article, quantity);

        // 3. check - compara el valor esperado con el obtenido
        assertEquals(expected, obtained, 0);

        // 4. Re-stablish initial state - limpia el carrito eliminando los artículos agregados
        try {
            basket.removeBasket(article, quantity);
        } catch (PurchaseException e) {
            fail("Error al eliminar artículos del carrito después de la prueba");
        }
    }
    
    
    

    // Test para el método removeBasket
    @Test
    public void testRemoveBasket() {
        // 1. Agregamos primero el artículo al carrito para poder probar su eliminación
        basket.addBasket(article, quantity);
        double initialCost = quantity * price;

        // 2. Remove part of the basket
        int quantityToRemove = 2;
        double expectedAfterRemoval = (quantity - quantityToRemove) * price;
        double obtainedAfterRemoval;

        try {
            // 3. invoke the method and get the result
            obtainedAfterRemoval = basket.removeBasket(article, quantityToRemove);

            // 4. check - compara el valor esperado con el obtenido
            assertEquals(expectedAfterRemoval, obtainedAfterRemoval, 0);
            
        } catch (PurchaseException e) {
            fail("No se debería haber lanzado excepción al eliminar artículos del carrito");
        }

        // 5. Vacía completamente el carrito y comprueba que el costo sea 0
        try {
            basket.removeBasket(article, quantity - quantityToRemove);
            assertEquals(0.0, basket.getCost(), 0);
        } catch (PurchaseException e) {
            fail("Error al eliminar los artículos restantes del carrito");
        }
    }
}
