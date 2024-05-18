package lnsage.bluearchivemod.campaign;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.AICoreAdminPlugin;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.SpecialItemPlugin;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.FullName.Gender;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.impl.campaign.ids.Skills;
import lnsage.bluearchivemod.campaign.ids.BACommodities;
import lnsage.bluearchivemod.campaign.ids.Items;

public class AroPlaAdminImpl implements AICoreAdminPlugin{
    public PersonAPI createPerson(String aiCoreId, String factionId, long seed) {

        boolean arona = Items.ARO_CORE.equals(aiCoreId);
        boolean plana = Items.PLA_CORE.equals(aiCoreId);

        PersonAPI person = Global.getFactory().createPerson();
        person.setFaction(factionId);
        person.setAICoreId(aiCoreId);

        if (arona) {
            person.setName(new FullName("Arona", "", Gender.FEMALE));
            person.setPortraitSprite("graphics/portraits/portrait_arona.png");

            person.setRankId(null);
            person.setPostId(Ranks.POST_ADMINISTRATOR);

//		person.getStats().setSkillLevel(Skills.PLANETARY_OPERATIONS, 1);
//		person.getStats().setSkillLevel(Skills.SPACE_OPERATIONS, 1);
            person.getStats().setSkillLevel(Skills.INDUSTRIAL_PLANNING, 1);
            person.getStats().setSkillLevel(Skills.HYPERCOGNITION, 1);

        } else if (plana){
            person.setName(new FullName("Plana", "", Gender.FEMALE));
            person.setPortraitSprite("graphics/portraits/portrait_plana.png");

            person.setRankId(null);
            person.setPostId(Ranks.POST_ADMINISTRATOR);

//		person.getStats().setSkillLevel(Skills.PLANETARY_OPERATIONS, 1);
//		person.getStats().setSkillLevel(Skills.SPACE_OPERATIONS, 1);
            person.getStats().setSkillLevel(Skills.INDUSTRIAL_PLANNING, 1);
            person.getStats().setSkillLevel(Skills.HYPERCOGNITION, 1);
        }


        return person;
    }
}
