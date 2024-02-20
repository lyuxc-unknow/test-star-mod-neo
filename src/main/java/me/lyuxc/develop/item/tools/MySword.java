package me.lyuxc.develop.item.tools;

import me.lyuxc.develop.Tiers;
import me.lyuxc.develop.utils.TextUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MySword extends SwordItem {
    //品质,伤害,攻速,属性
    public MySword() {
        super(Tiers.LEVEL_INF, 0, 65535 - 4, new Item.Properties());
    }

    //是否可以攻击（破坏）方块
    @Override
    public boolean canAttackBlock(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player) {
        return !player.isCreative();
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack pStack, @NotNull LivingEntity pTarget, @NotNull LivingEntity pAttacker) {
        pTarget.hurt(pAttacker.damageSources().source(DamageTypes.FELL_OUT_OF_WORLD), Integer.MAX_VALUE);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if (!pLevel.isClientSide) {
            AABB aabb = pPlayer.getBoundingBox().deflate(48);
            List<Entity> entityList = pLevel.getEntities(pPlayer, aabb);
            DamageSource damageSource = pPlayer.damageSources().source(DamageTypes.EXPLOSION);
            if(entityList.isEmpty()) return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
            if(!pPlayer.isCreative()) pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 100);
            for(Entity entity :entityList) {
                if(entity instanceof EnderDragon enderDragon) {
                    enderDragon.hurt(enderDragon.head,damageSource,Integer.MAX_VALUE);
                } else if(entity instanceof LivingEntity) {
                    Entity light = new LightningBolt(EntityType.LIGHTNING_BOLT, pLevel);
                    light.moveTo(entity.getX(), entity.getY() + 4, entity.getZ());
                    pLevel.addFreshEntity(light);
                    entity.hurt(damageSource, Integer.MAX_VALUE);
                }
                if ((pPlayer.getInventory().getFreeSlot() != -1 && entity instanceof ItemEntity)||entity instanceof ExperienceOrb) {
                    entity.absMoveTo(pPlayer.getX(),pPlayer.getY(),pPlayer.getZ());
                }
            }
        }
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }
    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pStack.setHoverName(Component.empty().append(TextUtils.applyAllColor(Component.translatable("item.test_star.my_sword"))));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
