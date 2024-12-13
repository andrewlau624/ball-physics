import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame system = new JFrame();
		
		system.setResizable(false);
		system.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		system.setTitle("Physics");
		
		Game game = new Game();
		system.add(game);
		
		system.pack();
		
		system.setLocationRelativeTo(null);
		system.setVisible(true);
	}

}
