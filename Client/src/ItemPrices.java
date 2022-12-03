import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ItemPrices {

    public static final Map<Integer, Integer> PRICES = new HashMap<Integer, Integer>();
    public static boolean originalPrices = false;

    public static void init() {
	try {
	    String urlz = ("http://avalon-prices.surge.sh/prices.txt");
	    URL url = new URL(urlz);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	    String line;
	    int itemId;
	    int newPrice;
	    while ((line = reader.readLine()) != null) {
		originalPrices = line.contains("originalPrice: true");
		if (line.contains("originalPrice"))
		    continue;
		String[] data = line.split(" - ");
		if (data != null) {
		    itemId = Integer.parseInt(data[0]);
		    newPrice = Integer.parseInt(data[1]);
		    PRICES.put(itemId, newPrice);
		}
	    }
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
