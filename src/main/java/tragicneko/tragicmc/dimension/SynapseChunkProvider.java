package tragicneko.tragicmc.dimension;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraftforge.event.terraingen.TerrainGen;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.worldgen.CircuitWorldGen;
import tragicneko.tragicmc.worldgen.HangingCoralWorldGen;
import tragicneko.tragicmc.worldgen.SynapseVariantGen;

public class SynapseChunkProvider implements IChunkProvider
{
	private Random synapseRNG;
	private NoiseGeneratorOctaves netherNoiseGen1;
	private NoiseGeneratorOctaves netherNoiseGen2;
	private NoiseGeneratorOctaves netherNoiseGen3;
	private NoiseGeneratorOctaves slowsandGravelNoiseGen;
	private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
	public NoiseGeneratorOctaves netherNoiseGen6;
	public NoiseGeneratorOctaves netherNoiseGen7;
	private World worldObj;
	private double[] noiseField;
	private double[] slowsandNoise = new double[256];
	private double[] gravelNoise = new double[256];
	private double[] netherrackExclusivityNoise = new double[256];
	private MapGenBase netherCaveGenerator = new MapGenCavesHell();
	double[] noiseData1;
	double[] noiseData2;
	double[] noiseData3;
	double[] noiseData4;
	double[] noiseData5;
	
	private HangingCoralWorldGen coralGen;
	private HangingCoralWorldGen coralGen2;
	private CircuitWorldGen circuitGen;
	private SynapseVariantGen circuitVarGen;

