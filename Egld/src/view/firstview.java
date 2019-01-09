package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.game.Game;
import model.game.Player;

public class firstview extends JFrame implements ActionListener {
	JLabel pic= new JLabel(new ImageIcon("Superhero.Chesss.jpg"));
	JLabel p1=new JLabel("Player 1");
	JLabel p2=new JLabel("Player 2");

	
	
	JTextField a =new JTextField(10);
	JTextField b =new JTextField(10);
	JButton startG=new JButton("Start Game");
	JPanel m=new JPanel();
	Game game;

	public firstview()
	{
		
		super();
		
		Container pane =this.getContentPane();
		this.setSize(900,785);
		this.setTitle("Super Chess");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	    startG.addActionListener(this);
	    startG.setActionCommand("Start");
	    a.setMaximumSize( a.getPreferredSize() );
	    b.setMaximumSize( b.getPreferredSize() );
		pane.add(pic,BorderLayout.CENTER);
		pane.add(p1,BorderLayout.CENTER);
		pane.add(a,BorderLayout.CENTER);
		pane.add(p2,BorderLayout.CENTER);
		pane.add(b,BorderLayout.CENTER);
		pane.add(startG,BorderLayout.CENTER);
		pane.setBackground(Color.WHITE);
		this.add(m);
		this.setVisible(true);
		//pane.setComponentOrientation();
		pic.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		a.setAlignmentX(Component.CENTER_ALIGNMENT);
		b.setAlignmentX(Component.CENTER_ALIGNMENT);
		p1.setAlignmentX(Component.CENTER_ALIGNMENT);
		p2.setAlignmentX(Component.CENTER_ALIGNMENT);
		startG.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.getRootPane().setDefaultButton(startG);
	}
	
	public static void main(String[] args) {
		firstview start=new firstview();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("Start"))
		{
			if(a.getText().equals("")||b.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please Enter your name");
				
			}
			else
			{
				game =new Game(new Player(a.getText()),new Player(b.getText()));
				mainview main=new mainview(game);
				main.setLocationRelativeTo(null);
				main.setSize(900,900);
				this.dispose();
			}
			
		}
	}
}