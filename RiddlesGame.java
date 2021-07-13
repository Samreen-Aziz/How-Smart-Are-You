package quiz;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
/*In this code we design Riddles game using GUI.In the GUI frame we have these components(TextField that 
represents question number that is currently running,Textarea for the question itself,Buttons for user 
response,Labels for each possible answer and Timer that will countdown from 10 and if it reachs zero it
skips to the next question).All wrong anwers will be colored RED and if timer reaches zero then also wrong 
answers colored RED and correct will be GREEN.When all questions are complete it will show you the results that how many of your 
answers are correct.So in this code we have 1 constructor and 3 methods named nextQuestion(),displayAnswer() and results().I'll define these methods later in the code*/
public class QUIZ implements ActionListener{
    String[] questions = {
                            "What can be seen once in a Minute,Twice in a Moment,And never in a Tousand Years?",
			    "What has 13 hearts,but no other organ?",
			    "Which word is written incorrectly in a dictionary?",
			    "Which hand is best for Stirring sugar into a cup of tea?",
                            "What gets wet when drying?",
                            "I exist only when there is a light but direct light kills me what am I?",
                            "Which month has 28 days?",
                            "If you have one,You want to share it.But once you share it,you don't have it.what is it?",
                            "What starts with 'e' and ends with 'e' but has only one letter in it?",
                            "If a plan crashes on the border between the US and Canada, where do they bury the survivors?"
			};//Array of questions of String data type
    String[][] options = {
			    {"Letter M","Sun","Moon","Letter N"},
			    {"A soccer team","Science lab","A deck of cards","Operation theatre"},
			    {"Wrong","Calendar","Incorrectly","Latte"},
			    {"Right","Left","Both","It's better to use spoon"},
                            {"Towel","Dryer","Handkercheif","Both towel and handkercheif"},
                            {"Bulb","Shadow","Fire","None"},
                            {"February","November","January","All of them"},
                            {"A secret","Talent","Love","Happiness"},
                            {"Letter E","Envelope","Eye","Edge"},
                            {"US","Canada","Graveyard","Survivors aren't buried"}
			 };//This 2D array holds all possible answers of questions and it is also of String type
    char[] answers = {'A','C','C','D','D','B','D','A','B','D'};//Array of correct answers
    char guess;
    char answer;
    int index;
    int correct_guesses =0;
    int total_questions = questions.length;
    int result;
    int seconds=10;
	
    JFrame frame = new JFrame();
    JTextField textfield = new JTextField();
    JTextArea textarea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();
    Timer timer = new Timer(1000, new ActionListener() { //Here we create object of Timer Class named timer for creating the timer
        @Override
	public void actionPerformed(ActionEvent e) {
            seconds--;
	    seconds_left.setText(String.valueOf(seconds));
            if(seconds<=0) {
		                displayAnswer();}
	}
	});
	
