package application;
	
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class Main extends Application {
	GridPane mainPane = new GridPane();
	VBox rbtree = new VBox();
	ToggleGroup rbbutton = new ToggleGroup();
	ToggleButton insert = new ToggleButton("Insert");
	ToggleButton search = new ToggleButton("Search");
	TextField insertNTF = new TextField();
	VBox insertf = new VBox();
	Button searchB = new Button("add");
	TextField insertSTF = new TextField();
	TextField insertPTF = new TextField();
	TextField insertATF = new TextField("0");
	TextArea display = new TextArea();
	Text name = new Text("Name");
	Text state = new Text("State");
	Text phone = new Text("Phone");
	Text age = new Text("Age");
	VBox searchF = new VBox();
	Button searchFB = new Button("Search");
	Text nameS = new Text("Name");
	Text stateS = new Text("State");
	TextField insertstf= new TextField();
	TextField insertptf = new TextField();
	VBox inorders = new VBox();
	Text names = new Text("State");
	TextField insertst = new TextField();
	Button in = new Button("Display");
	File file2 = new File("src\\application\\Developer's log.txt");
	BufferedWriter bfwriter = null; 
	
	
	State blank = new State(" ");
	staterbt srbt = new staterbt(blank);
	
	@Override
	public void start(Stage primaryStage) {
		try {
			bfwriter = new BufferedWriter(new FileWriter(file2));
			bfwriter.write("" + java.time.LocalDate.now());
			bfwriter.close();
			Pane root = makePanes();
			Scene scene = new Scene(root,1000,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//allows us to make the Primary Pane
	public Pane makePanes() {
		onStartUp(); // This allows us to populate the main tree with all the states
		
		gridPaneProperties();//adds the items to the Pane and manages its properties
		
		leftSide();//allows us to set the properties for the left side of the grid pane
		
		middleSide();//sets the properties for the middle section
		
		bottemSide();
		
		return mainPane;
	}
	
	public void gridPaneProperties() {
		mainPane.setGridLinesVisible(false);
		mainPane.add(rbtree, 150, 140);
		mainPane.add(insertf, 250, 250);
		mainPane.add(searchF, 250, 250);
		mainPane.add(display, 250, 700);
		mainPane.add(inorders, 250, 250);
	}
	
	public void leftSide() {
		rbtree.getChildren().add(0, insert);
		rbtree.getChildren().add(1, search);
		setButtonsProperty();
	}
	
	public void middleSide() {
		//these are for the insert stuff
		insertf.getChildren().add(0, name);
		insertf.getChildren().add(1, insertNTF);
		insertf.getChildren().add(2, state);
		insertf.getChildren().add(3, insertSTF);
		insertf.getChildren().add(4, phone);
		insertf.getChildren().add(5, insertPTF);
		insertf.getChildren().add(6, age);
		insertf.getChildren().add(7, insertATF);
		insertf.getChildren().add(8, searchB);
		//these are for the search stuff
		searchF.getChildren().add(0, nameS);
		searchF.getChildren().add(1, insertstf);
		searchF.getChildren().add(2, stateS);
		searchF.getChildren().add(3, insertptf);
		searchF.getChildren().add(4, searchFB);
		//these are for the inorder stuff
		inorders.getChildren().add(0, names);
		inorders.getChildren().add(1, insertst);
		inorders.getChildren().add(2, in);
	}
	
	public void bottemSide() {
		display.setEditable(false);
		display.setWrapText(true);
	}
	
	public void setButtonsProperty() {
		insertEventHandler iEV = new insertEventHandler();
		//builds the buttons and automatically sets insert as toggled upon startup
		insert.setToggleGroup(rbbutton);
		insert.setSelected(true);
		insert.setOnAction(iEV);
		
		search.setToggleGroup(rbbutton);
		search.setOnAction(iEV);
		turnOn(1);
		
		searchEventHandler sEV = new searchEventHandler();
		searchFB.setOnAction(sEV);
		
	}
	
	public void onStartUp() {
		try {
		bfwriter = new BufferedWriter(new FileWriter(file2));
		File file;
		String temp;
		
			try {
				file = new File("src\\application\\States.txt");
				
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				long time1 = System.currentTimeMillis();
				while((temp = br.readLine()) != null) {
					State s = new State(temp);
					srbt.insert(s);
				}
				long time2 = System.currentTimeMillis();
				String start = String.valueOf(time1);
				String end = String.valueOf(time2);
				String total = String.valueOf(time2 - time2);
				
				bfwriter.write("Insert States: " + "Starting time: " + start + "End time: " + end + "Time taken: " + total);
				bfwriter.close();
				fr.close();
				br.close();
				
				
			}catch(Exception e) {
				System.out.println("File not found");
			}
		}catch(Exception e) {
			System.out.println("Filed doesn't exist stupid!");
		}
	}
	
	public void insert() {
		if(insertf.isVisible())
			searchB.setOnAction(new searchButtonEventHandler());
	}

	public void searchTree() {
		String name = insertNTF.getText();
		String state = insertSTF.getText();
		String num = insertPTF.getText();
		String age = insertATF.getText();
		long nums = Long.parseLong(num);
		int ages = Integer.parseInt(age);
		Person p;
		State temp = new State(state);

		State s = srbt.searchN(temp).data;
		
		if(ages == 0) {
			p = new Person(name, nums);
		}else
			p = new Person(name, ages, nums);

		try {
			bfwriter = new BufferedWriter(new FileWriter("Developer.txt"));
			long time1 = System.currentTimeMillis();
			s.insert(p);
			long time2 = System.currentTimeMillis();
			
			String start = String.valueOf(time1);
			String end = String.valueOf(time2);
			String total = String.valueOf(time2 - time1);
			
			bfwriter.write("Insert People: " + "Starting time: " + start + "End time: " + end + "Time taken: " + total);
			bfwriter.close();
		}catch(Exception e) {
			System.out.println("File not found you stupid!");
		}
		display.appendText("Person added to tree\n");
		System.out.printf("\nInorder for %s", s);
		s.inorder();
		System.out.printf("\nPreorder for %s", s);
		s.preorder();
		display.appendText(p + "\n\n");
		clear();
	}
	
	public void clear() {
		insertNTF.clear();
		insertSTF.clear();
		insertPTF.clear();
		insertATF.clear();
		insertATF.appendText("0");
	}
	
	public void turnOn(int o) {
		if(o == 1) {
			insertf.setVisible(true);
			searchF.setVisible(false);
			inorders.setVisible(false);
		}else if(o == 2) {
			insertf.setVisible(false);
			searchF.setVisible(true);
			inorders.setVisible(false);
		}else if(o == 3) {
			insertf.setVisible(false);
			searchF.setVisible(false);
			inorders.setVisible(true);
		}
	}

	public void search() {
		
		String name = insertstf.getText();
		String state = insertptf.getText();
		
		try {
			bfwriter = new BufferedWriter(new FileWriter("Developer.txt"));
			
			long time1 = System.currentTimeMillis();
			State s = srbt.searchN(new State(state)).data;
		
			Person p = s.searchNode(new Person(name, 0)).data;
			long time2 = System.currentTimeMillis();
			
			String start = String.valueOf(time1);
			String end = String.valueOf(time2);
			String total = String.valueOf(time2 - time1);
			
			bfwriter.write("Search: " + "Starting time: " + start + "End time: " + end + "Time taken: " + total);
			bfwriter.close();
			display.appendText(p.toString());
		}catch(Exception e) {
			System.out.println("YOU ARE AN IDIOT");
		}
		
	}
	
	public void in() {
		insertst.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				KeyCode kc = ke.getCode();
				if(kc.equals(KeyCode.BACK_QUOTE)) {
					inorder(insertst.getText());
				}
			}
		});
		
	}
	
	public void inorder(String s) {
		State state = new State(s);
		state.inorder();
	}
	
	public class insertEventHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent e) {
			if(insert.isSelected()) {
				turnOn(1);
				insert();
			}
			else if(search.isSelected())
				turnOn(2);
		}
	}
	
	public class searchEventHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent e) {
			turnOn(2);
			search();
		}
	}
	
	public class searchButtonEventHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent e) {
			searchTree();
			
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
