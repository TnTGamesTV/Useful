package de.mountainsmc.server.plugins.lobby.sammlung.formatter;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.mountainsmc.server.plugins.lobby.sammlung.ItemData;
import de.mountainsmc.server.plugins.lobby.sammlung.ItemPage;
import de.mountainsmc.server.plugins.lobby.sammlung.ItemType;
import de.mountainsmc.server.plugins.lobby.sammlung.ItemsViewer;

public class ItemsDecoder {
	
	/**
	 * Decodes the data from {@link ItemsDatabaseManager#getItems(org.bukkit.entity.Player) getItems(org.bukkit.entity.Player)}}
	 * @param data the data to decode
	 * @return an {@link ItemViewer} to open as a 'Sammlung'
	 */
	public static ItemsViewer decode(String data){
		ItemsViewer viewer = new ItemsViewer();
		String[] items = data.split(","); //Spliting the items into an array
		if(!items[0].equalsIgnoreCase("")){
			for(int i = 0; i < items.length; i++){
				String item = items[i];
				items[i] = removeCurlyBrackets(item);
			} //removing all curly brackets around items for better access
			
//			Main.debug("items.length: " + items.length);
			
			ItemPage[] pages = new ItemPage[calcNeededPages(items.length)]; //creating an array where all pages will be stored soon
//			Main.debug("pages.length: " + pages.length);
			
			int counter3 = 0; //counter for the pages used
//			Main.debug("Counter: counter2: " + counter2 + "; counter3: " + counter3 + ";");
			
			for(int i = 0; i < items.length; i++){
				String item = items[i];
//				Main.debug("item: " + item);
				ItemData itemData = convertItemToData(item);
				ItemStack stack = convertItem(item);
				ItemPage page = pages[counter3];
				
				if(page == null){
					page = new ItemPage();
					page.setPageID(counter3+1);
					pages[counter3] = page;
					viewer.addPage(page);
				}
				
				page.addContent(stack); //adding this item to the page that is filled at the moment
				page.addData(itemData); //adding this data to the page that is filled at the moment
//				Main.debug("Counter: counter2: " + counter2 + "; counter3: " + counter3 + ";");
				
				if(page.getPageContent().size() == 18){
					counter3++;
				}
			}
		}else{
			ItemPage page = new ItemPage();
			page.setPageID(1);
			viewer.addPage(page);
		}
		
		return viewer;
	}
	
	private static ItemData convertItemToData(String data) {
		String[] dataAr = data.split(":"); //splits the data into 0 -> type and 1 -> amount
		int amount = Integer.parseInt(dataAr[1]); //parses the string amount to an integer amount
		String type = dataAr[0]; //defines the type
		
		ItemData itemData = new ItemData(type, amount);
		return itemData;
	}

