import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.swing.text.DateFormatter;

public class GiftFactory {
	// <Giver, <Reciever, <AnniversaryDate, Wish>>>
	private HashMap<Person, List<Wish>> wishesList;
	private List<Date> holidays;
	private List<Gift> suggestions;
	private DateFormat formatter = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

	public GiftFactory() {
		this.wishesList = new HashMap<Person, List<Wish>>();
		this.holidays = new ArrayList<Date>();
		this.suggestions = new ArrayList<Gift>();
		this.GenerateGifts();
	}

	private void GenerateGifts() {
		final Place technopolis = new Place("Technopolis", "Iliantsi District");
		final Place carsBg = new Place("cars.bg", "The Internet");
		final Place petsBg = new Place("pets.bg", "The Internet");
		final Place animalsBg = new Place("animals.bg", "The Internet");
		final Place technomarket = new Place("Technomarket", "55, Some Address");
		final Place floristsInc = new Place("Florists Inc.", "34, Some Address");
		final Place theMall = new Place("The Local Mall", "Istambulsko Shose");

		this.suggestions.add(new Gift("Car", new ArrayList<Place>() {
			{
				add(carsBg);
			}
		}));

		this.suggestions.add(new Gift("Cat", new ArrayList<Place>() {
			{
				add(petsBg);
				add(animalsBg);
			}
		}));

		this.suggestions.add(new Gift("Dog", new ArrayList<Place>() {
			{
				add(petsBg);
				add(animalsBg);
			}
		}));

		this.suggestions.add(new Gift("Smartphone", new ArrayList<Place>() {
			{
				add(technopolis);
				add(technomarket);
				add(theMall);
			}
		}));

		this.suggestions.add(new Gift("Flowers", new ArrayList<Place>() {
			{
				add(floristsInc);
				add(theMall);
			}
		}));
	}

	private Gift PrepareRandomGift() {
		Random rn = new Random();
		int i = rn.nextInt(suggestions.size());
		Gift gift = suggestions.get(i);
		return gift;
	}

	public void AddHoliday(Date date) {
		this.holidays.add(date);
	}

	public void MakeAWish(Person giver, Person reciever, Date date,
			String wishText) {
		HashMap<Date, String> wishProps = new HashMap<Date, String>();

		wishProps.put(date, wishText);
		Wish wish = new Wish(reciever, wishProps);

		giver.giftsInStash.add(this.PrepareRandomGift());
		if (this.wishesList.containsKey(giver)) {
			List<Wish> l = this.wishesList.get(giver);
			l.add(wish);
		} else {
			List<Wish> giftProps = new ArrayList<Wish>();
			giftProps.add(wish);
			this.wishesList.put(giver, giftProps);
		}
	}

	public void CheckForAnniversaries(Date date) {
		HashMap<Date, String> wishContent = new HashMap<Date, String>();
		List<Wish> giftProps = new ArrayList<Wish>();
		boolean anniversaryFound = false;
		
		Date dateWithZeroTime = null;
		try {
			dateWithZeroTime =formatter.parse(formatter.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Iterator<Person> giversIterator = wishesList.keySet().iterator();
		while (giversIterator.hasNext()) {// for every Person-giver
			Person giver = giversIterator.next();
			giftProps = wishesList.get(giver); // List<Wish>

			Iterator<Wish> wishesIterator = giftProps.iterator();
			while (wishesIterator.hasNext()) { // for every Person-receiver
				Wish wish = wishesIterator.next();
				Person receiver = wish.receiver;
				
				if (receiver.HasAnniversary(dateWithZeroTime)) { // If receiver has
														// anniversary
					wishContent = wish.wishContent; // <Date, String>

					if (wishContent.containsKey(dateWithZeroTime)) { // If there is a wish
															// for that date
						String wishText = wishContent.get(dateWithZeroTime);
						Gift gift = giver.GetGift(receiver.GetName());
						receiver.RecieveGift(giver, gift, wishText);
						anniversaryFound = true;
						wishContent.remove(dateWithZeroTime); // Gift given, clean wish
					}
				}
			}
		}
		
		if (!anniversaryFound) {
			System.out.println("No anniversaries...");
		}
	}

	public Gift CheckForHolidays(Date date) {
		return null;
	}

}
