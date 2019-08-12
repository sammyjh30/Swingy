package za.co.swingy.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Weapon;
import za.co.swingy.view.CreateHeroView;
import za.co.swingy.view.LoadFileView;
import za.co.swingy.view.MapView;
import za.co.swingy.view.MenuView;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.ArrayList;

import static java.lang.Math.pow;

@Getter
@Setter
@Builder
public class CharacterController {
	@NotNull
	private CreateHeroView		createHeroView;
	private LoadFileView		loadFileView;
	private Hero						hero;
	@NotNull(message = "Menu View cannot be NULL")
	private MenuView					menuView;
	private ArrayList<Hero> saves;

	// Builder
	public static class CharacterControllerBuilder {
		private CreateHeroView				createHeroView;
		private LoadFileView				loadFileView;
		private MenuView					menuView;
		private MapView						mapView;

		public CharacterControllerBuilder				menuView(MenuView menuView) {
			this.menuView = menuView;
			return this;
		}

		public CharacterControllerBuilder				createHeroView(CreateHeroView createHeroView) {
			this.createHeroView = createHeroView;
			return this;
		}

		public CharacterControllerBuilder				loadFileView(LoadFileView loadFileView) {
			this.loadFileView = loadFileView;
			return this;
		}
	}

	public void				returnToMenu() {
		this.menuView.resetMenu();
	}

	public void				generateHero(String name, String type) {
		this.hero = Hero.builder().classType(type).name(name).inventory().build();
		this.hero.starterHero();
		this.createHeroView.printHeroStatus(this.hero);
	}

	public void			callTypePrompt(){
		this.createHeroView.promptType();
//		return null;
	}
	public void				createNewHero() {
		this.createHeroView.promptName(this);
//		this.callNamePrompt();

		// Call Name prompt -> name prompt calls type prompt -> type prompt calls generate Hero.
		//Generate Hero returns a int from print hero status -> carries int back to prompt name which we return in the controller

	}

	public void				loadSave(int i) {
		Hero loadedHero = null;
		//Assign Hero
		if (i >= 0) {
			loadedHero = this.saves.get(i);
		}
		this.loadFileView.printLoadedHero(loadedHero, this);
	}

	public void				loadHero() {
		this.loadFileView.setCharacterController(this);
		Hero loadedHero = null;
		//Get File
		this.saves = readSaves();
		if (saves == null || saves.size() == 0) {
			this.loadFileView.noSaves();
			this.menuView.resetMenu();
		}
		//List available heroes
		this.loadFileView.saveList(saves);
	}

	public static boolean 	isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public ArrayList<Hero>	readSaves() {
		String st;
		try {
			File file = new File("resources/saves.txt");
			if (file.canRead()) {
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
						//1|Name|Type|Level|Attack|Defence|HitPoints|MaxHitPoints|Experience|xPos|yPos
						//1|Inventory|usedSlots|MaxSlots|equippedWeaponIndex|equippedArmorIndex|equippedHelmIndex
						//1|Weapons|Index|Name|Level|AttackIncrease
						//1|Armors|Index|Name|Level|DefenceIncrease
						//1|Helms|Index|Name|Level|hitpointIncrease
						line = st.split("\\|");
						if (isNumeric(line[0])){
							if (Integer.parseInt(line[0]) > save) {
								save = Integer.parseInt(line[0]);
								//Create new character
								heroToAdd = Hero.builder().name(line[1]).classType(line[2]).inventory().build();
								if (Integer.parseInt(line[3]) > 0) {
									heroToAdd.setLevel(Integer.parseInt(line[3]));
								} else {
									heroToAdd.setLevel(1);
								}
								heroToAdd.setXpRequired( heroToAdd.getLevel() * 1000 + ((int)pow((heroToAdd.getLevel() - 1), 2) * 450));
								heroToAdd.setAttack(Integer.parseInt(line[4]));
								heroToAdd.setDefence(Integer.parseInt(line[5]));
								heroToAdd.setHitPoints(Integer.parseInt(line[6]));
								heroToAdd.setMaxHitPoints(Integer.parseInt(line[7]));
								heroToAdd.setExperience(Integer.parseInt(line[8]));
								heroToAdd.setXPos(Integer.parseInt(line[9]));
								heroToAdd.setYPos(Integer.parseInt(line[10]));
								heroSaves.add(heroToAdd);
							} else if (Integer.parseInt(line[0]) == save && line[1].equalsIgnoreCase("Inventory")) {
								//add the inventory to the hero
								heroSaves.get(save - 1).getInventory().setUsedSlots(Integer.parseInt(line[2]));
								heroSaves.get(save - 1).getInventory().setMaxSlots(Integer.parseInt(line[3]));
								heroSaves.get(save - 1).getInventory().setEquippedWeaponIndex(Integer.parseInt(line[4]));
								heroSaves.get(save - 1).getInventory().setEquippedArmorIndex(Integer.parseInt(line[5]));
								heroSaves.get(save - 1).getInventory().setEquippedHelmIndex(Integer.parseInt(line[6]));
							} else if (Integer.parseInt(line[0]) == save && line[1].equalsIgnoreCase("Weapons")) {
								//Loop through and assign all weapons
								for (int i = 2; i < line.length; i++) {
									weapon = Weapon.builder().build();
									weapon.setName(line[++i]);
									weapon.setLevel(Integer.parseInt(line[++i]));
									weapon.setAttackIncrease(Integer.parseInt(line[++i]));
									heroSaves.get(save - 1).getInventory().getWeapons().add(weapon);
								}
								if (heroToAdd.getInventory().getEquippedWeaponIndex() >= 0) {
									heroSaves.get(save - 1).setEquippedWeapon(heroToAdd.getInventory().getWeapons().get(heroToAdd.getInventory().getEquippedWeaponIndex()));
								} else {
									heroSaves.get(save - 1).setEquippedWeapon(null);
								}
							} else if (Integer.parseInt(line[0]) == save && line[1].equalsIgnoreCase("Armors")) {
								//Loop through and assign all armors
								for (int i = 2; i < line.length; i++) {
									armor = Armor.builder().build();
									armor.setName(line[++i]);
									armor.setLevel(Integer.parseInt(line[++i]));
									armor.setDefenceIncrease(Integer.parseInt(line[++i]));
									heroSaves.get(save - 1).getInventory().getArmors().add(armor);
								}
								if (heroToAdd.getInventory().getEquippedArmorIndex() >= 0) {
									heroSaves.get(save - 1).setEquippedArmor(heroToAdd.getInventory().getArmors().get(heroToAdd.getInventory().getEquippedArmorIndex()));
								} else {
									heroSaves.get(save - 1).setEquippedArmor(null);
								}
							} else if (Integer.parseInt(line[0]) == save && line[1].equalsIgnoreCase("Helms")) {
								//Loop through and assign all helms
								for (int i = 2; i < line.length; i++) {
									helm = Helm.builder().build();
									helm.setName(line[++i]);
									helm.setLevel(Integer.parseInt(line[++i]));
									helm.setHitPointIncrease(Integer.parseInt(line[++i]));
									heroSaves.get(save - 1).getInventory().getHelms().add(helm);
								}
								if (heroToAdd.getInventory().getEquippedHelmIndex() >= 0) {
									heroSaves.get(save - 1).setEquippedHelm(heroToAdd.getInventory().getHelms().get(heroToAdd.getInventory().getEquippedHelmIndex()));
								} else {
									heroSaves.get(save - 1).setEquippedHelm(null);
								}
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
