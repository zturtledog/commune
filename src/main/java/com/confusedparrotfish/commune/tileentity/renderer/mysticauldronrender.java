package com.confusedparrotfish.commune.tileentity.renderer;

import com.confusedparrotfish.commune.tileentity.mysticauldrontile;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class mysticauldronrender extends TileEntityRenderer<mysticauldrontile> {
    public mysticauldronrender(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(mysticauldrontile tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        ItemStack itemstack = new ItemStack(Items.ACACIA_BOAT);
        if (itemstack != ItemStack.EMPTY) {
            matrixStackIn.push();
            // matrixStackIn.translate(0.5D, 0.44921875D, 0.5D);
            // Direction direction1 = Direction.byHorizontalIndex((i + direction.getHorizontalIndex()) % 4);
            // float f = -direction1.getHorizontalAngle();
            // matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
            matrixStackIn.translate(0.5, 0.9375, 0.5);
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0F));
            matrixStackIn.scale(0.2375F, 0.2375F, 0.375F);

            int rots = 23;

            for (int i = 0; i < rots; i++) {
                matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(360/rots));

                matrixStackIn.push();
                matrixStackIn.translate(1.3, 0, 0.01*(i%3));//0.98563
                Minecraft.getInstance().getItemRenderer().renderItem(itemstack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
                matrixStackIn.pop();
            }
            
            matrixStackIn.pop();
         }
    }
}