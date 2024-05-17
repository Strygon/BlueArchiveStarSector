package lnsage.bluearchivemod.campaign;

import java.util.Random;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.AICoreOfficerPlugin;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.FullName.Gender;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Personalities;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.impl.campaign.ids.Skills;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lnsage.bluearchivemod.campaign.ids.BACommodities;

public class AroPlaCorePluginImpl implements AICoreOfficerPlugin {

    /**
     * Extra points added to deployment recovery cost for computing the effect of the "Automated Ships" skill.
     */
//	public static int OMEGA_POINTS = 140;
//	public static int ALPHA_POINTS = 70;
//	public static int BETA_POINTS = 50;
//	public static int GAMMA_POINTS = 30;
    public static int OMEGA_POINTS = 0;
    public static int ALPHA_POINTS = 0;
    public static int BETA_POINTS = 0;
    public static int GAMMA_POINTS = 0;

    /**
     * Multiplier to deployment recovery cost for computing the effect of the "Automated Ships" skill.
     */
    public static float OMEGA_MULT = 5;
    public static float ALPHA_MULT = 4;
    public static float BETA_MULT = 3;
    public static float GAMMA_MULT = 2;
    public static float ARONA_MULT = 0;
    public static float PLANA_MULT = 0;

    static {
//		if (BaseSkillEffectDescription.USE_RECOVERY_COST) {
//			OMEGA_POINTS = 20;
//			ALPHA_POINTS = 10;
//			BETA_POINTS = 7;
//			GAMMA_POINTS = 5;
//		}
    }


    public PersonAPI createPerson(String aiCoreId, String factionId, Random random) {
        if (random == null) random = new Random();

        PersonAPI person = Global.getFactory().createPerson();
        person.setFaction(factionId);
        person.setAICoreId(aiCoreId);

        CommoditySpecAPI spec = Global.getSettings().getCommoditySpec(aiCoreId);
        boolean arona = BACommodities.ARONA_CORE.equals(aiCoreId);
        boolean plana = BACommodities.PLANA_CORE.equals(aiCoreId);

        person.getStats().setSkipRefresh(true);


        int points = 0;
        float mult = 1f;
        if (arona) { // assume it's not going to be integrated, no reason to do it - same as assuming it's always integrated
            person.setName(new FullName("Arona", "", Gender.ANY));
            person.setPortraitSprite("graphics/portraits/portrait_arona.png");
            person.getStats().setLevel(9);
            person.getStats().setSkillLevel(Skills.HELMSMANSHIP, 2);
            person.getStats().setSkillLevel(Skills.TARGET_ANALYSIS, 2);
            person.getStats().setSkillLevel(Skills.IMPACT_MITIGATION, 2);
            //person.getStats().setSkillLevel(Skills.SHIELD_MODULATION, 2);
            person.getStats().setSkillLevel(Skills.FIELD_MODULATION, 2);
            //person.getStats().setSkillLevel(Skills.SYSTEMS_EXPERTISE, 2);
            person.getStats().setSkillLevel(Skills.GUNNERY_IMPLANTS, 2);
            //person.getStats().setSkillLevel(Skills.RELIABILITY_ENGINEERING, 2);
            person.getStats().setSkillLevel(Skills.COMBAT_ENDURANCE, 2);
            person.getStats().setSkillLevel(Skills.DAMAGE_CONTROL, 2);
            person.getStats().setSkillLevel(Skills.POINT_DEFENSE, 2);
            person.getStats().setSkillLevel(Skills.ENERGY_WEAPON_MASTERY, 2);
            person.getStats().setSkillLevel(Skills.OMEGA_ECM, 2);
            points = OMEGA_POINTS;
            mult = ARONA_MULT;
        } else if (plana) {
            person.setName(new FullName("Plana", "", Gender.ANY));
            person.setPortraitSprite("graphics/portraits/portrait_plana.png");
            person.getStats().setLevel(9);
            person.getStats().setSkillLevel(Skills.HELMSMANSHIP, 2);
            person.getStats().setSkillLevel(Skills.TARGET_ANALYSIS, 2);
            person.getStats().setSkillLevel(Skills.IMPACT_MITIGATION, 2);
            //person.getStats().setSkillLevel(Skills.SHIELD_MODULATION, 2);
            person.getStats().setSkillLevel(Skills.FIELD_MODULATION, 2);
            //person.getStats().setSkillLevel(Skills.SYSTEMS_EXPERTISE, 2);
            person.getStats().setSkillLevel(Skills.GUNNERY_IMPLANTS, 2);
            //person.getStats().setSkillLevel(Skills.RELIABILITY_ENGINEERING, 2);
            person.getStats().setSkillLevel(Skills.COMBAT_ENDURANCE, 2);
            person.getStats().setSkillLevel(Skills.DAMAGE_CONTROL, 2);
            person.getStats().setSkillLevel(Skills.POINT_DEFENSE, 2);
            person.getStats().setSkillLevel(Skills.ENERGY_WEAPON_MASTERY, 2);
            person.getStats().setSkillLevel(Skills.OMEGA_ECM, 2);
            points = ALPHA_POINTS;
            mult = PLANA_MULT;
        }

        if (points != 0) {
            //person.getMemoryWithoutUpdate().set(AUTOMATED_POINTS_VALUE, points);
        }
        //person.getMemoryWithoutUpdate().set(AUTOMATED_POINTS_MULT, mult);

        person.setPersonality(Personalities.RECKLESS);
        person.setRankId(Ranks.SPACE_CAPTAIN);
        person.setPostId(null);

        person.getStats().setSkipRefresh(false);

        return person;
    }

    @Override
    public void createPersonalitySection(PersonAPI person, TooltipMakerAPI tooltip) {

    }

}




