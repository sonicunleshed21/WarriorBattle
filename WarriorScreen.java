import javax.swing.JFrame;

public class WarriorScreen extends JFrame{
	// this is where the game is actually made.
	WarriorScreen (){
		// this generates the frame and sets some boundries that the game must follow
		this.add(new WarriorAI());
		this.setTitle("Warrior Battle");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
