
/**
 * @description	 This program creates a window, panel, and nine grid slots. 
 * 				 The grid slots are inserted in the panel. The panel is 
 * 				 inserted in the window. Create array to hold values of the 
 * 				 nine slots. Each of the nine slots needs to reference an 
 * 				 index in the array. Loop thru to check if any of the 8 
 * 				 combinations of spots that can win the game. Set the button 
 * 				 event to change the value of the specific slot when the 
 * 				 button is clicked or selected by the computer. If the button 
 * 				 has already been given a value then print “spot is taken” in 
 * 				 a loop to let the user choose a different spot; for the 
 * 				 computer’s AI simply use an “if else” statement to avoid 
 * 				 selecting an index that already has a value. To create 
 * 				 computer AI that 1).Win the game, 2).Stop you from winning,  
 * 				 3).Increase chances of winning, and last pick a random spot 
 * 			     	 on the tic tac toe board . The AI needs to loop through the 
 * 				 array and check the conditions of the game to determine 
 * 				 which of the 8 combinations are most important. If the 
 * 				 computer wins, its score will be incremented. The same will 
 * 				 happen if the user wins.
 * 				
 * 						
 * @written 4/25/13
 * @author Jonathan Booth
 *
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class TicTacToe extends JFrame implements ActionListener
{
	//data members
	private JPanel game; 
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JButton button10;
	private JButton button11;
	private JLabel Score;
	private String letter, userName, compLetter, winner;
	private int count;
	public int humanWin, computerWin; 
	private int startOrder;
	private boolean win;


	public TicTacToe(String name, String l, int o)
	{
		//user variables initialized
		userName = name;	
		letter = l;
		startOrder = o;

		//compares user's selections
		//and sets variable
		if(l.equals("X")){
			compLetter = "O";
		}else{
			compLetter = "X";
		}
		//initialize variable
		win = false;

		//initialize and set label
		Score = new JLabel(" " + userName + " VS CPU");

		//initialize buttons
		button1 = new JButton("");
		button2 = new JButton("");
		button3 = new JButton("");
		button4 = new JButton("");
		button5 = new JButton("");
		button6 = new JButton("");
		button7 = new JButton("");
		button8 = new JButton("");
		button9 = new JButton("");
		button10 = new JButton("New Game");
		button11 = new JButton("Quit");

		//creates window
		setTitle("TicTacToe");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(500, 100);

		//creates panels
		game = new JPanel();
		game.setSize(400, 400);
		game.setLayout(new GridLayout(4, 3));

		//Add Buttons to panel
		game.add(button1);
		game.add(button2);
		game.add(button3);
		game.add(button4);
		game.add(button5);
		game.add(button6);
		game.add(button7);
		game.add(button8);
		game.add(button9);
		game.add(button10);
		game.add(button11);
		game.add(Score);

		//creates button action
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		button7.addActionListener(this);
		button8.addActionListener(this);
		button9.addActionListener(this);
		button10.addActionListener(this);
		button11.addActionListener(this);

		//add panel to window
		add(game);

		//sets visibility
		setVisible(true);

		//tests start order
		if(startOrder == 2){
			CompTurn();
		}else{

		}


	}


	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e){
		//test if new game button is clicked
		if (e.getSource() == button10)
		{
			//resets game
			button1.setText("");
			button1.setEnabled(true);
			button2.setText("");
			button2.setEnabled(true);
			button3.setText("");
			button3.setEnabled(true);
			button4.setText("");
			button4.setEnabled(true);
			button5.setText("");
			button5.setEnabled(true);
			button6.setText("");
			button6.setEnabled(true);
			button7.setText("");
			button7.setEnabled(true);
			button8.setText("");
			button8.setEnabled(true);
			button9.setText("");
			button9.setEnabled(true);
			win = false;
			count = 0;
		}
		//test if quit game button is clicked
		else if (e.getSource() == button11)
		{
			//exits game
			System.exit(0);
		}
		//test if one of the nine grid game buttons are clicked 
		else{
			count++;//tracks if tic tac toe grid button is clicked
			JButton b=(JButton)e.getSource();//checks which button is clicked
			b.setEnabled(false);//disables clicked button
			b.setText(letter);//marks button with the user's symbol
			horizontalWin(letter);//calls method to check if user has a horizontal win
			win(letter);//calls win method for user
			verticalWin(letter);//calls method to check if user has a vertical win
			win(letter);//calls win method for user
			diagonalWin(letter);//calls method to check if user has a diagonal win
			win(letter);//calls win method for user
			CompTurn();//calls computer to take its turn
		}
	}

	/*
	 * @description	temporarily stores buttons in an array then systematically
	 * 				goes through each combination of buttons to win, block, or
	 * 				take a turn when applicable.
	 */
	public void CompTurn(){
		//variables
		int steps = 0;//counts steps to make sure cpu AI is logically in order
		int turn = 0;//keeps cpu from making two moves in a single turn

		//array to store buttons
		JButton buttons [] = new JButton[9];

		//puts buttons in array
		buttons[0] = button1;
		buttons[1] = button2; 
		buttons[2] = button3;
		buttons[3] = button4;
		buttons[4] = button5;
		buttons[5] = button6;
		buttons[6] = button7;
		buttons[7] = button8;
		buttons[8] = button9;

		//tracks steps and turn
		if(steps == 0 && (turn == 0)){
			steps++;
			//Offensive horizontal win
			for(int i = 0; i < 3; i++){
				if(buttons[i].getText() == buttons[i+1].getText() && buttons[i].getText()==(compLetter)&& buttons[i+2].getText()==("")){
					buttons[i+2].setText(compLetter);
					buttons[i+2].setEnabled(false);
					turn++;
					count++;//tracks if tic tac toe grid button is clicked
					horizontalWin(compLetter);
					win(compLetter);
					break;
				}else if(buttons[i].getText() == buttons[i+2].getText()&& buttons[i].getText()==(compLetter)&& buttons[i+1].getText()==("")){
					buttons[i+1].setText(compLetter);
					buttons[i+1].setEnabled(false);
					turn++;
					count++;//tracks if tic tac toe grid button is clicked
					horizontalWin(compLetter);
					win(compLetter);
					break;
				}else if(buttons[i+1].getText()== buttons[i+2].getText()&& buttons[i+1].getText()==(compLetter)&& buttons[i].getText()==("")){
					buttons[i].setText(compLetter);
					buttons[i].setEnabled(false);
					turn++;
					count++;//tracks if tic tac toe grid button is clicked
					horizontalWin(compLetter);
					win(compLetter);
					break;
				}
				i=(i+3);

			}
			//tracks steps and turn
		}if(steps == 1 && (turn == 0)){
			steps++;
			//Offensive vertical win
			for(int i = 0; i < 3; i++){
				if(buttons[i].getText() == buttons[i+3].getText() && buttons[i].getText()==(compLetter)&& buttons[i+6].getText()==("")){
					buttons[i+6].setText(compLetter);
					buttons[i+6].setEnabled(false);
					turn++;
					count++;//tracks if tic tac toe grid button is clicked
					verticalWin(compLetter);
					win(compLetter);
					break;
				}else if(buttons[i].getText()==  buttons[i+6].getText()&& buttons[i].getText()==(compLetter)&& buttons[i+3].getText()==("")){
					buttons[i+3].setText(compLetter);
					buttons[i+3].setEnabled(false);
					turn++;
					count++;//tracks if tic tac toe grid button is clicked
					verticalWin(compLetter);
					win(compLetter);
					break;
				}else if(buttons[i+3].getText()== buttons[i+6].getText() && buttons[i+3].getText()==(compLetter)&& buttons[i].getText()==("")){
					buttons[i].setText(compLetter);
					buttons[i].setEnabled(false);
					turn++;
					count++;//tracks if tic tac toe grid button is clicked
					verticalWin(compLetter);
					win(compLetter);
					break;
				}

			}
			//tracks steps and turn	
		}if(steps == 2 && (turn == 0)){
			steps++;
			//Offensive diagonal win(1,5,9)
			if(buttons[0].getText()== buttons[4].getText() && buttons[0].getText()==(compLetter)&& buttons[8].getText()==("")){
				buttons[8].setText(compLetter);
				buttons[8].setEnabled(false);
				turn++;
				count++;//tracks if tic tac toe grid button is clicked
				diagonalWin(compLetter);
				win(compLetter);

			}else if(buttons[0].getText()== buttons[8].getText() && buttons[0].getText()==(compLetter)&& buttons[4].getText()==("")){
				buttons[4].setText(compLetter);
				buttons[4].setEnabled(false);
				turn++;
				count++;//tracks if tic tac toe grid button is clicked
				diagonalWin(compLetter);
				win(compLetter);

			}else if(buttons[4].getText()== buttons[8].getText() && buttons[4].getText()==(compLetter)&& buttons[0].getText()==("")){
				buttons[0].setText(compLetter);
				buttons[0].setEnabled(false);
				turn++;
				count++;//tracks if tic tac toe grid button is clicked
				diagonalWin(compLetter);
				win(compLetter);

			}
			//tracks steps and turn	
		}if(steps == 3 && (turn == 0)){
			steps++;
			//Offensive diagonal win(3,5,7)
			if(buttons[2].getText()== buttons[4].getText() && buttons[2].getText() ==(compLetter)&& buttons[6].getText()==("")){
				buttons[6].setText(compLetter);
				buttons[6].setEnabled(false);
				turn++;
				count++;//tracks if tic tac toe grid button is clicked
				diagonalWin(compLetter);
				win(compLetter);

			}else if(buttons[4].getText()== buttons[6].getText() && buttons[4].getText()==(compLetter)&& buttons[2].getText()==("")){
				buttons[2].setText(compLetter);
				buttons[2].setEnabled(false);
				turn++;
				count++;//tracks if tic tac toe grid button is clicked
				diagonalWin(compLetter);
				win(compLetter);

			}else if(buttons[6].getText()== buttons[2].getText() && buttons[6].getText()==(compLetter)&& buttons[4].getText()==("")){
				buttons[4].setText(compLetter);
				buttons[4].setEnabled(false);
				turn++;
				count++;//tracks if tic tac toe grid button is clicked
				diagonalWin(compLetter);
				win(compLetter);
			}	
		}
		//Defense
		//tracks steps and turn
		if(steps == 4 && (turn == 0)){
			steps++;
			//Defensive horizontal block 
			for(int i = 0; i < 3; i++){
				if(buttons[i].getText() == buttons[i+1].getText() && buttons[i].getText()==(letter)&& buttons[i+2].getText()==("")){
					buttons[i+2].setText(compLetter);
					buttons[i+2].setEnabled(false);
					turn++;
					count++;//tracks if tic tac toe grid button is clicked
					horizontalWin(compLetter);
					win(compLetter);
					break;
				}else if(buttons[i].getText() == buttons[i+2].getText()&& buttons[i].getText()==(letter)&& buttons[i+1].getText()==("")){
					buttons[i+1].setText(compLetter);
					buttons[i+1].setEnabled(false);
					turn++;
					count++;//tracks if tic tac toe grid button is clicked
					horizontalWin(compLetter);
					win(compLetter);
					break;
				}else if(buttons[i+1].getText()== buttons[i+2].getText()&& buttons[i+1].getText()==(letter)&& buttons[i].getText()==("")){
					buttons[i].setText(compLetter);
					buttons[i].setEnabled(false);
					turn++;
					count++;//tracks if tic tac toe grid button is clicked
					horizontalWin(compLetter);
					win(compLetter);
					break;
				}
				i=(i+3);
			}
			//tracks steps and turn
		}if(steps == 5 && (turn == 0)){
			steps++;
			//Defensive vertical block
			for(int i = 0; i < 3; i++){
				if(buttons[i].getText() == buttons[i+3].getText() && buttons[i].getText()==(letter)&& buttons[i+6].getText()==("")){
					buttons[i+6].setText(compLetter);
					buttons[i+6].setEnabled(false);
					turn++;
					count++;//tracks if tic tac toe grid button is clicked
					verticalWin(compLetter);
					win(compLetter);
					break;
				}else if(buttons[i].getText()==  buttons[i+6].getText()&& buttons[i].getText()==(letter)&& buttons[i+3].getText()==("")){
					buttons[i+3].setText(compLetter);
					buttons[i+3].setEnabled(false);
					turn++;
					count++;//tracks if tic tac toe grid button is clicked
					verticalWin(compLetter);
					win(compLetter);
					break;
				}else if(buttons[i+3].getText()== buttons[i+6].getText() && buttons[i+3].getText()==(letter)&& buttons[i].getText()==("")){
					buttons[i].setText(compLetter);
					buttons[i].setEnabled(false);
					turn++;
					count++;//tracks if tic tac toe grid button is clicked
					verticalWin(compLetter);
					win(compLetter);
					break;
				}
			}
			//tracks steps and turn
		}if(steps == 6 && (turn == 0)){
			steps++;
			//Defensive diagonal block(1,5,9)
			if(buttons[0].getText()== buttons[4].getText() && buttons[0].getText()==(letter)&& buttons[8].getText()==("")){
				buttons[8].setText(compLetter);
				buttons[8].setEnabled(false);
				turn++;
				count++;//tracks if tic tac toe grid button is clicked
				diagonalWin(compLetter);
				win(compLetter);

			}else if(buttons[0].getText()== buttons[8].getText() && buttons[0].getText()==(letter)&& buttons[4].getText()==("")){
				buttons[4].setText(compLetter);
				buttons[4].setEnabled(false);
				turn++;
				count++;//tracks if tic tac toe grid button is clicked
				diagonalWin(compLetter);
				win(compLetter);

			}else if(buttons[4].getText()== buttons[8].getText() && buttons[4].getText()==(letter)&& buttons[0].getText()==("")){
				buttons[0].setText(compLetter);
				buttons[0].setEnabled(false);
				turn++;
				count++;//tracks if tic tac toe grid button is clicked
				diagonalWin(compLetter);
				win(compLetter);

			}
			//tracks steps and turn
		}if(steps == 7 && (turn == 0)){
			steps++;
			//Defensive diagonal block(3,5,7)
			if(buttons[2].getText()== buttons[4].getText() && buttons[2].getText() ==(letter)&& buttons[6].getText()==("")){
				buttons[6].setText(compLetter);
				buttons[6].setEnabled(false);
				turn++;
				count++;//tracks if tic tac toe grid button is clicked
				diagonalWin(compLetter);
				win(compLetter);

			}else if(buttons[4].getText()== buttons[6].getText() && buttons[4].getText()==(letter)&& buttons[2].getText()==("")){
				buttons[2].setText(compLetter);
				buttons[2].setEnabled(false);
				turn++;
				count++;//tracks if tic tac toe grid button is clicked
				diagonalWin(compLetter);
				win(compLetter);

			}else if(buttons[6].getText()== buttons[2].getText() && buttons[6].getText()==(letter)&& buttons[4].getText()==("")){
				buttons[4].setText(compLetter);
				buttons[4].setEnabled(false);
				turn++;
				count++;//tracks if tic tac toe grid button is clicked
				diagonalWin(compLetter);
				win(compLetter);

			}	
		}

		//last stage
		//CPU picks middle open space if available, random if not.
		//tracks steps and turn
		if(steps == 8 && (turn == 0)){

			if(buttons[4].getText()==""){
				buttons[4].setText(compLetter);
				buttons[4].setEnabled(false);
				turn++;
				count++;//tracks if tic tac toe grid button is clicked

			}else {
				//chooses random
				int value = (int) (9 * Math.random());
				while(true){
					if(buttons[value].getText()==""){
						buttons[value].setText(compLetter);
						buttons[value].setEnabled(false);
						turn++;
						count++;//tracks if tic tac toe grid button is clicked
						diagonalWin(compLetter);
						win(compLetter);
						verticalWin(compLetter);
						win(compLetter);
						horizontalWin(compLetter);
						win(compLetter);
						break;		
					}
					else{
						//chooses different random button if previous is taken
						value = (int) (9 * Math.random());	
					}
				}
			}		

		}






	}

	/*
	 * @description	Checking who wins Horizontally then sets win to true
	 * @param w is identify who is calling the method, cpu or user.
	 * 
	 */
	public void horizontalWin(String w){
		winner = w;
		if (button1.getText() == button2.getText() && button2.getText() == button3.getText() && button1.getText() != "")
		{
			win = true;
		}
		else if (button4.getText() == button5.getText() && button5.getText() == button6.getText() && button4.getText() != "")
		{
			win = true;
		}
		else if (button7.getText() == button8.getText() && button8.getText() == button9.getText() && button7.getText() != "")
		{
			win = true;
		}
		else{
			win = false;
		}
	}


	/*
	 * @description	Checking who wins vertically then sets win to true
	 * @param w is identify who is calling the method, cpu or user.
	 */
	public void verticalWin(String w){
		winner= w;
		if (button1.getText() == button4.getText() && button4.getText() == button7.getText() && button1.getText() != "")
		{
			win = true;
		}
		else if (button2.getText() == button5.getText() && button5.getText() == button8.getText() && button2.getText() != "")
		{
			win = true;
		}
		else if (button3.getText() == button6.getText() && button6.getText() == button9.getText() && button3.getText() != "")
		{
			win = true;
		}
		else{
			win = false;
		}
	}

	/*
	 * @description	Checking who wins diagonally then sets win to true
	 * @param w is identify who is calling the method, cpu or user.
	 */
	public void diagonalWin(String w){
		winner = w;
		if (button1.getText() == button5.getText() && button5.getText() == button9.getText() && button1.getText() != "")
		{
			win = true;
		}
		else if (button3.getText() == button5.getText() && button5.getText() == button7.getText() && button3.getText() != "")
		{
			win = true;
		}
		else
		{
			win = false;
		}

	}

	/*
	 * @description	Show who wins or if a tie then resets the board
	 * @param w is identify who is calling the method, cpu or user.
	 */
	public void win(String w){
		//variable
		winner = w;

		//checks for a user win
		if ((win == true) && (winner== letter)){
			humanWin++;//user score is incremented

			//displays user wins and score
			JOptionPane.showMessageDialog(null, userName +" " + "Wins!\n"+"SCORES  "+userName+":"+humanWin+"  CPU:"+computerWin);

			//resets game
			button1.setText("");
			button1.setEnabled(true);
			button2.setText("");
			button2.setEnabled(true);
			button3.setText("");
			button3.setEnabled(true);
			button4.setText("");
			button4.setEnabled(true);
			button5.setText("");
			button5.setEnabled(true);
			button6.setText("");
			button6.setEnabled(true);
			button7.setText("");
			button7.setEnabled(true);
			button8.setText("");
			button8.setEnabled(true);
			button9.setText("");
			button9.setEnabled(true);
			win = false;
			count = 0;

		}
		//checks for cpu win
		else if((win == true) && (winner == compLetter)){
			computerWin++;//cpu score is incremented

			//displays cpu wins and score
			JOptionPane.showMessageDialog(null, "CPU Wins!\n"+"SCORES  "+userName+":"+humanWin+"  CPU:"+computerWin);

			//resets game
			button1.setText("");
			button1.setEnabled(true);
			button2.setText("");
			button2.setEnabled(true);
			button3.setText("");
			button3.setEnabled(true);
			button4.setText("");
			button4.setEnabled(true);
			button5.setText("");
			button5.setEnabled(true);
			button6.setText("");
			button6.setEnabled(true);
			button7.setText("");
			button7.setEnabled(true);
			button8.setText("");
			button8.setEnabled(true);
			button9.setText("");
			button9.setEnabled(true);
			win = false;
			count = 0;

		}

		//checks for tie
		else if (count == 9 && win == false)
		{
			//displays tie and score
			JOptionPane.showMessageDialog(null, "It's a tie!\n"+"SCORES  "+userName+":"+humanWin+"  CPU:"+computerWin);

			//resets game
			button1.setText("");
			button1.setEnabled(true);
			button2.setText("");
			button2.setEnabled(true);
			button3.setText("");
			button3.setEnabled(true);
			button4.setText("");
			button4.setEnabled(true);
			button5.setText("");
			button5.setEnabled(true);
			button6.setText("");
			button6.setEnabled(true);
			button7.setText("");
			button7.setEnabled(true);
			button8.setText("");
			button8.setEnabled(true);
			button9.setText("");
			button9.setEnabled(true);
			count = 0;

		}
	}



	public static void main(String args[]){
		//(Bonus)
		//variable initialized
		int order = 0;

		//collects users name
		String name = JOptionPane.showInputDialog("Enter your name?");

		//collects users desired symbol
		String symbol = JOptionPane.showInputDialog(name.toUpperCase()+ " Welcone to Tic Tac Toe\nEnter the symbol you want:\n\"X\" or \"O\"");

		//ask who starts game
		int start = JOptionPane.showConfirmDialog(null, "Would you like to make the first move?", "Confirm", JOptionPane.YES_NO_OPTION);
		if(start == JOptionPane.YES_OPTION)
		{
			order = 1;
		}else if(start == JOptionPane.NO_OPTION) {
			order = 2;
			
		}

		//game instance is created
		TicTacToe a = new TicTacToe(name.toUpperCase(), symbol.toUpperCase(), order);

	}
}

