package tragicneko.tragicmc.compatibility;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.util.Tuple;

public class CompatibilityThaumcraft {

	public static void init() {
		try
		{
			registerThaumcraftAspects(); //TODO add Thaumcraft aspects to the mod's blocks, items and mobs
		}
		catch (NoSuchMethodError e)
		{
			TragicMC.logError("Error while attempting to do Thaumcraft registrations, if Thaumcraft is not installed then this can safely be ignored.");
			return;
		}
	}

	@net.minecraftforge.fml.common.Optional.Method(modid="Thaumcraft")
	public static void registerThaumcraftAspects()
	{
		if (TragicConfig.getBoolean("allowMobs"))
		{
			if (TragicConfig.getBoolean("allowTox")) registerEntityAspects("TragicMC.Tox", getList(new Tuple(Aspect.PLANT, 3)));
			if (TragicConfig.getBoolean("allowInkling")) registerEntityAspects("TragicMC.Inkling", getList(new Tuple(Aspect.DARKNESS, 2), new Tuple(Aspect.AVERSION, 1), new Tuple(Aspect.MAN, 1)));
			if (TragicConfig.getBoolean("allowAbomination")) registerEntityAspects("TragicMC.Abomination", getList(new Tuple(Aspect.BEAST, 2), new Tuple(Aspect.COLD, 1)));
			if (TragicConfig.getBoolean("allowCryse")) registerEntityAspects("TragicMC.Cryse", getList(new Tuple(Aspect.COLD, 3)));
			if (TragicConfig.getBoolean("allowJabba")) registerEntityAspects("TragicMC.Jabba", getList(new Tuple(Aspect.FIRE, 3)));
			if (TragicConfig.getBoolean("allowRagr")) registerEntityAspects("TragicMC.Ragr", getList(new Tuple(Aspect.COLD, 1), new Tuple(Aspect.MAN, 1), new Tuple(Aspect.FIRE, 1)));
			if (TragicConfig.getBoolean("allowTragicNeko")) registerEntityAspects("TragicMC.TragicNeko", getList(new Tuple(Aspect.MAN, 3), new Tuple(Aspect.FIRE, 1)));
		}
		
		if (TragicConfig.getBoolean("allowNonMobItems") && TragicConfig.getBoolean("allowNonMobBlocks"))
		{
			registerObjectAspects(new ItemStack(TragicItems.Ash), getList(new Tuple(Aspect.DEATH, 1), new Tuple(Aspect.FIRE, 1)));
			registerObjectAspects(new ItemStack(TragicItems.EnchantedTears), getList(new Tuple(Aspect.LIFE, 2), new Tuple(Aspect.WATER, 1), new Tuple(Aspect.CRAFT, 1)));
		}
	}

	@net.minecraftforge.fml.common.Optional.Method(modid="Thaumcraft")
	public static void registerEntityAspects(String entityListName, AspectList list)
	{
		ThaumcraftApi.registerEntityTag(entityListName, list);
	}

	@net.minecraftforge.fml.common.Optional.Method(modid="Thaumcraft")
	public static void registerObjectAspects(ItemStack stack, AspectList list)
	{
		ThaumcraftApi.registerObjectTag(stack, list);
	}

	@net.minecraftforge.fml.common.Optional.Method(modid="Thaumcraft")
	public static AspectList getList(Tuple<Aspect, Integer>...aspects)
	{
		AspectList list = new AspectList();

		for (Tuple<Aspect, Integer> t : aspects)
		{
			list = list.add(t.getLeft(), t.getRight());
		}

		return list;
	}
}