	/**
	 * Converting data into an itemstack
	 * @param data the items data
	 * @return an itemstack representing the given data as an ItemStack
	 */
	private static ItemStack convertItem(String data){
		String[] dataAr = data.split(":"); //splits the data into 0 -> type and 1 -> amount
		int amount = Integer.parseInt(dataAr[1]); //parses the string amount to an integer amount
		String type = dataAr[0]; //defines the type
		int realAmount = amount;
		if(amount > 64) amount = 64;
		
		if(type.equalsIgnoreCase(ItemType.ENDER_PEARL)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.ENDER_PEARL, amount, (short) 0); //new itemstack an enderpearl mit the given amount -> UNSAFE because of unlimited amount!
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§9Enderperle: §a" + realAmount + " §7x");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§9Enderperle: §e" + realAmount + " §7x");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§9Enderperle: §c" + realAmount + " §7x");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.HOOK)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.FISHING_ROD, amount, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§9Enterhaken: §a" + realAmount + " §7x");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§9Enterhaken: §e" + realAmount + " §7x");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§9Enterhaken: §c" + realAmount + " §7x");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.GOLD_BOOTS)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.GOLD_BOOTS, 1, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§6Sprinterschuhe");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§6Sprinterschuhe");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§6Sprinterschuhe");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.GOLD_CHESTPLATE)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.GOLD_CHESTPLATE, 1, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§6Goldpanzer");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§6Goldpanzer");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§6Goldpanzer");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.GOLD_LEGGINGS)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.GOLD_LEGGINGS, 1, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§6Läuferhose");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§6Läuferhose");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§6Läuferhose");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.GOLD_HELMET)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.GOLD_HELMET, 1, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§6Krone");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§6Krone");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§6Krone");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.SLIME_TRAMPOLIN)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.SLIME_BALL, amount, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§aSchleimtrampolin: §a" + realAmount + " §7x");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§aSchleimtrampolin: §e" + realAmount + " §7x");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§aSchleimtrampolin: §c" + realAmount + " §7x");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.FIREWORK_SMALL)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.FIREWORK_CHARGE, amount, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§9Kleines Feuerwerk: §a" + realAmount + " §7x");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§9Kleines Feuerwerk: §e" + realAmount + " §7x");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§9Kleines Feuerwerk: §c" + realAmount + " §7x");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.FIREWORK_BIG)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.FIREWORK, amount, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§9Großes Feuerwerk: §a" + realAmount + " §7x");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§9Großes Feuerwerk: §e" + realAmount + " §7x");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§9Großes Feuerwerk: §c" + realAmount + " §7x");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.EXPLOSION)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.TNT, amount, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§4Explosion: §a" + realAmount + " §7x");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§4Explosion: §e" + realAmount + " §7x");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§4Explosion: §c" + realAmount + " §7x");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.SKULL_SKELETON)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.SKULL_ITEM, amount, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§9Skelettschädel: §7" + realAmount + " x");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§9Skelettschädel: §7" + realAmount + " x");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§9Skelettschädel: §7" + realAmount + " x");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.SKULL_WSKELETON)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 1);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§9Witherskelettkopf");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§9Witherskelettkopf");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§9Witherskelettkopf");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.SKULL_ZOMBIE)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§2Zombiemaske");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§2Zombiemaske");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§2Zombiemaske");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.SKULL_STEVE)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§9Steve-Kopf");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§9Steve-Kopf");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§9Steve-Kopf");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.SKULL_CREEPER)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 4);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§aCreepermaske");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§aCreepermaske");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§aCreepermaske");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.PUFFERFISH)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.RAW_FISH, amount, (short) 3);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§eKugelfisch: §7" + realAmount + " x");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§eKugelfisch: §7" + realAmount + " x");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§eKugelfisch: §7" + realAmount + " x");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.FROST)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.PRISMARINE_SHARD, amount, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§1EINFRIEREN!: §7" + realAmount + " x");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§1EINFRIEREN!: §7" + realAmount + " x");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§1EINFRIEREN!: §7" + realAmount + " x");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.PARTICLE_PURPLE)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.INK_SACK, 1, (short) 5);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§5Violette Spur");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§5Violette Spur");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§5Violette Spur");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.PARTICLE_CYAN)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.INK_SACK, 1, (short) 6);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§3Türkise Spur");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§3Türkise Spur");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§3Türkise Spur");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.PARTICLE_GREY)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.INK_SACK, 1, (short) 9);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§dPinke Spur");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§dPinke Spur");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§dPinke Spur");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.PARTICLE_LIME)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.INK_SACK, 1, (short) 10);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§aHellgrüne Spur");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§aHellgrüne Spur");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§aHellgrüne Spur");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.PARTICLE_LIGHTBLUE)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.INK_SACK, 1, (short) 12);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§9Hellblaue Spur");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§9Hellblaue Spur");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§9Hellblaue Spur");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.PARTICLE_ORANGE)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.INK_SACK, 1, (short) 14);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§6Orangene Spur");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§6Orangene Spur");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§6Orangene Spur");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.PARTICLE_SHINY)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.GLOWSTONE_DUST, 1, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§fGlitzernde Spur");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§fGlitzernde Spur");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§fGlitzernde Spur");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.PARTICLE_WATER_BUBBLE)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.WATER_BUCKET, 1, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§9Wasserspur");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§9Wasserspur");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§9Wasserspur");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.PARTICLE_CLOUD)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.INK_SACK, 1, (short) 15);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§fWolkenspur");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§fWolkenspur");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§fWolkenspur");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.PARTICLE_HEART)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.REDSTONE, 1, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§4Herzspur");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§4Herzspur");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§4Herzspur");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.PARTICLE_MUSIC)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.NOTE_BLOCK, 1, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§9Musikspur");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§9Musikspur");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§9Musikspur");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		else if(type.equalsIgnoreCase(ItemType.PARTICLE_MAGIC)){//checking for type; ItemType has constants with strings -> ENDER_PEARL = a1
			ItemStack i1 = new ItemStack(Material.POTION, 1, (short) 0);
			ItemMeta i1m = i1.getItemMeta(); //new item meta
			if(realAmount > 9){ //checking for diffrent amount values for changing item display name
				i1m.setDisplayName("§5Magiespur");
			}else if(realAmount > 3 && realAmount <= 9){
				i1m.setDisplayName("§5Magiespur");
			}else if(realAmount <= 3){
				i1m.setDisplayName("§5Magiespur");
			}
			i1.setItemMeta(i1m); //setting meta
			return i1; //return item
		}

		
		return null;
	}
	
	/**
	 * removes curly brackets, nothing else
	 * @param in a string with curly brackets
	 * @return a string without curly brackets
	 */
	private static String removeCurlyBrackets(String in){
		in = in.replace("{", ""); //replaces open curly bracket
		in = in.replace("}", ""); //replacing closing curly bracket
		return in;
	}
	
	/**
	 * Calculating the needed pages for an amount of items
	 * @param items the amount of items
	 * @return the pages needed
	 */
	private static int calcNeededPages(int items){
		return (int) (items / 17) + 1; 
	}
}
