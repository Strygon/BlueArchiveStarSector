package lnsage.bluearchivemod;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.PluginPick;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.combat.MissileAIPlugin;
import com.fs.starfarer.api.combat.MissileAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;
import com.fs.starfarer.campaign.Faction;
import exerelin.campaign.SectorManager;
import org.apache.log4j.Logger;
import lnsage.bluearchivemod.ba_gen;

public class BlueArchivePlugin extends BaseModPlugin {
    static Logger log = Global.getLogger(BlueArchivePlugin.class);
    public static boolean blackrockExists = false;
    public static boolean borkenExists = false;
    public static boolean checkMemory = false;
    public static boolean diableExists = false;
    public static boolean exigencyExists = false;
    public static boolean hasDynaSector = false;
    public static boolean hasGraphicsLib = false;
    public static boolean hasMagicLib = false;
    public static boolean hasUnderworld = false;
    public static boolean iceExists = false;
    public static boolean imperiumExists = false;
    public static boolean junkPiratesExists = false;
    public static boolean oraExists = false;
    public static boolean scalarTechExists = false;
    public static boolean scyExists = false;
    public static boolean shadowyardsExists = false;
    public static boolean templarsExists = false;
    public static boolean tiandongExists = false;
    public static boolean tyradorExists = false;
    public static boolean dmeExists = false;
    public static boolean arkgneisisExists = false;
    public static boolean nexerelinEnabled = false;
    public static final String millenium_ID = "millenium";
    @Override
    public void onApplicationLoad() throws Exception {
        super.onApplicationLoad();

        nexerelinEnabled = Global.getSettings().getModManager().isModEnabled("nexerelin");



//        hasUnderworld = Global.getSettings().getModManager().isModEnabled("underworld");
//        hasDynaSector = Global.getSettings().getModManager().isModEnabled("dynasector");
//
//        borkenExists = Global.getSettings().getModManager().isModEnabled("fob");
//        iceExists = Global.getSettings().getModManager().isModEnabled("nbj_ice");
//        imperiumExists = Global.getSettings().getModManager().isModEnabled("Imperium");
//        templarsExists = Global.getSettings().getModManager().isModEnabled("Templars");
//        blackrockExists = Global.getSettings().getModManager().isModEnabled("blackrock_driveyards");
//        exigencyExists = Global.getSettings().getModManager().isModEnabled("exigency");
//        shadowyardsExists = Global.getSettings().getModManager().isModEnabled("shadow_ships");
//        junkPiratesExists = Global.getSettings().getModManager().isModEnabled("junk_pirates_release");
//        scyExists = Global.getSettings().getModManager().isModEnabled("SCY");
//        tiandongExists = Global.getSettings().getModManager().isModEnabled("THI");
//        diableExists = Global.getSettings().getModManager().isModEnabled("diableavionics");
//        oraExists = Global.getSettings().getModManager().isModEnabled("ORA");
//        tyradorExists = Global.getSettings().getModManager().isModEnabled("TS_Coalition");
//        dmeExists = Global.getSettings().getModManager().isModEnabled("istl_dam");
//        scalarTechExists = Global.getSettings().getModManager().isModEnabled("tahlan_scalartech");
//        nexerelinEnabled = Global.getSettings().getModManager().isModEnabled("nexerelin");

        // Test that the .jar is loaded and working, using the most obnoxious way possible.
        //throw new RuntimeException("Template mod loaded! Remove this crash in TemplateModPlugin.");
    }

    @Override
    public void onNewGame() {
        super.onNewGame();
        if (!nexerelinEnabled || SectorManager.getManager().isCorvusMode()) {
            new ba_gen().generate(Global.getSector());
//             Add code that creates a new star system (will only run if Nexerelin's Random (corvus) mode is disabled).
        }
    }

    public void onNewGameAfterEconomyLoad(){
        
    }
}