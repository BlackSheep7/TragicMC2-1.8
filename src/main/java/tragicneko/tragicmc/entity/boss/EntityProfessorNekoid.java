package tragicneko.tragicmc.entity.boss;

import net.minecraft.world.World;

public class EntityProfessorNekoid extends TragicBoss {
	
	/*
	 * So, first half of it's attack phase is it spawning on a mecha, it then uses the mecha's abilities and attacks as normal
	 * After either the Mecha is destroyed or it's health drops below half, it starts spawning in random Nekos to help it out
	 * it only spawns a couple per "wave" and only spawns up to like 10
	 * 
	 * After it is defeated, all the Nekos around it are automatically released
	 */

	public EntityProfessorNekoid(World par1World) {
		super(par1World);
	}

}
