import java.awt.*; 
import java.awt.event.*; 
public class AWTRegistration extends Frame implements ActionListener
{ 
	private String msg=""; 
	private Label namep, passp, ph, lang, gender, statep; 
	private TextField name, pass, phone; 
	private Checkbox eng, hin, tel; 
	private CheckboxGroup cbg;
	private Checkbox male, female;
	private Choice state;
	private Button submit;
	private TextArea txt;
	private Panel one, two, three, four, five;  
	public AWTRegistration() 
	{ 
		initializeComponents();
		addComponentsToFrame();
		addListenerInterfaces();
		setLayout(new FlowLayout());
		setBackground(Color.lightGray); 
		setFont(new Font("SansSerif", Font.BOLD, 12)); 
		setSize(500, 400);        
    	setTitle("Registration");
    	setVisible(true);
	}
	private void initializeComponents()
	{
		//Text Fields
		namep = new Label("Name: ");
		name = new TextField(12);    
		passp = new Label("Password: ");
		pass = new TextField(8); 
    	pass.setEchoChar('*'); 		
		ph = new Label("Phone:");
		phone = new TextField(10);    
		one = new Panel();
		one.setLayout(new GridLayout(3, 2));
	
		//Checkboxes
		lang = new Label("Select Languages Known:");
		eng = new Checkbox("English", null, false); 	
		hin = new Checkbox("Hindi"); 
		tel = new Checkbox("Telugu");    
	    two = new Panel();
		two.setLayout(new GridLayout(1, 3)); 
		three = new Panel();
		three.setLayout(new GridLayout(1, 2));
	
		//Radio buttons
    	gender = new Label("Select Gender:");
    	cbg = new CheckboxGroup(); 
    	male = new Checkbox("Male", cbg, false); 
    	female = new Checkbox("Female", cbg, false); 
		four = new Panel();
		four.setLayout(new GridLayout(1, 3));

		//Choice
		statep = new Label("Select state:");	
		state= new Choice();	
		state.add("Andhra Pradesh"); //add all the other states as well
		state.add("Telangana");		
		five = new Panel();
		five.setLayout(new GridLayout(1, 2));

    	submit = new Button("Submit");
    	submit.setBackground(Color.orange);	
    	txt = new TextArea(msg, 8, 30);     	
	} 
	private void addComponentsToFrame()
	{
		one.add(namep);
		one.add(name);
		one.add(passp);
		one.add(pass);
		one.add(ph);
		one.add(phone);
		add(one);
		
		two.add(eng); 
		two.add(hin); 
		two.add(tel);
		three.add(lang);
		three.add(two);
		add(three);
		
		four.add(gender);
    	four.add(female); 
    	four.add(male); 
		add(four);
		
		five.add(statep);
		five.add(state); 
		add(five);
		add(submit);
		add(txt);		
	}
	private void addListenerInterfaces()
	{
	   submit.addActionListener(this);
	   addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);		
			}	 
		}	   
	   );
	}  
	public void actionPerformed(ActionEvent ae) 
	{ 
		 msg="";
		 String lang="\n";
		 if(eng.getState()) lang+="English\n";
		 if(hin.getState()) lang+="Hindi\n";
		 if(tel.getState()) lang+="Telugu\n";
		 
		 if(pass.getText().length()<8)
			msg+="Password should have at least 8 characters";
		 else if(phone.getText().length()!=10) 
			msg+="Phone number should have 10 digits";
		 else if(!(isInteger(phone.getText())))
			msg+="Phone number should have only numbers";
		 else
		 {
			 msg+="Name="+name.getText()+"\n";
			 msg+="Languages Known:"+lang;
			 msg+="Phone="+phone.getText()+"\n";
			 msg += "Gender="+ cbg.getSelectedCheckbox().getLabel()+"\n"; 
			 msg+="State=" + state.getSelectedItem() + "\n"; 
		 }
		 txt.setText(msg);
	}
	private boolean isInteger(String s) 
	{	
		try 
		{ 
			Long.parseLong(s); 
		} 
		catch(NumberFormatException e) 
		{ 
			return false; 
		} 
		catch(NullPointerException e) 
		{
			return false;
		}
		return true;
	}     
	public static void main(String[] args)
	{
		new AWTRegistration();
	}    
}		
