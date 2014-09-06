package wolfRam;
import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;


/*
 * WRONGN WAY -- > TURN BACK!
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * YOU ARE NOT PERMITTED TO CHANGE THIS CODE. CHANGES DETECTED IN THIS CODE WILL LEAD 
 * TO A LOSS OF MARKS IN YOUR ASSIGNMENT
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

public class WolframAlpha {
	private String APPID = "";

	public WolframAlpha(String appid){
		this.APPID = appid;
	}

	public String query(String question) {
		WAEngine engine = new WAEngine();
		engine.setAppID(this.APPID);
		engine.addFormat("plaintext");
		WAQuery query = engine.createQuery();
		query.setInput(question);
		try {
			System.out.println("Asking Wolfram Alpha: " + question+"\n"+
					"Please wait for response. This may take 5 -10 seconds.");
			WAQueryResult queryResult = engine.performQuery(query);

			if (queryResult.isError()) {
				return "<noresults></noresults>";
			} else if (!queryResult.isSuccess()) {
				return "<noresults></noresults>";
			} else {
				StringBuffer sb = new StringBuffer("");
				for (WAPod pod : queryResult.getPods()) {
					sb.append("<section><title>" + pod.getTitle() + "</title><sectioncontents>");
					for (WASubpod subpod : pod.getSubpods()) {
						for (Object element : subpod.getContents()) {
							if (element instanceof WAPlainText) {
								sb.append(((WAPlainText) element).getText());
							}
						}
					}
					sb.append("</sectioncontents></section>");
				}
				return sb.toString();
			}
		} catch (WAException e) {
			return "<noresults></noresults>";
		}
	}
}
