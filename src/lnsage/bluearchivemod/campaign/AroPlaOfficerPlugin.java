package lnsage.bluearchivemod.campaign;

import java.util.Random;

import com.fs.starfarer.api.campaign.AICoreOfficerPlugin;
import com.fs.starfarer.api.campaign.GenericPluginManagerAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.fleets.BaseGenerateFleetOfficersPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;


public class AroPlaOfficerPlugin extends BaseGenerateFleetOfficersPlugin implements AICoreOfficerPlugin {

    @Override
    public int getHandlingPriority(Object params) {
        if (!(params instanceof GenerateFleetOfficersPickData)) return -1;

        GenerateFleetOfficersPickData data = (GenerateFleetOfficersPickData) params;
        if (data.params != null && !data.params.withOfficers) return -1;
        // "contains", so includes Dustkeeper Auxiliaries in automated defenses
        return GenericPluginManagerAPI.MOD_SUBSET;
    }
    /**
     * In person memory, how many points worth it counts for when installed
     * on an automated ship, for the purposes of the Automated Ships skill ONLY.
     */
    public static String AUTOMATED_POINTS_VALUE = "$autoPointsValue";

    /**
     * In person memory, by how much it multiplies the automated points cost
     * on an automated ship, for the purposes of the Automated Ships skill ONLY.
     */
    public static String AUTOMATED_POINTS_MULT = "$autoPointsMult";

    public PersonAPI createPerson(String aiCoreId, String factionId, Random random) {

        return null;
    }

    public void createPersonalitySection(PersonAPI person, TooltipMakerAPI tooltip) {

    }
    //StoryPointActionDelegate createIntegrateDelegate(PersonAPI person, FleetMemberAPI member);
}

