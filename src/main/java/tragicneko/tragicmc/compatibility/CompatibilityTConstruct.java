package tragicneko.tragicmc.compatibility;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class CompatibilityTConstruct {

	public static void sendIMC() {
		//default: Fluid fluid = new Fluid("fluid", new ResourceLocation("tconstruct:blocks/fluids/molten_metal"), new ResourceLocation("tconstruct:blocks/fluids/molten_metal_flow"));
		//fluid, name
		//ore, name
		//toolForge, boolean
		//FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", new NBTTagCompound());
		
		Fluid ruby = new Fluid("ruby", new ResourceLocation("tconstruct:blocks/fluids/molten_metal"), new ResourceLocation("tconstruct:blocks/fluids/molten_metal_flow")) {
			@Override
			public int getColor() {
				return 0xFFA42323;
			}
		};
		if (!FluidRegistry.isFluidRegistered("ruby")) FluidRegistry.registerFluid(ruby);
		FluidRegistry.addBucketForFluid(ruby);
		
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("fluid", ruby.getName());
		tag.setString("ore", "Ruby");
		tag.setBoolean("toolForge", false);
		FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);
		
		Fluid nekite = new Fluid("nekite", new ResourceLocation("tconstruct:blocks/fluids/molten_metal"), new ResourceLocation("tconstruct:blocks/fluids/molten_metal_flow")) {
			@Override
			public int getColor() {
				return 0xFFD74848;
			}
		};
		if (!FluidRegistry.isFluidRegistered("nekite")) FluidRegistry.registerFluid(nekite);
		FluidRegistry.addBucketForFluid(nekite);
		
		tag = new NBTTagCompound();
		tag.setString("fluid", nekite.getName());
		tag.setString("ore", "Nekite");
		tag.setBoolean("toolForge", true);
		FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);
	} 
}
