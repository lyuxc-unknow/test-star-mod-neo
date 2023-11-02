package me.lyuxc.develop.item.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Deprecated
public class ArmorBoot extends ArmorItem {
    public ArmorBoot(Properties pProperties) {
        super(new ArmorMaterial() {
            @Override
            public int getDurabilityForType(@NotNull Type pType) {
                return 0;
            }

            @Override
            public int getDefenseForType(@NotNull Type pType) {
                return 0;
            }

            @Override
            public int getEnchantmentValue() {
                return 30;
            }

            @Override
            public SoundEvent getEquipSound() {
                return null;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return null;
            }

            @Override
            public String getName() {
                return "boot";
            }

            @Override
            public float getToughness() {
                return 0;
            }

            @Override
            public float getKnockbackResistance() {
                return 0;
            }
        }, Type.BOOTS, pProperties);
    }

    @Deprecated
    public static class Boots extends ArmorBoot {
        public Boots(Properties pProperties) {
            super(pProperties);
        }

        @Override
        public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return super.getArmorTexture(stack, entity, slot, type);
        }

        @Override
        public void onArmorTick(ItemStack stack, Level level, Player player) {
            Objects.requireNonNull(player.getAttributes().getInstance(NeoForgeMod.ENTITY_GRAVITY.get())).setBaseValue(0.08);
            super.onArmorTick(stack, level, player);
        }
    }
}
