package tragicneko.tragicmc.worldgen.subbiome;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import tragicneko.tragicmc.TragicBlocks;

public class TragicSubBiome {

	public final int subID;
	public final String subName;
	
	public IBlockState subBlock;
	
	public static TragicSubBiome[] subBiomes = new TragicSubBiome[16];
	
	public static TragicSubBiome ferris = new TragicSubBiome(0, "ferris"); //heavy iron amounts, little of anything else
	public static TragicSubBiome corsite = new TragicSubBiome(1, "corsite").setSubBlock(TragicBlocks.Corsin); //very little total ores, that small amount is split between the more rare ores
	public static TragicSubBiome scyllis = new TragicSubBiome(2, "scyllis").setSubBlock(Blocks.leaves); //very easy to mine, contains very few ores
	public static TragicSubBiome relicanthic = new TragicSubBiome(3, "relicanthic").setSubBlock(TragicBlocks.BoneBlock); //contains high amounts of vanilla ores
	public static TragicSubBiome serris = new TragicSubBiome(4, "serris").setSubBlock(Blocks.sand); //easy to mine contains lots of less valuable ores, coal
	public static TragicSubBiome lumia = new TragicSubBiome(5, "lumia").setSubBlock(Blocks.glowstone); //generates with glowy blocks, contains a mostly normal mix of ores but may also contain xp ores
	public static TragicSubBiome epsis = new TragicSubBiome(6, "epsis").setSubBlock(Blocks.planks); //generates with two types of ores per chunk
	public static TragicSubBiome fyria = new TragicSubBiome(7, "fyria").setSubBlock(Blocks.netherrack); //generates with nether resources, rubies, sapphires, glowstone ore, quartz, slow sand
	public static TragicSubBiome xyxxi = new TragicSubBiome(8, "xyxxi").setSubBlock(Blocks.obsidian); //very hard to mine but has the highest amount of resources
	public static TragicSubBiome lerria = new TragicSubBiome(9, "lerria").setSubBlock(Blocks.dirt); //very easy to mine with lots of empty spaces and very few resources
	public static TragicSubBiome yaria = new TragicSubBiome(10, "yaria").setSubBlock(Blocks.pumpkin); //lots of single blocks of ores, very few veins
	
	public TragicSubBiome(int id, String name) {
		this.subID = id;
		this.subName = name;
		this.subBlock = Blocks.stone.getDefaultState();
		
		if (subBiomes[id] != null) throw new IllegalArgumentException("There is already a sub-biome with that ID!");
		subBiomes[id] = this;
	}
	
	public TragicSubBiome setSubBlock(Block block) {
		this.subBlock = block.getDefaultState();
		return this;
	}
	
}
