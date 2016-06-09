package link.mcseu.badhuman.util;

import link.mcseu.badhuman.util.api.BadItem;
import com.google.common.base.CaseFormat;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import cpw.mods.fml.common.registry.GameRegistry;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import link.mcseu.badhuman.util.api.ForgeType;
import link.mcseu.badhuman.util.gson.ItemDeserializer;
import link.mcseu.badhuman.util.gson.ItemStackDeserializer;
import link.mcseu.badhuman.util.gson.UUIDSerializer;
import lombok.experimental.UtilityClass;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;

@UtilityClass
public class ForgeUtil {
    private static final CaseFormat FROM = UPPER_CAMEL;
    private static final CaseFormat FORMAT = LOWER_UNDERSCORE;
    private final Class<?> CONTEXT = ForgeUtil.class;
    private final GsonBuilder GSON = new GsonBuilder();

    static {
        GSON.registerTypeHierarchyAdapter(Item.class, new ItemDeserializer());
        GSON.registerTypeAdapter(ItemStack.class, new ItemStackDeserializer());
        GSON.registerTypeAdapter(UUID.class, new UUIDSerializer());
    }

    public void registerBlock(ForgeType<Block> block) {
        GameRegistry.registerBlock(block.unsafe(), block.getSimpleName());
        MinecraftForge.EVENT_BUS.register(block);
    }

    public void registerItem(ForgeType<Item> item) {
        GameRegistry.registerItem(item.unsafe(), item.getSimpleName());
        MinecraftForge.EVENT_BUS.register(item);
    }

    public CreativeTabs newTab(String label, BadItem item) {
        return new CreativeTabs(label) {
            @Override
            public Item getTabIconItem() {
                return item.unsafe();
            }
        };
    }

    public <T> T loadConfig(String res, Class<T> type) throws IOException {
        final InputStream is = CONTEXT.getResourceAsStream("/" + res + ".json");
        final Reader reader = new BufferedReader(new InputStreamReader(is));
        return GSON.create().fromJson(reader, type);
    }

    public String formatType(Class<?> type) {
        return FROM.to(FORMAT, type.getSimpleName());
    }
}