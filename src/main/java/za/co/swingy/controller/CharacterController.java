package za.co.swingy.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Weapon;
import za.co.swingy.view.CreateHero;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.ArrayList;

@Getter
@Setter
@Builder
public class CharacterController {
	@NotNull
	private CreateHero		createHeroView;
	private Hero			hero;

	// Builder
	public static class CharacterControllerBuilder {
		private CreateHero		createHeroView;

		public CharacterControllerBuilder		createHeroView() {
			this.createHeroView = new CreateHero();
			return this;
		}
	}

	public void			createNewHero() {
		String name = this.createHeroView.promptName();
		String type = this.createHeroView.promptType();
		this.hero = Hero.builder().classType(type).name(name).inventory().build();
		this.hero.starterHero();
		this.createHeroView.printHeroStatus(this.hero);
	}

	public void			loadHero() {
		//Get File
		ArrayList<Hero> saves = readSaves();
		//List available heroes
		//Take input
		//Assign Hero
	}

	public static boolean 	isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public ArrayList<Hero>				readSaves() {
//		int numberOfRuns = 0;
		String st;
		try {
			File file = new File("resources/saves.txt");
			if (file.canRead()) {
				//To save
//				BufferedWriter writer = new BufferedWriter(new FileWriter("./simulation.txt", true));
//				writer.write(message + '\n');
//				writer.close();
				//To load
				BufferedReader br = new BufferedReader(new FileReader(file));
				ArrayList<Hero> heroSaves = new ArrayList<Hero>();
				Hero heroToAdd = null;
				Weapon weapon;
				Armor armor;
				Helm helm;
				try {
					int save = 0;
					String line[];
					while ((st = br.readLine()) != null) {
						//1|Name|Type|Level|Attack|Defence|HitPoints|MaxHitPoints|Experience|
						//1|Inventory|usedSlots|MaxSlots|equippedWeaponIndex|equippedArmorIndex|equippedHelmIndex
						//1|Weapons|Index|Name|Level|AttackIncrease
						//1|Armors|Index|Name|Level|DefenceIncrease
						//1|Helms|Index|Name|Level|hitpointIncrease
						line = st.split("|");
						if (isNumeric(line[0])){
							if (Integer.parseInt(line[0]) > save) {
								if (heroToAdd != null) {
									heroSaves.add(heroToAdd);
								}
								save = Integer.parseInt(line[0]);
								//Create new character
								heroToAdd = Hero.builder().name(line[1]).classType(line[2]).inventory().build();
								heroToAdd.setLevel(Integer.parseInt(line[3]));
								heroToAdd.setAttack(Integer.parseInt(line[4]));
								heroToAdd.setDefence(Integer.parseInt(line[5]));
								heroToAdd.setHitPoints(Integer.parseInt(line[6]));
								heroToAdd.setMaxHitPoints(Integer.parseInt(line[7]));
								heroToAdd.setExperience(Integer.parseInt(line[8]));
							} else if (Integer.parseInt(line[0]) == save && line[1] == "Inventory") {
								//add the inventory to the hero
								heroToAdd.getInventory().setUsedSlots(Integer.parseInt(line[2]));
								heroToAdd.getInventory().setMaxSlots(Integer.parseInt(line[3]));
								heroToAdd.getInventory().setEquippedWeaponIndex(Integer.parseInt(line[4]));
								heroToAdd.getInventory().setEquippedArmorIndex(Integer.parseInt(line[5]));
								heroToAdd.getInventory().setEquippedHelmIndex(Integer.parseInt(line[6]));
							} else if (Integer.parseInt(line[0]) == save && line[1] == "Weapons") {
								//Loop through and assign all weapons
								for (int i = 2; i < line.length; i++) {
									weapon = Weapon.builder().build();
									weapon.setName(line[++i]);
									weapon.setLevel(Integer.parseInt(line[++i]));
									weapon.setAttackIncrease(Integer.parseInt(line[++i]));
									heroToAdd.getInventory().getWeapons().add(weapon);
								}
								heroToAdd.setEquippedWeapon(heroToAdd.getInventory().getWeapons().get(heroToAdd.getInventory().getEquippedWeaponIndex()));
							} else if (Integer.parseInt(line[0]) == save && line[1] == "Armors") {
								//Loop through and assign all armors
								for (int i = 2; i < line.length; i++) {
									armor = Armor.builder().build();
									armor.setName(line[++i]);
									armor.setLevel(Integer.parseInt(line[++i]));
									armor.setDefenceIncrease(Integer.parseInt(line[++i]));
									heroToAdd.getInventory().getArmors().add(armor);
								}
								heroToAdd.setEquippedArmor(heroToAdd.getInventory().getArmors().get(heroToAdd.getInventory().getEquippedArmorIndex()));
							} else if (Integer.parseInt(line[0]) == save && line[1] == "Helms") {
								//Loop through and assign all helms
								for (int i = 2; i < line.length; i++) {
									helm = Helm.builder().build();
									helm.setName(line[++i]);
									helm.setLevel(Integer.parseInt(line[++i]));
									helm.setHitPointIncrease(Integer.parseInt(line[++i]));
									heroToAdd.getInventory().getHelms().add(helm);
								}
								heroToAdd.setEquippedHelm(heroToAdd.getInventory().getHelms().get(heroToAdd.getInventory().getEquippedHelmIndex()));
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				return heroSaves;
			}
		} catch ( FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
