package me.lyuxc.develop.item.tools;

import me.lyuxc.develop.Tiers;
import me.lyuxc.develop.Variables;
import me.lyuxc.develop.utils.TextUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.ToolAction;
import net.neoforged.neoforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AllOurposeTool extends DiggerItem {
    private final Tier TIER;
    public AllOurposeTool(Tier pTier) {
        super(4,-2,pTier,
                TagKey.create(Registries.BLOCK,new ResourceLocation(Variables.MOD_ID,"mineable/aotools")),
                new Properties().durability( pTier.getUses() * 5));
        this.TIER = pTier;
    }

    @Override
    public boolean canPerformAction(@NotNull ItemStack stack, @NotNull ToolAction toolAction) {
        if(toolAction == ToolActions.AXE_DIG || toolAction == ToolActions.PICKAXE_DIG || toolAction == ToolActions.SHOVEL_DIG || toolAction == ToolActions.SWORD_DIG || toolAction == ToolActions.SHEARS_DIG || toolAction == ToolActions.HOE_DIG)
            return true;
        return super.canPerformAction(stack, toolAction);
    }

//    @Override
//    public @NotNull InteractionResult useOn(@NotNull UseOnContext pContext) {
//        if (pContext.getPlayer() == null) {
//            return InteractionResult.FAIL;
//        }
//        if (!pContext.getPlayer().isCrouching()) {
//            return Items.NETHERITE_HOE.useOn(pContext);
//        }
//        return Items.NETHERITE_SHOVEL.useOn(pContext);
//    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack, @NotNull Enchantment enchantment) {
        return super.canApplyAtEnchantingTable(stack, enchantment) || enchantment.category.canEnchant(Items.DIAMOND_SWORD);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if(TIER == Tiers.LEVEL_INF) {
            pStack.setHoverName(Component.empty().append(TextUtils.applyAllColor(Component.translatable("item.test_star.allinone_tool_inf"))));
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if (!pLevel.isClientSide) {
            if(TIER == Tiers.LEVEL_INF) {
                AABB aabb = pPlayer.getBoundingBox().deflate(48);
                List<Entity> entityList = pLevel.getEntities(pPlayer, aabb);
                DamageSource damageSource = pPlayer.damageSources().source(DamageTypes.EXPLOSION);
                if(entityList.isEmpty()) return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
                if(!pPlayer.isCreative()) pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 100);
                for(Entity entity :entityList) {
                    if ((pPlayer.getInventory().getFreeSlot() != -1 && entity instanceof ItemEntity)||entity instanceof ExperienceOrb) {
                        entity.absMoveTo(pPlayer.getX(),pPlayer.getY(),pPlayer.getZ());
                    }else if(entity instanceof EnderDragon enderDragon) {
                        enderDragon.hurt(enderDragon.head,damageSource,Integer.MAX_VALUE);
                    } else /*if(entity instanceof LivingEntity)*/ {
                        Entity light = new LightningBolt(EntityType.LIGHTNING_BOLT, pLevel);
                        light.moveTo(entity.getX(), entity.getY() + 4, entity.getZ());
                        pLevel.addFreshEntity(light);
                        entity.hurt(damageSource, Integer.MAX_VALUE);
                    }
                }
            }
        }
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    public @NotNull Tier getTier() {
        return TIER;
    }
}
