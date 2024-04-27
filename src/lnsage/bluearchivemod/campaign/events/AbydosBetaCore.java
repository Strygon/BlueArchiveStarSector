package lnsage.bluearchivemod.campaign.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.listeners.ListenerUtil;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.impl.campaign.econ.impl.PlanetaryShield;
import com.fs.starfarer.api.impl.campaign.ids.MemFlags;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.PlanetaryShieldIntel;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.PlanetaryShieldIntel.PSIStage;
import com.fs.starfarer.api.impl.campaign.procgen.SalvageEntityGenDataSpec.DropData;
import com.fs.starfarer.api.impl.campaign.procgen.themes.MiscellaneousThemeGenerator;
import com.fs.starfarer.api.impl.campaign.rulecmd.BaseCommandPlugin;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.SalvageEntity;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.special.BaseSalvageSpecial;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Misc.Token;
import org.lazywizard.lazylib.MathUtils;
// import org.magiclib;

public class AbydosBetaCore extends BaseCommandPlugin {
    @Override
    public boolean execute(String ruleId, InteractionDialogAPI dialog, List<Misc.Token> params, Map<String, MemoryAPI> memoryMap) {
        CampaignFleetAPI playerFleet = Global.getSector().getPlayerFleet();



        TextPanelAPI text = dialog.getTextPanel();

        PlanetAPI planet = getTargetPlanet();

        if (planet != null) {
            Misc.makeImportant(planet, "uoh");
            AbydosBetaCoreIntel intel = new AbydosBetaCoreIntel(planet);
            if (!intel.isDone()) {
                Global.getSector().getIntelManager().addIntel(intel, false, text);
            }
        }

        return true;
    }
    public static PlanetAPI getTargetPlanet(){
        ArrayList<String> blacklist =  new ArrayList<String>();
        ArrayList<String> systemblacklist =  new ArrayList<String>();

        blacklist.add(Tags.THEME_HIDDEN);
        blacklist.add(Tags.THEME_REMNANT);
        blacklist.add(Tags.THEME_UNSAFE);
        blacklist.add(Tags.SYSTEM_ABYSSAL);

        StarSystemAPI system = ta_Utils.getRandomSystemWithBlacklist(systemblacklist, blacklist, Global.getSector());

        while (system == null || system.getPlanets().isEmpty()) system = ta_Utils.getRandomSystemWithBlacklist(systemblacklist, blacklist, Global.getSector());
        PlanetAPI planet = system.getPlanets().get(MathUtils.getRandomNumberInRange(1, system.getPlanets().size()-1));
        while (planet == null || planet.isStar()){
            planet = system.getPlanets().get(MathUtils.getRandomNumberInRange(0, system.getPlanets().size()-1));
        }

        //fleet = MagicCampaign.createFleetBuilder()


        return planet;



    }
}
