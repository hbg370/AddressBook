import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddressBook extends Application{
	
	RandomAccessFile file;
	String name, street, city, state, zip;
	int ofs, entryCounter;
/*	byte [] b1x;
	byte [] b2x;
	byte [] b3x;
	byte [] b4x;
	byte [] b5x; */
	
	byte [] b1x = new byte[0];
	byte [] b2x = new byte[0];
	byte [] b3x = new byte[0];
	byte [] b4x = new byte[0];
	byte [] b5x = new byte[0];
	
	int pad = 20;
	String nm, str, cty, st, zp;

	
	public static void main(String args[]) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Button add = new Button("Add");
		Button first = new Button("First");
		Button next = new Button("Next");
		Button prev = new Button("Previous");
		Button last = new Button("Last");
		Button upd = new Button("Update");
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		Label nameLabel = new Label("Name: ");
		GridPane.setConstraints(nameLabel, 0, 0);
		
		TextField nameField = new TextField();
		GridPane.setConstraints(nameField, 1, 0);
		
		Label streetLabel = new Label("Street: ");
		GridPane.setConstraints(streetLabel, 0, 1);

		TextField streetField = new TextField();
		GridPane.setConstraints(streetField, 1, 1);

		Label cityLabel = new Label("City: ");
		GridPane.setConstraints(cityLabel, 0, 2);

		TextField cityField = new TextField();
		GridPane.setConstraints(cityField, 1, 2);

		Label stateLabel = new Label("State: ");
		GridPane.setConstraints(stateLabel, 3, 2);

		TextField stateField = new TextField();
		GridPane.setConstraints(stateField, 4, 2);

		Label zipLabel = new Label("Zip: ");
		GridPane.setConstraints(zipLabel, 5, 2);

		TextField zipField = new TextField();
		GridPane.setConstraints(zipField, 6, 2);
		
		try{
			file = new RandomAccessFile(new File("Address_Book.txt"), "rw");
		
		long fsize = file.length();	
		int rec = 90; //total bytes per record
	
		
		add.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent e)
			{
				try {
				nm = nameField.getText();
				for(int i = nm.length(); i < pad; i++)
				{
					nm += " ";
				}
				file.writeBytes(nm);
				
				
				str = streetField.getText();
				for(int i = str.length(); i < pad; i++)
				{
					str += " ";
				}
				file.writeBytes(str);

				
				cty = cityField.getText();
				for(int i = cty.length(); i < pad; i++)
				{
					cty += " ";
				}
				file.writeBytes(cty);
				
				st = stateField.getText();
				for(int i = st.length(); i < pad; i++)
				{
					st += " ";
				}
				file.writeBytes(st);
				
				zip = zipField.getText();
				for(int i = zip.length(); i < pad; i++)
				{
					zip += " ";
				}
				file.writeBytes(zip);
				entryCounter++; //used to reach end of file to retrieve last entry...

			}
				
			 catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				nameField.clear();
				streetField.clear();
				cityField.clear();
				stateField.clear();
				zipField.clear();
			}
			});
		
		first.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent e)
			{
				nameField.clear();
				streetField.clear();
				cityField.clear();
				stateField.clear();
				zipField.clear();
				
				
				try {
					ofs = reset(ofs);
					file.seek(ofs);
					b1x = new byte[nm.length()];
					file.read(b1x);
					nameField.appendText(new String(b1x));
					
					
					
					b2x = new byte[str.length()];
					file.read(b2x);
					streetField.appendText(new String(b2x));
					
					
					
					b3x = new byte[cty.length()];
					file.read(b3x);
					cityField.appendText(new String(b3x));
					
				
					
					b4x = new byte[st.length()];
					file.read(b4x);
					stateField.appendText(new String(b4x));
					
					
					
					b5x = new byte[zip.length()];
					file.read(b5x);
					zipField.appendText(new String(b5x));
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		
		next.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent e)
			{
				nameField.clear();
				streetField.clear();
				cityField.clear();
				stateField.clear();
				zipField.clear();
				
				
				try {
					ofs = offset(ofs);
					//System.out.println(ofs);

					file.seek(ofs);
					file.read(b1x);
					nameField.appendText(new String(b1x));
					
					
					b2x = new byte[str.length()];
					file.read(b2x);
					streetField.appendText(new String(b2x));
					
					
					
					b3x = new byte[cty.length()];
					file.read(b3x);
					cityField.appendText(new String(b3x));
					
				
					
					b4x = new byte[st.length()];
					file.read(b4x);
					stateField.appendText(new String(b4x));
					
					
					
					b5x = new byte[zip.length()];
					file.read(b5x);
					zipField.appendText(new String(b5x));
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		prev.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent e)
			{
				nameField.clear();
				streetField.clear();
				cityField.clear();
				stateField.clear();
				zipField.clear();
				
				
				try {
					ofs = last(ofs);
					//System.out.println(ofs);

					file.seek(ofs);
					file.read(b1x);
					nameField.appendText(new String(b1x));
					
					
					b2x = new byte[str.length()];
					file.read(b2x);
					streetField.appendText(new String(b2x));
					
					
					
					b3x = new byte[cty.length()];
					file.read(b3x);
					cityField.appendText(new String(b3x));
					
				
					
					b4x = new byte[st.length()];
					file.read(b4x);
					stateField.appendText(new String(b4x));
					
					
					
					b5x = new byte[zip.length()];
					file.read(b5x);
					zipField.appendText(new String(b5x));
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		
		});
		
		last.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent e)
			{
				nameField.clear();
				streetField.clear();
				cityField.clear();
				stateField.clear();
				zipField.clear();
				
				
				try {
					ofs = end(ofs);
					//System.out.println(ofs);

					file.seek(ofs);
					file.read(b1x);
					nameField.appendText(new String(b1x));
					
					
					b2x = new byte[str.length()];
					file.read(b2x);
					streetField.appendText(new String(b2x));
					
					
					
					b3x = new byte[cty.length()];
					file.read(b3x);
					cityField.appendText(new String(b3x));
					
				
					
					b4x = new byte[st.length()];
					file.read(b4x);
					stateField.appendText(new String(b4x));
					
					
					
					b5x = new byte[zip.length()];
					file.read(b5x);
					zipField.appendText(new String(b5x));
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		
		}catch(IOException e)
		{
			e.getStackTrace();
		}
		
		GridPane.setConstraints(add, 0, 3);
		GridPane.setConstraints(first, 1, 3);
		GridPane.setConstraints(next, 2, 3);
		GridPane.setConstraints(prev, 3, 3);
		GridPane.setConstraints(last, 4, 3);
		GridPane.setConstraints(upd, 5, 3);
		
		HBox horiz = new HBox(47);
		horiz.setTranslateX(48);
		horiz.getChildren().addAll(add, first, next, prev, last, upd);
		

		BorderPane b1 = new BorderPane();
		b1.setTop(grid);
		b1.setCenter(horiz);
		grid.getChildren().addAll(nameLabel, nameField, streetLabel, 
		streetField, cityLabel, cityField, stateLabel, stateField, 
		zipLabel, zipField);
		
				
		primaryStage.setScene(new Scene(b1, 600, 125));
		primaryStage.setTitle("Address Book");
		primaryStage.show();
	}
	
	public int offset(int x)
	{
		x += 100;
		return x;
	}
	
	public int reset(int x)
	{
		x -= x;
		return x;
	}
	
	public int last(int x)
	{
		x -= 100;
		return x;
	}
	
	public int end(int x)//dummy value passed
	{
		x -= x; //reset to 0
		x = (entryCounter * 100) - 100; //reach (n entries * 100) = final value (-100) points to first at end
		return x;
	}
		

}
