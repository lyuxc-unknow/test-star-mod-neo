package me.lyuxc.develop.block.blockContainer;

import me.lyuxc.develop.block.BlockRegistry;
import me.lyuxc.develop.block.blockEntity.SuperGeneratorEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import static me.lyuxc.develop.block.blockEntity.SuperGeneratorEntity.SLOT;
import static me.lyuxc.develop.block.blockEntity.SuperGeneratorEntity.SLOT_COUNT;

public class SuperGeneratorContainer extends AbstractContainerMenu {
    private final BlockPos pos;
    private int power;

    public SuperGeneratorContainer(int pContainerId, Player player, BlockPos pos) {
        super(BlockRegistry.SUPER_GENERATOR_CONTAINER.get(), pContainerId);
        this.pos = pos;
        if (player.level().getBlockEntity(pos) instanceof SuperGeneratorEntity generator) {
            addSlot(new SlotItemHandler(generator.getItems(), SLOT, 82, 31));
            addDataSlot(new DataSlot() {
                @Override
                public int get() {
                    return generator.getStoredPower() & 0xffff;
                }

                @Override
                public void set(int pValue) {
                    SuperGeneratorContainer.this.power = (SuperGeneratorContainer.this.power & 0xffff0000) | (pValue & 0xffff);
                }
            });
            addDataSlot(new DataSlot() {
                @Override
                public int get() {
                    return (generator.getStoredPower() >> 16) & 0xffff;
                }

                @Override
                public void set(int pValue) {
                    SuperGeneratorContainer.this.power = (SuperGeneratorContainer.this.power & 0xffff) | ((pValue & 0xffff) << 16);
                }
            });
        }
        layoutPlayerInventorySlots(player.getInventory(), 70);
    }

    public int getPower() {
        return power;
    }

    private int addSlotRange(Container playerInventory, int index, int x, int y) {
        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(playerInventory, index, x, y));
            x += 18;
            index++;
        }
        return index;
    }

    private void addSlotBox(Container playerInventory, int index, int y) {
        for (int j = 0; j < 3; j++) {
            index = addSlotRange(playerInventory, index, 10, y);
            y += 18;
        }
    }

    private void layoutPlayerInventorySlots(Container playerInventory, int topRow) {
        //玩家背包
        addSlotBox(playerInventory, 9, topRow);

        //快捷栏
        topRow += 58;
        addSlotRange(playerInventory, 0, 10, topRow);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index < SLOT_COUNT) {
                if (!this.moveItemStackTo(stack, SLOT_COUNT, Inventory.INVENTORY_SIZE + SLOT_COUNT, true)) {
                    return ItemStack.EMPTY;
                }
            }
            if (!this.moveItemStackTo(stack, SLOT, SLOT + 1, false)) {
                if (index < 27 + SLOT_COUNT) {
                    if (!this.moveItemStackTo(stack, 27 + SLOT_COUNT, 36 + SLOT_COUNT, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < Inventory.INVENTORY_SIZE + SLOT_COUNT && !this.moveItemStackTo(stack, SLOT_COUNT, 27 + SLOT_COUNT, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(player.level(), pos), player, BlockRegistry.SUPER_GENERATOR.get());
    }
}
