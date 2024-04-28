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

public class ba_geh_kokytos {
    public void generate(SectorAPI sector) {

        StarSystemAPI system = sector.createStarSystem("Kokytos");
        system.getLocation().set(7200, -3100);
        system.setEnteredByPlayer(true);
        Misc.setAllPlanetsSurveyed(system, true);
        system.setBackgroundTextureFilename("graphics/backgrounds/background2.jpg");

        PlanetAPI KokytosStar = system.initStar("ba_star_kokytos", // unique id for this star
                "star_orange", // id in planets.json
                600f,        // radius (in pixels at default zoom)
                400, // corona radius, from star edge
                8f, // solar wind burn level
                0.6f, // flare probability
                5f); // cr loss mult

        PlanetAPI Perfidia = system.addPlanet("ba_planet_perfidia",
                KokytosStar,
                "Perfidia",
                "tundra",
                40f,
                180f,
                2400f,
                100f);
        Perfidia.setCustomDescriptionId("ba_perfidia"); //reference descriptions.csv
        Perfidia.setInteractionImage("illustrations","spiritusimage");

        MarketAPI Perfidia_market = ba_gen.addMarketplace(
                "gehenna",
                Perfidia,
                null,
                "Perfidia",
                6,

                new ArrayList<>(
                        Arrays.asList(
                                Conditions.POPULATION_5,
                                Conditions.RARE_ORE_ULTRARICH,
                                Conditions.ORE_RICH,
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
                                Industries.STARFORTRESS_HIGH,
                                Industries.CRYOSANCTUM,
                                Industries.MEGAPORT
                        )
                ),
                //tariffs
                0.3f,
                //freeport
                true,
                //junk and chatter
                true);

        Perfidia_market.getIndustry(Industries.STARFORTRESS_HIGH).setAICoreId(Commodities.ALPHA_CORE);
        Perfidia_market.getIndustry(Industries.HEAVYBATTERIES).setSpecialItem(new SpecialItemData(Items.DRONE_REPLICATOR, null));


        PlanetAPI Malebolge = system.addPlanet("ba_planet_malebolge",
                KokytosStar,
                "Malebolge",
                "arid",
                250f,
                120f,
                5300f,
                250f);
        Malebolge.setCustomDescriptionId("ba_malebolge"); //reference descriptions.csv
        Malebolge.setInteractionImage("illustrations","vehiclepowerroom");

        MarketAPI Malebolge_market = ba_gen.addMarketplace(
                "gehenna",
                Malebolge,
                null,
                "Malebolge",
                4,

                new ArrayList<>(
                        Arrays.asList(
                                Conditions.POPULATION_4,
                                Conditions.HABITABLE,
                                Conditions.ORE_RICH,
                                Conditions.HOT
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
                                Industries.BATTLESTATION,
                                Industries.SPACEPORT,
                                Industries.PATROLHQ
                        )
                ),
                //tariffs
                0.3f,
                //freeport
                true,
                //junk and chatter
                false);

        //Asteroid belt
        system.addAsteroidBelt(KokytosStar, 400, 6000, 200, 250, 300, Terrain.ASTEROID_BELT, "Inner Band");
        system.addRingBand(KokytosStar, "misc", "rings_asteroids0", 256f, 3, Color.gray, 256f, 6000, 300f);

        //Ring
        system.addRingBand(KokytosStar, "misc", "rings_dust0", 256f, 0, Color.gray, 600f, 4000, 220, Terrain.RING, "Outer ring");
        system.addRingBand(KokytosStar, "misc", "rings_dust0", 256f, 0, Color.gray, 600f, 4500, 220, Terrain.RING, "Outer ring");

        //add Comm relay
        SectorEntityToken MakeshiftRelay = system.addCustomEntity("ba_comm_relay_makeshift", // unique id
                "Gehenna Comm Relay", // name - if null, defaultName from custom_entities.json will be used
                "comm_relay", // type of object, defined in custom_entities.json
                "gehenna"); // faction
        MakeshiftRelay.setCircularOrbitPointingDown(KokytosStar, 180f, 8700f, 265);

        // Nav beacon
        SectorEntityToken NavBeacon = system.addCustomEntity("ba_nav_buoy_makeshift", // unique id
                "Gehenna Navigation Beacon", // name - if null, defaultName from custom_entities.json will be used
                "nav_buoy_makeshift", // type of object, defined in custom_entities.json
                "gehenna"); // faction
        NavBeacon.setCircularOrbitPointingDown(KokytosStar, -90f, 6000f, 105);

        // Sensor relay
        SectorEntityToken SensorRelay = system.addCustomEntity("ba_sensor_array", // unique id
                "Gehenna Sensor Relay", // name - if null, defaultName from custom_entities.json will be used
                "sensor_array", // type of object, defined in custom_entities.json
                "gehenna"); // faction
        SensorRelay.setCircularOrbitPointingDown(KokytosStar, 100f, 9000f, 450f);

        //Jump point
        JumpPointAPI jumpPoint1 = Global.getFactory().createJumpPoint(
                "ba_center_jump",
                "Center System Jump");

        jumpPoint1.setCircularOrbit(system.getEntityById("ba_star_kokytos"), 280, 2400, 100f);
        jumpPoint1.setStandardWormholeToHyperspaceVisual();

        JumpPointAPI jumpPoint2 = Global.getFactory().createJumpPoint(
                "ba_fringe_jump",
                "Fringe System Jump");

        jumpPoint2.setCircularOrbit(system.getEntityById("ba_star_kokytos"), 100, 10000, 400f);
        jumpPoint2.setStandardWormholeToHyperspaceVisual();

        //
        float radiusAfter2 = StarSystemGenerator.addOrbitingEntities(system, KokytosStar, StarAge.YOUNG,
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
