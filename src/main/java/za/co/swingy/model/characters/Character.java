package za.co.swingy.model.characters;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Character {
//	private String		name;
	@NotNull
	private int			level;
	@NotNull
	private int			attack;
	@NotNull
	private int			defence;
	@NotNull
	private int			hitPoints;



	public				Character(Builder builder) {
		this.level = builder.level;
		this.attack = builder.attack;
		this.defence = builder.defence;
		this.hitPoints = builder.hitPoints;
	}
	// Builder
	public static class Builder {
		private int			level;
		private int			attack;
		private int			defence;
		private int			hitPoints;

		public static Builder 			newInstance() {
			return new Builder();
		}

		private Builder() {}

		public Builder					setLevel(int level) {
			this.level = level;
			return this;
		}

		public Builder					setAttack(int attack) {
			this.attack = attack;
			return this;
		}

		public Builder					setDefence(int defence) {
			this.defence = defence;
			return this;
		}

		public Builder					setHitpoints(int hitPoints) {
			this.hitPoints = hitPoints;
			return this;
		}

		public Character 				build() {
			return new Character(this);
		}

	}
}
