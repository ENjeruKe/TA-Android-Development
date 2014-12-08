import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class EntryPoint {
	public static DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
	public static Date GetDate(String dateString) throws ParseException{
		Date date = new Date();
		
		if (dateString != null) {
			date = dateFormat.parse(dateString);
		}
		
		return date;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GiftFactory giftFactory = new GiftFactory();
		
		Person ivancho = new Person("Ivancho");
		Person mariika = new Person("Mariika");

		Date mariikasBirthday = null;
		Date mariikasNameDay = null;
		Date now = null;
		try {
			mariikasBirthday = GetDate("January 2, 2014");
			now = dateFormat.parse(dateFormat.format(GetDate(null)));
			//mariikasNameDay = GetDate("September 15, 2014");
			mariikasNameDay = now;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mariika.AddAnniversary(mariikasBirthday);
		mariika.AddAnniversary(mariikasNameDay);
				
		ivancho.ThinkAbout(mariika, mariikasNameDay, "Happy name day!", giftFactory);
		ivancho.ThinkAbout(mariika, mariikasBirthday, "Happy bithday, Mimi!", giftFactory);
		
		Date today = mariikasBirthday;
		System.out.println(today);
		giftFactory.CheckForAnniversaries(today);
		
		System.out.println();
		today = now;
		System.out.println(today);
		giftFactory.CheckForAnniversaries(today);
		
		//Let's check again
		System.out.println();
		today = now;
		System.out.println(today);
		giftFactory.CheckForAnniversaries(today);
	}

}
