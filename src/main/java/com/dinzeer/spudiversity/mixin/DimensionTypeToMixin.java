package com.dinzeer.spudiversity.mixin;

import com.dinzeer.spudiversity.util.ILevel;
import com.dinzeer.spudiversity.util.Returnner;
import net.minecraft.util.Mth;
import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.OptionalLong;

@Mixin(DimensionType.class)
public abstract class DimensionTypeToMixin implements ILevel {

      @Shadow
      public abstract OptionalLong fixedTime();

      @Inject(method = "timeOfDay", at = @At("HEAD"),cancellable = true)
      public void updateTimeOfDay(long originTime, CallbackInfoReturnable<Float> cir) {
          if (Returnner.getDois()){
              float time= fixedTime().orElse(originTime);
              if (dayTimeTarget>0){
                  time= dayTimeTarget;
              }
              double d0 = Mth.frac((double)time / 24000.0D - 0.25D);
              double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
              cir.setReturnValue((float)(d0 * 2.0D + d1) / 3.0F);
          }
      }



    @Unique
    private long dayTimeTarget = -1;
    @Override
    public void updateTimeOfDay(long target) {
    this.dayTimeTarget = target;
    }

}
