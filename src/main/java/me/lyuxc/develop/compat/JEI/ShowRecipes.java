package me.lyuxc.develop.compat.JEI;

import me.lyuxc.develop.keys.Keys;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class ShowRecipes {
    @SubscribeEvent
    public static void onRenderTickEvent(ClientTickEvent.Pre event) {
        if (Keys.jei_recipe.consumeClick()) {
            showJeiRecipeGUI(false);
        }
        if (Keys.jei_using.consumeClick()) {
            showJeiRecipeGUI(true);
        }
    }

    private static void showJeiRecipeGUI(boolean isUses) {
        Minecraft minecraft = Minecraft.getInstance();
        HitResult hitResult = minecraft.hitResult;
        if (hitResult != null && hitResult.getType() == HitResult.Type.BLOCK) {
            if (hitResult instanceof BlockHitResult result) {
                if (minecraft.player != null) {
                    Level level = minecraft.player.level();
                    ItemStack itemStack = level.getBlockState(result.getBlockPos()).getBlock().asItem().getDefaultInstance();
                    if (itemStack != ItemStack.EMPTY || itemStack != Items.AIR.getDefaultInstance()) {
                        if (isUses)
                            TestStarJEIPlugin.displayUses(itemStack);
                        else
                            TestStarJEIPlugin.displayRecipes(itemStack);
                    }
                }
            }
        }
    }
}
