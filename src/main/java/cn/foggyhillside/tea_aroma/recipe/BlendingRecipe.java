package cn.foggyhillside.tea_aroma.recipe;

import cn.foggyhillside.tea_aroma.items.KettleItem;
import cn.foggyhillside.tea_aroma.registry.ModItems;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;

import javax.annotation.Nullable;

public class BlendingRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;

    private final ItemStack output;

    private final ItemStack tea;

    public BlendingRecipe(ResourceLocation id, ItemStack output, ItemStack tea) {
        this.id = id;
        this.output = output;
        this.tea = tea;
    }

    public ItemStack getTea() {
        return tea.copy();
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide) {
            return false;
        }
        boolean isBoilingMilkKettle = false;
        ItemStack kettle = pContainer.getItem(pContainer.getContainerSize() - 1);
        if (kettle.is(ModItems.KETTLE.get())) {
            if (KettleItem.getStackLiquid(kettle).equals("boiling_milk")) {
                isBoilingMilkKettle = true;
            }
        } else {
            isBoilingMilkKettle = true;
        }

        return isBoilingMilkKettle && pContainer.getItem(0).is(this.tea.getItem());
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess access) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return BlendingRecipe.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return BlendingRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<BlendingRecipe> {
        private Type() {
        }

        public static final BlendingRecipe.Type INSTANCE = new BlendingRecipe.Type();
    }

    public static class Serializer implements RecipeSerializer<BlendingRecipe> {
        public static final BlendingRecipe.Serializer INSTANCE = new BlendingRecipe.Serializer();

        @Override
        public BlendingRecipe fromJson(ResourceLocation location, JsonObject json) {
            ItemStack tea = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "tea"), true);
            if (tea.isEmpty()) {
                throw new JsonParseException("No ingredient for blending recipe");
            } else {
                ItemStack output = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);
                return new BlendingRecipe(location, output, tea);
            }
        }

        @Nullable
        public BlendingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            ItemStack output = buffer.readItem();
            ItemStack tea = buffer.readItem();
            return new BlendingRecipe(id, output, tea);
        }

        public void toNetwork(FriendlyByteBuf buffer, BlendingRecipe recipe) {
            buffer.writeItem(recipe.output);
            buffer.writeItem(recipe.tea);
        }
    }

}
