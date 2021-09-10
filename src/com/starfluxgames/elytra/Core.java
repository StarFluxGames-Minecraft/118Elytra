package com.starfluxgames.elytra;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onDamageEvent(PlayerItemDamageEvent event)
	{
		if (event.getItem().getType().equals(Material.ELYTRA))
		{
			event.setCancelled(true);
		}
	}
	
	
	/*
	 * Below Code By Redempt On Spigot Discord
	 */
	
	@EventHandler
	public void useItemInHand(PlayerInteractEvent e)
	{
		if (e.getAction() != Action.RIGHT_CLICK_AIR || e.getItem() == null || !e.getPlayer().isGliding()) {
			  return;
			}
			ItemStack item = e.getItem();
			if (item.getType() != Material.FIREWORK_ROCKET) {
			  return;
			}
			ItemStack chest = e.getPlayer().getInventory().getChestplate();
			if (chest.getType() != Material.ELYTRA) {
			  return;
			}
			
			int durability = (4 - chest.getEnchantmentLevel(Enchantment.DURABILITY));
			if (durability <= 0)
				durability = 1;
			
			chest.setDurability((short) Math.min(chest.getType().getMaxDurability(), chest.getDurability() + durability));
			e.getPlayer().getInventory().setChestplate(chest);
	}
	
}