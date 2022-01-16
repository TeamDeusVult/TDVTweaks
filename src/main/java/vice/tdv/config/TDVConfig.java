package vice.tdv.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import lombok.val;
import net.minecraftforge.common.ForgeConfigSpec;
import java.nio.file.Path;

import static net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class TDVConfig
{
    public static ForgeConfigSpec ConfigSpec;

    public static ConfigValue<Boolean> Enabled;

    static
    {
        val builder = new ConfigBuilder("Dynamic Lights Settings");

        builder.Block("Misc", b -> {
            Enabled = b.define("Render Fog", true);
        });

        ConfigSpec = builder.Save();
    }

    public static void loadConfig(Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();

        configData.load();
        ConfigSpec.setConfig(configData);
    }
}
