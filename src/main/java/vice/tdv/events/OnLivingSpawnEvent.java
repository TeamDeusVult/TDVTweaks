package vice.tdv.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vice.tdv.TDV;
import vice.tdv.config.TDVConfig;

@Mod.EventBusSubscriber(modid = TDV.MODID)
public class OnLivingSpawnEvent {

    @SubscribeEvent
    public static void onSpecialLivingSpawnEvent(LivingSpawnEvent.SpecialSpawn event) {
        checkSpawn(event);
    }

    @SubscribeEvent
    public static void onLivingSpawnEvent(LivingSpawnEvent.CheckSpawn event) {
        checkSpawn(event);
    }

    private static void checkSpawn(LivingSpawnEvent event) {
        Entity entity = event.getEntity();

        if (entity.getEntity() instanceof WanderingTraderEntity && TDVConfig.WanderingTrader.get() && event.isCancelable()) {
            event.setCanceled(true);
        }
    }
}
