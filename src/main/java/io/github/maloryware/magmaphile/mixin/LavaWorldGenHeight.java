package io.github.maloryware.magmaphile.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.maloryware.magmaphile.Config;
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


	@ModifyVariable(method = "createFluidLevelSampler",	at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/AquiferSampler$FluidLevel;<init>(ILnet/minecraft/block/BlockState;)V"), index = 1)
	private static AquiferSampler.FluidLevel lavaHeightLevel(AquiferSampler.FluidLevel value){
		return new AquiferSampler.FluidLevel(Config.lava_max_height_gen, Blocks.LAVA.getDefaultState());
	}

	// catches synthetic method
	@ModifyExpressionValue(method = "method_45509", at = @At(value = "CONSTANT", args = "intValue=-54"))
	private static int lavaGenHigherBound(int original){
		return Config.override_lava_gen ? Config.lava_max_height_gen : original;
	}

	// VERY sloppy, but couldn't get expressions going properly, so this'll have to do

	@SuppressWarnings("LocalMayBeArgsOnly")
    @ModifyReturnValue(method = "method_45509", at = @At("RETURN"))
	private static AquiferSampler.FluidLevel lavaGenLowerBound(AquiferSampler.FluidLevel original,
															   @Local(ordinal = 2) int y,
															   @Local(ordinal = 0) AquiferSampler.FluidLevel fluidLevel,
															   @Local(ordinal = 1) AquiferSampler.FluidLevel fluidLevel2){

		if (!Config.override_lava_gen) { return original; }
		boolean check = Config.toggle_lower_bound
				? y < Config.lava_max_height_gen
				: y < Config.lava_max_height_gen && y > Config.lava_min_height_gen;

        return check ? fluidLevel : fluidLevel2;

	}
}