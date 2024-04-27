package lnsage.bluearchivemod;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;

import static lnsage.bluearchivemod.campaign.ids.People.*;

import lnsage.bluearchivemod.world.systems.*;

import java.util.ArrayList;

public class ba_gen implements SectorGeneratorPlugin {


    //Shorthand function for adding a market
    public static MarketAPI addMarketplace(String factionID, SectorEntityToken primaryEntity, ArrayList<SectorEntityToken> connectedEntities, String name,
                                           int size, ArrayList<String> marketConditions, ArrayList<String> submarkets, ArrayList<String> industries, float tarrif,
                                           boolean freePort, boolean withJunkAndChatter) {
        EconomyAPI globalEconomy = Global.getSector().getEconomy();
        String planetID = primaryEntity.getId();
        String marketID = planetID + "_market";

        MarketAPI newMarket = Global.getFactory().createMarket(marketID, name, size);
        newMarket.setFactionId(factionID);
        newMarket.setPrimaryEntity(primaryEntity);
        newMarket.getTariff().modifyFlat("generator", tarrif);

        //Adds submarkets
        if (null != submarkets) {
            for (String market : submarkets) {
                newMarket.addSubmarket(market);
            }
        }

        //Adds market conditions
        for (String condition : marketConditions) {
            newMarket.addCondition(condition);
        }

        //Add market industries
        for (String industry : industries) {
            newMarket.addIndustry(industry);
        }

        //Sets us to a free port, if we should
        newMarket.setFreePort(freePort);

        //Adds our connected entities, if any
        if (null != connectedEntities) {
            for (SectorEntityToken entity : connectedEntities) {
                newMarket.getConnectedEntities().add(entity);
            }
        }

        globalEconomy.addMarket(newMarket, withJunkAndChatter);
        primaryEntity.setMarket(newMarket);
        primaryEntity.setFaction(factionID);

        if (null != connectedEntities) {
            for (SectorEntityToken entity : connectedEntities) {
                entity.setMarket(newMarket);
                entity.setFaction(factionID);
            }
        }

        //Finally, return the newly-generated market
        return newMarket;
    }

    @Override
    public void generate(SectorAPI sector) {

        FactionAPI mil = sector.getFaction("millenium");
        FactionAPI tri = sector.getFaction("trinity");
        FactionAPI geh = sector.getFaction("gehenna");
        FactionAPI ari = sector.getFaction("arius");
        FactionAPI aby = sector.getFaction("abydos");

        //Generate your system
        new ba_mil_landon().generate(sector);
        new ba_tri_camulodunum().generate(sector);
        new ba_tri_victoria().generate(sector);
        new ba_ari_apollinaris().generate(sector);
        new ba_aby_sahid().generate(sector);
        new ba_geh_kokytos().generate(sector);

        addYuuka();
        addNoa();
        addHimari();
        addNagisa();
        addMari();
        addSakurako();
        addIori();
        addHina();
        addHoshino();
        addShiroko();
        addSerika();
        addAyane();
        addNonomi();

        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("millenium");
        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("trinity");
        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("arius");
        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("gehenna");

        //vanilla factions
        mil.setRelationship(Factions.LUDDIC_CHURCH, -0.1f);
        mil.setRelationship(Factions.LUDDIC_PATH, -0.7f);
        mil.setRelationship(Factions.TRITACHYON, -0.3f);
        mil.setRelationship(Factions.PERSEAN, 0.2f);
        mil.setRelationship(Factions.PIRATES, -0.5f);
        mil.setRelationship(Factions.INDEPENDENT, 0.5f);
        mil.setRelationship(Factions.DIKTAT, -0.1f);
        mil.setRelationship(Factions.LIONS_GUARD, -0.1f);
        mil.setRelationship(Factions.HEGEMONY, -0.4f);
        mil.setRelationship(Factions.REMNANTS, -0.5f);
        mil.setRelationship("millenium", 1f);
        mil.setRelationship("trinity", 0.1f);
        mil.setRelationship("gehenna", 0.1f);
        tri.setRelationship("arius", -0.9f);

        tri.setRelationship(Factions.LUDDIC_CHURCH, -0.2f);
        tri.setRelationship(Factions.LUDDIC_PATH, -0.7f);
        tri.setRelationship(Factions.TRITACHYON, -0.1f);
        tri.setRelationship(Factions.PERSEAN, 0.1f);
        tri.setRelationship(Factions.PIRATES, -0.5f);
        tri.setRelationship(Factions.INDEPENDENT, -0.1f);
        tri.setRelationship(Factions.DIKTAT, 0.2f);
        tri.setRelationship(Factions.HEGEMONY, 0.5f);
        tri.setRelationship(Factions.REMNANTS, -0.5f);
        tri.setRelationship("millenium", 0.1f);
        tri.setRelationship("trinity", 1f);
        tri.setRelationship("gehenna", -0.9f);
        tri.setRelationship("arius", -1f);

        ari.setRelationship(Factions.LUDDIC_CHURCH, -0.9f);
        ari.setRelationship(Factions.LUDDIC_PATH, 0.4f);
        ari.setRelationship(Factions.TRITACHYON, -0.9f);
        ari.setRelationship(Factions.PERSEAN, -0.7f);
        ari.setRelationship(Factions.PIRATES, -0.5f);
        ari.setRelationship(Factions.INDEPENDENT, -0.7f);
        ari.setRelationship(Factions.DIKTAT, -0.8f);
        ari.setRelationship(Factions.HEGEMONY, -0.9f);
        ari.setRelationship(Factions.REMNANTS, 0.6f);
        ari.setRelationship("millenium", -0.9f);
        ari.setRelationship("trinity", -1f);
        ari.setRelationship("gehenna", -0.9f);
        ari.setRelationship(Factions.PLAYER, -0.5f);

        geh.setRelationship(Factions.LUDDIC_PATH, -0.4f);
        geh.setRelationship(Factions.TRITACHYON, -0.2f);
        geh.setRelationship(Factions.PERSEAN, -0.2f);
        geh.setRelationship(Factions.PIRATES, 0.3f);
        geh.setRelationship(Factions.INDEPENDENT, -0.4f);
        geh.setRelationship(Factions.DIKTAT, -0.9f);
        geh.setRelationship(Factions.HEGEMONY, -0.2f);
        geh.setRelationship(Factions.REMNANTS, -0.5f);
        geh.setRelationship("millenium", 0.1f);
        geh.setRelationship("trinity", -0.9f);
        geh.setRelationship("gehenna", 1f);
    }


}