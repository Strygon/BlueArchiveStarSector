package lnsage.bluearchivemod.campaign.ids;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.FullName.Gender;
import com.fs.starfarer.api.characters.ImportantPeopleAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import lnsage.bluearchivemod.campaign.ids.Voices;
import com.fs.starfarer.api.impl.campaign.missions.RecoverAPlanetkiller;
import com.fs.starfarer.api.impl.campaign.missions.hub.BaseMissionHub;
import com.fs.starfarer.api.impl.campaign.procgen.StarSystemGenerator;

public class People {

	// Hegemony
	public static String YUUKA = "yuuka";
	public static String NOA = "noa";

	public static String HIMARI = "himari";
	public static PersonAPI getPerson(String id) {
		return Global.getSector().getImportantPeople().getPerson(id);
	}
	
	public static void addYuuka() {
		MarketAPI market =  Global.getSector().getEconomy().getMarket("ba_planet_poincare_market");

			PersonAPI person = Global.getFactory().createPerson();

			person.setId(YUUKA);
			person.setFaction(Factions.MILLENNIUM);
			person.setGender(Gender.FEMALE);
			person.setVoice(Voices.YUUKA_VOICE);
			person.setRankId(Ranks.TREASURER);
			person.setPostId(Ranks.TREASURER);
			//person.setImportance(PersonImportance.VERY_HIGH);
			person.getName().setFirst("Yuuka");
			person.getName().setLast("Hayase");
			//person.addTag(Tags.CONTACT_TRADE);
			//person.addTag(Tags.CONTACT_MILITARY);
			person.setPortraitSprite("graphics/portraits/portrait_yuuka.png");
			//market.setAdmin(person);
			market.addPerson(person);
			market.getCommDirectory().addPerson(person);
	}

	public static void addNoa() {
		MarketAPI market =  Global.getSector().getEconomy().getMarket("ba_planet_poincare_market");

		PersonAPI person = Global.getFactory().createPerson();

		person.setId(HIMARI);
		person.setFaction(Factions.MILLENNIUM);
		person.setGender(Gender.FEMALE);
		person.setVoice(Voices.NOA_VOICE);
		person.setRankId(Ranks.SEM_SECRETARY);
		person.setPostId(Ranks.SEM_SECRETARY);
		//person.setImportance(PersonImportance.VERY_HIGH);
		person.getName().setFirst("Noa");
		person.getName().setLast("Ushio");
		//person.addTag(Tags.CONTACT_TRADE);
		//person.addTag(Tags.CONTACT_MILITARY);
		person.setPortraitSprite("graphics/portraits/portrait_noa.png");
		//market.setAdmin(person);
		market.addPerson(person);
		market.getCommDirectory().addPerson(person);
	}

	public static void addHimari() {
		MarketAPI market =  Global.getSector().getEconomy().getMarket("ba_planet_poincare_market");

		PersonAPI person = Global.getFactory().createPerson();

		person.setId(HIMARI);
		person.setFaction(Factions.MILLENNIUM);
		person.setGender(Gender.FEMALE);
		person.setVoice(Voices.HIMARI_VOICE);
		person.setRankId(Ranks.VER_PRESIDENT);
		person.setPostId(Ranks.VER_PRESIDENT);
		//person.setImportance(PersonImportance.VERY_HIGH);
		person.getName().setFirst("Himari");
		person.getName().setLast("Akeboshi");
		//person.addTag(Tags.CONTACT_TRADE);
		//person.addTag(Tags.CONTACT_MILITARY);
		person.setPortraitSprite("graphics/portraits/portrait_himari.png");
		//market.setAdmin(person);
		market.addPerson(person);
		market.getCommDirectory().addPerson(person);
	}

	/**
	 * Removes any people with this same post from the market.
	 * @param market
	 * @param postId
	 * @param person
	 */
	public static void assignPost(MarketAPI market, String postId, PersonAPI person) {
		for (PersonAPI curr : market.getPeopleCopy()) {
			if (postId.equals(curr.getPostId())) {
				market.removePerson(curr);
				market.getCommDirectory().removePerson(curr);
			}
		}
		person.setPostId(postId);
		market.addPerson(person);
		market.getCommDirectory().addPerson(person);
	}
}
