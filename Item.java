package RSItemFlipper;

import java.io.IOException;

public class Item {
	String name = "";
	int buyPrice = 0;
	int gePrice = 0;
	String location = "";
	String shop = "";
	int difference = 0;
	int id = 0;
	int stock = 0;
	public Item(String name, int stock, int buyPrice, String shop) throws IOException{
		this.name = name;
		this.buyPrice = buyPrice;
		this.shop = shop;
		this.stock = stock;
		RSItemFlipper rsif = new RSItemFlipper();
		this.id = rsif.getItemCode(name);
		this.gePrice = rsif.getPrice(id);
		this.difference = this.gePrice - this.buyPrice;
	}
	
	public String getname(){
		return name;
	}
	public int getbuyPrice(){
		return buyPrice;
	}
	public int getgePrice(){
		return gePrice;
	}
	public String getlocation(){
		return location;
	}
	public String getshop(){
		return shop;
	}
	public int getdiffernce(){
		return this.gePrice - this.buyPrice;
	}
	public int getID(){
		return id;
	}
	public int getstock(){
		return stock;
	}
	
	
	
	public void setname(String name){
		this.name = name;
	}
	public void setbuyPrice(int buyPrice){
		this.buyPrice = buyPrice;
		this.difference = this.buyPrice - this.gePrice;
	}
	public void setgePrice(int gePrice){
		this.gePrice = gePrice;
		this.difference = this.buyPrice - this.gePrice;
	}
	public void setlocation(String location){
		this.location = location;
	}
	public void setshop(String shop){
		this.shop = shop;
	}
	public void setID(int id){
		this.id = id;
	}
	public void setstock(int stock){
		this.stock = stock;
	}
	public String toString(){
		if(this.name == "Shield right half")
			return "";
		if(this.difference > 0 && this.stock > 0){
			int profit = this.stock * this.difference;
			return "You can make a profit of " + this.getdiffernce() + " buying " + this.name + " at " + this.shop + ". The stock of this item is " + this.stock + " Total: " + profit + "\n";
		}
		return "";
	}
}
