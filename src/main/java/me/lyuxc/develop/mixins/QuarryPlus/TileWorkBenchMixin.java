//package me.lyuxc.develop.mixins.QuarryPlus;
//
//import com.yogpc.qp.QuarryPlus;
//import com.yogpc.qp.machines.CheckerLog;
//import com.yogpc.qp.machines.HasItemHandler;
//import com.yogpc.qp.machines.InvUtils;
//import com.yogpc.qp.machines.PowerTile;
//import com.yogpc.qp.machines.workbench.TileWorkbench;
//import com.yogpc.qp.machines.workbench.WorkbenchRecipe;
//import com.yogpc.qp.packet.ClientSync;
//import com.yogpc.qp.packet.ClientSyncMessage;
//import com.yogpc.qp.packet.PacketHandler;
//import net.minecraft.core.BlockPos;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.Container;
//import net.minecraft.world.Containers;
//import net.minecraft.world.MenuProvider;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.block.state.BlockState;
//import org.jetbrains.annotations.NotNull;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//
//import java.util.List;
//
//@Mixin(TileWorkbench.class)
//public abstract class TileWorkBenchMixin extends PowerTile implements Container, MenuProvider, CheckerLog, HasItemHandler, ClientSync {
//    @Shadow
//    public boolean workContinue;
//    @Shadow
//    @Final
//    List<ItemStack> ingredientInventory;
//    @Shadow
//    @Final
//    private List<Player> openPlayers;
//    @Shadow
//    private Runnable initRecipeTask;
//    @Shadow
//    private WorkbenchRecipe currentRecipe;
//    @Shadow
//    private ResourceLocation currentRecipeId;
//
//    public TileWorkBenchMixin(BlockEntityType<?> type, @NotNull BlockPos pos, BlockState state) {
//        super(type, pos, state);
//    }
//
//    @Shadow
//    protected abstract void updateRecipeOutputs();
//
//    @Shadow
//    public abstract void setCurrentRecipe(ResourceLocation recipeName);
//
//    /**
//     * @author lyuxc
//     * @reason Removed the automatic increase of 5 energy per tick
//     */
//    @Overwrite()
//    public void tick() {
//        if (this.enabled && this.level != null && !this.level.isClientSide) {
//            if (this.initRecipeTask != null) {
//                this.initRecipeTask.run();
//                this.initRecipeTask = null;
//            }
//
//            if (this.currentRecipe.hasContent() && this.currentRecipe.getRequiredEnergy() <= this.getEnergy()) {
//                this.useEnergy(this.currentRecipe.getRequiredEnergy(), PowerTile.Reason.WORKBENCH, true);
//                ItemStack created = this.currentRecipe.output;
//                ItemStack toSpawnInWorld = InvUtils.injectToNearTile(this.level, this.getBlockPos(), created);
//                if (!toSpawnInWorld.isEmpty()) {
//                    Containers.dropItemStack(this.level, this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ(), toSpawnInWorld);
//                }
//
//                this.currentRecipe.consumeItems(this.ingredientInventory);
//                this.updateRecipeOutputs();
//                this.setCurrentRecipe(this.workContinue ? this.currentRecipeId : new ResourceLocation("quarryplus", "builtin_dummy"));
//            } else if (QuarryPlus.config.common.noEnergy.get()) {
//                this.addEnergy(this.currentRecipe.getRequiredEnergy() / 200L, false);
//            }
//
//            if (!this.openPlayers.isEmpty()) {
//                PacketHandler.sendToClient(new ClientSyncMessage(this), this.level);
//            }
//        }
//    }
//}
