import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Random;

public class Person {
	private String name;
	private List<Date> anniversaries;
	public List<Gift> giftsInStash;
	public List<Gift> recievedGifts;

	public Person(String name) {
		this.name = name;
		this.anniversaries = new ArrayList<Date>();
		this.giftsInStash = new ArrayList<Gift>();
		this.recievedGifts = new ArrayList<Gift>();
	}

	public Gift GetGift(String receiverName) {
		Random rn = new Random();
		int i = rn.nextInt(this.giftsInStash.size());
		Gift gift = giftsInStash.get(i);
		StringBuilder sb = new StringBuilder();
		sb.append(this.name + ", today " + receiverName
				+ " has an anniversary!\n");
		sb.append("As a gift you may buy " + gift.title + " from: \n");
		for (Iterator<Place> iterator = gift.placesToGet.iterator(); iterator
				.hasNext();) {
			Place place = (Place) iterator.next();
			sb.append(place.title + " at " + place.address + "\n");
		}
		System.out.println(sb.toString());
		return this.giftsInStash.remove(i);
	}

	public void AddGiftToStash(Gift gift) {
		this.giftsInStash.add(gift);
	}

	public void AddAnniversary(Date anniversary) {
		this.anniversaries.add(anniversary);
	}

	public boolean HasAnniversary(Date date) {
		if (this.anniversaries.contains(date)) {
			return true;
		}
		return false;
	}

	public void RecieveGift(Person giver, Gift gift, String wish) {
		this.recievedGifts.add(gift);
		System.out.println(this.name + ", you have a new gift from "
				+ giver.name);
		System.out.println("Your gift is: " + gift.title);
		System.out.println(giver.name + " wishes you " + "\"" + wish + "\"");
	}

	public String GetName() {
		return this.name;
	}

	public void ThinkAbout(Person receiver, Date date, String wishText,
			GiftFactory factory) {
		factory.MakeAWish(this, receiver, date, wishText);
	}

}
