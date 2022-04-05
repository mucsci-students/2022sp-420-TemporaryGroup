package TemporaryGroupGradle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.jline.builtins.Completers.Completer;
import org.jline.console.impl.Builtins.Command;
import org.jline.reader.Highlighter;
import org.jline.reader.History;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.MaskingCallback;
import org.jline.reader.ParsedLine;
import org.jline.reader.LineReader.Option;
import org.jline.reader.impl.DefaultParser;

import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.reader.impl.history.DefaultHistory;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class CLIView {

    private LineReader reader;
	private Terminal terminal;
	private ParsedLine parser;
	private History history;
	private Highlighter highlighter;

    public static Scanner input = new Scanner (System.in);

    //Initial prompt 
	public static void startup () {
		System.out.println("Welcome to TemporaryGroup's UML editor.");
		System.out.println("Type 'help' for list of valid commands.");
		System.out.println("Type 'exit' to quit the editor.");
  	}

      /**
	 * Testing
	 */
	public void setTerminal(UMLDiagram model){
		try {
			terminal = TerminalBuilder.builder().system(true).build();
			AggregateCompleter comp = new TabComplete().updateCompleter();
			reader = LineReaderBuilder.builder().terminal(terminal).completer(comp).highlighter(highlighter).history(history).variable(LineReader.MENU_COMPLETE, true).build();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
    public ArrayList<String> getInput() {

    	ArrayList<String> result = new ArrayList<String>();
		String line = null;
		line = reader.readLine("> ");

        if(line.trim().equalsIgnoreCase("exit") ){
            System.out.println("Are you sure you want to exit? [y/n]");
            String answer = input.nextLine();
            if(answer.equals("y")){
                System.exit(0);
            }else if(!answer.equals("y") && !answer.equals("n")) {
                commandNotRecognized();
            }
        }else{
            parser = reader.getParsedLine();
		    String[] arrayLine = parser.words().toArray(new String[parser.words().size()]);
		    result = new ArrayList<String>(Arrays.asList(arrayLine));

        }
        return result;
    }

    public static void commandNotRecognized() {
		System.out.println("Command not recognized. Type 'help' for list of valid commands.");
	}
    
}
