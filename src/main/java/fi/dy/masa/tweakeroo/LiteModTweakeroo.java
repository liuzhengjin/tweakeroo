package fi.dy.masa.tweakeroo;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mumfrey.liteloader.Configurable;
import com.mumfrey.liteloader.InitCompleteListener;
import com.mumfrey.liteloader.LiteMod;
import com.mumfrey.liteloader.ShutdownListener;
import com.mumfrey.liteloader.Tickable;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import fi.dy.masa.malilib.hotkeys.KeybindEventHandler;
import fi.dy.masa.tweakeroo.config.Callbacks;
import fi.dy.masa.tweakeroo.config.Configs;
import fi.dy.masa.tweakeroo.config.gui.TweakerooConfigPanel;
import fi.dy.masa.tweakeroo.event.InputEventHandler;
import fi.dy.masa.tweakeroo.util.PlacementTweaks;
import net.minecraft.client.Minecraft;

public class LiteModTweakeroo implements LiteMod, Configurable, InitCompleteListener, ShutdownListener, Tickable
{
    public static final Logger logger = LogManager.getLogger(Reference.MOD_ID);

    public LiteModTweakeroo()
    {
    }

    @Override
    public String getName()
    {
        return Reference.MOD_NAME;
    }

    @Override
    public String getVersion()
    {
        return Reference.MOD_VERSION;
    }

    @Override
    public Class<? extends ConfigPanel> getConfigPanelClass()
    {
        return TweakerooConfigPanel.class;
    }

    @Override
    public void init(File configPath)
    {
        Configs.load();
        KeybindEventHandler.getInstance().registerKeyEventHandler(InputEventHandler.getInstance());
    }

    @Override
    public void onInitCompleted(Minecraft minecraft, LiteLoader loader)
    {
        Callbacks.init();
    }

    @Override
    public void upgradeSettings(String version, File configPath, File oldConfigPath)
    {
    }

    @Override
    public void onTick(Minecraft minecraft, float partialTicks, boolean inGame, boolean clock)
    {
        PlacementTweaks.onTick(minecraft);
    }

    @Override
    public void onShutDown()
    {
        Configs.save();
    }
}