	public SynapseChunkProvider(World p_i2005_1_, long p_i2005_2_)
	{
		this.worldObj = p_i2005_1_;
		this.synapseRNG = new Random(p_i2005_2_);
		this.netherNoiseGen1 = new NoiseGeneratorOctaves(this.synapseRNG, 16);
		this.netherNoiseGen2 = new NoiseGeneratorOctaves(this.synapseRNG, 16);
		this.netherNoiseGen3 = new NoiseGeneratorOctaves(this.synapseRNG, 8);
		this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.synapseRNG, 4);
		this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.synapseRNG, 4);
		this.netherNoiseGen6 = new NoiseGeneratorOctaves(this.synapseRNG, 10);
		this.netherNoiseGen7 = new NoiseGeneratorOctaves(this.synapseRNG, 16);

		NoiseGenerator[] noiseGens = {netherNoiseGen1, netherNoiseGen2, netherNoiseGen3, slowsandGravelNoiseGen, netherrackExculsivityNoiseGen, netherNoiseGen6, netherNoiseGen7};
		this.netherNoiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
		this.netherNoiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
		this.netherNoiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
		this.slowsandGravelNoiseGen = (NoiseGeneratorOctaves)noiseGens[3];
		this.netherrackExculsivityNoiseGen = (NoiseGeneratorOctaves)noiseGens[4];
		this.netherNoiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
		this.netherNoiseGen7 = (NoiseGeneratorOctaves)noiseGens[6];
		
		this.circuitGen = new CircuitWorldGen();
		this.coralGen = new HangingCoralWorldGen((byte) 4, 32, 1000, TragicBlocks.Conduit, (byte) 0);
		this.coralGen2 = new HangingCoralWorldGen((byte) 3, 12, 800, TragicBlocks.Conduit, (byte) 0);
		this.circuitVarGen = new SynapseVariantGen();
	}

	public void primeChunkNoise(int x, int z, ChunkPrimer primer)
    {
        byte b0 = 4;
        byte b1 = 32;
        int k = b0 + 1;
        byte b2 = 17;
        int l = b0 + 1;
        this.noiseField = this.initializeNoiseField(this.noiseField, x * b0, 0, z * b0, k, b2, l);

        for (int i1 = 0; i1 < b0; ++i1)
        {
            for (int j1 = 0; j1 < b0; ++j1)
            {
                for (int k1 = 0; k1 < 16; ++k1)
                {
                    double d0 = 0.125D;
                    double d1 = this.noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 0];
                    double d2 = this.noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 0];
                    double d3 = this.noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 0];
                    double d4 = this.noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 0];
                    double d5 = (this.noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 1] - d1) * d0;
                    double d6 = (this.noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 1] - d2) * d0;
                    double d7 = (this.noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 1] - d3) * d0;
                    double d8 = (this.noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 1] - d4) * d0;

                    for (int l1 = 0; l1 < 8; ++l1)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i2 = 0; i2 < 4; ++i2)
                        {
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;

                            for (int j2 = 0; j2 < 4; ++j2)
                            {
                                IBlockState iblockstate = null;

                                if (k1 * 8 + l1 < b1)
                                {
                                    iblockstate = Blocks.air.getDefaultState();
                                }

                                if (d15 > 0.0D)
                                {
                                    iblockstate = TragicBlocks.CircuitBlock.getDefaultState();
                                }

                                int k2 = i2 + i1 * 4;
                                int l2 = l1 + k1 * 8;
                                int i3 = j2 + j1 * 4;
                                primer.setBlockState(k2, l2, i3, iblockstate);
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

	public void sortChunkNoise(int x, int z, ChunkPrimer primer)
    {
        byte b0 = 64;
        double d0 = 0.03125D;
        this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, x * 16, z * 16, 0, 16, 16, 1, d0, d0, 1.0D);
        this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, x * 16, 109, z * 16, 16, 1, 16, d0, 1.0D, d0);
        this.netherrackExclusivityNoise = this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.netherrackExclusivityNoise, x * 16, z * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

        for (int k = 0; k < 16; ++k)
        {
            for (int l = 0; l < 16; ++l)
            {
                boolean flag = this.slowsandNoise[k + l * 16] + this.synapseRNG.nextDouble() * 0.2D > 0.0D;
                boolean flag1 = this.gravelNoise[k + l * 16] + this.synapseRNG.nextDouble() * 0.2D > 0.0D;
                int i1 = (int)(this.netherrackExclusivityNoise[k + l * 16] / 3.0D + 3.0D + this.synapseRNG.nextDouble() * 0.25D);
                int j1 = -1;
                IBlockState iblockstate = TragicBlocks.CircuitBlock.getDefaultState();
                IBlockState iblockstate1 = TragicBlocks.CircuitBlock.getDefaultState();

                for (int k1 = 127; k1 >= 0; --k1)
                {
                    if (k1 < 106 - this.synapseRNG.nextInt(5) && k1 > 14 + this.synapseRNG.nextInt(5))
                    {
                        IBlockState iblockstate2 = primer.getBlockState(l, k1, k);

                        if (iblockstate2.getBlock() != null && iblockstate2.getBlock().getMaterial() != Material.air)
                        {
                            if (iblockstate2.getBlock() == TragicBlocks.CircuitBlock)
                            {
                                if (j1 == -1)
                                {
                                    if (i1 <= 0)
                                    {
                                        iblockstate = null;
                                        iblockstate1 = TragicBlocks.CircuitBlock.getDefaultState();
                                    }
                                    else if (k1 >= b0 - 4 && k1 <= b0 + 1)
                                    {
                                        iblockstate = TragicBlocks.CircuitBlock.getDefaultState();
                                        iblockstate1 = TragicBlocks.CircuitBlock.getDefaultState();
                                    }

                                    if (k1 < b0 && (iblockstate == null || iblockstate.getBlock().getMaterial() == Material.air))
                                    {
                                        iblockstate = null;
                                    }

                                    j1 = i1;

                                    if (k1 >= b0 - 1)
                                    {
                                        primer.setBlockState(l, k1, k, iblockstate);
                                    }
                                    else
                                    {
                                        primer.setBlockState(l, k1, k, iblockstate1);
                                    }
                                }
                                else if (j1 > 0)
                                {
                                    --j1;
                                    primer.setBlockState(l, k1, k, iblockstate1);
                                }
                            }
                        }
                        else
                        {
                            j1 = -1;
                        }
                    }
                    else
                    {
                        primer.setBlockState(l, k1, k, TragicConfig.allowDigitalSeaGen || synapseRNG.nextInt(16) != 0 ? TragicBlocks.DigitalSea.getDefaultState() : Blocks.air.getDefaultState());
                    }
                }
            }
        }
    }

	public Chunk provideChunk(int x, int z)
    {
        this.synapseRNG.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.primeChunkNoise(x, z, chunkprimer);
        this.sortChunkNoise(x, z, chunkprimer);
        this.netherCaveGenerator.generate(this, this.worldObj, x, z, chunkprimer);

        Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
        BiomeGenBase[] abiomegenbase = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, x * 16, z * 16, 16, 16);
        byte[] abyte = chunk.getBiomeArray();

        for (int k = 0; k < abyte.length; ++k)
        {
            abyte[k] = (byte)abiomegenbase[k].biomeID;
        }

        chunk.resetRelightChecks();
        return chunk;
    }

	private double[] initializeNoiseField(double[] p_73164_1_, int p_73164_2_, int p_73164_3_, int p_73164_4_, int p_73164_5_, int p_73164_6_, int p_73164_7_)
    {
        if (p_73164_1_ == null)
        {
            p_73164_1_ = new double[p_73164_5_ * p_73164_6_ * p_73164_7_];
        }

        double d0 = 684.412D;
        double d1 = 2053.236D;
        this.noiseData4 = this.netherNoiseGen6.generateNoiseOctaves(this.noiseData4, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 1.0D, 0.0D, 1.0D);
        this.noiseData5 = this.netherNoiseGen7.generateNoiseOctaves(this.noiseData5, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 100.0D, 0.0D, 100.0D);
        this.noiseData1 = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0 / 80.0D, d1 / 60.0D, d0 / 80.0D);
        this.noiseData2 = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0, d1, d0);
        this.noiseData3 = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0, d1, d0);
        int k1 = 0;
        double[] adouble1 = new double[p_73164_6_];
        int l1;

        for (l1 = 0; l1 < p_73164_6_; ++l1)
        {
            adouble1[l1] = Math.cos((double)l1 * Math.PI * 6.0D / (double)p_73164_6_) * 2.0D;
            double d2 = (double)l1;

            if (l1 > p_73164_6_ / 2)
            {
                d2 = (double)(p_73164_6_ - 1 - l1);
            }

            if (d2 < 4.0D)
            {
                d2 = 4.0D - d2;
                adouble1[l1] -= d2 * d2 * d2 * 10.0D;
            }
        }

        for (l1 = 0; l1 < p_73164_5_; ++l1)
        {
            for (int j2 = 0; j2 < p_73164_7_; ++j2)
            {
                double d3 = 0.0D;

                for (int i2 = 0; i2 < p_73164_6_; ++i2)
                {
                    double d4 = 0.0D;
                    double d5 = adouble1[i2];
                    double d6 = this.noiseData2[k1] / 512.0D;
                    double d7 = this.noiseData3[k1] / 512.0D;
                    double d8 = (this.noiseData1[k1] / 10.0D + 1.0D) / 2.0D;

                    if (d8 < 0.0D)
                    {
                        d4 = d6;
                    }
                    else if (d8 > 1.0D)
                    {
                        d4 = d7;
                    }
                    else
                    {
                        d4 = d6 + (d7 - d6) * d8;
                    }

                    d4 -= d5;
                    double d9;

                    if (i2 > p_73164_6_ - 4)
                    {
                        d9 = (double)((float)(i2 - (p_73164_6_ - 4)) / 3.0F);
                        d4 = d4 * (1.0D - d9) + -10.0D * d9;
                    }

                    if ((double)i2 < d3)
                    {
                        d9 = (d3 - (double)i2) / 4.0D;
                        d9 = MathHelper.clamp_double(d9, 0.0D, 1.0D);
                        d4 = d4 * (1.0D - d9) + -10.0D * d9;
                    }

                    p_73164_1_[k1] = d4;
                    ++k1;
                }
            }
        }

        return p_73164_1_;
    }

	 @Override
	 public boolean chunkExists(int p_73149_1_, int p_73149_2_)
	 {
		 return true;
	 }

	 @Override
	 public void populate(IChunkProvider p_73153_1_, int x, int z)
	 {
		 BlockFalling.fallInstantly = true;
		 int k = x * 16;
		 int l = z * 16;
		 BlockPos pos = new BlockPos(k, 0, l);
		 BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(pos);
		 biomegenbase.decorate(this.worldObj, this.worldObj.rand, pos);
		 int a;
		 int b;
		 int c;
		 byte i;
		 
		 this.circuitGen.generate(this.synapseRNG, x, z, this.worldObj);
		 
		 if (TragicConfig.allowSynapseVariants && this.synapseRNG.nextInt(TragicConfig.synapseVariantChance) == 0) this.circuitVarGen.generate(this.synapseRNG, x, z, this.worldObj);

		 for (i = 0; i < 4; i++)
		 {
			 a = k + this.synapseRNG.nextInt(16) + 8;
			 c = this.synapseRNG.nextInt(100) + 10;
			 b = l + this.synapseRNG.nextInt(16) + 8;
			 this.coralGen.generate(this.worldObj, this.synapseRNG, new BlockPos(a, c, b));
		 }

		 for (i = 0; i < 6; ++i)
		 {
			 a = k + this.synapseRNG.nextInt(16) + 8;
			 c = this.synapseRNG.nextInt(100) + 10;
			 b = l + this.synapseRNG.nextInt(16) + 8;
			 this.coralGen2.generate(this.worldObj, this.synapseRNG, new BlockPos(a, c, b));
		 }
		 
		 BlockFalling.fallInstantly = false;
	 }
	 
	 @Override
	 public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
	     return false;
	 }
	 
	 @Override
	 public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
		 return true;
	 }

	 @Override
	 public void saveExtraData() {}

	 @Override
	 public boolean unloadQueuedChunks() {
		 return false;
	 }

	 @Override
	 public boolean canSave() {
		 return true;
	 }

	 @Override
	 public String makeString() {
		 return "SynapseRandomLevelSource";
	 }

	 @Override
	 public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType type, BlockPos pos) {
		 BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(pos);
		 return biomegenbase.getSpawnableList(type);
	 }

	 public BlockPos getStrongholdGen(World world, String pieceName, BlockPos pos) {
	    return null;
	 }

	 @Override
	 public int getLoadedChunkCount() {
		 return 0;
	 }

	 @Override
	 public void recreateStructures(Chunk chunk, int x, int z) {}
	 
	 @Override
	 public Chunk provideChunk(BlockPos blockPosIn)
	 {
	    return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
	 }
}