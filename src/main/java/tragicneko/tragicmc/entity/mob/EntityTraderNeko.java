package tragicneko.tragicmc.entity.mob;

import java.util.Random;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicAchievements;
import tragicneko.tragicmc.TragicBlocks;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.TragicItems;
import tragicneko.tragicmc.TragicMC;

public class EntityTraderNeko extends EntityNeko implements IMerchant {

	public MerchantRecipeList tradingList;
	public EntityPlayer buyer;

	private static final EntityVillager.ITradeList[][] TRADE_LIST = new EntityVillager.ITradeList[][] {
		{new NekiteForItem(TragicItems.NekoMindControlDevice, new EntityVillager.PriceInfo(1, 1)).setNekitePrice(8, 20),
			new NekiteForItem(new ItemStack(TragicBlocks.TragicSapling, 1, 5), new EntityVillager.PriceInfo(1, 1)).setNekitePrice(2, 6),
			new NekiteForItem(new ItemStack(TragicBlocks.NekiteWire), new EntityVillager.PriceInfo(3, 6)).setNekitePrice(5, 10),
			new NekiteForItem(TragicItems.WarpDrive, new EntityVillager.PriceInfo(1, 1)).setNekitePrice(22, 48),
			new NekiteForItem(TragicItems.InfallibleMetal, new EntityVillager.PriceInfo(6, 16)).setNekitePrice(12, 32),
			new NekiteForItem(Items.glowstone_dust, new EntityVillager.PriceInfo(3, 8)).setNekitePrice(2, 4)},
		{new NekiteAndItemForItem(new ItemStack(TragicItems.WovenSilk), new EntityVillager.PriceInfo(4, 8), new ItemStack(TragicBlocks.NekiteWire), new EntityVillager.PriceInfo(2, 6)).setNekitePrice(4, 8),
				new NekiteAndItemForItem(TragicItems.Sushi, new EntityVillager.PriceInfo(3, 6), TragicItems.GoldenSushi, new EntityVillager.PriceInfo(1, 3)).setNekitePrice(12, 24),
				new NekiteAndItemForItem(new ItemStack(Items.glowstone_dust), new EntityVillager.PriceInfo(2, 5), new ItemStack(TragicBlocks.NekitePlate, 1, 0), new EntityVillager.PriceInfo(1, 3)).setNekitePrice(2, 5)},
		{new NekiteForItem(TragicItems.GoldenSushi, new EntityVillager.PriceInfo(1, 3)).setNekitePrice(16, 32),
					new NekiteForItem(Items.diamond, new EntityVillager.PriceInfo(1, 3)).setNekitePrice(12, 28),
					new NekiteForItem(Items.emerald, new EntityVillager.PriceInfo(1, 3)).setNekitePrice(16, 34),
					new NekiteForItem(Items.gold_nugget, new EntityVillager.PriceInfo(3, 8)).setNekitePrice(12, 32),
					new NekiteForItem(TragicItems.Ruby, new EntityVillager.PriceInfo(1, 3)).setNekitePrice(20, 48),
					new NekiteForItem(TragicItems.Sapphire, new EntityVillager.PriceInfo(1, 3)).setNekitePrice(20, 48)},
		{new ItemForNekite(TragicItems.GoldenSushi, new EntityVillager.PriceInfo(1, 3)).setNekitePrice(6, 15),
						new ItemForNekite(TragicItems.Sushi, new EntityVillager.PriceInfo(4, 8)).setNekitePrice(3, 12),
						new ItemForNekite(Items.gunpowder, new EntityVillager.PriceInfo(12, 32)).setNekitePrice(2, 4),
						new ItemForNekite(new ItemStack(Blocks.tnt), new EntityVillager.PriceInfo(1, 4)).setNekitePrice(16, 32)},
		{new ItemForNekite(TragicItems.UnstableIsotope, new EntityVillager.PriceInfo(2, 6)).setNekitePrice(6, 22),
							new ItemForNekite(TragicItems.ComplexCircuitry, new EntityVillager.PriceInfo(2, 6)).setNekitePrice(6, 22),
							new ItemForNekite(TragicItems.NekoMindControlDevice, new EntityVillager.PriceInfo(1, 1)).setNekitePrice(4, 8)},
		{new NekiteAndItemForItem(new ItemStack(TragicBlocks.NekitePlate, 1, 4), new EntityVillager.PriceInfo(4, 8), new ItemStack(Items.gold_nugget), new EntityVillager.PriceInfo(5, 10)).setNekitePrice(4, 8),
								new NekiteAndItemForItem(TragicItems.Wrench, new EntityVillager.PriceInfo(1, 3), Items.iron_ingot, new EntityVillager.PriceInfo(1, 3)).setNekitePrice(4, 8),
								new NekiteAndItemForItem(new ItemStack(TragicBlocks.NekitePlate, 1, 0), new EntityVillager.PriceInfo(1, 3), new ItemStack(Items.diamond), new EntityVillager.PriceInfo(1, 3)).setNekitePrice(2, 6),
								new NekiteAndItemForItem(new ItemStack(TragicBlocks.NekitePlate, 1, 0), new EntityVillager.PriceInfo(1, 3), new ItemStack(Items.emerald), new EntityVillager.PriceInfo(1, 3)).setNekitePrice(2, 6),
								new NekiteAndItemForItem(new ItemStack(TragicBlocks.NekitePlate, 1, 0), new EntityVillager.PriceInfo(1, 3), new ItemStack(TragicItems.Ruby), new EntityVillager.PriceInfo(1, 3)).setNekitePrice(2, 6),
								new NekiteAndItemForItem(new ItemStack(TragicBlocks.NekitePlate, 1, 0), new EntityVillager.PriceInfo(1, 3), new ItemStack(TragicItems.Sapphire), new EntityVillager.PriceInfo(1, 3)).setNekitePrice(2, 6)},
		{new NekiteAndItemForItem(Items.ender_pearl, new EntityVillager.PriceInfo(1, 3), TragicItems.WarpDrive, new EntityVillager.PriceInfo(1, 1)).setNekitePrice(6, 12),
									new NekiteAndItemForItem(new ItemStack(TragicItems.NekoidStrain), new EntityVillager.PriceInfo(1, 2), new ItemStack(Items.golden_apple, 1, 1), new EntityVillager.PriceInfo(1, 3)).setNekitePrice(4, 16)}
	};

