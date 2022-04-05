package TemporaryGroupGradle;
public class UMLEditor {

	public static void main(String[] args) throws Exception {
		
		if (args.length != 0 ) {
			if (args[0].equals("cli")) {
			CliController.main(args);
			}
		} else {
			GUIView.main(args);
			
		}
	}

}
