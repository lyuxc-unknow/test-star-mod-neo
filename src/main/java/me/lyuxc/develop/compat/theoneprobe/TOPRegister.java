package me.lyuxc.develop.compat.theoneprobe;

import mcjty.theoneprobe.api.ITheOneProbe;
import me.lyuxc.develop.compat.theoneprobe.MI.MachineComponentProvider;
import me.lyuxc.develop.compat.theoneprobe.MI.MachineProgressProvider;
import me.lyuxc.develop.compat.theoneprobe.MI.OverclockProvider;
import me.lyuxc.develop.compat.theoneprobe.MI.PipeDataProvider;
import me.lyuxc.develop.compat.theoneprobe.QuarryPlus.QuarryPlus;

import java.util.function.Function;

public class TOPRegister implements Function<ITheOneProbe,Void> {
    @Override
    public Void apply(ITheOneProbe iTheOneProbe) {
        iTheOneProbe.registerProvider(new QuarryPlus());
        iTheOneProbe.registerProvider(new PipeDataProvider());
        iTheOneProbe.registerProvider(new OverclockProvider());
        iTheOneProbe.registerProvider(new MachineProgressProvider());
        iTheOneProbe.registerProvider(new MachineComponentProvider());
        return null;
    }
}
