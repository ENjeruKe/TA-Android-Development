import java.util.Date;
import java.util.HashMap;

public class Wish {
	public HashMap<Date, String> wishContent;
	public Person receiver;

	public Wish(Person receiver, HashMap<Date, String> wishContent) {
		this.receiver = receiver;
		this.wishContent = wishContent;
	}
}
