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
import tragicneko.tragicmc.proxy.ClientProxy;

public class NekoHomeworldProvider extends WorldProvider {

	public NekoHomeworldProvider()
	{
		this.dimensionId = TragicConfig.getInt("nekoHomeworldID");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IRenderHandler getSkyRenderer()
	{
		return ClientProxy.nekoHWSkyRenderer;
	}

	@Override
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new NekoHomeworldChunkManager(this.worldObj);
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new NekoHomeworldChunkProvider(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
	}

	@Override
	public String getSaveFolder()
	{
		return "NekoHomeworld";
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
		return true;
	}

	@Override
	public boolean canRespawnHere()
	{
		return TragicConfig.getBoolean("allowNekoHomeworldRespawn");
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
		return 266.0F;
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
		return super.calcSunriseSunsetColors(f, f1);
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
	public String getDimensionName() {
		return "Neko Homeworld";
	}

	@Override
	public String getInternalNameSuffix() {
		return "NekoHomeworld";
	}
}
