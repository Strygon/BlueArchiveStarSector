package lnsage.bluearchivemod.campaign.events;


import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.util.Misc;
import org.lazywizard.lazylib.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class ta_Utils {

    public static final List<String> BLACKLISTED_SYSTEMS = new ArrayList<>();

    static {
        BLACKLISTED_SYSTEMS.add("spookysecretsystem_omega");
    }

    public static final List<String> BLACKLISTED_SYSTEM_TAGS = new ArrayList<>();
    public static final List<String> WHITELISTED_SYSTEM_TAGS = new ArrayList<>();

    static {
        BLACKLISTED_SYSTEM_TAGS.add("theme_breakers");
        BLACKLISTED_SYSTEM_TAGS.add("theme_breakers_main");
        BLACKLISTED_SYSTEM_TAGS.add("theme_breakers_secondary");
        BLACKLISTED_SYSTEM_TAGS.add("theme_breakers_no_fleets");
        BLACKLISTED_SYSTEM_TAGS.add("theme_breakers_destroyed");
        BLACKLISTED_SYSTEM_TAGS.add("theme_breakers_suppressed");
        BLACKLISTED_SYSTEM_TAGS.add("theme_breakers_resurgent");
        BLACKLISTED_SYSTEM_TAGS.add(Tags.THEME_HIDDEN);
        BLACKLISTED_SYSTEM_TAGS.add(Tags.THEME_REMNANT);
    }
    static {

        WHITELISTED_SYSTEM_TAGS.add(Tags.THEME_DERELICT);
        WHITELISTED_SYSTEM_TAGS.add(Tags.THEME_REMNANT);

    }

    /**
     * Utility function for getting a random system, with blacklist functionality in case some systems really shouldn't
     * be included.
     *
     * @param blacklist    A list of all the systems we are forbidden from picking
     * @param tagBlacklist A list of all the system tags that prevent a system from being picked
     * @param sector       The SectorAPI to check for systems in
     **/

    public static StarSystemAPI getRandomSystemWithBlacklist(List<String> blacklist, List<String> tagBlacklist, SectorAPI sector) {

        List<StarSystemAPI> validSystems = new ArrayList<>();
        for (StarSystemAPI system : sector.getStarSystems()) {
            if (blacklist.contains(system.getId())) {
                continue;
            }
            boolean isValid = true;
            for (String bannedTag : tagBlacklist) {
                if (system.hasTag(bannedTag)) {
                    isValid = false;
                    break;
                }
            }

            if (system.getStar() == null || !Misc.getMarketsInLocation(system).isEmpty() || system.getConstellation() == null) {
                isValid = false;
            }

            if (isValid) {
                validSystems.add(system);
            }
        }

        //If that list is empty, return null
        if (validSystems.isEmpty()) {
            return null;
        }

        //Otherwise, get a random element in it and return that
        else {
            int rand = MathUtils.getRandomNumberInRange(0, validSystems.size() - 1);
            return validSystems.get(rand);
        }
    }
}
