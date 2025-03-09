package cn.foggyhillside.tea_aroma.blocks.entities.inventory;

import cn.foggyhillside.tea_aroma.ModCompat;
import cn.foggyhillside.tea_aroma.registry.ModTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

public class CupItemHandler implements IItemHandlerModifiable {
    private final IItemHandlerModifiable itemHandler;

    public CupItemHandler(IItemHandlerModifiable itemHandler) {
        this.itemHandler = itemHandler;
    }

    @Override
    public void setStackInSlot(int slot, @NotNull ItemStack stack) {
        this.itemHandler.setStackInSlot(slot, stack);
    }

    @Override
    public int getSlots() {
        return itemHandler.getSlots();
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        return itemHandler.getStackInSlot(slot);
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if (stack.is(ModTags.TEA_INGREDIENTS) || (ModCompat.isSimplyTeaLoaded() && stack.is(ModTags.SIMPLYTEA_INGREDIENTS))) {
            return itemHandler.insertItem(slot, stack, simulate);
        }
        return stack;
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        return itemHandler.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return itemHandler.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return true;
    }
}
