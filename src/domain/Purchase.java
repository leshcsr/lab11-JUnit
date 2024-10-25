package domain;

import java.util.Date;
import java.util.HashMap;


import exceptions.PurchaseException;

/**
  * Represents the shopping basket with its purchased items and their cost.
  * Once the purchase is made on a specific date (buy), it is not allowed
  * add or remove products from the basket.
 * @author IS2
 *
 */
public class Purchase {
	private Date purchaseDate;
	private double cost;
	private HashMap<Article, PurchasedArticle> basket = new HashMap<Article, PurchasedArticle>();

	/**
	 * Get purchase date information
	 * 
	 * @return date on which the purchase was made
	 */
	public Date getDate() {
		return purchaseDate;
	}

	/**
	 * Indicates the current cost of the products that are in the basket
	 * 
	 * @return he current cost of the basket
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * The purchase will be made on the date indicated
	 * 
	 * @param d date on which the purchase is made
	 */
	public void buy(Date d) {
		this.purchaseDate = d;
	}

	/**
	 * Add an article to the basket.
	 * 
	 * @param art a new article to buy.
	 * @param q articles units added
	 * @return the total cost of the basket.
	 * @throws PurchaseException 
	 */
	public double addBasket(Article art, int q)   {
		if (purchaseDate != null) {
			throw new RuntimeException("The purchase is closed. No articles can be added");
		}

		if (art == null) {
	        throw new IllegalArgumentException("Article cannot be null");
	    }
		
		int stock = art.getStock();
		if (stock < q) {
			throw new RuntimeException("There is not enough stock");
		}
		art.setStock(stock - q);

		cost = cost + art.getPrice() * q;

		PurchasedArticle part = basket.get(art);
		if (part == null) {
			basket.put(art, new PurchasedArticle(art, q));
		} else {
			part.setQuantity(part.getQuantity() + q);
		}

		return cost;
	}

	/**
	 * Remove a number of units of an article from the basket that is not paid yet.
	 * @param art the removed article from the basket
	 * @param q   the removed units 
	 * @return    the updated cost of the basket
	 */
	public double removeBasket(Article art, int q) throws PurchaseException {
		if (purchaseDate != null) {
			throw new RuntimeException("The purchase is closed. No articles can be removed");
		}
		int quantity = 0;

		PurchasedArticle purchasedArticle = basket.get(art);
		if (purchasedArticle == null) {
			throw new PurchaseException("This product was not in the basket.");
		} else {
			quantity = purchasedArticle.getQuantity();
			if (quantity < q) {
			throw new PurchaseException("You don't have so many items in the basket");
			}
			
			int stock = art.getStock();
			art.setStock(stock + q);

			double amount = quantity * purchasedArticle.getPrice();
			cost = cost - amount;

			if (quantity > q) {
				purchasedArticle.setQuantity(quantity - q);
			} else {
				basket.remove(art);
			}
		}
		return cost;
	}

}
