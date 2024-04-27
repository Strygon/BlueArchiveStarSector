package lnsage.bluearchivemod.campaign.events;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.impl.campaign.rulecmd.AddRemoveCommodity;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Misc.Token;


public class AbydosBetaCoreIntel extends BaseIntelPlugin {

    public static enum AbydosBetaCoreStage {
        ACQUIRE_BETA_CORE,
        RETURN_CORE,
        DONE,
        ;
    }

    public static int FINISHED_XP = 20000;
    public static int PAY_PILOT_XP = 5000;

    protected PlanetAPI planet;
    protected AbydosBetaCoreBarEvent event;

    protected AbydosBetaCoreStage stage;
    protected int pilotCredits;

    public AbydosBetaCoreIntel(PlanetAPI planet) {
        this.planet = planet;
        this.event = event;

        // PersonAPI pilot = event.getPilot();

        pilotCredits = 30000 + 1000 * Misc.random.nextInt(10);



        //Misc.makeImportant(planet, "saci");
        //cache.getMemoryWithoutUpdate().set("$saic_eventRef", this);
        //Global.getSector().addScript(this);

        stage = AbydosBetaCoreStage.ACQUIRE_BETA_CORE;
    }

    @Override
    protected void notifyEnded() {
        super.notifyEnded();
        Global.getSector().removeScript(this);

        PersonAPI pilot = event.getPilot();
        MarketAPI market = event.getPilotMarket();
        market.removePerson(pilot);
        market.getCommDirectory().removePerson(pilot);
        Misc.makeUnimportant(planet, "abycore");
    }



    @Override
    public boolean callEvent(String ruleId, InteractionDialogAPI dialog,
                             List<Token> params, Map<String, MemoryAPI> memoryMap) {
        String action = params.get(0).getString(memoryMap);

        CampaignFleetAPI playerFleet = Global.getSector().getPlayerFleet();
        CargoAPI cargo = playerFleet.getCargo();
        //MemoryAPI memory = planet.getMemoryWithoutUpdate();

        PersonAPI pilot = event.getPilot();
        MarketAPI market = event.getPilotMarket();

        if (action.equals("prepare")) {
            Misc.makeImportant(planet, "abycore");
            pilot.getMemoryWithoutUpdate().set("$abycore_credits", Misc.getDGSCredits(pilotCredits), 0);
            pilot.getMemoryWithoutUpdate().set("$abycore_playerCredits", Misc.getDGSCredits(cargo.getCredits().get()), 0);
            stage = AbydosBetaCoreStage.ACQUIRE_BETA_CORE;
        } else if (action.equals("canPay")) {
            return cargo.getCredits().get() >= pilotCredits;
        } else if (action.equals("planetfound")) {


            Misc.makeImportant(planet, "abycore");
            stage = AbydosBetaCoreStage.RETURN_CORE;
            sendUpdate(AbydosBetaCoreStage.RETURN_CORE, dialog.getTextPanel());
        }

        return true;
    }

    @Override
    public void endAfterDelay() {
        stage = AbydosBetaCoreStage.DONE;
        Misc.makeUnimportant(planet, "abycore");
        super.endAfterDelay();
    }

    @Override
    protected void notifyEnding() {
        super.notifyEnding();
    }


    protected void addBulletPoints(TooltipMakerAPI info, ListInfoMode mode) {

        Color h = Misc.getHighlightColor();
        Color g = Misc.getGrayColor();
        float pad = 3f;
        float opad = 10f;

        float initPad = pad;
        if (mode == ListInfoMode.IN_DESC) initPad = opad;

        Color tc = getBulletColorForMode(mode);

        bullet(info);
        boolean isUpdate = getListInfoParam() != null;

        if (stage == AbydosBetaCoreStage.ACQUIRE_BETA_CORE) {
            info.addPara("Find the scammer", initPad, tc);
        } else if (stage == AbydosBetaCoreStage.RETURN_CORE) {
            info.addPara("Return the Beta Core", tc, initPad);
        }

        initPad = 0f;

        unindent(info);
    }


    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        Color c = getTitleColor(mode);
        info.setParaSmallInsignia();
        info.addPara(getName(), c, 0f);
        info.setParaFontDefault();
        addBulletPoints(info, mode);

    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        Color h = Misc.getHighlightColor();
        Color g = Misc.getGrayColor();
        Color tc = Misc.getTextColor();
        float pad = 3f;
        float opad = 10f;

        if (stage == AbydosBetaCoreStage.ACQUIRE_BETA_CORE) {
            info.addPara("The Abydos Foreclosure Task Force has asked you to retrieve " +
                    "a Beta Core. It was taken away by a fake Tri-Tachyon Fleet.", opad);
        } else if (stage == AbydosBetaCoreStage.RETURN_CORE) {
            info.addPara("You've retrieved the Beta Core from the scammer and " +
                    "can now return it to the Foreclosure Task Force.", opad);
        } else {
            info.addPara("The FTC has successfully regained their Beta Core.", opad);
        }

        addBulletPoints(info, ListInfoMode.IN_DESC);

    }

    @Override
    public String getIcon() {
        return Global.getSettings().getSpriteName("intel", "fleet_log");
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(Tags.INTEL_STORY);
        tags.add(Tags.INTEL_EXPLORATION);
        tags.add(Tags.INTEL_ACCEPTED);
        tags.add(Tags.INTEL_MISSIONS);
        return tags;
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_2;
    }

    public String getSortString() {
        return "Foreclosure Task Force - Beta Core";
    }

    public String getName() {
        if (isEnded() || isEnding()) {
            return "FTC Beta Core - Completed";
        }
        return "Foreclosure Task Force - Beta Core";
    }

    @Override
    public FactionAPI getFactionForUIColors() {
        return super.getFactionForUIColors();
    }

    public String getSmallDescriptionTitle() {
        return getName();
    }

    @Override
    public SectorEntityToken getMapLocation(SectorMapAPI map) {
        return planet;
    }

    @Override
    public boolean shouldRemoveIntel() {
        return super.shouldRemoveIntel();
    }

    @Override
    public String getCommMessageSound() {
        return getSoundMajorPosting();
    }
}
