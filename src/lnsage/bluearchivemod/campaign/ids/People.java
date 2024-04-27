package lnsage.bluearchivemod.campaign.ids;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.FullName.Gender;
import com.fs.starfarer.api.characters.PersonAPI;

public class People {

	// Hegemony
	public static String BA_YUUKA = "yuuka";
	public static String BA_NOA = "noa";
	public static String BA_HIMARI = "himari";
	public static String BA_NAGISA = "nagisa";
	public static String BA_MARI = "mari";
	public static String BA_SAKURAKO = "sakurako";
	public static String BA_IORI = "iori";
	public static String BA_HINA = "hina";
	public static String BA_SHIROKO = "shiroko";
	public static String BA_NONOMI = "nonomi";
	public static String BA_AYANE = "ayane";
	public static String BA_HOSHINO = "hoshino";
	public static String BA_SERIKA = "serika";
	public static PersonAPI getPerson(String id) {
		return Global.getSector().getImportantPeople().getPerson(id);
	}
	
	public static void addYuuka() {
		MarketAPI market =  Global.getSector().getEconomy().getMarket("ba_planet_poincare_market");

			PersonAPI person = Global.getFactory().createPerson();

			person.setId(BA_YUUKA);
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

		person.setId(BA_NOA);
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

		person.setId(BA_HIMARI);
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

	public static void addNagisa() {
		MarketAPI market =  Global.getSector().getEconomy().getMarket("ba_planet_deus_market");

		PersonAPI person = Global.getFactory().createPerson();

		person.setId(BA_NAGISA);
		person.setFaction(Factions.TRINITY);
		person.setGender(Gender.FEMALE);
		person.setVoice(Voices.NAGISA_VOICE);
		person.setRankId(Ranks.TEA_PARTY_LEADER);
		person.setPostId(Ranks.TEA_PARTY_LEADER);
		//person.setImportance(PersonImportance.VERY_HIGH);
		person.getName().setFirst("Nagisa");
		person.getName().setLast("Kirifuji");
		//person.addTag(Tags.CONTACT_TRADE);
		//person.addTag(Tags.CONTACT_MILITARY);
		person.setPortraitSprite("graphics/portraits/portrait_nagisa.png");
		//market.setAdmin(person);
		market.addPerson(person);
		market.getCommDirectory().addPerson(person);
	}
	public static void addMari() {
		MarketAPI market =  Global.getSector().getEconomy().getMarket("ba_planet_spiritus_market");

		PersonAPI person = Global.getFactory().createPerson();

		person.setId(BA_MARI);
		person.setFaction(Factions.TRINITY);
		person.setGender(Gender.FEMALE);
		person.setVoice(Voices.NAGISA_VOICE);
		person.setRankId(Ranks.SISTERHOOD_MEMBER);
		person.setPostId(Ranks.SISTERHOOD_MEMBER);
		//person.setImportance(PersonImportance.VERY_HIGH);
		person.getName().setFirst("Mari");
		person.getName().setLast("Iochi");
		//person.addTag(Tags.CONTACT_TRADE);
		//person.addTag(Tags.CONTACT_MILITARY);
		person.setPortraitSprite("graphics/portraits/portrait_mari.png");
		//market.setAdmin(person);
		market.addPerson(person);
		market.getCommDirectory().addPerson(person);
	}

	public static void addSakurako() {
		MarketAPI market =  Global.getSector().getEconomy().getMarket("ba_planet_watchpointbeta_market");

		PersonAPI person = Global.getFactory().createPerson();

		person.setId(BA_SAKURAKO);
		person.setFaction(Factions.TRINITY);
		person.setGender(Gender.FEMALE);
		person.setVoice(Voices.NAGISA_VOICE);
		person.setRankId(Ranks.SISTERHOOD_LEADER);
		person.setPostId(Ranks.SISTERHOOD_LEADER);
		//person.setImportance(PersonImportance.VERY_HIGH);
		person.getName().setFirst("Sakurako");
		person.getName().setLast("Utazumi");
		//person.addTag(Tags.CONTACT_TRADE);
		//person.addTag(Tags.CONTACT_MILITARY);
		person.setPortraitSprite("graphics/portraits/portrait_sakurako.png");
		//market.setAdmin(person);
		market.addPerson(person);
		market.getCommDirectory().addPerson(person);
	}


	public static void addIori() {
		MarketAPI market =  Global.getSector().getEconomy().getMarket("ba_planet_perfidia_market");

		PersonAPI person = Global.getFactory().createPerson();

		person.setId(BA_IORI);
		person.setFaction(Factions.GEHENNA);
		person.setGender(Gender.FEMALE);
		person.setVoice(Voices.IORI_VOICE);
		person.setRankId(Ranks.PFT_CAPTAIN);
		person.setPostId(Ranks.PFT_CAPTAIN);
		//person.setImportance(PersonImportance.VERY_HIGH);
		person.getName().setFirst("Iori");
		person.getName().setLast("Shiromi");
		//person.addTag(Tags.CONTACT_TRADE);
		//person.addTag(Tags.CONTACT_MILITARY);
		person.setPortraitSprite("graphics/portraits/portrait_iori.png");
		//market.setAdmin(person);
		market.addPerson(person);
		market.getCommDirectory().addPerson(person);
	}

	public static void addHina() {
		MarketAPI market =  Global.getSector().getEconomy().getMarket("ba_planet_perfidia_market");

		PersonAPI person = Global.getFactory().createPerson();

		person.setId(BA_HINA);
		person.setFaction(Factions.GEHENNA);
		person.setGender(Gender.FEMALE);
		person.setVoice(Voices.HINA_VOICE);
		person.setRankId(Ranks.PFT_LEADER);
		person.setPostId(Ranks.PFT_LEADER);
		person.setImportance(PersonImportance.VERY_HIGH);
		person.getName().setFirst("Hina");
		person.getName().setLast("Sorasaki");
		//person.addTag(Tags.CONTACT_TRADE);
		person.addTag(Tags.CONTACT_MILITARY);
		person.setPortraitSprite("graphics/portraits/portrait_hina.png");
		//market.setAdmin(person);
		market.addPerson(person);
		market.getCommDirectory().addPerson(person);
	}

	public static void addHoshino() {
		MarketAPI market =  Global.getSector().getEconomy().getMarket("ba_planet_dendera_market");

		PersonAPI person = Global.getFactory().createPerson();

		person.setId(BA_HOSHINO);
		person.setFaction(Factions.ABYDOS);
		person.setGender(Gender.FEMALE);
		person.setVoice(Voices.HINA_VOICE);
		person.setRankId(Ranks.FTF_CHAIRWOMAN);
		person.setPostId(Ranks.FTF_CHAIRWOMAN);
		person.setImportance(PersonImportance.VERY_HIGH);
		person.getName().setFirst("Hoshino");
		person.getName().setLast("Takanashi");
		//person.addTag(Tags.CONTACT_TRADE);
		person.addTag(Tags.CONTACT_MILITARY);
		person.setPortraitSprite("graphics/portraits/portrait_hoshino.png");
		//market.setAdmin(person);
		market.addPerson(person);
		market.getCommDirectory().addPerson(person);
	}

	public static void addShiroko() {
		MarketAPI market =  Global.getSector().getEconomy().getMarket("ba_planet_dendera_market");

		PersonAPI person = Global.getFactory().createPerson();

		person.setId(BA_SHIROKO);
		person.setFaction(Factions.ABYDOS);
		person.setGender(Gender.FEMALE);
		person.setVoice(Voices.HINA_VOICE);
		person.setRankId(Ranks.FTF_CAPTAIN);
		person.setPostId(Ranks.FTF_CAPTAIN);
		person.setImportance(PersonImportance.HIGH);
		person.getName().setFirst("Shiroko");
		person.getName().setLast("Sunookami");
		//person.addTag(Tags.CONTACT_TRADE);
		person.addTag(Tags.CONTACT_UNDERWORLD);
		person.setPortraitSprite("graphics/portraits/portrait_shiroko.png");
		//market.setAdmin(person);
		market.addPerson(person);
		market.getCommDirectory().addPerson(person);
	}

	public static void addSerika() {
		MarketAPI market =  Global.getSector().getEconomy().getMarket("ba_planet_dendera_market");

		PersonAPI person = Global.getFactory().createPerson();

		person.setId(BA_SERIKA);
		person.setFaction(Factions.ABYDOS);
		person.setGender(Gender.FEMALE);
		person.setVoice(Voices.HINA_VOICE);
		person.setRankId(Ranks.FTF_SECRETARY);
		person.setPostId(Ranks.FTF_SECRETARY);
		person.setImportance(PersonImportance.HIGH);
		person.getName().setFirst("Serika");
		person.getName().setLast("Kuromi");
		//person.addTag(Tags.CONTACT_TRADE);
		person.addTag(Tags.CONTACT_TRADE);
		person.setPortraitSprite("graphics/portraits/portrait_serika.png");
		//market.setAdmin(person);
		market.addPerson(person);
		market.getCommDirectory().addPerson(person);
	}

	public static void addAyane() {
		MarketAPI market =  Global.getSector().getEconomy().getMarket("ba_planet_dendera_market");

		PersonAPI person = Global.getFactory().createPerson();

		person.setId(BA_AYANE);
		person.setFaction(Factions.ABYDOS);
		person.setGender(Gender.FEMALE);
		person.setVoice(Voices.HINA_VOICE);
		person.setRankId(Ranks.FTF_TREASURER);
		person.setPostId(Ranks.FTF_TREASURER);
		person.setImportance(PersonImportance.HIGH);
		person.getName().setFirst("Ayane");
		person.getName().setLast("Okusora");
		//person.addTag(Tags.CONTACT_TRADE);
		person.addTag(Tags.CONTACT_SCIENCE);
		person.setPortraitSprite("graphics/portraits/portrait_ayane.png");
		//market.setAdmin(person);
		market.addPerson(person);
		market.getCommDirectory().addPerson(person);
	}

	public static void addNonomi() {
		MarketAPI market =  Global.getSector().getEconomy().getMarket("ba_planet_dendera_market");

		PersonAPI person = Global.getFactory().createPerson();

		person.setId(BA_NONOMI);
		person.setFaction(Factions.ABYDOS);
		person.setGender(Gender.FEMALE);
		person.setVoice(Voices.HINA_VOICE);
		person.setRankId(Ranks.FTF_MEMBER);
		person.setPostId(Ranks.FTF_MEMBER);
		person.setImportance(PersonImportance.HIGH);
		person.getName().setFirst("Nonomi");
		person.getName().setLast("Izayoi");
		//person.addTag(Tags.CONTACT_TRADE);
		person.addTag(Tags.CONTACT_TRADE);
		person.setPortraitSprite("graphics/portraits/portrait_nonomi.png");
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
