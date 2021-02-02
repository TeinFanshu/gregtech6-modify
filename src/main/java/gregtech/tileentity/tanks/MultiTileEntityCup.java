/**
 * Copyright (c) 2020 GregTech-6 Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech.tileentity.tanks;

import static gregapi.data.CS.*;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetFoodValues;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetItemUseAction;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetMaxItemUseDuration;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_IgnorePlayerCollisionWhenPlacing;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnEaten;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureFluid;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.tank.TileEntityBase10FluidContainerSyncSmall;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCup extends TileEntityBase10FluidContainerSyncSmall implements IMTE_IgnorePlayerCollisionWhenPlacing, IMTE_GetFoodValues, IMTE_OnEaten, IMTE_GetItemUseAction, IMTE_GetMaxItemUseDuration {
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return mTank.has() ? 6 : 5;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: box(aBlock, PX_P[ 5], PX_P[ 1], PX_P[ 6], PX_N[10], PX_N[11], PX_N[ 6]); return T;
		case  1: box(aBlock, PX_P[ 6], PX_P[ 1], PX_P[ 5], PX_N[ 6], PX_N[11], PX_N[10]); return T;
		case  2: box(aBlock, PX_P[10], PX_P[ 1], PX_P[ 6], PX_N[ 5], PX_N[11], PX_N[ 6]); return T;
		case  3: box(aBlock, PX_P[ 6], PX_P[ 1], PX_P[10], PX_N[ 6], PX_N[11], PX_N[ 5]); return T;
		case  4: box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[15], PX_N[ 6]); return T;
		case  5: box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[12], PX_N[ 6]); return T;
		}
		return F;
	}
	
	public static IIconContainer
	sTextureSides       = new Textures.BlockIcons.CustomIcon("machines/tanks/cup/colored/sides"),
	sTextureInsides     = new Textures.BlockIcons.CustomIcon("machines/tanks/cup/colored/insides"),
	sTextureTop         = new Textures.BlockIcons.CustomIcon("machines/tanks/cup/colored/top"),
	sTextureBottom      = new Textures.BlockIcons.CustomIcon("machines/tanks/cup/colored/bottom"),
	sOverlaySides       = new Textures.BlockIcons.CustomIcon("machines/tanks/cup/overlay/sides"),
	sOverlayInsides     = new Textures.BlockIcons.CustomIcon("machines/tanks/cup/overlay/insides"),
	sOverlayTop         = new Textures.BlockIcons.CustomIcon("machines/tanks/cup/overlay/top"),
	sOverlayBottom      = new Textures.BlockIcons.CustomIcon("machines/tanks/cup/overlay/bottom");
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch(aSide) {
		case SIDE_X_NEG: return aRenderPass == 2 ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlayInsides)) : BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlaySides));
		case SIDE_X_POS: return aRenderPass == 0 ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlayInsides)) : BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlaySides));
		case SIDE_Z_NEG: return aRenderPass == 3 ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlayInsides)) : BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlaySides));
		case SIDE_Z_POS: return aRenderPass == 1 ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlayInsides)) : BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlaySides));
		case SIDE_Y_NEG: return aRenderPass != 4 || aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureBottom, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlayBottom)) : null;
		case SIDE_Y_POS: return aRenderPass == 5 ? BlockTextureFluid.get(mTank) : BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlayTop));
		}
		return null;
	}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[11], PX_N[ 5]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[11], PX_N[ 5]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[11], PX_N[ 5]);}
	
	@Override public float getSurfaceDistance(byte aSide) {return SIDES_VERTICAL[aSide]?0.0F:PX_P[ 5];}
	
	@Override public boolean canFillWithRain() {return T;}
	@Override public boolean canDrinkFromSide(byte aSide) {return T;}
	@Override public boolean ignorePlayerCollisionWhenPlacing() {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.cup";}
}
