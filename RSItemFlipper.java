package RSItemFlipper;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jsoup.*;
import org.jsoup.nodes.Document;


public class RSItemFlipper {
	
	
	public int getPrice(int id) throws IOException{
		if(id == 1060)
			id++;
		Document doc = Jsoup.connect("http://services.runescape.com/m=itemdb_oldschool/Cosmic_rune/viewitem?obj=" + id)
				.data("query", "Java")
		        .userAgent("Mozilla")
		        .cookie("auth", "token")
		        .timeout(3000)
		        .post();
		String price = doc.text();
		int o = price.indexOf("Current Guide Price ");
		price = price.substring(o+20);
		int j = price.indexOf(" ");
		price = price.substring(0, j);
		double pricedub = 0.0;
		int intprice = 0;
		if(price.indexOf(",") > -1){
			price = price.substring(0, price.indexOf(",")) + price.substring(price.indexOf(",") + 1, price.length());
		}
		if(price.indexOf("k") > -1){
			
			price = price.substring(0,price.indexOf("k"));
			pricedub = Double.parseDouble(price);
			pricedub = pricedub * 1000;
			intprice = (int)pricedub;
			return intprice;
		}
		if(price.length() < 1){
			return 0;
		}
		intprice = Integer.parseInt(price);
		return intprice;
		
	}
	
	public int getItemCode(String name) throws IOException{
		Document doc = Jsoup.connect("http://rspscodes.synthasite.com/item-codes.php")
				.data("query", "Java")
		        .userAgent("Mozilla")
		        .cookie("auth", "token")
		        .timeout(1000)
		        .post();
		String text = doc.text();
		if(text.indexOf(name)-11 < 0){
			return 4699;
		}
		text = text.substring(text.indexOf(name)-11);
		int j = text.indexOf(" Name");
		text = text.substring(0,  j);
		while(text.indexOf(" ") != -1){
			text = text.substring(1);
		}
		int k = Integer.parseInt(text);
		return k;
	}
	
	public String test() throws IOException{
		int urlID = 1;
		
		String name = "";
		String shop = "";
		String priceStr = "";
		String stockStr = "";
		String text = "";
		int m = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int o = 0;
		int buyPrice = 0;
		int stock = 0;
		Item nrd = null;
		while(urlID < 299){
			while(urlID == 37 || urlID == 62 || urlID == 65)
				urlID++;
			try{
				Document doc = Jsoup.connect("http://2007rshelp.com/shops.php?id=" + urlID)
					.data("query", "Java")
			        .userAgent("Mozilla")
			        .cookie("auth", "token") 
			        .timeout(1000)
			        .post();
				text = doc.text();
				m = text.indexOf("- Runescape Shop");
				shop = text.substring(0, m-1);
				k = text.indexOf("Item Price Default Stock");
				text = text.substring(k+25);
				
				j = text.indexOf("gp");
				while (j > -1){
				if(j < 0){
					
				}
				else {
					String temp = text.substring(0,j);
					o = temp.lastIndexOf(" ");
					name = text.substring(0, o);
					
					text = text.substring(o+1);
					l = text.substring(0, j).indexOf(",");
					j = text.indexOf("gp");
					if(l > -1){
						priceStr = text.substring(0,l) + text.substring(l+1,j);
						buyPrice = Integer.parseInt(priceStr);
					}
					else
						buyPrice = Integer.parseInt(text.substring(0,j));
					
					l = text.indexOf(" ");
					text = text.substring(l+1);
					stockStr = text.substring(0, text.indexOf(" "));
					//System.out.println(stockStr);
					stock = Integer.parseInt(stockStr);
					//System.out.println(stock);
					text = text.substring(text.indexOf(" ") + 1);
					
				}
				nrd = new Item(name,stock,buyPrice,shop);
				System.out.print(nrd.toString());
				j = text.indexOf("gp");
				}
			} catch(SocketTimeoutException e) {
				System.out.println("Couldn't get data for " + name + " (SocketTimeoutException)");
			}
			catch(IndexOutOfBoundsException e){
				//System.out.println("Couldn't get data for " + name + " (IndexOutOfBoundsException)");
			}
		 
		urlID++;
		}
		return text;
	}
}

