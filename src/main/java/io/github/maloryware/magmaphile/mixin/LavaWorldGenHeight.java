package io.github.maloryware.magmaphile.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.chunk.AquiferSampler;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Debug(export = true)
@Mixin(NoiseChunkGenerator.class)
public class LavaWorldGenHeight {

	// yes re-hardcoding is not the best decision but shrug
	@ModifyVariable(method = "createFluidLevelSampler",	at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/AquiferSampler$FluidLevel;<init>(ILnet/minecraft/block/BlockState;)V"), index = 1)
	private static AquiferSampler.FluidLevel lavaHeightLevel(AquiferSampler.FluidLevel value){
		return new AquiferSampler.FluidLevel(-117, Blocks.LAVA.getDefaultState());
	}

	// ditto
	// catches synthetic method
	@ModifyExpressionValue(method = "method_45509", at = @At(value = "CONSTANT", args = "intValue=-54"))
	private static int lava(int original){
		return -117;
	}

}