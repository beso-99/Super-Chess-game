package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import exceptions.InvalidPowerUseException;
import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;

public class mainview extends JFrame implements ActionListener {
	JPanel main = new JPanel();
	JPanel left = new JPanel();
	JPanel right = new JPanel();
	JPanel moves = new JPanel();
	static JButton board[][] = new JButton[7][6];
	int i;
	int j;

	JLabel currentplayer = new JLabel();
	JLabel payload = new JLabel();
	JLabel player1dead = new JLabel("Player 1 DeadCharachters");
	JLabel player2dead = new JLabel("Player 2 DeadCharachters");
	Toolkit tk = Toolkit.getDefaultToolkit();
	// SR
	JPanel SR = new JPanel();
	JLabel a = new JLabel("Please chose a direction for the power ");
	JComboBox<String> direction = new JComboBox<String>();
	// me
	JPanel m = new JPanel();
	JLabel b = new JLabel("Please chose a piece to resurrect");
	JComboBox<String> dead = new JComboBox<String>();
	// tech
	JPanel tech = new JPanel();
	JLabel c = new JLabel("Please choose a power to use");
	JComboBox<String> powers = new JComboBox<String>();
	boolean hackon = false;
	boolean teleon = false;
	boolean teleon2 = false;
	Piece teled;
	Point teleLoc;

	JComboBox<String> deadcharachtersp1 = new JComboBox<String>();
	JComboBox<String> deadcharachtersp2 = new JComboBox<String>();

	Game game;

