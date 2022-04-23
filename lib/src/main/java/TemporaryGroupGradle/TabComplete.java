package TemporaryGroupGradle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.Editor;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;
import org.jline.reader.impl.completer.NullCompleter;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.StringsCompleter;

public class TabComplete {
    
    private AggregateCompleter comp;

    public TabComplete(){
        this.comp = new AggregateCompleter(
                            new ArgumentCompleter(
                                new StringsCompleter("add"),
                                new StringsCompleter("class", "method", "field", "parameter", "relationship"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("delete"),
                                new StringsCompleter("class", "method", "field", "parameter", "relationship"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("rename"),
                                new StringsCompleter("class", "method", "field", "parameter", "relationship"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("add"),
                                new StringsCompleter("relationship"),
                                new StringsCompleter("aggregation", "composition", "inheritance", "realization"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("save"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("load"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("change"),
                                new StringsCompleter("field", "method", "parameter", "relationship"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("list"),
                                new StringsCompleter("class", "all", "relationships"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("undo"),
                                new NullCompleter()
                        ),
                            new ArgumentCompleter(
                                new StringsCompleter("redo"),
                                new NullCompleter()
                        ),
                        new ArgumentCompleter(
                                new StringsCompleter("exit"),
                                new NullCompleter()
                        ),
                        new ArgumentCompleter(
                                new StringsCompleter("help"),
                                new NullCompleter()
                        )
        );
    }

    public AggregateCompleter updateCompleter(UMLDiagram model){
        Collection<Completer> completers = comp.getCompleters();
        completers = new ArrayList<>(completers);
        
        ArrayList<UMLClass> classes = model.getClasses();
        ArrayList<String> classNames = new ArrayList<String>();
        Iterator<HashMap.Entry<String, UMLClass>> hmIter = model.umlDiagram.entrySet().iterator();
			while (hmIter.hasNext()) {
				Map.Entry<String, UMLClass> mapElem = (Map.Entry<String, UMLClass>) hmIter.next();
				classNames.add(mapElem.getKey());
            }
        
    
        for(UMLClass key : classes){
            classNames.add(key.getClassName());


            ArrayList<String> fields = new ArrayList<String>();
            for(Field field : key.getFields()){
                fields.add(field.getFieldName());

                completers.add(
                    new ArgumentCompleter(
                        new StringsCompleter("delete", "rename", "change"),
                        new StringsCompleter("field"),
                        new StringsCompleter(classNames),
                        new StringsCompleter(fields),
                        new NullCompleter()
                    )
                );
            }

            ArrayList<String> methods = new ArrayList<String>();
            for(Method method : key.getMethods()){
                methods.add(method.getMethodName());

                completers.add(
                    new ArgumentCompleter(
                        new StringsCompleter("delete", "rename", "change"),
                        new StringsCompleter("method"),
                        new StringsCompleter(classNames),
                        new StringsCompleter(methods),
                        new NullCompleter()
                    )
                );
            }
        }

        return new AggregateCompleter(completers);
    }

    public AggregateCompleter relationComplete(){
        AggregateCompleter completers;

        completers = new AggregateCompleter(
            new StringsCompleter("aggregation", "composition", "inheritance", "realization"),
            new NullCompleter()
        );
        return new AggregateCompleter(completers);
    }

}
