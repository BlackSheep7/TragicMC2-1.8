/*
 * TragicMC 2 - Minecraft Mod that uses Minecraft Forge API
 * Copyright (C) 2014 TragicNeko
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package tragicneko.tragicmc;

import java.util.List;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.Type;
import tragicneko.tragicmc.proxy.CommonProxy;

@Mod(modid = TragicMC.MODID, name = TragicMC.MODNAME, version = TragicMC.VERSION, acceptedMinecraftVersions = TragicMC.ACCEPTED_VERSION, dependencies="required-after:Forge", updateJSON=TragicMC.VERSION_JSON)
public class TragicMC
{
	public static final String MODNAME = "TragicMC 2";
	public static final String MODID = "TragicMC";
	public static final String VERSION = "4.47.3017";
	public static final String ACCEPTED_VERSION = "[1.8.9]";
	public static final String VERSION_JSON = "https://raw.githubusercontent.com/TragicNeko/TragicMC2-1.8/master/update.json";

	@Instance(TragicMC.MODID)
	private static TragicMC instance;

	@SidedProxy(clientSide = "tragicneko.tragicmc.proxy.ClientProxy", serverSide = "tragicneko.tragicmc.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final Random rand = new Random();

	public static CreativeTabs Survival;
	public static CreativeTabs Creative;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}

	@EventHandler
	public void onServerLoad(FMLServerStartingEvent event)
	{
		if (TragicConfig.allowDoom) event.registerServerCommand(new tragicneko.tragicmc.commands.DoomCommand());
		if (TragicConfig.allowDoomsdays) event.registerServerCommand(new tragicneko.tragicmc.commands.DoomsdayComand());

		if (!event.getServer().isFlightAllowed())
		{
			TragicConfig.allowFlight = false;
			logWarning("Flight potion effect is disabled due to the server not allowing it. Change the option in your server.properties file if you want it enabled.");
		}
	}

	@EventHandler
	public void onMapping(FMLMissingMappingsEvent event)
	{
		if (!TragicConfig.allowNonMobBlocks || !TragicConfig.allowNonMobItems) return;

		List<MissingMapping> list = event.get();

		for (MissingMapping mm : list)
		{			
			if (mm.name.equals("TragicMC:darkStoneBlocks"))
			{
				if (mm.type == Type.BLOCK)
				{
					mm.remap(TragicBlocks.DarkStone);
				}
				else
				{
					mm.remap(Item.getItemFromBlock(TragicBlocks.DarkStone));
				}
			}
			else if (mm.name.equals("TragicMC:obsidianVariants"))
			{
				if (mm.type == Type.BLOCK)
				{
					mm.remap(TragicBlocks.TragicObsidian);
				}
				else
				{
					mm.remap(Item.getItemFromBlock(TragicBlocks.TragicObsidian));
				}
			}
			else if (mm.name.equals("TragicMC:darkCobbleBlocks"))
			{
				if (mm.type == Type.BLOCK)
				{
					mm.remap(TragicBlocks.DarkCobblestone);
				}
				else
				{
					mm.remap(Item.getItemFromBlock(TragicBlocks.DarkCobblestone));
				}
			}
			else if (mm.name.equals("TragicMC:rubyOreBlock"))
			{
				if (mm.type == Type.BLOCK)
				{
					mm.remap(TragicBlocks.RubyOre);
				}
				else
				{
					mm.remap(Item.getItemFromBlock(TragicBlocks.RubyOre));
				}
			}
			else if (mm.name.equals("TragicMC:sapphireOreBlock"))
			{
				if (mm.type == Type.BLOCK)
				{
					mm.remap(TragicBlocks.SapphireOre);
				}
				else
				{
					mm.remap(Item.getItemFromBlock(TragicBlocks.SapphireOre));
				}
			}
			else if (mm.name.equals("TragicMC:mercuryOreBlock"))
			{
				if (mm.type == Type.BLOCK)
				{
					mm.remap(TragicBlocks.MercuryOre);
				}
				else
				{
					mm.remap(Item.getItemFromBlock(TragicBlocks.MercuryOre));
				}
			}
			else if (mm.name.equals("TragicMC:tungstenOreBlock"))
			{
				if (mm.type == Type.BLOCK)
				{
					mm.remap(TragicBlocks.TungstenOre);
				}
				else
				{
					mm.remap(Item.getItemFromBlock(TragicBlocks.TungstenOre));
				}
			}
			else if (mm.name.equals("TragicMC:summonBlocks"))
			{
				if (mm.type == Type.BLOCK)
				{
					mm.remap(TragicBlocks.SummonBlock);
				}
				else
				{
					mm.remap(Item.getItemFromBlock(TragicBlocks.SummonBlock));
				}
			}
			else if (mm.name.equals("TragicMC:quicksandBlocks"))
			{
				if (mm.type == Type.BLOCK)
				{
					mm.remap(TragicBlocks.Quicksand);
				}
				else
				{
					mm.remap(Item.getItemFromBlock(TragicBlocks.Quicksand));
				}
			}
			else if (mm.name.equals("TragicMC:foxBlock"))
			{
				if (mm.type == Type.BLOCK)
				{
					mm.remap(TragicBlocks.SmoothNetherrack);
				}
				else
				{
					mm.remap(Item.getItemFromBlock(TragicBlocks.SmoothNetherrack));
				}
			}
			else if (mm.name.equals("TragicMC:storageBlocks"))
			{
				if (mm.type == Type.BLOCK)
				{
					mm.remap(TragicBlocks.CompactOre);
				}
				else
				{
					mm.remap(Item.getItemFromBlock(TragicBlocks.CompactOre));
				}
			}
			else if (mm.name.equals("TragicMC:structureSeeds"))
			{
				if (mm.type == Type.BLOCK)
				{
					mm.remap(TragicBlocks.StructureSeed);
				}
				else
				{
					mm.remap(Item.getItemFromBlock(TragicBlocks.StructureSeed));
				}
			}
			else if (mm.name.equals("TragicMC:lightCobble"))
			{
				if (mm.type == Type.BLOCK)
				{
					mm.remap(TragicBlocks.LightCobblestone);
				}
				else
				{
					mm.remap(Item.getItemFromBlock(TragicBlocks.LightCobblestone));
				}
			}
			else if (mm.name.equals("TragicMC:fragileLightInvis"))
			{
				if (mm.type == Type.BLOCK)
				{
					mm.remap(TragicBlocks.FragileLight);
				}
				else
				{
					mm.remap(Item.getItemFromBlock(TragicBlocks.FragileLight));
				}
			}
			else if (mm.name.equals("TragicMC:geyserSteaming"))
			{
				if (mm.type == Type.BLOCK)
				{
					mm.remap(TragicBlocks.Geyser);
				}
				else
				{
					mm.remap(Item.getItemFromBlock(TragicBlocks.Geyser));
				}
			}
			else if (mm.name.contains("TragicMC:"))
			{
				mm.ignore();
			}
		}
	}

	public static void logError(String s)
	{
		proxy.getLogger().error(s);
	}

	public static void logError(String s, Exception e)
	{
		proxy.getLogger().error(s, e);
	}

	public static void logError(String s, Throwable t)
	{
		proxy.getLogger().error(s, t);
	}

	public static void logInfo(String s)
	{
		proxy.getLogger().info(s);
	}

	public static void logWarning(String s)
	{
		proxy.getLogger().warn(s);
	}

	public static TragicMC getInstance() {
		return instance;
	}
}
