package lnsage.bluearchivemod.campaign.plugins;

import com.fs.starfarer.api.PluginPick;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import lnsage.bluearchivemod.campaign.AroPlaAdminImpl;
import lnsage.bluearchivemod.campaign.AroPlaCorePluginImpl;
import lnsage.bluearchivemod.campaign.AroPlaOfficerPlugin;
import lnsage.bluearchivemod.campaign.ids.BACommodities;

public class BACampaignPlugin extends BaseCampaignPlugin{
    public String getId() {
        return "BACampaignPlugin";
    }



    public PluginPick<AICoreOfficerPlugin> pickAICoreOfficerPlugin(String commodityID) {
         if (BACommodities.ARONA_CORE.equals(commodityID)) {
            return new PluginPick<AICoreOfficerPlugin>(new AroPlaCorePluginImpl(), PickPriority.MOD_SET);
        }
        else if (BACommodities.PLANA_CORE.equals(commodityID)) {
            return new PluginPick<AICoreOfficerPlugin>(new AroPlaCorePluginImpl(), PickPriority.MOD_SET);
        }
        else{
            return null;
        }
    }

    public PluginPick<AICoreAdminPlugin> pickAICoreAdminPlugin(String commodityID) {
        if (BACommodities.ARONA_CORE.equals(commodityID)) {
            return new PluginPick<AICoreAdminPlugin>(new AroPlaAdminImpl(), PickPriority.MOD_SET);
        } else if (BACommodities.PLANA_CORE.equals(commodityID)) {
            return new PluginPick<AICoreAdminPlugin>(new AroPlaAdminImpl(), PickPriority.MOD_SET);
        }
        else {
            return null;
        }
    }
}

