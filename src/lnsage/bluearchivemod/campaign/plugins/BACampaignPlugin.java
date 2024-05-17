package lnsage.bluearchivemod.campaign.plugins;

import com.fs.starfarer.api.PluginPick;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import lnsage.bluearchivemod.campaign.AroPlaCorePluginImpl;
import lnsage.bluearchivemod.campaign.AroPlaOfficerPlugin;
import lnsage.bluearchivemod.campaign.ids.BACommodities;

public class BACampaignPlugin extends BaseCampaignPlugin{
    public String getId() {
        return "BACampaignPlugin";
    }



    public PluginPick<AICoreOfficerPlugin> pickAICoreOfficerPlugin(String commodityID) {
         if (BACommodities.ARONA_CORE.equals(commodityID)) {
            return new PluginPick<com.fs.starfarer.api.campaign.AICoreOfficerPlugin>(new AroPlaCorePluginImpl(), PickPriority.MOD_SET);
        }
        else if (BACommodities.PLANA_CORE.equals(commodityID)) {
            return new PluginPick<com.fs.starfarer.api.campaign.AICoreOfficerPlugin>(new AroPlaCorePluginImpl(), PickPriority.MOD_SET);
        }
        else{
            return null;
        }
    }
}

