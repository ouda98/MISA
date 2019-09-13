package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Core.Main;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.ScrollPane;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextPane input;
	private JLabel registers;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("Cahe Me Outside");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		input = new JTextPane();
		input.setBounds(10, 10, 358, 499);
		contentPane.add(input);
		
		JButton btnCompileAndRun = new JButton("Compile and Run");
		btnCompileAndRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String in = input.getText();
				String out;
				Main main =new Main();
				try {
					out = main.compile(in);
					if(out.equals("Done"))
						registers.setText(main.print());
					else
						registers.setText(out);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCompileAndRun.setBounds(127, 526, 152, 31);
		contentPane.add(btnCompileAndRun);
		
		ScrollPane Registers = new ScrollPane();
		Registers.setBounds(387, 10, 196, 499);
		contentPane.add(Registers);
		
		registers = new JLabel("");
		Registers.add(registers);
		registers.setBounds(654, 10, 192, 499);
		Main main1=new Main();
		registers.setText(main1.print());
		registers.setBackground(Color.white);
		registers.setOpaque(true);
		
		
		
	}
}
