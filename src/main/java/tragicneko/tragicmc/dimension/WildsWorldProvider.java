package tragicneko.tragicmc.dimension;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.client.ClientProxy;
import tragicneko.tragicmc.worldgen.subbiome.TragicSubBiome;

public class WildsWorldProvider extends WorldProvider
{
	public WildsWorldProvider()
	{
		this.dimensionId = TragicConfig.collisionID;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IRenderHandler getSkyRenderer()
	{
		return ClientProxy.collisionSkyRenderer;
	}

	@Override
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WildsChunkManager(this.worldObj);
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new WildsChunkProvider(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
	}

	@Override
	public String getSaveFolder()
	{
		return "Wilds";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int x, int z)
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isSkyColored()
	{
		return false;
	}

	@Override
	public boolean canRespawnHere()
	{
		return TragicConfig.allowCollisionRespawn;
	}

	@Override
	public boolean isSurfaceWorld()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getCloudHeight()
	{
		return 240.0F;
	}

	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2)
	{
		Block block = this.worldObj.getBlockState(this.worldObj.getTopSolidOrLiquidBlock(new BlockPos(par1, 0, par2))).getBlock();
		return block.isOpaqueCube();
	}

	@Override
	public BlockPos getRandomizedSpawnPoint()
	{
		BlockPos chunkcoordinates = this.worldObj.getSpawnPoint();

		boolean isAdventure = worldObj.getWorldInfo().getGameType() == GameType.ADVENTURE;
		int spawnFuzz = this.worldObj.getWorldInfo().getTerrainType().getSpawnFuzz();
		int spawnFuzzHalf = spawnFuzz / 2;

		if (!hasNoSky && !isAdventure)
		{
			chunkcoordinates.add(this.worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf, 0,
				this.worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf);
			chunkcoordinates = this.worldObj.getTopSolidOrLiquidBlock(chunkcoordinates);
		}

		return chunkcoordinates;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getVoidFogYFactor()
	{
		return 0.01;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float[] calcSunriseSunsetColors(float f, float f1)
	{
		float[] colors = new float[4];
		float f2 = 0.4F;
		float f3 = MathHelper.cos(f * 3.141593F * 2.0F);
		float f4 = -0.0F;
		if (f3 >= f4 - f2 && f3 <= f4 + f2)
		{
			float f5 = (f3 - f4) / f2 * 0.5F + 0.5F;
			float f6 = 1.0F - (1.0F - MathHelper.sin(f5 * 3.141593F)) * 0.99F;
			f6 *= f6;
			colors[0] = f5 * 0.3F + 0.7F;
			colors[1] = f5 * f5 * 0.7F + 0.2F;
			colors[2] = 0.2F;
			colors[3] = f6;
			return colors;
		}
		return null;
	}

	@Override
	public int getMoonPhase(long par1)
	{
		return 0;
	}

	@Override
	public float calculateCelestialAngle(long time, float f)
	{
		int j = (int) (17000L % 24000L);
		float f1 = (j + f) / 24000.0F - 0.25F;
		if (f1 < 0.0F)
		{
			f1 += 1.0F;
		}
		if (f1 > 1.0F)
		{
			f1 -= 1.0F;
		}
		float f2 = f1;
		f1 = 1.0F - (float) ((Math.cos(f1 * 3.141592653589793D) + 1.0D) / 2.0D);
		f1 = f2 + (f1 - f2) / 3.0F;
		return f1;
	}


	@Override
	protected void generateLightBrightnessTable()
	{
		float f = 0.0F;

		for (int i = 0; i <= 15; ++i)
		{
			float f1 = 1.0F - i / 15.0F;
			this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
		}
	}

	@Override
	public String getDimensionName() {
		return "The Wilds";
	}

	@Override
	public String getInternalNameSuffix() {
		return "Wilds";
	}
	/*
	public TragicSubBiome getSubBiomeForGen(BlockPos pos) {
		return this.worldChunkMgr.getSubBiomeGenAt(pos);
	} */
}