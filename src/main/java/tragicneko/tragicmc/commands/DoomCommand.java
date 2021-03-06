package tragicneko.tragicmc.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomCommand extends CommandBase {

	public DoomCommand()
	{
	}

	@Override
	public String getCommandName() {
		return "doom";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "Usage: /doom <player> <int to apply>";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if (var2.length != 2)
		{
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + this.getCommandUsage(var1)));
			return;
		}
		
		EntityPlayerMP mp;
		try {
			mp = this.getCommandSenderAsPlayer(var1);
		} catch (PlayerNotFoundException e1) {
			e1.printStackTrace();
			return;
		}
		PropertyDoom doom = PropertyDoom.get(mp);

		if (mp.isDead)
		{
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "You are dead and cannot use this command right now."));
			return;
		}
		int amount;

		try
		{
			amount = Integer.parseInt(var2[1]);
		}
		catch (NumberFormatException e)
		{
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "That is not a proper value, must be an integer value!"));
			return;
		}

		if (amount == 0)
		{
			doom.setCooldown(0);
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "" + mp.getName() + "'s cooldown was removed."));
			return;
		}

		if (doom.getCurrentDoom() == doom.getMaxDoom() && amount > 0)
		{
			doom.setCooldown(0);
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "" + mp.getName() + "'s cooldown was removed."));
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Doom of " + mp.getName() + " is already at max."));
			return;
		}

		if (doom.getCurrentDoom() == 0 && amount < 0)
		{
			doom.setCooldown(0);
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "" + mp.getName() + "'s cooldown was removed."));
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Doom of " + mp.getName() + " is already at 0."));
			return;
		}

		if (amount + doom.getCurrentDoom() >= doom.getMaxDoom())
		{
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Doom of " + mp.getName() + " is full now."));
		}
		else if (amount + doom.getCurrentDoom() <= 0)
		{
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Doom of " + mp.getName() + " is empty now."));
		}

		if (doom != null)
		{
			doom.increaseDoom(amount);
			doom.setCooldown(0);
			var1.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Doom of " + mp.getName() + " was set to " + doom.getCurrentDoom()));
		}
	}

	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr, BlockPos pos)
	{
		return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, this.getAllUsernames()) : null;
	}

	protected String[] getAllUsernames()
	{
		return MinecraftServer.getServer().getAllUsernames();
	}

	@Override
	public boolean isUsernameIndex(String[] par1ArrayOfStr, int par2)
	{
		return par2 == 0;
	}
}
