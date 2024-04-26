package lnsage.bluearchivemod.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.*;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.StarAge;
import com.fs.starfarer.api.impl.campaign.procgen.StarSystemGenerator;
import com.fs.starfarer.api.impl.campaign.terrain.AsteroidFieldTerrainPlugin;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.util.Misc;
import org.lazywizard.lazylib.MathUtils;
import lnsage.bluearchivemod.ba_gen;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ba_landon {

    public void generate(SectorAPI sector) {

        StarSystemAPI system = sector.createStarSystem("Landon");
        system.getLocation().set(-4500, -2500);
        system.setEnteredByPlayer(true);
        Misc.setAllPlanetsSurveyed(system, true);
        system.setBackgroundTextureFilename("graphics/backgrounds/background1.jpg");

        PlanetAPI LandonStar = system.initStar("ba_star_landon", // unique id for this star
                "star_red_giant", // id in planets.json
                700f,        // radius (in pixels at default zoom)
                500, // corona radius, from star edge
                10f, // solar wind burn level
                0.8f, // flare probability
                5f); // cr loss mult

        PlanetAPI Poincare = system.addPlanet("ba_planet_poincare",
                LandonStar,
                "Poincare",
                "terran",
                40f,
                180f,
                3000f,
                100f);
        Poincare.setCustomDescriptionId("ba_poincare"); //reference descriptions.csv
        Poincare.setInteractionImage("illustrations","millenniumrooftop1");

        MarketAPI Poincare_market = ba_gen.addMarketplace(
                "millenium",
                Poincare,
                null,
                "Poincare",
                6,

                new ArrayList<>(
                        Arrays.asList(
                                Conditions.POPULATION_6,
                                Conditions.ORGANICS_COMMON,
                                Conditions.FARMLAND_BOUNTIFUL,
                                Conditions.HABITABLE,
                                Conditions.MILD_CLIMATE,
                                Conditions.REGIONAL_CAPITAL,
                                Conditions.LOW_GRAVITY,
                                Conditions.RUINS_VAST
                        )
                ),

                new ArrayList<>(
                        Arrays.asList(
                                Submarkets.GENERIC_MILITARY,
                                Submarkets.SUBMARKET_OPEN,
                                Submarkets.SUBMARKET_STORAGE,
                                Submarkets.SUBMARKET_BLACK
                        )
                ),
                new ArrayList<>(
                        Arrays.asList(
                                Industries.POPULATION,
                                Industries.HEAVYBATTERIES,
                                Industries.FARMING,
                                Industries.STARFORTRESS_HIGH,
                                Industries.LIGHTINDUSTRY,
                                Industries.HIGHCOMMAND,
                                Industries.MEGAPORT,
                                Industries.COMMERCE
                        )
                ),
                //tariffs
                0.3f,
                //freeport
                false,
                //junk and chatter
                true);

        Poincare_market.getIndustry(Industries.STARFORTRESS_HIGH).setAICoreId(Commodities.ALPHA_CORE);
        Poincare_market.getIndustry(Industries.HIGHCOMMAND).setAICoreId(Commodities.ALPHA_CORE);
        Poincare_market.getIndustry(Industries.HEAVYBATTERIES).setSpecialItem(new SpecialItemData(Items.DRONE_REPLICATOR, null));


        PlanetAPI Riemann = system.addPlanet("ba_planet_riemann",
                LandonStar,
                "Riemann",
                "desert",
                80f,
                100f,
                5100f,
                150f);
        Riemann.setCustomDescriptionId("ba_riemann"); //reference descriptions.csv

        MarketAPI Riemann_market = ba_gen.addMarketplace(
                "millenium",
                Riemann,
                null,
                "Riemann",
                6,

                new ArrayList<>(
                        Arrays.asList(
                                Conditions.POPULATION_6,
                                Conditions.DESERT,
                                Conditions.RARE_ORE_ULTRARICH,
                                Conditions.MILD_CLIMATE,
                                Conditions.ORE_ABUNDANT,
                                Conditions.THIN_ATMOSPHERE
                        )
                ),

                new ArrayList<>(
                        Arrays.asList(
                                Submarkets.GENERIC_MILITARY,
                                Submarkets.SUBMARKET_OPEN,
                                Submarkets.SUBMARKET_STORAGE,
                                Submarkets.SUBMARKET_BLACK
                        )
                ),
                new ArrayList<>(
                        Arrays.asList(
                                Industries.POPULATION,
                                Industries.HEAVYBATTERIES,
                                Industries.MINING,
                                Industries.BATTLESTATION_HIGH,
                                Industries.REFINING,
                                Industries.ORBITALWORKS,
                                Industries.MEGAPORT,
                                Industries.MILITARYBASE
                        )
                ),
                //tariffs
                0.3f,
                //freeport
                false,
                //junk and chatter
                true);
        Riemann_market.getIndustry(Industries.ORBITALWORKS).setSpecialItem(new SpecialItemData(Items.CORRUPTED_NANOFORGE, null));
        Riemann_market.getIndustry(Industries.MILITARYBASE).setAICoreId(Commodities.ALPHA_CORE);

        PlanetAPI YangMills = system.addPlanet("ba_planet_yangmills",
                LandonStar,
                "Yang-Mills",
                "barren",
                250f,
                120f,
                7300f,
                250f);
        YangMills.setCustomDescriptionId("ba_yangmills"); //reference descriptions.csv
        system.addRingBand(YangMills, "misc", "rings_dust0", 256f, 1, Color.gray, 600f, 300f, 100);

        MarketAPI YangMills_market = ba_gen.addMarketplace(
                "millenium",
                YangMills,
                null,
                "Yang-Mills",
                4,

                new ArrayList<>(
                        Arrays.asList(
                                Conditions.POPULATION_4,
                                Conditions.THIN_ATMOSPHERE,
                                Conditions.ORE_RICH
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
                                Industries.HEAVYBATTERIES,
                                Industries.MINING,
                                Industries.BATTLESTATION,
                                Industries.SPACEPORT,
                                Industries.PATROLHQ
                        )
                ),
                //tariffs
                0.3f,
                //freeport
                false,
                //junk and chatter
                false);

        //Asteroid belt
        system.addAsteroidBelt(LandonStar, 400, 6000, 200, 250, 300, Terrain.ASTEROID_BELT, "Inner Band");
        system.addRingBand(LandonStar, "misc", "rings_asteroids0", 256f, 3, Color.gray, 256f, 6000, 300f);

        //Ring
        system.addRingBand(LandonStar, "misc", "rings_dust0", 256f, 0, Color.gray, 600f, 4000, 220, Terrain.RING, "Outer ring");
        system.addRingBand(LandonStar, "misc", "rings_dust0", 256f, 0, Color.gray, 600f, 4500, 220, Terrain.RING, "Outer ring");

        //add Comm relay
        SectorEntityToken MakeshiftRelay = system.addCustomEntity("ba_comm_relay_makeshift", // unique id
                "Landon Comm Relay", // name - if null, defaultName from custom_entities.json will be used
                "comm_relay_makeshift", // type of object, defined in custom_entities.json
                "millenium"); // faction
        MakeshiftRelay.setCircularOrbitPointingDown(LandonStar, 180f, 8700f, 265);

        // Nav beacon
        SectorEntityToken NavBeacon = system.addCustomEntity("ba_nav_buoy_makeshift", // unique id
                "Landon Nav Beacon", // name - if null, defaultName from custom_entities.json will be used
                "nav_buoy_makeshift", // type of object, defined in custom_entities.json
                "millenium"); // faction
        NavBeacon.setCircularOrbitPointingDown(LandonStar, -90f, 6000f, 105);

        // Sensor relay
        SectorEntityToken SensorRelay = system.addCustomEntity("ba_sensor_array", // unique id
                "Landon Sensor Relay", // name - if null, defaultName from custom_entities.json will be used
                "sensor_array", // type of object, defined in custom_entities.json
                "millenium"); // faction
        SensorRelay.setCircularOrbitPointingDown(LandonStar, 100f, 9000f, 450f);

        //Jump point
        JumpPointAPI jumpPoint1 = Global.getFactory().createJumpPoint(
                "ba_center_jump",
                "Center System Jump");

        jumpPoint1.setCircularOrbit(system.getEntityById("ba_star_Landon"), 280, 2400, 100f);
        jumpPoint1.setStandardWormholeToHyperspaceVisual();

        JumpPointAPI jumpPoint2 = Global.getFactory().createJumpPoint(
                "ba_fringe_jump",
                "Fringe System Jump");

        jumpPoint2.setCircularOrbit(system.getEntityById("ba_star_Landon"), 100, 10000, 400f);
        jumpPoint2.setStandardWormholeToHyperspaceVisual();

        //
        float radiusAfter2 = StarSystemGenerator.addOrbitingEntities(system, LandonStar, StarAge.YOUNG,
                3, 5, // min/max entities to add
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