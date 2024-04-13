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

public class ba_apollinaris {

    public void generate(SectorAPI sector) {

        StarSystemAPI system = sector.createStarSystem("Apollinaris");
        system.getLocation().set(-11200, -9800);
        system.setEnteredByPlayer(true);
        Misc.setAllPlanetsSurveyed(system, true);
        system.setBackgroundTextureFilename("graphics/backgrounds/background4.jpg");

        PlanetAPI ApollinarisStar = system.initStar("ba_star_apollinaris", // unique id for this star
                "star_neutron", // id in planets.json
                600f,        // radius (in pixels at default zoom)
                400, // corona radius, from star edge
                8f, // solar wind burn level
                0.6f, // flare probability
                5f); // cr loss mult

        PlanetAPI Laodicea = system.addPlanet("ba_planet_laodicea",
                ApollinarisStar,
                "Laodicea",
                "barren-bombarded",
                40f,
                180f,
                2400f,
                100f);
        Laodicea.setCustomDescriptionId("ba_laodicea"); //reference descriptions.csv
        Laodicea.setInteractionImage("illustrations","ariuscampus");

        MarketAPI Laodicea_market = ba_gen.addMarketplace(
                "arius",
                Laodicea,
                null,
                "Laodicea",
                5,

                new ArrayList<>(
                        Arrays.asList(
                                Conditions.POPULATION_5,
                                Conditions.ORGANICS_COMMON,
                                Conditions.FARMLAND_POOR,
                                Conditions.POLLUTION,
                                Conditions.IRRADIATED,
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
                                Industries.MEGAPORT
                        )
                ),
                //tariffs
                0.3f,
                //freeport
                true,
                //junk and chatter
                true);

        Laodicea_market.getIndustry(Industries.STARFORTRESS_HIGH).setAICoreId(Commodities.ALPHA_CORE);
        Laodicea_market.getIndustry(Industries.HEAVYBATTERIES).setSpecialItem(new SpecialItemData(Items.DRONE_REPLICATOR, null));


        //Asteroid belt
        system.addAsteroidBelt(ApollinarisStar, 400, 6000, 200, 250, 300, Terrain.ASTEROID_BELT, "Inner Band");
        system.addRingBand(ApollinarisStar, "misc", "rings_asteroids0", 256f, 3, Color.gray, 256f, 6000, 300f);

        //Ring
        system.addRingBand(ApollinarisStar, "misc", "rings_dust0", 256f, 0, Color.gray, 600f, 4000, 220, Terrain.RING, "Outer ring");
        system.addRingBand(ApollinarisStar, "misc", "rings_dust0", 256f, 0, Color.gray, 600f, 4500, 220, Terrain.RING, "Outer ring");

        //add Comm relay
        SectorEntityToken MakeshiftRelay = system.addCustomEntity("ba_comm_relay_makeshift", // unique id
                "Trinity Comm Relay", // name - if null, defaultName from custom_entities.json will be used
                "comm_relay", // type of object, defined in custom_entities.json
                "trinity"); // faction
        MakeshiftRelay.setCircularOrbitPointingDown(ApollinarisStar, 180f, 8700f, 265);

        // Nav beacon
        SectorEntityToken NavBeacon = system.addCustomEntity("ba_nav_buoy_makeshift", // unique id
                "Trinity Lighthouse Beacon", // name - if null, defaultName from custom_entities.json will be used
                "nav_buoy_makeshift", // type of object, defined in custom_entities.json
                "trinity"); // faction
        NavBeacon.setCircularOrbitPointingDown(ApollinarisStar, -90f, 6000f, 105);

        // Sensor relay
        SectorEntityToken SensorRelay = system.addCustomEntity("ba_sensor_array", // unique id
                "Trinity Sensor Relay", // name - if null, defaultName from custom_entities.json will be used
                "sensor_array", // type of object, defined in custom_entities.json
                "trinity"); // faction
        SensorRelay.setCircularOrbitPointingDown(ApollinarisStar, 100f, 9000f, 450f);

        //Jump point
        JumpPointAPI jumpPoint1 = Global.getFactory().createJumpPoint(
                "ba_center_jump",
                "Center System Jump");

        jumpPoint1.setCircularOrbit(system.getEntityById("ba_star_Apollinaris"), 280, 2400, 100f);
        jumpPoint1.setStandardWormholeToHyperspaceVisual();

        JumpPointAPI jumpPoint2 = Global.getFactory().createJumpPoint(
                "ba_fringe_jump",
                "Fringe System Jump");

        jumpPoint2.setCircularOrbit(system.getEntityById("ba_star_Apollinaris"), 100, 10000, 400f);
        jumpPoint2.setStandardWormholeToHyperspaceVisual();

        //
        float radiusAfter2 = StarSystemGenerator.addOrbitingEntities(system, ApollinarisStar, StarAge.YOUNG,
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
