import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Frame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Panel panel;
	InputPanel inputPanel;
	int width, height;
	GameMechanics game;

	public Frame(int width,int height) {
		game = new GameMechanics(width,height);
		this.width = width;
		this.height = height;
		setSize(width,height);
		setTitle("Cluedo");
		setResizable(false);
		setLayout(new BorderLayout());
		init();
	}
	
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new Panel(852,height,game);
		inputPanel = new InputPanel(195,height,game,game.getAccuse());
	
		add(panel,BorderLayout.WEST);
		add(inputPanel,BorderLayout.EAST);
	
		setVisible(true);	
	}
	
}
