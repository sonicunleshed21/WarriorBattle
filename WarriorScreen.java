import javax.swing.JFrame;

public class WarriorScreen extends JFrame{
	
	WarriorScreen (){
		this.add(new WarriorAI());
		this.setTitle("GameTemplate");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
