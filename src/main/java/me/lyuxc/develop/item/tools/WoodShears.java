package me.lyuxc.develop.item.tools;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WoodShears extends ShearsItem {
    public WoodShears(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockPos pPos, @NotNull LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide && pState.is(BlockTags.LEAVES)) {
            pStack.hurtAndBreak(-2, pEntityLiving,
                    (pEntity) -> pEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND)
            );
        } else {
            pStack.hurtAndBreak(4, pEntityLiving,
                    (pEntity) -> pEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        }
        return !pState.is(BlockTags.LEAVES) || super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }
}
