package com.dinzeer.spudiversity.util;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ProjectileUtil {

    /**
     * 获取投射物的碰撞结果
     *
     * @param projectile 投射物实体
     * @param canHit 判断实体是否可击中的谓词
     * @return 碰撞结果
     */
    public static HitResult getHitResult(Projectile projectile, Predicate<Entity> canHit) {
        Vec3 start = projectile.position();
        Vec3 end = start.add(projectile.getDeltaMovement());

        // 首先检测方块碰撞
        HitResult blockHit = projectile.level().clip(new ClipContext(
                start,
                end,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                projectile
        ));

        if (blockHit.getType() != HitResult.Type.MISS) {
            // 如果碰到方块，调整终点为碰撞点
            end = blockHit.getLocation();
        }

        // 检测实体碰撞
        EntityHitResult entityHit = getEntityHitResult(projectile, start, end, canHit);

        // 优先返回实体碰撞结果
        if (entityHit != null) {
            return entityHit;
        }

        // 如果没有实体碰撞，返回方块碰撞结果
        return blockHit;
    }

    /**
     * 获取实体碰撞结果
     *
     * @param projectile 投射物实体
     * @param start 起始位置
     * @param end 结束位置
     * @param canHit 判断实体是否可击中的谓词
     * @return 实体碰撞结果
     */
    @Nullable
    public static EntityHitResult getEntityHitResult(Projectile projectile, Vec3 start, Vec3 end, Predicate<Entity> canHit) {
        Level level = projectile.level();
        double distance = start.distanceToSqr(end);
        Entity nearestHit = null;
        Vec3 nearestHitLocation = null;

        // 获取投射物路径附近的实体
        AABB aabb = projectile.getBoundingBox()
                .expandTowards(projectile.getDeltaMovement())
                .inflate(1.0); // 扩大检测范围

        List<Entity> entities = level.getEntities(projectile, aabb, canHit::test);

        for (Entity entity : entities) {
            AABB entityAABB = entity.getBoundingBox().inflate(0.3);
            Optional<Vec3> hitLocation = entityAABB.clip(start, end);

            if (hitLocation.isPresent()) {
                double hitDistance = start.distanceToSqr(hitLocation.get());

                if (hitDistance < distance) {
                    nearestHit = entity;
                    nearestHitLocation = hitLocation.get();
                    distance = hitDistance;
                }
            }
        }

        if (nearestHit != null) {
            return new EntityHitResult(nearestHit, nearestHitLocation);
        }

        return null;
    }

    /**
     * 计算抛射物旋转角度
     *
     * @param projectile 投射物实体
     * @return 旋转角度 (yRot, xRot)
     */
    public static float[] getProjectileRotation(Projectile projectile) {
        Vec3 deltaMovement = projectile.getDeltaMovement();
        double horizontalDistance = Math.sqrt(deltaMovement.x * deltaMovement.x + deltaMovement.z * deltaMovement.z);
        float yRot = (float)(Math.atan2(deltaMovement.z, deltaMovement.x) * (180F / Math.PI)) - 90.0F;
        float xRot = (float)(-Math.atan2(deltaMovement.y, horizontalDistance) * (180F / Math.PI));
        return new float[]{yRot, xRot};
    }

    /**
     * 应用重力效果
     *
     * @param projectile 投射物实体
     * @param gravity 重力系数 (0.0-1.0)
     */
    public static void applyGravity(Projectile projectile, float gravity) {
        Vec3 deltaMovement = projectile.getDeltaMovement();
        projectile.setDeltaMovement(
                deltaMovement.x,
                deltaMovement.y - (double)gravity,
                deltaMovement.z
        );
    }

    /**
     * 检查投射物是否在液体中
     *
     * @param projectile 投射物实体
     * @return 是否在液体中
     */
    public static boolean isInFluid(Projectile projectile) {
        return projectile.isInWaterOrBubble() ||
                projectile.isInLava();
    }
}
