package me.lyuxc.develop.item.items;

import me.lyuxc.develop.Star;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class Light extends Item {
    public Light(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if (pLevel instanceof ServerLevel _level) {
            Vec3 playerPos = pPlayer.getEyePosition();
            Entity light = new LightningBolt(EntityType.LIGHTNING_BOLT, _level);
            light.moveTo(Star.random.nextInt(5) > 2 ? playerPos.x + Star.random.nextInt(5) : playerPos.x - Star.random.nextInt(5), playerPos.y - 1, Star.random.nextInt(5) > 2 ? playerPos.z + Star.random.nextInt(5) : playerPos.z - Star.random.nextInt(5), _level.getRandom().nextFloat() * 360F, 0);
            pLevel.addFreshEntity(light);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
