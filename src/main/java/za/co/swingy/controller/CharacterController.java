package za.co.swingy.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.CreateHero;

import javax.validation.constraints.NotNull;
import java.io.*;

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
		//List available heroes
		//Take input
		//Assign Hero
	}

	public void				readSaves() {
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
				try {
					while ((st = br.readLine()) != null) {
						//1|Name|Type|Level|
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch ( FileNotFoundException e) {
			e.printStackTrace();
		}

			String st;

			int     line = 0;
			// Read file
			try {
				while ((st = br.readLine()) != null) {
					try {
						if (line == 0) {
							if (isNumeric(st)) {
								numberOfRuns = Integer.parseInt(st);
							} else {
								throw new InvalidFileException((char) 27 + "[31m" + "ERROR: " + (char) 27 + "[37m" + "Invalid first line");
							}
						} else {
							//Create and register aircraft
							String details[] = st.split(" ");
							if (this.interpretLine(details) != 1) {
								return (-1);
							}
						}
					} catch (InvalidFileException e) {
						e.printStackTrace();
					}
					line++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return numberOfRuns;
	}
}
