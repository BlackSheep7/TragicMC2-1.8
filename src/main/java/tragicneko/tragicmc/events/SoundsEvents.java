package tragicneko.tragicmc.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker.MusicType;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import tragicneko.tragicmc.TragicConfig;
import tragicneko.tragicmc.dimension.SynapseWorldProvider;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.entity.alpha.EntityOverlordCore;

public class SoundsEvents {

	private final Minecraft mc;
	public static final ResourceLocation collisionSurvival = new ResourceLocation("tragicmc:music.dimension.bog");
	public static final ResourceLocation collisionCreative = new ResourceLocation("tragicmc:music.dimension.catacombs");
	public static final ResourceLocation synapseSurvival = new ResourceLocation("tragicmc:music.dimension.sanctuary");
	public static final ResourceLocation synapseOverlord = new ResourceLocation("tragicmc:music.dimension.prime");
	private static ISound currentTrack;
	
	public SoundsEvents(Minecraft mc)
	{
		this.mc = mc;
	}

	@SubscribeEvent
	public void overrideSound(PlaySoundEvent event)
	{
		MusicType musictype = mc.getAmbientMusicType();

		if (event.sound.getSoundLocation() == musictype.getMusicLocation())
		{
			if (mc.theWorld != null && (mc.theWorld.provider instanceof TragicWorldProvider || mc.theWorld.provider instanceof SynapseWorldProvider))
			{
				WorldProvider prov = mc.theWorld.provider;
				ResourceLocation rl = null;

				if (TragicConfig.allowDimensionalMusic && rl == null)
				{
					if (prov instanceof TragicWorldProvider) // && mc.thePlayer != null && !mc.thePlayer.isPotionActive(TragicPotion.Nightmare))
					{
						rl = musictype == MusicType.GAME ? collisionSurvival : collisionCreative;
					}
					else if (prov instanceof SynapseWorldProvider) // && mc.thePlayer != null && !mc.thePlayer.isPotionActive(TragicPotion.Nightmare))
					{
						rl = mc.thePlayer != null && !mc.theWorld.getEntitiesWithinAABB(EntityOverlordCore.class, mc.thePlayer.getEntityBoundingBox().expand(120, 128, 120)).isEmpty() ? synapseOverlord : (musictype == MusicType.GAME ? synapseSurvival : collisionCreative);
					}
				}	
				
				if (rl != null && (this.currentTrack == null || this.currentTrack.getSoundLocation() != rl || !mc.getSoundHandler().isSoundPlaying(this.currentTrack)))
				{
					if (this.currentTrack != null && mc.getSoundHandler().isSoundPlaying(this.currentTrack)) mc.getSoundHandler().stopSound(this.currentTrack);
					this.currentTrack = PositionedSoundRecord.create(rl);
					event.result = this.currentTrack;
				}
				else event.result = null;
			}
		}

		/*
		if (mc.thePlayer != null)
		{
			if (mc.thePlayer.isPotionActive(TragicPotion.Deafening.id))
			{
				event.result = null;
				mc.getSoundHandler().stopSounds();
			}
		}  */
	} 
	
	@SubscribeEvent
	public void stopSound(PlayerEvent.Clone event) //if player swaps dimensions this gets triggered to stop the music that plays for my dimensions
	{
		if (event.original.worldObj.provider.getDimensionId() != event.entityPlayer.worldObj.provider.getDimensionId())
		{
			if (this.currentTrack != null && mc.getSoundHandler().isSoundPlaying(this.currentTrack)) mc.getSoundHandler().stopSound(this.currentTrack);
		}
	}
}
