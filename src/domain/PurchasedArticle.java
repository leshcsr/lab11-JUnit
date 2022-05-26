package domain;


public class PurchasedArticle {
	private Article art;
	private int quantity;
	private double price;
	private boolean isOutlet;
	
	public PurchasedArticle(Article art, int quantity) {
		super();
		this.art = art;
		this.quantity = quantity;
		this.price = art.getPrice();
		this.isOutlet = art.isOutlet();
	}

	public Article getArt() {
		return art;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int cantidad) {
		this.quantity = cantidad;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double precio) {
		this.price = precio;
	}
	public boolean isOutlet() {
		return isOutlet;
	}
	public void setOutlet(boolean isOutlet) {
		this.isOutlet = isOutlet;
	}

}
