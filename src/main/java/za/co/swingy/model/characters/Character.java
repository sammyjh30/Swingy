package za.co.swingy.model.characters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
//@Builder
public class Character {
	@NotNull
	private int			level;
	@NotNull
	private int			attack;
	@NotNull
	private int			defence;
	@NotNull
	private int			hitPoints;
	@NotNull
	private int			maxHitPoints;
}
