package za.co.swingy.model.items;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Random;

import static java.lang.Math.abs;

@Getter
@Setter
@Builder
@Data
public class Helm {
	@NotNull
	private int		level;
	@NotNull
	private int		hitPointIncrease;
	@NotNull
	private String	name;

	// Builder
	public static class HelmBuilder {
		private int		level;
		private int		hitPointIncrease;
		private String	name;

		public HelmBuilder			level(int playerLevel) {
			this.level = playerLevel;
			Random rand = new Random();
			this.hitPointIncrease =  rand.nextInt(abs(playerLevel)) + 2;
			return this;
		}

		public HelmBuilder			name() {
			Random rand = new Random();
			String helmetStyle[] = {"Leather","Chain","Wooden","Bone","Shadow","Gold"};

			String name = helmetStyle[rand.nextInt(6)] + " Helmet";
			this.name = name;
			return this;
		}
	}

	public void			setStarterHelm() {
		this.name = "Straw Helmet";
		this.level = 1;
		this.hitPointIncrease = 1;
	}
}


//Helm helm = Helm.Builder.newInstance().setLevel(playerLevel).setName().build();