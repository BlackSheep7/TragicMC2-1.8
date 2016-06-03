package tragicneko.tragicmc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatBase;
import net.minecraft.util.RegistryNamespacedDefaultedByKey;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.util.TragicEntityList.EntityEggInfo;

public class TragicEntityList
{    
    public static RegistryNamespacedDefaultedByKey<String, Class<? extends Entity>> entityRegistry = new RegistryNamespacedDefaultedByKey<String, Class<? extends Entity>>("");
    public static HashMap<Integer, EntityEggInfo> entityEggs = new LinkedHashMap<Integer, EntityEggInfo>();

	public static void addMapping(Class<? extends Entity> clazz, String name, int id)
    {
		if (entityRegistry.containsKey(name)) throw new IllegalArgumentException("Entity list already contains a mapping with that name! " + name);
		if (entityRegistry.getObjectById(id) != null) throw new IllegalArgumentException("Entity list already contains a mapping for that id! " + id);
        entityRegistry.register(id, name, clazz);
    }

	public static void addMapping(Class<? extends Entity> clazz, String name, int id, int eggColor, int eggColor2)
	{
		addMapping(clazz, name, id);
		entityEggs.put(id, new EntityEggInfo(name, eggColor, eggColor2, EnumEggType.NORMAL));
	}

	public static void addMapping(Class<? extends Entity> clazz, String name, int id, int eggColor, int eggColor2, EnumEggType eggType)
	{
		addMapping(clazz, name, id);
		entityEggs.put(id, new EntityEggInfo(name, eggColor, eggColor2, eggType));
	}

	public static Entity createEntityByName(String name, World world)
    {
        Entity entity = null;

        try
        {
            Class<? extends Entity> oclass = entityRegistry.getObject(name);

            if (oclass != null)
            {
                entity = (Entity)oclass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {world});
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return entity;
    }

	public static Entity createEntityFromNBT(NBTTagCompound tag, World world)
	{
		Entity entity = null;
        Class<? extends Entity> oclass = null;
        try
        {
            oclass = entityRegistry.getObject(tag.getString("id"));

            if (oclass != null)
            {
                entity = (Entity)oclass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {world});
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        if (entity != null)
        {
            try
            {
            	entity.readFromNBT(tag);
            }
            catch (Exception e)
            {
                net.minecraftforge.fml.common.FMLLog.log(org.apache.logging.log4j.Level.ERROR, e,
                        "An Entity %s(%s) has thrown an exception during loading, its state cannot be restored. Report this to the mod author",
                        tag.getString("id"), oclass.getName());
                entity = null;
            }
        }
        else
        {
            TragicMC.logWarning("Skipping Entity with id " + tag.getString("id"));
        }

        return entity;
	}

	public static Entity createEntityByID(int id, World world)
	{
		Entity entity = null;

		try
		{
			Class oclass = getClassFromID(id);

			if (oclass != null)
			{
				entity = (Entity)oclass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {world});
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}

		if (entity == null)
		{
			TragicMC.logWarning("Skipping Entity with id " + id);
		}

		return entity;
	}

	public static int getEntityID(Entity entity)
    {
        Integer integer = entityRegistry.getIDForObject(entity.getClass());
        return integer == null ? 0 : integer.intValue();
    }

    public static Class<? extends Entity> getClassFromID(int id)
    {
        return entityRegistry.getObjectById(id);
    }

    public static String getEntityString(Entity entity)
    {
        return entity != null ? entityRegistry.getNameForObject(entity.getClass()) : null;
    }

    public static String getStringFromID(int id)
    {
        return entityRegistry.getNameForObject(getClassFromID(id));
    }

    public static int getIDFromString(String string)
    {
        Integer integer = entityRegistry.getIDForObject(entityRegistry.getObject(string));
        return integer == null ? -1 : integer.intValue();
    }

    public static List getEntityNameList()
    {
        Set<String> set = entityRegistry.getKeys();
        ArrayList arraylist = Lists.newArrayList();
        Iterator<String> iterator = set.iterator();

        while (iterator.hasNext())
        {
            String s = iterator.next();
            Class<? extends Entity> oclass = entityRegistry.getObject(s);

            if ((oclass.getModifiers() & 1024) != 1024) arraylist.add(s);
        }

        return arraylist;
    }

	public static class EntityEggInfo
	{
		public final String name;
		public final int primaryColor;
		public final int secondaryColor;
		public final StatBase killStat;
        public final StatBase killedByStat;

		public final EnumEggType eggType;

		public EntityEggInfo(String name, int par2, int par3, EnumEggType eggType)
		{
			this.name = name;
			this.primaryColor = par2;
			this.secondaryColor = par3;
			this.eggType = eggType;
			this.killStat = (new StatBase("stat.killEntity." + name, new net.minecraft.util.ChatComponentTranslation("stat.entityKill",     new net.minecraft.util.ChatComponentTranslation("entity." + name + ".name")))).registerStat();
            this.killedByStat = (new StatBase("stat.entityKilledBy." + name, new net.minecraft.util.ChatComponentTranslation("stat.entityKilledBy", new net.minecraft.util.ChatComponentTranslation("entity." + name + ".name")))).registerStat();
		}
	}

	public enum EnumEggType
	{
		NORMAL,
		PET,
		MINIBOSS,
		BOSS,
		ALPHA
	}
}