	public static class NekiteAndItemForItem implements EntityVillager.ITradeList
	{
		public ItemStack buyingItemStack;
		public EntityVillager.PriceInfo buyingPriceInfo;
		public ItemStack sellingItemstack;
		public EntityVillager.PriceInfo sellingPriceInfo;

		public EntityVillager.PriceInfo nekitePriceInfo;

		public NekiteAndItemForItem(Item item, EntityVillager.PriceInfo priceInfo, Item item2, EntityVillager.PriceInfo priceInfo2)
		{
			this.buyingItemStack = new ItemStack(item);
			this.buyingPriceInfo = priceInfo;
			this.sellingItemstack = new ItemStack(item2);
			this.sellingPriceInfo = priceInfo2;
		}

		public NekiteAndItemForItem(ItemStack item, EntityVillager.PriceInfo priceInfo, ItemStack item2, EntityVillager.PriceInfo priceInfo2)
		{
			this.buyingItemStack = item;
			this.buyingPriceInfo = priceInfo;
			this.sellingItemstack = item2;
			this.sellingPriceInfo = priceInfo2;
		}

		public NekiteAndItemForItem setNekitePrice(int i, int j) {
			this.nekitePriceInfo = new EntityVillager.PriceInfo(i, j);
			return this;
		}

		@Override
		public void modifyMerchantRecipeList(MerchantRecipeList recipeList, Random random)
		{
			int i = 1;
			if (this.buyingPriceInfo != null)  i = this.buyingPriceInfo.getPrice(random);

			int j = 1;
			if (this.sellingPriceInfo != null) j = this.sellingPriceInfo.getPrice(random);

			int k = 1;
			if (this.nekitePriceInfo != null) k = this.nekitePriceInfo.getPrice(random);

			recipeList.add(new MerchantRecipe(new ItemStack(this.buyingItemStack.getItem(), i, this.buyingItemStack.getMetadata()), new ItemStack(TragicItems.Nekite, k), new ItemStack(this.sellingItemstack.getItem(), j, this.sellingItemstack.getMetadata())));
		}
	}

