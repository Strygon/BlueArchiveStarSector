package lnsage.bluearchivemod.campaign.events;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.fleets.FleetFactoryV3;
import com.fs.starfarer.api.impl.campaign.fleets.FleetParamsV3;
import com.fs.starfarer.api.impl.campaign.ids.*;
import com.fs.starfarer.api.impl.campaign.rulecmd.BaseCommandPlugin;
import com.fs.starfarer.api.util.Misc;
import org.lwjgl.util.vector.Vector2f;

import java.util.List;
import java.util.Map;

public class exampleFleetSpawner extends BaseCommandPlugin {
    @Override
    public boolean execute(String ruleId, InteractionDialogAPI dialog, List<Misc.Token> params, Map<String, MemoryAPI> memoryMap) {
        CampaignFleetAPI playerfleet = Global.getSector().getPlayerFleet();
        dialog.getTextPanel().addPara("Example fleet just spawned.");

        FleetParamsV3 testfleetparams = new FleetParamsV3(
                null, // add a source(has to be from a MarketAPI)
                Misc.findNearestJumpPoint(playerfleet).getLocation(), // loc in hyper; don't need if have market
                Factions.PIRATES,
                2f, // quality override route.getQualityOverride()
                FleetTypes.PATROL_SMALL,
                100f, // combatPts(minimal so special ships can be added)(1000f otherwise)
                0f, // freighterPts
                0f, // tankerPts
                0f, // transportPts
                0f, // linerPts
                0f, // utilityPts
                2f// qualityMod
        );

        testfleetparams.maxOfficersToAdd = 10;
        testfleetparams.factionId = Factions.INDEPENDENT;
        testfleetparams.averageSMods = 100;
        CampaignFleetAPI testfleetcampaign = FleetFactoryV3.createFleet(testfleetparams);
        testfleetcampaign.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_NO_REP_IMPACT, true);
        testfleetcampaign.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_MAKE_HOSTILE, true);
        testfleetcampaign.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_MAKE_HOLD_VS_STRONGER, true);
        testfleetcampaign.getMemoryWithoutUpdate().set(MemFlags.FLEET_FIGHT_TO_THE_LAST, true);
        testfleetcampaign.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_MAKE_PREVENT_DISENGAGE, true);


        FleetMemberAPI flag = testfleetcampaign.getFleetData().addFleetMember("doom_Strike");
        flag.setFlagship(true);
        flag.getRepairTracker().setCR(flag.getRepairTracker().getMaxCR());

        PersonAPI person1 = Global.getSector().getFaction(Factions.INDEPENDENT).createRandomPerson(FullName.Gender.ANY);
        person1.setPostId(Ranks.POST_OFFICER);
        person1.setRankId(Ranks.CLONE);
        person1.setPersonality(Personalities.AGGRESSIVE);
        person1.getStats().setSkillLevel(Skills.COMBAT_ENDURANCE, 2);
        person1.getStats().setSkillLevel(Skills.MISSILE_SPECIALIZATION, 2);
        person1.getStats().setSkillLevel(Skills.TARGET_ANALYSIS, 1);
        person1.getStats().setSkillLevel(Skills.FIELD_MODULATION, 1);
        person1.getStats().setSkillLevel(Skills.IMPACT_MITIGATION, 2);
        person1.getStats().setSkillLevel(Skills.HELMSMANSHIP, 2);
        person1.getStats().setSkillLevel(Skills.ORDNANCE_EXPERTISE, 1);

        person1.getStats().setSkillLevel(Skills.PHASE_CORPS, 1);
        person1.getStats().setLevel(7);

        flag.setCaptain(person1);
        testfleetcampaign.setCommander(person1);
        testfleetcampaign.getFleetData().sort();
        testfleetcampaign.forceSync();

        playerfleet.getContainingLocation().addEntity(testfleetcampaign);
        testfleetcampaign.setAI(Global.getFactory().createFleetAI(testfleetcampaign));
        testfleetcampaign.isNoAutoDespawn();
        testfleetcampaign.setNoFactionInName(true);
        testfleetcampaign.getAI().addAssignment(FleetAssignment.INTERCEPT, playerfleet,  90f, null);


        return true;
    }
}
