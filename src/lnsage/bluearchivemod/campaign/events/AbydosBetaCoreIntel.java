package lnsage.bluearchivemod.campaign.events;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.fleets.AutoDespawnScript;
import com.fs.starfarer.api.impl.campaign.fleets.FleetFactory;
import com.fs.starfarer.api.impl.campaign.fleets.FleetFactoryV3;
import com.fs.starfarer.api.impl.campaign.fleets.FleetParamsV3;
import com.fs.starfarer.api.impl.campaign.ids.MemFlags;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.impl.campaign.intel.raid.AssembleStage;
import com.fs.starfarer.api.impl.campaign.rulecmd.AddRemoveCommodity;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.special.TransmitterTrapSpecial;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Misc.Token;
import lnsage.bluearchivemod.campaign.ids.Factions;
import org.lwjgl.util.vector.Vector2f;

import static lnsage.bluearchivemod.campaign.events.AbydosBetaCoreBarEvent.getTargetPlanet;


public class AbydosBetaCoreIntel extends BaseIntelPlugin {

    protected PlanetAPI planet;
    public AbydosBetaCoreIntel(PlanetAPI planet){
        this.planet = planet;

    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        info.addPara("The Abydos Foreclosure Task Force has lost their Beta Core to an alleged Tri-Tachyon fleet.", 5f);
        info.addPara("Go to the " + planet.getStarSystem().getName() + " and retrieve the Beta Core.", 5f);
    }

    @Override
    public String getIcon() {
        return Global.getSettings().getSpriteName("intel", "fleet_log");
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        info.addPara("Go to the " + planet.getStarSystem().getName() + " and retrieve the Beta Core.", 5f);
    }

    @Override
    public boolean callEvent(String ruleId, InteractionDialogAPI dialog, List<Misc.Token> params, Map<String, MemoryAPI> memoryMap) {

        return true;
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(Tags.INTEL_IMPORTANT);
        tags.add(Tags.INTEL_ACCEPTED);
        return tags;

    }
    public String getName() {
        if (isEnded() || isEnding()) {
            return "Abydos Beta Core - Returned";
        }
        return "Abydos Beta Core";
    }

    @Override
    public SectorEntityToken getMapLocation(SectorMapAPI map) {
        return planet;
    }

    public static void spawnPiratesToInvestigate(SectorEntityToken locToken, float fp) {

        FleetFactory.PatrolType type;
        if (fp < AssembleStage.FP_SMALL * 1.5f) {
            type = FleetFactory.PatrolType.FAST;
        } else if (fp < AssembleStage.FP_MEDIUM * 1.5f) {
            type = FleetFactory.PatrolType.COMBAT;
        } else {
            type = FleetFactory.PatrolType.HEAVY;
        }

        FleetParamsV3 params = new FleetParamsV3(
                null,
                Global.getSector().getPlayerFleet().getLocationInHyperspace(),
                Factions.PIRATES,
                null,
                type.getFleetType(),
                fp, // combatPts
                0f, // freighterPts
                fp * 0.1f, // tankerPts
                0f, // transportPts
                0f, // linerPts
                0f, // utilityPts
                0f // qualityMod
        );

        CampaignFleetAPI fleet = FleetFactoryV3.createFleet(params);
        if (fleet.isEmpty()) fleet = null;

        if (fleet != null) {
            fleet.addScript(new AutoDespawnScript(fleet));

            fleet.setTransponderOn(false);
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_PIRATE, true);

            float range = 2000f + (float) Math.random() * 2000f;
            Vector2f loc = Misc.getPointAtRadius(locToken.getLocation(), range);

            locToken.getContainingLocation().addEntity(fleet);
            fleet.setLocation(loc.x, loc.y);

            TransmitterTrapSpecial.makeFleetInterceptPlayer(fleet, false, true, 30f);

            fleet.addAssignment(FleetAssignment.PATROL_SYSTEM, locToken, 1000f);
            //fleet.addDropRandom("blueprints_guaranteed", 1);
        }

    }
}