	public mainview(Game game) {
		this.game = game;
		this.setTitle("Super Chess");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		left.setLayout(new GridLayout(6, 1));
		right.setLayout(new GridLayout(6, 1));
		main.setLayout(new GridLayout(7, 6));
		moves.setLayout(new GridLayout(3, 3));

		putbuttons(main, board);
		UpdateCurrentplayer(currentplayer);
		UpdatePayload(payload);
		Updatedeadcharachters(deadcharachtersp1, game.getPlayer1());
		Updatedeadcharachters(deadcharachtersp2, game.getPlayer2());
		Updatemoves(moves);

		direction.addItem("UP");
		direction.addItem("UP Left");
		direction.addItem("UP Right");
		direction.addItem("Left");
		direction.addItem("Right");
		direction.addItem("Down");
		direction.addItem("Down Left");
		direction.addItem("Down Right");

		left.add(payload);
		left.add(player1dead);
		left.add(deadcharachtersp1);
		left.add(player2dead);
		left.add(deadcharachtersp2);

		right.add(currentplayer);

		this.add(right, BorderLayout.EAST);
		this.add(left, BorderLayout.WEST);
		this.add(main, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public static void setButtonText(int i, int j, String s) {
		board[i][j].setText(s);
	}

	public void updateboard() {
		UpdateImgs();
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				String name = game.getCellAt(i, j).toString();
				board[i][j].setText("");

				if (name.equals("")) {
					board[i][j].setToolTipText("There is no piece in this cell");
					board[i][j].setActionCommand("");
				} else {
					board[i][j].setActionCommand(name);
					String info = name + "/" + game.getCellAt(i, j).getPiece().getOwner().getName();
					if (game.getCellAt(i, j).getPiece() instanceof Armored) {
						String Armor = (((Armored) game.getCellAt(i, j).getPiece()).isArmorUp()) ? "Armor is UP "
								: "Armor is Down";
						info = info + "/" + Armor;

					}
					if (game.getCellAt(i, j).getPiece() instanceof ActivatablePowerHero) {
						String power = (((ActivatablePowerHero) game.getCellAt(i, j).getPiece()).isPowerUsed())
								? "Power is used "
								: "Power is not used";
						info = info + "/" + power;
					}
					board[i][j].setToolTipText(info);

				}
			}
		}
	}
	public void UpdateImgs () {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				String name = game.getCellAt(i, j).toString();
				switch (name) {
				case "Armored 1":
					if(((Armored)game.getCellAt(i, j).getPiece()).isArmorUp())board[i][j].setIcon(new ImageIcon("Armored.png"));
					else board[i][j].setIcon(new ImageIcon("Armored down.png"));
					break;
				case "Armored 2":
				if(((Armored)game.getCellAt(i, j).getPiece()).isArmorUp())board[i][j].setIcon(new ImageIcon("Armored p2.png"));
				else board[i][j].setIcon(new ImageIcon("Armored down p2.png"));
				break;
				
				
				
				case "Speedster 1":board[i][j].setIcon(new ImageIcon("Speedster.png"));
				
				break;
				case "Speedster 2":board[i][j].setIcon(new ImageIcon("Speedster p2.png"));
				
				break;
				case "Medic 1":board[i][j].setIcon(new ImageIcon("Medic.png"));
				
				break;
				case "Medic 2":board[i][j].setIcon(new ImageIcon("Medic p2.png"));
				
				break;
				case "Ranged 1":board[i][j].setIcon(new ImageIcon("Ranged.png"));
				
				break;
				case "Ranged 2":board[i][j].setIcon(new ImageIcon("Ranged p2.png"));
				
				break;
				case "Tech 1":board[i][j].setIcon(new ImageIcon("Tech.png"));
				
				break;
				case "Tech 2":board[i][j].setIcon(new ImageIcon("Tech p2.png"));
				
				break;
				case "Super 1":board[i][j].setIcon(new ImageIcon("Super.png"));
				
				break;
				case "Super 2":board[i][j].setIcon(new ImageIcon("Super p2.png"));
				
				break;
				case "SideKick 1":board[i][j].setIcon(new ImageIcon("SideKick.png"));
				
				break;
				case "SideKick 2":board[i][j].setIcon(new ImageIcon("SideKick p2.png"));
				
				
				break;
				case "":board[i][j].setIcon(null);
				
				default:
					break;
				}
				//board[i][j].setText(name);

				if (name.equals("")) {
					board[i][j].setToolTipText("There is no piece in this cell");
					board[i][j].setActionCommand("");
				} else {
					board[i][j].setActionCommand(name);
					String info = name + "/" + game.getCellAt(i, j).getPiece().getOwner().getName();
					if (game.getCellAt(i, j).getPiece() instanceof Armored) {
						String Armor = (((Armored) game.getCellAt(i, j).getPiece()).isArmorUp()) ? "Armor is UP "
								: "Armor is Down";
						info = info + "/" + Armor;

					}
					if (game.getCellAt(i, j).getPiece() instanceof ActivatablePowerHero) {
						String power = (((ActivatablePowerHero) game.getCellAt(i, j).getPiece()).isPowerUsed())
								? "Power is used "
								: "Power is not used";
						info = info + "/" + power;
					}
					board[i][j].setToolTipText(info);

				}
			}
		}
		
		
	}
	public void UpdateSR() {
		SR.removeAll();
		SR.add(a);
		SR.add(direction);
	}

	public void Updatem() {
		m.removeAll();
		dead.removeAllItems();
		m.add(a);
		m.add(direction);
		m.add(b);
		m.add(dead);
		for (int i = 0; i < game.getCurrentPlayer().getDeadCharacters().size(); i++) {
			dead.addItem(game.getCurrentPlayer().getDeadCharacters().get(i).toString());
		}
	}

	public void Updatet() {
		tech.removeAll();
		powers.removeAllItems();
		tech.add(c);
		tech.add(powers);
		powers.addItem("Hack or Restore");
		// powers.addItem("Restore");
		powers.addItem("Teleport");
	}

	public void putbuttons(JPanel x, JButton board[][]) {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				String name = game.getCellAt(i, j).toString();
				board[i][j] = new JButton("");
				board[i][j].addActionListener(this);
				x.add(board[i][j]);
				if (name.equals("")) {
					board[i][j].setToolTipText("There is no piece in this cell");
					board[i][j].setActionCommand("");

				} else {
					board[i][j].setActionCommand(name);
					String info = name + "/" + game.getCellAt(i, j).getPiece().getOwner().getName();
					if (game.getCellAt(i, j).getPiece() instanceof Armored) {
						String Armor = (((Armored) game.getCellAt(i, j).getPiece()).isArmorUp()) ? "Armor is UP "
								: "Armor is Down";
						info = info + "/" + Armor;

					}
					if (game.getCellAt(i, j).getPiece() instanceof ActivatablePowerHero) {
						String power = (((ActivatablePowerHero) game.getCellAt(i, j).getPiece()).isPowerUsed())
								? "Power is used "
								: "Power is not used";
						info = info + "/" + power;
					}
					board[i][j].setToolTipText(info);

				}
			}
		}
		UpdateImgs ();
	}

	public void UpdateCurrentplayer(JLabel a) {
		a.setText("The current Player is " + game.getCurrentPlayer().getName());

	}

	public void UpdatePayload(JLabel a) {
		a.setText(this.game.getPlayer1().getName() + "'s payload is " + game.getPlayer1().getPayloadPos() + "  "
				+ this.game.getPlayer2().getName() + "'s payload is " + game.getPlayer2().getPayloadPos());
		if(game.checkWinner()) {
			if(game.getPlayer1().getPayloadPos()==6)
				JOptionPane.showMessageDialog(null, game.getPlayer1().getName()+" Won !");
			else 	JOptionPane.showMessageDialog(null, game.getPlayer2().getName()+" Won !"); dispose();

		}
	}

	public void Updatedeadcharachters(JComboBox<String> a, Player p) {

		a.removeAllItems();
		for (int i = 0; i < p.getDeadCharacters().size(); i++) {
			a.addItem(p.getDeadCharacters().get(i).toString());
		}
	}

	public void Updatemoves(JPanel m) {
		JButton a1 = new JButton("");
		a1.setIcon(new ImageIcon("UP LEFT.png"));
		a1.addActionListener(this);
		a1.setActionCommand("UP Left");

		JButton a2 = new JButton("");
		a2.setIcon(new ImageIcon("UP.png"));
		a2.addActionListener(this);
		a2.setActionCommand("UP");

		JButton a3 = new JButton("");
		a3.setIcon(new ImageIcon("UP RIGHT.png"));
		a3.addActionListener(this);
		a3.setActionCommand("UP Right");

		JButton a4 = new JButton("");
		a4.setIcon(new ImageIcon("LEFT.png"));
		a4.addActionListener(this);
		a4.setActionCommand("Left");

		JButton a5 = new JButton("");
		a5.setIcon(new ImageIcon("Power.png"));
		a5.addActionListener(this);
		a5.setActionCommand("Power");

		JButton a6 = new JButton("");
		a6.setIcon(new ImageIcon("RIGHT.png"));
		a6.addActionListener(this);
		a6.setActionCommand("Right");

		JButton a7 = new JButton("");
		a7.setIcon(new ImageIcon("DOWN LEFT.png"));
		a7.addActionListener(this);
		a7.setActionCommand("Down Left");

		JButton a8 = new JButton("");
		a8.setIcon(new ImageIcon("DOWN.png"));
		a8.addActionListener(this);
		a8.setActionCommand("Down");

		JButton a9 = new JButton("");
		a9.setIcon(new ImageIcon("DOWN RIGHT.png"));
		a9.addActionListener(this);
		a9.setActionCommand("Down Right");

		m.add(a1);
		m.add(a2);
		m.add(a3);
		m.add(a4);
		m.add(a5);
		m.add(a6);
		m.add(a7);
		m.add(a8);
		m.add(a9);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(teleon2) {
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					if (board[i][j].equals(e.getSource())) {
						
						this.updateboard();

						try {
							((ActivatablePowerHero) game.getCellAt(this.i, this.j).getPiece()).usePower(null,
									teled,new Point(i, j) );
							this.updateboard();
							teleon2=false;
						} catch (InvalidPowerUseException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
							teleon2=false;

						} catch (WrongTurnException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
							teleon2=false;

						}
					}
						}
					}
			
		}
		else if (teleon) {

			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					if (board[i][j].equals(e.getSource())) {

						if (game.getCellAt(i, j).getPiece() == null) {
							JOptionPane.showMessageDialog(null, "Can't select an empty cell to teleport");
							teleon = false;
						} else {
							teled = game.getCellAt(i, j).getPiece();
							teleon = false;
							teleon2 = true;
							JOptionPane.showMessageDialog(null, "Please select a location");

						}
					}
				}

			}

		} else if (hackon) {
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					if (board[i][j].equals(e.getSource())) {
						try {
							((ActivatablePowerHero) game.getCellAt(this.i, this.j).getPiece()).usePower(null,
									game.getCellAt(i, j).getPiece(), null);
							this.updateboard();
							hackon = false;
						} catch (InvalidPowerUseException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
							hackon = false;

						} catch (WrongTurnException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
							hackon = false;
						}
						// this.i=i;
						// this.j=j;
						// right.remove(moves);
						// this.setVisible(true);
					}

				}
			}
		} else if (e.getActionCommand().equals("")) {

			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					if (board[i][j].equals(e.getSource())) {
						this.i = i;
						this.j = j;
						right.remove(moves);
						this.setVisible(true);
					}

				}
			}
		} else {
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					if (board[i][j].equals(e.getSource())) {
						this.i = i;
						this.j = j;
						right.add(moves);
						this.setVisible(true);
						
					}
					
				}
			}
			if(game.getCellAt(i, j).getPiece()==null)
			{
				JOptionPane.showMessageDialog(null,"Chose a piece to move");	;
			}
			else
			{
			switch (e.getActionCommand()) {
			case "Power":
				if (game.getCellAt(i, j).getPiece() instanceof ActivatablePowerHero) {
					if ((game.getCellAt(i, j).getPiece() instanceof Super
							|| (game.getCellAt(i, j).getPiece() instanceof Ranged))) {
						UpdateSR();
						JOptionPane.showMessageDialog(null, SR);
						Direction d;
						switch (direction.getSelectedItem().toString()) {
						case "UP":
							d = Direction.UP;
							break;
						case "UP Left":
							d = Direction.UPLEFT;
							break;
						case "UP right":
							d = Direction.UPRIGHT;
							break;
						case "Left":
							d = Direction.LEFT;
							break;
						case "Right":
							d = Direction.RIGHT;
							break;
						case "Down":
							d = Direction.DOWN;
							break;
						case "Down Left":
							d = Direction.DOWNLEFT;
							break;
						case "Down Right":
							d = Direction.DOWNRIGHT;
							break;
						default:
							d = Direction.DOWN;
						}
						try {
							((ActivatablePowerHero) game.getCellAt(i, j).getPiece()).usePower(d, null, null);
							if(game.checkWinner()) {
								if(game.getPlayer1().getPayloadPos()==6)
									JOptionPane.showMessageDialog(null, game.getPlayer1().getName()+" Won !");
								else 	JOptionPane.showMessageDialog(null, game.getPlayer2().getName()+" Won !");


									
							}
						} catch (InvalidPowerUseException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());

						} catch (WrongTurnException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
						this.UpdateCurrentplayer(currentplayer);
						this.updateboard();
						this.Updatedeadcharachters(deadcharachtersp1, game.getPlayer1());
						this.Updatedeadcharachters(deadcharachtersp2, game.getPlayer2());
						this.UpdatePayload(payload);
					}
					if (game.getCellAt(i, j).getPiece() instanceof Medic) {
						Updatem();
						JOptionPane.showMessageDialog(null, m);
						Direction dm;
						Piece target = null;
						String tr = (String) dead.getSelectedItem();
						switch (direction.getSelectedItem().toString()) {
						case "UP":
							dm = Direction.UP;
							break;
						case "UP Left":
							dm = Direction.UPLEFT;
							break;
						case "UP right":
							dm = Direction.UPRIGHT;
							break;
						case "Left":
							dm = Direction.LEFT;
							break;
						case "Right":
							dm = Direction.RIGHT;
							break;
						case "Down":
							dm = Direction.DOWN;
							break;
						case "Down Left":
							dm = Direction.DOWNLEFT;
							break;
						case "Down Right":
							dm = Direction.DOWNRIGHT;
							break;
						default:
							dm = Direction.DOWN;
						}
						for (int i = 0; i < game.getCurrentPlayer().getDeadCharacters().size(); i++) {
							if (game.getCurrentPlayer().getDeadCharacters().get(i).toString().equals(tr)) {
								target = game.getCurrentPlayer().getDeadCharacters().get(i);

							}
						}
						if (target == null) {
							JOptionPane.showMessageDialog(null, "This target is alive");
						} else {
							try {
								((ActivatablePowerHero) game.getCellAt(i, j).getPiece()).usePower(dm, target, null);
							} catch (InvalidPowerUseException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage());
							} catch (WrongTurnException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage());
							}
						}
						this.UpdateCurrentplayer(currentplayer);
						this.updateboard();
						this.Updatedeadcharachters(deadcharachtersp1, game.getPlayer1());
						this.Updatedeadcharachters(deadcharachtersp2, game.getPlayer2());
						this.UpdatePayload(payload);

					}

				}
				if (game.getCellAt(i, j).getPiece() instanceof Tech) {
					Updatet();
					JOptionPane.showMessageDialog(null, tech);
					switch (powers.getSelectedItem().toString()) {
					case "Hack or Restore":
						hackon = true;
						break;
					case "Teleport":
						teleon = true;
						JOptionPane.showMessageDialog(null, "Select the piece you want to teleport");
						break;
					}

					this.UpdateCurrentplayer(currentplayer);
					this.updateboard();
					this.Updatedeadcharachters(deadcharachtersp1, game.getPlayer1());
					this.Updatedeadcharachters(deadcharachtersp2, game.getPlayer2());
					this.UpdatePayload(payload);

				}
				break;
			case "Down":
				try {
					game.getCellAt(i, j).getPiece().move(Direction.DOWN);
					this.UpdateCurrentplayer(currentplayer);
					this.updateboard();
					this.Updatedeadcharachters(deadcharachtersp1, game.getPlayer1());
					this.Updatedeadcharachters(deadcharachtersp2, game.getPlayer2());
					this.UpdatePayload(payload);
					if(game.checkWinner()) {
						if(game.getPlayer1().getPayloadPos()==6)
							JOptionPane.showMessageDialog(null, game.getPlayer1().getName()+" Won !");
						else 	JOptionPane.showMessageDialog(null, game.getPlayer2().getName()+" Won !"); dispose();

					}
				} catch (UnallowedMovementException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (OccupiedCellException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (WrongTurnException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
					;
				}
				break;
			case "UP":
				try {
					game.getCellAt(i, j).getPiece().move(Direction.UP);
					this.UpdateCurrentplayer(currentplayer);
					this.updateboard();
					this.Updatedeadcharachters(deadcharachtersp1, game.getPlayer1());
					this.Updatedeadcharachters(deadcharachtersp2, game.getPlayer2());
					this.UpdatePayload(payload);
					if(game.checkWinner()) {
						if(game.getPlayer1().getPayloadPos()==6)
							JOptionPane.showMessageDialog(null, game.getPlayer1().getName()+" Won !");
						else 	JOptionPane.showMessageDialog(null, game.getPlayer2().getName()+" Won !");

					}
				} catch (UnallowedMovementException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (OccupiedCellException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (WrongTurnException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
					;
				}
				break;
			case "Left":
				try {
					game.getCellAt(i, j).getPiece().move(Direction.LEFT);
					this.UpdateCurrentplayer(currentplayer);
					this.updateboard();
					this.Updatedeadcharachters(deadcharachtersp1, game.getPlayer1());
					this.Updatedeadcharachters(deadcharachtersp2, game.getPlayer2());
					this.UpdatePayload(payload);
					if(game.checkWinner()) {
						if(game.getPlayer1().getPayloadPos()==6)
							JOptionPane.showMessageDialog(null, game.getPlayer1().getName()+" Won !");
						else 	JOptionPane.showMessageDialog(null, game.getPlayer2().getName()+" Won !");

					}
				} catch (UnallowedMovementException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (OccupiedCellException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (WrongTurnException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
					;
				}
				
				
				break;
			case "Right":
				try {
					game.getCellAt(i, j).getPiece().move(Direction.RIGHT);
					this.UpdateCurrentplayer(currentplayer);
					this.updateboard();
					this.Updatedeadcharachters(deadcharachtersp1, game.getPlayer1());
					this.Updatedeadcharachters(deadcharachtersp2, game.getPlayer2());
					this.UpdatePayload(payload);
					if(game.checkWinner()) {
						if(game.getPlayer1().getPayloadPos()==6)
							JOptionPane.showMessageDialog(null, game.getPlayer1().getName()+" Won !");
						else 	JOptionPane.showMessageDialog(null, game.getPlayer2().getName()+" Won !");

					}
				} catch (UnallowedMovementException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (OccupiedCellException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (WrongTurnException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
					;
				}
				break;
			case "UP Left":
				try {
					game.getCellAt(i, j).getPiece().move(Direction.UPLEFT);
					this.UpdateCurrentplayer(currentplayer);
					this.updateboard();
					this.Updatedeadcharachters(deadcharachtersp1, game.getPlayer1());
					this.Updatedeadcharachters(deadcharachtersp2, game.getPlayer2());
					this.UpdatePayload(payload);
					if(game.checkWinner()) {
						if(game.getPlayer1().getPayloadPos()==6)
							JOptionPane.showMessageDialog(null, game.getPlayer1().getName()+" Won !");
						else 	JOptionPane.showMessageDialog(null, game.getPlayer2().getName()+" Won !");

					}
				} catch (UnallowedMovementException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (OccupiedCellException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (WrongTurnException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
					;
				}
				break;
			case "UP Right":
				try {
					game.getCellAt(i, j).getPiece().move(Direction.UPRIGHT);
					this.UpdateCurrentplayer(currentplayer);
					this.updateboard();
					this.Updatedeadcharachters(deadcharachtersp1, game.getPlayer1());
					this.Updatedeadcharachters(deadcharachtersp2, game.getPlayer2());
					this.UpdatePayload(payload);
					if(game.checkWinner()) {
						if(game.getPlayer1().getPayloadPos()==6)
							JOptionPane.showMessageDialog(null, game.getPlayer1().getName()+" Won !");
						else 	JOptionPane.showMessageDialog(null, game.getPlayer2().getName()+" Won !");

					}
				} catch (UnallowedMovementException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (OccupiedCellException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (WrongTurnException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
					;
				}
				break;
			case "Down Left":
				try {
					game.getCellAt(i, j).getPiece().move(Direction.DOWNLEFT);
					this.UpdateCurrentplayer(currentplayer);
					this.updateboard();
					this.Updatedeadcharachters(deadcharachtersp1, game.getPlayer1());
					this.Updatedeadcharachters(deadcharachtersp2, game.getPlayer2());
					this.UpdatePayload(payload);
					if(game.checkWinner()) {
						if(game.getPlayer1().getPayloadPos()==6)
							JOptionPane.showMessageDialog(null, game.getPlayer1().getName()+" Won !");
						else 	JOptionPane.showMessageDialog(null, game.getPlayer2().getName()+" Won !");

					}
				} catch (UnallowedMovementException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (OccupiedCellException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (WrongTurnException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
					;
				}
				break;
			case "Down Right":
				try {
					game.getCellAt(i, j).getPiece().move(Direction.DOWNRIGHT);
					this.UpdateCurrentplayer(currentplayer);
					this.updateboard();
					this.Updatedeadcharachters(deadcharachtersp1, game.getPlayer1());
					this.Updatedeadcharachters(deadcharachtersp2, game.getPlayer2());
					this.UpdatePayload(payload);
					if(game.checkWinner()) {
						if(game.getPlayer1().getPayloadPos()==6)
							JOptionPane.showMessageDialog(null, game.getPlayer1().getName()+" Won !");
						else 	JOptionPane.showMessageDialog(null, game.getPlayer2().getName()+" Won !");

					}
				} catch (UnallowedMovementException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (OccupiedCellException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
				} catch (WrongTurnException e8) {

					JOptionPane.showMessageDialog(null, e8.getMessage());
					;
				}
				break;

			}
			}
		}
	}
}
