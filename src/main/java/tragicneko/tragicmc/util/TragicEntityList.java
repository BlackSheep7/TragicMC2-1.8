package tragicneko.tragicmc.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class TragicEntityList
{
	public static final Map stringToClassMapping = Maps.newHashMap();
    public static final Map classToStringMapping = Maps.newHashMap();
    public static final Map idToClassMapping = Maps.newHashMap();
    private static final Map classToIDMapping = Maps.newHashMap();
    private static final Map stringToIDMapping = Maps.newHashMap();
    public static final Map entityEggs = Maps.newLinkedHashMap();

	public static void addMapping(Class clazz, String name, int id)
    {
        if (id < 0 || id > 255) throw new IllegalArgumentException("Attempted to register a entity with invalid ID: " + id + " Name: " + name + " Class: " + clazz);
        if (stringToClassMapping.containsKey(name))
        {
            throw new IllegalArgumentException("ID is already registered: " + name);
        }
        else if (idToClassMapping.containsKey(Integer.valueOf(id)))
        {
            throw new IllegalArgumentException("ID is already registered: " + id);
        }
        else if (clazz == null)
        {
            throw new IllegalArgumentException("Cannot register null clazz for id: " + id);
        }
        else
        {
            stringToClassMapping.put(name, clazz);
            classToStringMapping.put(clazz, name);
            idToClassMapping.put(Integer.valueOf(id), clazz);
            classToIDMapping.put(clazz, Integer.valueOf(id));
            stringToIDMapping.put(name, Integer.valueOf(id));
        }
    }

	public static void addMapping(Class clazz, String name, int id, int eggColor, int eggColor2)
	{
		addMapping(clazz, name, id);
		entityEggs.put(Integer.valueOf(id), new TragicEntityList.EntityEggInfo(name, eggColor, eggColor2, EnumEggType.NORMAL));
	}

	public static void addMapping(Class clazz, String name, int id, int eggColor, int eggColor2, EnumEggType eggType)
	{
		addMapping(clazz, name, id);
		entityEggs.put(Integer.valueOf(id), new TragicEntityList.EntityEggInfo(name, eggColor, eggColor2, eggType));
	}

	public static Entity createEntityByName(String name, World world)
    {
        Entity entity = null;

        try
        {
            Class oclass = (Class)stringToClassMapping.get(name);

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

        if ("Minecart".equals(tag.getString("id")))
        {
            tag.setString("id", EntityMinecart.EnumMinecartType.byNetworkID(tag.getInteger("Type")).getName());
            tag.removeTag("Type");
        }

        Class oclass = null;
        try
        {
            oclass = (Class)stringToClassMapping.get(tag.getString("id"));

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
        Integer integer = (Integer)classToIDMapping.get(entity.getClass());
        return integer == null ? 0 : integer.intValue();
    }

    public static Class getClassFromID(int id)
    {
        return (Class)idToClassMapping.get(Integer.valueOf(id));
    }

    public static String getEntityString(Entity entity)
    {
        return (String)classToStringMapping.get(entity.getClass());
    }

    public static String getStringFromID(int id)
    {
        return (String)classToStringMapping.get(getClassFromID(id));
    }

    public static int getIDFromString(String string)
    {
        Integer integer = (Integer)stringToIDMapping.get(string);
        return integer == null ? -1 : integer.intValue();
    }

    public static List getEntityNameList()
    {
        Set set = stringToClassMapping.keySet();
        ArrayList arraylist = Lists.newArrayList();
        Iterator iterator = set.iterator();

        while (iterator.hasNext())
        {
            String s = (String)iterator.next();
            Class oclass = (Class)stringToClassMapping.get(s);

            if ((oclass.getModifiers() & 1024) != 1024)
            {
                arraylist.add(s);
            }
        }

        arraylist.add("LightningBolt");
        return arraylist;
    }
    
    public static boolean isStringEntityName(Entity entity, String name)
    {
        String s1 = getEntityString(entity);

        if (s1 == null && entity instanceof EntityPlayer)
        {
            s1 = "Player";
        }
        else if (s1 == null && entity instanceof EntityLightningBolt)
        {
            s1 = "LightningBolt";
        }

        return name.equals(s1);
    }

    public static boolean isStringValidEntityName(String name)
    {
        return "Player".equals(name) || getEntityNameList().contains(name);
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