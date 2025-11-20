package com.dinzeer.spudiversity.mixin;

/* 跟踪玩家破坏方块事件 */
import com.dinzeer.spudiversity.effect.FurnaceBlessing;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(ServerPlayerGameMode.class)
public class ServerPlayerGameModeMixins {

	@Shadow
	@Final
	protected ServerPlayer player;

	@Inject(at = @At("HEAD"), method = "destroyBlock")
	public void destroyBlock_markPlayerBeginBreak(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		FurnaceBlessing.pushPlayer(player, pos);
	}


	@Inject(at = @At("RETURN"), method = "destroyBlock")
	public void destroyBlock_markPlayerEndBreak(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		FurnaceBlessing.popPlayer(player);
	}

}
