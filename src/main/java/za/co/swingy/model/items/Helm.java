package za.co.swingy.model.items;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Random;

@Getter
@Setter
public class Helm {
	@NotNull
	private int		level;
	@NotNull
	private int		hitPointIncrease;
	@NotNull
	private String	name;

	public				Helm(Builder builder) {
		this.level = builder.level;
		this.hitPointIncrease = builder.hitPointIncrease;
		this.name = builder.name;
	}

	// Builder
	public static class Builder {
		private int		level;
		private int		hitPointIncrease;
		private String	name;

		public static Builder 		newInstance() {
			return new Builder();
		}

		private Builder() {}

		public Builder				setLevel(int level) {
			this.level = level;
			Random rand = new Random();
			this.hitPointIncrease =  rand.nextInt(level) + 2;
			return this;
		}

		public Builder			setName() {
			Random rand = new Random();
			String helmetStyle[] = {"Leather","Chain","Wooden","Bone","Shadow","Gold"};

			String name = helmetStyle[rand.nextInt(6)] + " Helmet";
			this.name = name;
			return this;
		}

		public Helm 				build() {
			return new Helm(this);
		}
	}
}


//Helm helm = Helm.Builder.newInstance().setLevel(playerLevel).setName().build();