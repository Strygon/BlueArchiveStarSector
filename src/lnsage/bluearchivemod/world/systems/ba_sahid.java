package lnsage.bluearchivemod.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.*;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.StarAge;
import com.fs.starfarer.api.impl.campaign.procgen.StarSystemGenerator;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.util.Misc;
import lnsage.bluearchivemod.ba_gen;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ba_sahid {

    public void generate(SectorAPI sector) {

        StarSystemAPI system = sector.createStarSystem("Sahid");
        system.getLocation().set(4200, -12800);
        system.setEnteredByPlayer(true);
        Misc.setAllPlanetsSurveyed(system, true);
        system.setBackgroundTextureFilename("graphics/backgrounds/background4.jpg");

        PlanetAPI SahidStar = system.initStar("ba_star_sahid", // unique id for this star
                "star_red_giant", // id in planets.json
                600f,        // radius (in pixels at default zoom)
                400, // corona radius, from star edge
                8f, // solar wind burn level
                0.6f, // flare probability
                5f); // cr loss mult

        PlanetAPI Dendera = system.addPlanet("ba_planet_dendera",
                SahidStar,
                "Dendera",
                "barren-bombarded",
                40f,
                180f,
                9400f,
                100f);
        Dendera.setCustomDescriptionId("ba_laodicea"); //reference descriptions.csv
        Dendera.setInteractionImage("illustrations","ariuscampus");

        MarketAPI Dendera_market = ba_gen.addMarketplace(
                "abydos",
                Dendera,
                null,
                "Dendera",
                1,

                new ArrayList<>(
                        Arrays.asList(
                                Conditions.POPULATION_1,
                                Conditions.ORGANICS_TRACE,
                                Conditions.FARMLAND_POOR,
                                Conditions.EXTREME_WEATHER,
                                Conditions.IRRADIATED,
                                Conditions.RUINS_SCATTERED
                        )
                ),

                new ArrayList<>(
                        Arrays.asList(
                                Submarkets.SUBMARKET_OPEN,
                                Submarkets.SUBMARKET_STORAGE,
                                Submarkets.SUBMARKET_BLACK
                        )
                ),
                new ArrayList<>(
                        Arrays.asList(
                                Industries.POPULATION,
                                Industries.ORBITALSTATION,
                                Industries.SPACEPORT
                        )
                ),
                //tariffs
                0.5f,
                //freeport
                false,
                //junk and chatter
                true);

        Dendera_market.getIndustry(Industries.ORBITALSTATION).setAICoreId(Commodities.ALPHA_CORE);


        //Asteroid belt
        system.addAsteroidBelt(SahidStar, 400, 6600, 200, 250, 300, Terrain.ASTEROID_BELT, "Abydos Asteroid Belt");
        system.addRingBand(SahidStar, "misc", "rings_asteroids0", 256f, 3, Color.gray, 256f, 6000, 300f);

        //add Comm relay
        SectorEntityToken MakeshiftRelay = system.addCustomEntity("ba_comm_relay_makeshift", // unique id
                "Abydos Comm Relay", // name - if null, defaultName from custom_entities.json will be used
                "comm_relay", // type of object, defined in custom_entities.json
                "abydos"); // faction
        MakeshiftRelay.setCircularOrbitPointingDown(SahidStar, 180f, 8700f, 265);


        //Jump point
        JumpPointAPI jumpPoint1 = Global.getFactory().createJumpPoint(
                "ba_center_jump",
                "Center System Jump");

        jumpPoint1.setCircularOrbit(system.getEntityById("ba_star_sahid"), 20, 2400, 100f);
        jumpPoint1.setStandardWormholeToHyperspaceVisual();

        JumpPointAPI jumpPoint2 = Global.getFactory().createJumpPoint(
                "ba_fringe_jump",
                "Fringe System Jump");

        jumpPoint2.setCircularOrbit(system.getEntityById("ba_star_sahid"), 300, 10000, 400f);
        jumpPoint2.setStandardWormholeToHyperspaceVisual();

        //
        float radiusAfter2 = StarSystemGenerator.addOrbitingEntities(system, SahidStar, StarAge.YOUNG,
                5, 7, // min/max entities to add
                8000, // radius to start adding at
                3, // name offset - next planet will be <system name> <roman numeral of this parameter + 1>
                true); // whether to use custom or system-name based names

        system.addEntity(jumpPoint1);
        system.addEntity(jumpPoint2);

        system.autogenerateHyperspaceJumpPoints(true, false);
        cleanup(system);
    }

    void cleanup(StarSystemAPI system) {
        HyperspaceTerrainPlugin plugin = (HyperspaceTerrainPlugin) Misc.getHyperspaceTerrain().getPlugin();
        NebulaEditor editor = new NebulaEditor(plugin);
        float minRadius = plugin.getTileSize() * 2f;

        float radius = system.getMaxRadiusInHyperspace();
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius * 0.5f, 0, 360f);
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f, 0.25f);
    }

}
