package za.co.swingy.view;

public interface EncounterView {
	int						display();

	void					fight(String attacker, String defender, int success, int damage);

	void					run(int success);

	void					simulate(int turn);

	void						title();

	int						itemDrop(String item, int index);
	}
