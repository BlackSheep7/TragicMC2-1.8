package tragicneko.tragicmc.entity.alpha;

import net.minecraft.world.World;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.entity.mob.TragicMob;

public class EntityGhostAdmin extends TragicMob {
	
	public EntityAdmin admin = null;
	
	public static final int DW_ADMIN_ID = 20; //datawatcher for syncing the admin id to the client for particle effects or something

	public EntityGhostAdmin(World par1World) {
		super(par1World);
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	protected boolean canCorrupt()
	{
		return false;
	}

	protected boolean canChange()
	{
		return false;
	}
	
	public boolean hasAdmin() {
		return this.admin != null;
	}
	
	public void setAdmin(EntityAdmin admin) {
		this.admin = admin;
	}
	
	public EntityAdmin getAdmin() {
		return this.admin;
	}
}
