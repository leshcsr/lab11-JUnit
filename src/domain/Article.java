package domain;

public class Article {
	private String id;
	private String description;
	private double price;
	private boolean isOutlet;
	private int stock;
	
	public Article(String id, String descripcion, double price, boolean isOutlet, int stock) {
		this.id = id;
		this.description = descripcion;
		this.price = price;
		this.isOutlet = isOutlet;
		this.stock= stock;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean isOutlet() {
		return isOutlet;
	}
	public void setOutlet(boolean isOutlet) {
		this.isOutlet = isOutlet;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}

 

