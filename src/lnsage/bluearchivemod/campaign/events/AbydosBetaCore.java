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
import com.fs.starfarer.api.impl.campaign.procgen.SalvageEntityGenDataSpec;
import com.fs.starfarer.api.impl.campaign.procgen.SalvageEntityGenDataSpec.DropData;
import com.fs.starfarer.api.impl.campaign.procgen.themes.MiscellaneousThemeGenerator;
import com.fs.starfarer.api.impl.campaign.rulecmd.BaseCommandPlugin;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.SalvageEntity;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.special.BaseSalvageSpecial;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Misc.Token;
import org.lazywizard.lazylib.MathUtils;
// import org.magiclib; I haven't got the faintest fucking idea why IntelliJ refuses to acknowledge the existence of MagicLib which sucks because I wanted to use their fleetmaker stuff
// Guess I gotta relegate myself to using more conventional methods...

public class AbydosBetaCore extends BaseCommandPlugin {

    protected CampaignFleetAPI playerFleet;
    protected SectorEntityToken entity;
    protected PlanetAPI planet;
    protected FactionAPI playerFaction;
    protected FactionAPI entityFaction;
    protected TextPanelAPI text;
    protected OptionPanelAPI options;
    protected CargoAPI playerCargo;
    protected MemoryAPI memory;
    protected MarketAPI market;
    protected InteractionDialogAPI dialog;
    protected Map<String, MemoryAPI> memoryMap;
    protected FactionAPI faction;


    @Override
    public boolean execute(String ruleId, InteractionDialogAPI dialog, List<Misc.Token> params, Map<String, MemoryAPI> memoryMap) {
        CampaignFleetAPI playerFleet = Global.getSector().getPlayerFleet();
        this.dialog = dialog;
        this.memoryMap = memoryMap;




        String command = params.get(0).getString(memoryMap);
        if (command == null) return false;

        if (command.equals("genLoot")) {
            genLoot();
        }


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
        while (planet == null || planet.isStar()){ // TODO: This code will cause a crash if the system contains no planets. Implement safeguards against it to check another system if there are no planets in the current one.
            planet = system.getPlanets().get(MathUtils.getRandomNumberInRange(0, system.getPlanets().size()-1));
        }

        //fleet = MagicCampaign.createFleetBuilder()


        return planet;



    }

    protected void genLoot() {

        OptionPanelAPI options = dialog.getOptionPanel();
        TextPanelAPI text = dialog.getTextPanel();

        MemoryAPI memory = planet.getMemoryWithoutUpdate();
        long seed = memory.getLong(MemFlags.SALVAGE_SEED);
        Random random = Misc.getRandom(seed, 100);

        SalvageEntityGenDataSpec.DropData d = new SalvageEntityGenDataSpec.DropData();
        d.chances = 5;
        d.group = "blueprints";
        planet.addDropRandom(d);

        d = new SalvageEntityGenDataSpec.DropData();
        d.chances = 1;
        d.group = "rare_tech";
        planet.addDropRandom(d);

        CargoAPI salvage = SalvageEntity.generateSalvage(random, 1f, 1f, 1f, 1f, planet.getDropValue(), planet.getDropRandom());
        CargoAPI extra = BaseSalvageSpecial.getCombinedExtraSalvage(memoryMap);
        salvage.addAll(extra);
        BaseSalvageSpecial.clearExtraSalvage(memoryMap);
        if (!extra.isEmpty()) {
            ListenerUtil.reportExtraSalvageShown(planet);
        }
        salvage.addSpecial(new SpecialItemData("industry_bp", "planetaryshield"), 1);
        salvage.sort();

        dialog.getVisualPanel().showLoot("Salvaged", salvage, false, true, true, new CoreInteractionListener() {
            public void coreUIDismissed() {
                dialog.dismiss();
                dialog.hideTextPanel();
                dialog.hideVisualPanel();

                AbydosBetaCoreIntel intel = (AbydosBetaCoreIntel) Global.getSector().getIntelManager().getFirstIntel(AbydosBetaCoreIntel.class);
                if (intel != null) {
                    Global.getSector().addScript(intel);
                    intel.endAfterDelay();
                    //intel.sendUpdate(PSIStage.DONE, textPanel);
                    //intel.sendUpdateIfPlayerHasIntel(PSIStage.DONE, false);
                }
                long xp = AbydosBetaCoreIntel.FINISHED_XP;
                Global.getSector().getPlayerPerson().getStats().addXP(xp);
            }
        });
        options.clearOptions();
        dialog.setPromptText("");


//		if (keptPromise) {
//			if (random.nextFloat() > 0.5f) {
//				SectorEntityToken loc = planet.getContainingLocation().createToken(planet.getLocation());
//				spawnPiratesToInvestigate(loc, 50f + random.nextFloat() * 50f);
//				if (random.nextFloat() > 0.5f) {
//					spawnPiratesToInvestigate(loc, 50f + random.nextFloat() * 50f);
//				}
//			}
//		}
    }
}
