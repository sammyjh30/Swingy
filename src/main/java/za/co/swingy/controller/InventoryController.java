package za.co.swingy.controller;

import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.InventoryView;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class InventoryController {
	@NotNull
	private Hero hero;

	// Builder
	public static class 		InventoryControllerBuilder {
		private Hero hero;
		private InventoryView inventoryView;

		public InventoryControllerBuilder		hero(Hero hero) {
			this.hero = hero;
			return this;
		}

		public InventoryControllerBuilder			inventoryView(InventoryView view) {
			this.inventoryView = view;
			return this;
		}

	}
}
