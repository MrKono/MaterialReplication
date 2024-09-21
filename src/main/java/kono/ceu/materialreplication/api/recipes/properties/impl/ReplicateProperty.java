package kono.ceu.materialreplication.api.recipes.properties.impl;

import static net.minecraft.util.text.TextFormatting.GOLD;
import static net.minecraft.util.text.TextFormatting.YELLOW;

import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GregTechAPI;
import gregtech.api.recipes.properties.RecipeProperty;
import gregtech.client.utils.TooltipHelper;

public final class ReplicateProperty extends RecipeProperty<String> {

    public static final String KEY = "replicate";

    private static ReplicateProperty INSTANCE;

    private ReplicateProperty() {
        super(KEY, String.class);
    }

    public static ReplicateProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReplicateProperty();
            GregTechAPI.RECIPE_PROPERTIES.register(KEY, INSTANCE);
        }
        return INSTANCE;
    }

    @Override
    public @NotNull NBTBase serialize(@NotNull Object value) {
        return new NBTTagString(castValue(value));
    }

    @Override
    public @NotNull Object deserialize(@NotNull NBTBase nbt) {
        return nbt.toString();
    }

    public static TooltipHelper.GTFormatCode BLINKING_ORANGE_FAST = TooltipHelper.createNewCode(5, GOLD, YELLOW);

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(BLINKING_ORANGE_FAST + I18n.format("recipemap.replication.property1"), x, y,
                color);
        minecraft.fontRenderer.drawString(BLINKING_ORANGE_FAST + I18n.format("recipemap.replication.property2"), x,
                y + 10, color);
    }
}
