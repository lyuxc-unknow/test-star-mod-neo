package me.lyuxc.develop.compat.theoneprobe;

import mcjty.theoneprobe.api.ITheOneProbe;
import me.lyuxc.develop.compat.theoneprobe.MI.*;
import me.lyuxc.develop.compat.theoneprobe.TestStar.SuperGeneratorProvider;

import java.util.function.Function;

public class TOPRegister implements Function<ITheOneProbe, Void> {
    @Override
    public Void apply(ITheOneProbe iTheOneProbe) {
//        iTheOneProbe.registerProvider(new QuarryPlus());
        iTheOneProbe.registerProvider(new OverclockProvider());
        iTheOneProbe.registerProvider(new NetworkTierProvider());
        iTheOneProbe.registerProvider(new MachineTierProvider());
        iTheOneProbe.registerProvider(new PipeDataProvider());
        iTheOneProbe.registerProvider(new MultiblockProvider());
        iTheOneProbe.registerProvider(new MachineProgressProvider());
        iTheOneProbe.registerProvider(new MachineComponentProvider());
        iTheOneProbe.registerProvider(new SuperGeneratorProvider());
        return null;
    }
}