	public static class NekiteForItem implements EntityVillager.ITradeList {
		public ItemStack sellItem;
		public EntityVillager.PriceInfo price;
		public EntityVillager.PriceInfo nekitePrice;

		public NekiteForItem(Item itemIn, EntityVillager.PriceInfo priceIn)
		{
			this.sellItem = new ItemStack(itemIn);
			this.price = priceIn;
		}

		public NekiteForItem(ItemStack stack, EntityVillager.PriceInfo price) 
		{
			this.sellItem = stack;
			this.price = price;
		}

		public NekiteForItem setNekitePrice(int i, int j)
		{
			this.nekitePrice = new EntityVillager.PriceInfo(i, j);
			return this;
		}

		@Override
		public void modifyMerchantRecipeList(MerchantRecipeList recipeList, Random random)
		{
			int i = 1;
			if (this.price != null) i = this.price.getPrice(random);

			int j = 1;
			if (this.nekitePrice != null) j = this.nekitePrice.getPrice(random);

			recipeList.add(new MerchantRecipe(new ItemStack(TragicItems.Nekite, j, 0), new ItemStack(this.sellItem.getItem(), i, this.sellItem.getMetadata())));
		}
	}

	public static class ItemForNekite implements EntityVillager.ITradeList {

		public ItemStack itemToBuy;
		public EntityVillager.PriceInfo priceInfo;
		public EntityVillager.PriceInfo nekitePriceInfo;

		public ItemForNekite(Item par1Item, EntityVillager.PriceInfo priceInfo)
		{
			this.itemToBuy = new ItemStack(par1Item);
			this.priceInfo = priceInfo;
		}

		public ItemForNekite(ItemStack stack, EntityVillager.PriceInfo priceInfo)
		{
			this.itemToBuy = stack;
			this.priceInfo = priceInfo;
		}

		public ItemForNekite setNekitePrice(int i, int j) {
			this.nekitePriceInfo = new EntityVillager.PriceInfo(i, j);
			return this;
		}

		@Override
		public void modifyMerchantRecipeList(MerchantRecipeList recipeList, Random random)
		{
			int i = 1;
			if (this.priceInfo != null) i = this.priceInfo.getPrice(random);

			int j = 1;
			if (this.nekitePriceInfo != null) j = this.nekitePriceInfo.getPrice(random);

			recipeList.add(new MerchantRecipe(new ItemStack(this.itemToBuy.getItem(), i, this.itemToBuy.getMetadata()), new ItemStack(TragicItems.Nekite, j, 0)));
		}
	}

	public static final int DW_TEXTURE = 25;

	public EntityTraderNeko(World par1World) {
		super(par1World);
		this.experienceValue = 25;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(DW_TEXTURE, 0);
	}

	public int getTextureId() {
		return this.dataWatcher.getWatchableObjectInt(DW_TEXTURE);
	}

	private void setTextureId(int i) {
		this.dataWatcher.updateObject(DW_TEXTURE, i);
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance ins, IEntityLivingData data) {
		if (!this.worldObj.isRemote) this.setTextureId(rand.nextInt(7));
		return super.onInitialSpawn(ins, data);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		double[] traderNekoStats = TragicConfig.getMobStat("traderNekoStats").getStats();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(traderNekoStats[0]);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(traderNekoStats[1]);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(traderNekoStats[2]);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(traderNekoStats[3]);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(traderNekoStats[4]);
	}

	@Override
	public int getTotalArmorValue() {
		return TragicConfig.getMobStat("traderNekoStats").getArmorValue();
	}

	@Override
	protected void updateNekoTasks() {
		this.tasks.removeTask(attackOnCollide);
		this.tasks.removeTask(moveTowardsTarget);
		this.targetTasks.removeTask(hurtByNekos);
		this.targetTasks.removeTask(targetPlayers);
		this.targetTasks.removeTask(targetUnreleasedNekos);
		this.targetTasks.removeTask(targetNekoid);
	}

	@Override
	public int getDropAmount() {
		return 1;
	}

	@Override
	public void setCustomer(EntityPlayer player) {
		this.buyer = player;
	}

	@Override
	public EntityPlayer getCustomer() {
		return this.buyer;
	}

	@Override
	public MerchantRecipeList getRecipes(EntityPlayer player) {
		if (this.tradingList == null || this.tradingList.size() < 1) this.populateTradingList();
		return this.tradingList;
	}

