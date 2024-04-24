//package me.lyuxc.develop.item.items;
//
//import com.yogpc.qp.machines.PowerTile;
//import com.yogpc.qp.machines.workbench.TileWorkbench;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.context.UseOnContext;
//import net.minecraft.world.level.Level;
//import org.jetbrains.annotations.NotNull;
//
//public class SolidStateEnergy extends Item {
//    private final int energy;
//
//    public SolidStateEnergy(int energy) {
//        super(new Properties()
//                .stacksTo(16)
//        );
//        this.energy = energy;
//    }
//
//    @Override
//    public @NotNull InteractionResult useOn(UseOnContext pContext) {
//        Level level = pContext.getLevel();
//        if (level.getBlockEntity(pContext.getClickedPos()) instanceof TileWorkbench workbench) {
//            workbench.addEnergy(energy * PowerTile.ONE_FE, false);
//            pContext.getItemInHand().setCount(pContext.getItemInHand().getCount() - 1);
//        }
//        return super.useOn(pContext);
//    }
//}
