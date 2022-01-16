package vice.tdv;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vice.tdv.config.TDVConfig;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("tdv_tweaks")
public class TDV
{
    public static final String MODID = "tdv_tweaks";
    public static final Logger LOGGER = LogManager.getLogger();

    public TDV() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);

        TDVConfig.loadConfig(FMLPaths.CONFIGDIR.get().resolve("tdv_tweaks.toml"));

    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }
}