	@Override
	public void setRecipes(MerchantRecipeList recipeList) {

	}

	@Override
	public void useRecipe(MerchantRecipe recipe) {
		if (!this.worldObj.isRemote && this.buyer != null)
		{
			if (TragicConfig.getBoolean("allowAchievements")) this.buyer.triggerAchievement(TragicAchievements.tragicNekoTrader);

			if (!TragicConfig.getBoolean("allowMobSounds") || this.livingSoundTime > -80) return;

			this.livingSoundTime -= this.getTalkInterval();
			this.worldObj.playSoundAtEntity(this, "tragicmc:mob.traderneko.tradeaccept", 1.0F, 1.0F);
		}
	}

	@Override
	public void verifySellingItem(ItemStack stack) {
		if (!this.worldObj.isRemote && stack == null && TragicConfig.getBoolean("allowMobSounds") && rand.nextInt(4) == 0 && this.livingSoundTime > -160)
		{
			this.livingSoundTime -= this.getTalkInterval();
			this.worldObj.playSoundAtEntity(this, "tragicmc:mob.traderneko.tradedecline", 1.0F, 1.0F);
		}
	}

	public boolean canTrade() {
		return this.buyer == null && (this.isReleased() && TragicConfig.getBoolean("traderNekoReleaseTrading") || !TragicConfig.getBoolean("traderNekoReleaseTrading")) && TragicConfig.getBoolean("traderNekoTrading");
	}

	@Override
	public boolean interact(EntityPlayer player)
	{
		ItemStack itemstack = player.inventory.getCurrentItem();
		boolean flag = itemstack != null && (itemstack.getItem() == Items.spawn_egg || itemstack.getItem() == Items.lead);

		if (!flag && this.isEntityAlive() && !player.isSneaking() && (this.canTrade() || player.capabilities.isCreativeMode))
		{
			if (!this.worldObj.isRemote && (this.tradingList == null || this.tradingList.size() > 0))
			{
				this.setCustomer(player);
				player.displayVillagerTradeGui(this);
			}

			return true;
		}
		else
		{
			if (!this.worldObj.isRemote && this.buyer != null && this.buyer != player) {
				player.addChatMessage(new ChatComponentText(this.getName() + " is currently trading with " + this.buyer.getName()));
			}

			return super.interact(player);
		}
	}

	private void populateTradingList()
	{
		if (this.tradingList == null) this.tradingList = new MerchantRecipeList();

		final int k = rand.nextInt(TRADE_LIST.length - rand.nextInt(4)) + 4;

		for (int j = 0; j < k && j < TRADE_LIST.length; j++)
		{
			EntityVillager.ITradeList[] list = TRADE_LIST[j];
			EntityVillager.ITradeList entry = list[rand.nextInt(list.length)];
			if (entry != null) entry.modifyMerchantRecipeList(this.tradingList, this.rand);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		super.writeEntityToNBT(tagCompound);
		if (this.tradingList != null) tagCompound.setTag("Trades", this.tradingList.getRecipiesAsTags());
		tagCompound.setInteger("textureID", this.getTextureId());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound)
	{
		super.readEntityFromNBT(tagCompound);

		if (tagCompound.hasKey("Trades", 10))
		{
			NBTTagCompound nbttagcompound = tagCompound.getCompoundTag("Trades");
			this.tradingList = new MerchantRecipeList(nbttagcompound);
		}

		if (tagCompound.hasKey("textureID")) this.setTextureId(tagCompound.getInteger("textureID"));
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public String getLivingSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") && this.buyer == null ? "tragicmc:mob.traderneko.idle" : null;
	}

	@Override
	public String getHurtSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") && rand.nextInt(4) == 0 ? "tragicmc:mob.traderneko.hurt" : super.getHurtSound();
	}

	@Override
	public String getDeathSound()
	{
		return TragicConfig.getBoolean("allowMobSounds") ? "tragicmc:mob.traderneko.death" : null;
	}

	@Override
	public float getSoundPitch()
	{
		return 1.0F;
	}

	@Override
	public float getSoundVolume()
	{
		return 1.0F;
	}
	
	@Override
	public boolean isBuffExempt() {
		return true;
	}
}
