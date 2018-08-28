import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int width,height;
	Images images = new Images();
	BufferedImage background;
	GameMechanics game;
	Graphics g;
	boolean running = true;
	int draw = 0;
	int x=0,y=0;
	
	JButton Button = new JButton("Start Game");
	JButton buttonTwo = new JButton("How to play");
	
	
	public Panel(int width,int height,GameMechanics game) {
		this.game = game;
		game.setPanel(this);
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(width,height));
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				int x = arg0.getX();
				int y = arg0.getY();
				
				if(x>15 && x<135 && game.getGameState() == 2) {
					game.setPassword(1);
					reDraw();
				}else {
					game.setPassword(0);
					reDraw();
				}
				
				if(x>=160 && x<=460 && game.getGameState() == 2 && y >= 500) {
					game.setHidden(true);
					reDraw();
				}else {
					game.setHidden(false);
					reDraw();
				}
				
				if(game.getGameState() == 6 && y > 500) {
					game.setShow(true);
					reDraw();
				}else {
					game.setShow(false);
					reDraw();
				}
				
			}
			
		});
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if(game.getGameState() == 1) {
					if(arg0.getX()>=480 && arg0.getX()<=681) {
						if(arg0.getY()>=80 && arg0.getY() <= 140) {
							game.getInputPanel().message("How many players are there?");
						}else if(arg0.getY() >= 150 && arg0.getY() <= 190) {
							game.getInputPanel().rules();
						}
					}
				}else if(game.getGameState() == 8 && game.choosen.size() < game.getNumOfPlayers()) {
					if(arg0.getX()>= 10 && arg0.getX()<= 130 && arg0.getY() >= 240 && arg0.getY() < 402) {
						game.addToChoosen(1);
						game.addToStart("Crazy Cat Lady");
						reDraw();
						if(game.choosen.size() == game.getNumOfPlayers()) {
							game.Initialise();
							game.choosen.clear();
							game.start.clear();
						}
					}else if(arg0.getX()>= 150 && arg0.getX()<= 270 && arg0.getY() >= 240 && arg0.getY() < 402) {
						game.addToChoosen(2);
						game.addToStart("Fat Tony");
						reDraw();
						if(game.choosen.size() == game.getNumOfPlayers()) {
							game.Initialise();
							game.choosen.clear();
							game.start.clear();
						}
					}else if(arg0.getX()>= 290 && arg0.getX()<= 410 && arg0.getY() >= 240 && arg0.getY() < 402) {
						game.addToChoosen(3);
						game.addToStart("Hanz Moleman");
						reDraw();
						if(game.choosen.size() == game.getNumOfPlayers()) {
							game.Initialise();
							game.choosen.clear();
							game.start.clear();
						}
					}else if(arg0.getX()>= 430 && arg0.getX()<= 550 && arg0.getY() >= 240 && arg0.getY() < 402) {
						game.addToChoosen(4);
						game.addToStart("Homer Simpson");
						reDraw();
						if(game.choosen.size() == game.getNumOfPlayers()) {
							game.Initialise();
							game.choosen.clear();
							game.start.clear();
						}
					}else if(arg0.getX()>= 570 && arg0.getX()<= 690 && arg0.getY() >= 240 && arg0.getY() < 402) {
						game.addToChoosen(5);
						game.addToStart("Maggie Simpson");
						reDraw();
						if(game.choosen.size() == game.getNumOfPlayers()) {
							game.Initialise();
							game.choosen.clear();
							game.start.clear();
						}
					}else if(arg0.getX()>= 710 && arg0.getX()<= 830 && arg0.getY() >= 240 && arg0.getY() < 402) {
						game.addToChoosen(6);
						game.addToStart("Moe Syzlack");
						reDraw();
						if(game.choosen.size() == game.getNumOfPlayers()) {
							game.Initialise();
							game.choosen.clear();
							game.start.clear();
						}
					}
				}
				
				if(arg0.getX()>=810 && arg0.getX()<=845 && game.getGameState() == 2 && arg0.getY() <= 45 && game.getOb().getDoor() > 0) {
				
					game.setCurrentGameState(5);
					reDraw();
				}
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	public void paint(Graphics g) {
		game.start(g);
		//repaint();
	}
	
	public void reDraw() {
		repaint();
	}
	
	

}
