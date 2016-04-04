package tragicneko.tragicmc.entity.alpha;

import java.util.ArrayList;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicEntities;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.entity.mob.EntityArchangel;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityAdmin extends TragicBoss {

	//Make sure that when it acquires a target that it tries to stay within ~40 blocks of it
	//This will make sure it's easier to keep track of as well as ensure that you can do certain phases of it's AI without worrying about it's location

	//Stats
	//Health: 6000
	//Attack (normal hit): 16
	//Damage (normal-projectile): 4.5 armor-piercing
	//Damage (slow-moving projectile): 8 armor-piercing
	//Damage (annhilator beam): 100 armor-piercing, this might make it viable to use in a mob battle against other mobs
	//Speed: 0.223 (invulnerable mode), 0.375 (flying speed), 0.425 (ghost mode speed)
	//knockback resistance: 0 (can be knocked away and towards things)
	//Armor value: 24 (maximum value so that the strongest weapons are required)
	//Follow Range: 64

	//Description of AI:
	//Floats around near it's target and fires large slow moving projectiles that can pass through walls, these projectiles will also inflict the Hacked effect
	//While invulnerable it only does this part of it's AI
	//If "damaged" enough it'll become stunned for a long period of time, this is to allow you to escape it for a while to actually do stuff in the Dimension

	//When a spirit catcher is used on it, it becomes vulnerable
	//It'll then teleport away (despawn) and drop an Administrator passcode

	//When you enter the Dimension it'll already be vulnerable
	//While in this phase it'll spawn in control towers that shock you when you get near them randomly
	//You must "mine" these towers, causing them to overload at which point they'll damage the Admin
	//These must be mined before a certain time limit, otherwise they will reset and regenerate it's health up to full
	//This will take it's health down to half when completed, each tower phase will take 1/6th of it's total health and it'll remove the towers during each
	//of these

	//When you successfully overload and damage it up to half of it's health, it'll become separated from it's body module (which will stay in place during this phase)
	//It will gain an instakill beam that has a long charge rate, it'll become fixed on one point and fire the beam after a few seconds, instantly killing
	//anything within it
	//To damage it in this phase, you must use the towers it left behind to "shock" it with Directed Lightning, this will need to be done until it's dropped
	//down to no health in which it goes into sleep mode
	//at certain phases it'll create a singularity which will alter your active effects and change your current health and hunger amounts, in other words,
	//it'll basically hack your stats (won't affect inventory and current equipped items but the effects will actually occur

	//It'll then begin regenerating towers, if these towers are allowed to completely regenerate then it'll go back into it's first phase with fully powered
	//towers, you must destroy the towers before they regenerate long enough for it to be cut off from their power, then it'll have to factory reset it's systems
	//in order to continue living, which will clear it of all corruption
	//While it's regenerating towers, it'll also fire slow moving homing rockets which can be used to assist you in destroying the towers if used
	//cleverly enough, these will go through walls though so you'll take damage if you use them to destroy the towers

	//New block, it'll have three states, one active and one inactive similarly to how I originally wanted to do the Digital Sea and one "broken"
	//The broken block state will signify that part of the tower has been destroyed, there will also be core states
	//core states will be in the middle of the tower, if an active block is not touching a core block it'll become inactive

	//Sounds will be referencing Windows notifications ("Your computer is about to restart to finish installing updates", "Powering down...", "Powering up.",
	//"That command does not exist", "File not found in database, would you like to continue? Y/N?", "No files match search query", etc.)
	//"That file is corrupted, would you like to attempt recovery?", "Memory allocation is inadequate for this operation.", "Doing this requires you to power off
	//your computer", "System32 has been restored, that was close."
	//Could have one set of male and one set of female-ish sounds (since I would be recording both), this could be determined on spawn if male or female voice
	//Move to a "corrupt" set of the same sounds when in "ghost" form
	//"Resuming system-wide clean-up algorithms... Stand by."

	private ControlTower[] towers;
	private boolean phaseChange = false;

	public static final int TOWER_LIMIT = 8;
	public int phaseTime = 1000;
	public EntityGhostAdmin ghost = null; //the ghost admin to be used for updating the overloaded phase
	
	public static final int DW_PHASE = 20;

	public EntityAdmin(World par1World) {
		super(par1World);
		this.setSize(3.5F, 6.0F);
		this.towers = new ControlTower[TOWER_LIMIT];
		this.moveHelper = new EntityArchangel.FlyingMoveHelper(this);
	}
	
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(DW_PHASE, Integer.valueOf(0));
	}
	
	private void setIntState(int i) {
		this.dataWatcher.updateObject(DW_PHASE, i);
	}
	
	public int getIntState() {
		return this.dataWatcher.getWatchableObjectInt(DW_PHASE);
	}
	
	private void setState(EnumAdminState state) {
		this.setIntState(state.getIntValue());
	}
	
	public EnumAdminState getState() {
		return EnumAdminState.values()[this.getIntState()];
	}
	
	@Override
	public void onLivingUpdate() {
		if (this.getState() == EnumAdminState.OVERLOADED) this.motionX = this.motionY = this.motionZ = 0D;
		super.onLivingUpdate();
		
		if (this.worldObj.isRemote)
		{
			return;
		}
		
		if (this.phaseTime > 0) this.phaseTime--;
		else
		{
			if (this.phaseChange)
			{
				this.setState(EnumAdminState.getNextPhase(this.getState()));
				this.initPhase();
			}
			else
			{
				this.resetPhase();
			}
		}
		
		this.updatePhase();
	}
	
	public void initPhase() {
		switch(this.getState())
		{
		case INITIAL:
			break;
		case INVINCIBLE:
			break;
		case OVERLOADED:
			break;
		case REGENERATING:
			break;
		case NPC:
			break;
		default:
			break;
		}
	}
	
	public void resetPhase() {
		switch(this.getState())
		{
		case INITIAL:
			break;
		case INVINCIBLE:
			break;
		case OVERLOADED:
			break;
		case REGENERATING:
			break;
		case NPC:
			break;
		default:
			break;
		}
	}
	
	public void updatePhase() {
		switch(this.getState())
		{
		case INITIAL:
			break;
		case INVINCIBLE:
			break;
		case OVERLOADED:
			break;
		case REGENERATING:
			break;
		case NPC:
			break;
		default:
			break;
		}
	}

	private void updateTowers() {
		for (int i = 0; i < towers.length; i++)
		{
			ControlTower ct = towers[i];
			if (ct != null) ct.update();
		}
	}

	private void generateTowers() {
		byte t = 0;

		ArrayList<BlockPos> list = WorldHelper.getBlocksInSphericalRange(this.worldObj, 25.5, this.getPosition());
		label: for (int i = 0; i < 128 && t < TOWER_LIMIT; i++)
		{
			BlockPos pos = list.get(rand.nextInt(list.size()));
			IBlockState state = this.worldObj.getBlockState(pos);
			if (Math.abs(pos.getY() - this.posY) > 10) continue;
			if (state.getBlock().getBlockHardness(this.worldObj, pos) > 0F && World.doesBlockHaveSolidTopSurface(this.worldObj, pos) && this.worldObj.canBlockSeeSky(pos))
			{
				for (int j = 0; j < towers.length; j++) //to ensure it doesn't generate a tower too close to an existing one
				{
					ControlTower ct = towers[j];
					if (ct != null)
					{
						if (ct.origin.distanceSqToCenter(pos.getX(), pos.getY(), pos.getZ()) < 8) continue label; //3d check so that things just aren't close to each other
						double x = ct.origin.getX() - pos.getX();
						double z = ct.origin.getZ() - pos.getZ();
						double d0 = MathHelper.sqrt_double(x * x + z * z);
						if (d0 < 8) continue label; //2d check to ensure that they aren't generated right on top of each other (literally)
					}
				}
				towers[t++] = new ControlTower(this, pos);
			}
		}
	}
	
	private void regenerateTowers() { //if this admin has nbt data for it's towers, it'll recreate them in it's control tower array rather than generate fresh ones
		
	}

	private void deleteTowers() {
		for (int i = 0; i < towers.length; i++)
		{
			ControlTower ct = towers[i];
			if (ct != null) ct.kill();
		}
		towers = new ControlTower[8];
	}

	@Override
	public void setAir(int i){}

	@Override
	protected void despawnEntity() {}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void fall(float dist, float multi) {}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return TragicEntities.Synapse;
	}

	public enum EnumAdminState {
		INVINCIBLE(0), //when in the wilds dimension and invincible
		INITIAL(1), //when you first fight it in it's own dimension
		OVERLOADED(2), //when you complete it's first phase and overload it
		REGENERATING(3), //when you "defeat" it, forcing it to attempt to regenerate
		NPC(4); //After you defeat it, it basically becomes an NPC

		public static EnumAdminState getNextPhase(EnumAdminState state) {
			return state == INVINCIBLE ? INITIAL : (state == INITIAL ? OVERLOADED : NPC );
		}
		
		private final int id;
		private EnumAdminState(int i) { id = i; }
		public int getIntValue() { return this.id; }
	}

	public static class ControlTower {

		private final World worldObj;
		private final EntityAdmin admin;
		private final byte sizeH = 16;
		private final byte sizeW = 4;
		private final byte sizeL = 4;
		private EnumTowerState state = EnumTowerState.INACTIVE; // 0 = inactive, 1 = active, 2 = overloaded
		private final BlockPos origin;
		private float activePer;
		private static final float LOAD_LIMIT = 56.735F;

		protected ControlTower(EntityAdmin admin, BlockPos ori) {
			this.admin = admin;
			this.worldObj = admin.worldObj;
			this.origin = ori;
			this.activePer = 100F;
		}

		public boolean isOverloaded() {
			return state == EnumTowerState.OVERLOADED;
		}

		public boolean isActive() {
			return state != EnumTowerState.INACTIVE && state != EnumTowerState.REGENERATING;
		}

		public void update() {

			if (this.state == EnumTowerState.INACTIVE && this.shouldActivate())
			{
				this.state = EnumTowerState.ACTIVE;
			}
			else if (this.state == EnumTowerState.ACTIVE)
			{
				updateComposition();
				updateState();
				damageEntities();
			}
		}

		private boolean shouldActivate() {
			return this.activePer == 100F && this.admin.ticksExisted > 0; //change to activate at a certain phase of it's ai
		}

		public void updateComposition() {
			//iterate through composition values
			//match with world mappings for those values (with origin offset) to determine if
			//majority of the blocks in the tower are still active
			this.activePer = 100F;
		}

		private void updateState() {
			if (this.activePer > LOAD_LIMIT) this.state = EnumTowerState.ACTIVE;
			else this.state = EnumTowerState.OVERLOADED;
		}

		private void kill() {
			//delete all of the blocks that make up the tower, for when the Admin is killed
		}

		private void damageEntities() {
			//damages all nearby entities with directed lightning
		}

		public static enum EnumTowerState {
			ACTIVE,
			INACTIVE,
			OVERLOADED,
			REGENERATING;
		}
	}
}
