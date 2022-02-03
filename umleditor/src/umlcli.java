import UMLCLass.java;
import UMLDiagram.java;
import java.util.*;

public class umlcli {
	
	public void listClasses () {
		Iterator hmIter = umlDiagram.entrySet.iterator();
		while (hmIter .hasNext()) {
			Map.Entry mapElem = (Map.Entry) hmIter.next();
			System.out.println(mapElem.getKey());
		}
	}
	
	public void listClass () {
		Iterator hmIter = umlDiagram.entrySet.iterator();
		while (hmIter .hasNext()) {
			Map.Entry mapElem = (Map.Entry) hmIter.next();
		}
		
	}
	
	
	
	
}