    public QUIZ() { //In constructor we set the layout of frame and all components and at last add all the components to frame and called the nextQuestion() method. 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(850,650);
	frame.getContentPane().setBackground(new Color(50,50,50));
	frame.setLayout(null);
	frame.setResizable(false);//People doesn't able to resize the window size
	textfield.setBounds(0,0,850,50);
	textfield.setBackground(new Color(25,25,25));
	textfield.setForeground(new Color(25,255,0));
	textfield.setFont(new Font("Ink Free",Font.BOLD,30));
	textfield.setBorder(BorderFactory.createBevelBorder(1));
	textfield.setHorizontalAlignment(JTextField.CENTER);
	textfield.setEditable(false); 
		
	textarea.setBounds(0,50,850,100);
	textarea.setLineWrap(true); /*It going to wraps around the text onto the next line if text becomes 
                                    off the screen or off the text area*/
	textarea.setWrapStyleWord(true);
	textarea.setBackground(new Color(25,25,25));
	textarea.setForeground(new Color(25,255,0));
	textarea.setFont(new Font("MV Boli",Font.BOLD,25));
	textarea.setBorder(BorderFactory.createBevelBorder(1));
	textarea.setEditable(false);
		
	buttonA.setBounds(0,200,100,100);
	buttonA.setFont(new Font("MV Boli",Font.BOLD,35));
	buttonA.setFocusable(false);
	buttonA.addActionListener(this);
	buttonA.setText("A");
		
	buttonB.setBounds(0,300,100,100);
	buttonB.setFont(new Font("MV Boli",Font.BOLD,35));
	buttonB.setFocusable(false);
	buttonB.addActionListener(this);
	buttonB.setText("B");
		
	buttonC.setBounds(0,400,100,100);
	buttonC.setFont(new Font("MV Boli",Font.BOLD,35));
	buttonC.setFocusable(false);
	buttonC.addActionListener(this);
	buttonC.setText("C");
		
	buttonD.setBounds(0,500,100,100);
        buttonD.setFont(new Font("MV Boli",Font.BOLD,35));
	buttonD.setFocusable(false);
	buttonD.addActionListener(this);
	buttonD.setText("D");
		
	answer_labelA.setBounds(125,200,500,100);
	answer_labelA.setBackground(new Color(50,50,50));
	answer_labelA.setForeground(new Color(25,255,0));
	answer_labelA.setFont(new Font("MV Boli",Font.PLAIN,35));
		
	answer_labelB.setBounds(125,300,500,100);
	answer_labelB.setBackground(new Color(50,50,50));
	answer_labelB.setForeground(new Color(25,255,0));
	answer_labelB.setFont(new Font("MV Boli",Font.PLAIN,35));
		
	answer_labelC.setBounds(125,400,500,100);
	answer_labelC.setBackground(new Color(50,50,50));
	answer_labelC.setForeground(new Color(25,255,0));
	answer_labelC.setFont(new Font("MV Boli",Font.PLAIN,35));
		
	answer_labelD.setBounds(125,500,500,100);
	answer_labelD.setBackground(new Color(50,50,50));
	answer_labelD.setForeground(new Color(25,255,0));
	answer_labelD.setFont(new Font("MV Boli",Font.PLAIN,35));
		
	seconds_left.setBounds(735,510,100,100);
	seconds_left.setBackground(new Color(25,25,25));
	seconds_left.setForeground(new Color(255,0,0));
	seconds_left.setFont(new Font("Ink Free",Font.BOLD,60));
	seconds_left.setBorder(BorderFactory.createBevelBorder(1));
	seconds_left.setOpaque(true);
	seconds_left.setHorizontalAlignment(JTextField.CENTER);
	seconds_left.setText(String.valueOf(seconds));
		
	time_label.setBounds(735,475,100,25);
	time_label.setBackground(new Color(50,50,50));
	time_label.setForeground(new Color(255,0,0));
	time_label.setFont(new Font("MV Boli",Font.PLAIN,16));
	time_label.setHorizontalAlignment(JTextField.CENTER);
	time_label.setText("timer >:D");
		
	number_right.setBounds(300,225,200,100);
	number_right.setBackground(new Color(25,25,25));
	number_right.setForeground(new Color(25,255,0));
	number_right.setFont(new Font("Ink Free",Font.BOLD,50));
	number_right.setBorder(BorderFactory.createBevelBorder(1));
	number_right.setHorizontalAlignment(JTextField.CENTER);
	number_right.setEditable(false);
		
	percentage.setBounds(300,325,200,100);
	percentage.setBackground(new Color(25,25,25));
	percentage.setForeground(new Color(25,255,0));
	percentage.setFont(new Font("Ink Free",Font.BOLD,50));
	percentage.setBorder(BorderFactory.createBevelBorder(1));
	percentage.setHorizontalAlignment(JTextField.CENTER);
	percentage.setEditable(false);
		
	frame.add(time_label);
	frame.add(seconds_left);
	frame.add(answer_labelA);
	frame.add(answer_labelB);
	frame.add(answer_labelC);
	frame.add(answer_labelD);
	frame.add(buttonA);
	frame.add(buttonB);
	frame.add(buttonC);
	frame.add(buttonD);
	frame.add(textarea);
	frame.add(textfield);
	frame.setVisible(true);
	nextQuestion();
	}
    public void nextQuestion() { //This method will show the next Question
	if(index>=total_questions) {
            results();
	}
	else {
            textfield.setText("Question "+(index+1));
	    textarea.setText(questions[index]);
	    answer_labelA.setText(options[index][0]);
	    answer_labelB.setText(options[index][1]);
	    answer_labelC.setText(options[index][2]);
	    answer_labelD.setText(options[index][3]);
	    timer.start();
	}
	}
    @Override
    public void actionPerformed(ActionEvent e) { //This method will work when a button is clicked.When a user clicks a button it matchs the user's answer to the correct answer and then dispalay the correct answer means wrong answers will be RED.
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
	buttonC.setEnabled(false);
	buttonD.setEnabled(false);
	if(e.getSource()==buttonA) {
            answer = 'A';
	    if(answer == answers[index]) {
                correct_guesses++;
	    }
	}
	if(e.getSource()==buttonB) {
	    answer= 'B';
	    if(answer == answers[index]) {
		correct_guesses++;
	    }
	}
	if(e.getSource()==buttonC) {
	    answer= 'C';
	    if(answer == answers[index]) {
		correct_guesses++;
	    }
        }
	if(e.getSource()==buttonD) {
	    answer= 'D';
	    if(answer == answers[index]) {
		correct_guesses++;
	    }
	}
	displayAnswer();
	}
    public void displayAnswer() {//This method first stops the timer and then display the correct answer.The correct answer will be GREEN and wrong will be RED and then call nextQuestion() method.
	timer.stop();
	buttonA.setEnabled(false);
	buttonB.setEnabled(false);
	buttonC.setEnabled(false);
	buttonD.setEnabled(false);
	if(answers[index] != 'A')
	    answer_labelA.setForeground(new Color(255,0,0));
	if(answers[index] != 'B')
	    answer_labelB.setForeground(new Color(255,0,0));
        if(answers[index] != 'C')
	    answer_labelC.setForeground(new Color(255,0,0));
	if(answers[index] != 'D')
	    answer_labelD.setForeground(new Color(255,0,0));
	Timer pause = new Timer(1000, new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	    answer_labelA.setForeground(new Color(25,255,0));
	    answer_labelB.setForeground(new Color(25,255,0));
            answer_labelC.setForeground(new Color(25,255,0));
	    answer_labelD.setForeground(new Color(25,255,0));
	    answer = ' ';
	    seconds=10;
	    seconds_left.setText(String.valueOf(seconds));
            buttonA.setEnabled(true);
	    buttonB.setEnabled(true);
	    buttonC.setEnabled(true);
	    buttonD.setEnabled(true);
	    index++;
	    nextQuestion();
	}
	});
	pause.setRepeats(false);
	pause.start();
    }
    public void results(){ //This method shows the results.
	buttonA.setEnabled(false);
	buttonB.setEnabled(false);
	buttonC.setEnabled(false);
	buttonD.setEnabled(false);
	result = (int)((correct_guesses/(double)total_questions)*100);
	textfield.setText("RESULTS!");
	textarea.setText("");
	answer_labelA.setText("");
	answer_labelB.setText("");
	answer_labelC.setText("");
	answer_labelD.setText("");
	number_right.setText("("+correct_guesses+"/"+total_questions+")");
	percentage.setText(result+"%");
	frame.add(number_right);
	frame.add(percentage);
    }
    public static void main(String[] args) {
        QUIZ quiz = new QUIZ();
    }
    
}
