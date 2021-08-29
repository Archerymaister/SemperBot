package semperbot.command.commandtree;

import semperbot.command.ICommand;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandTreeEntity {
    private HashMap<String, CommandTreeEntity> nodes = new HashMap<>();
    private String name;
    private Class<? extends ICommand> clazz;

    public CommandTreeEntity(){}

    public CommandTreeEntity(Class<? extends ICommand> clazz){
        this.clazz = clazz;
    }

    public CommandTreeEntity(String name) {
        this.name = name;
    }

    public CommandTreeEntity(String name, Class<? extends ICommand> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public CommandTreeEntity getChild(String name){
        return this.nodes.get(name);
    }

    public CommandTreeEntity addLeaf(String path, Class<? extends ICommand> clazz){
        if(!path.contains(".")){
            this.nodes.put(path, new CommandTreeEntity(path, clazz));
            return this.nodes.get(path);
        }

        Matcher pathMatcher = Pattern.compile("([a-z0-9]+)").matcher(path);
        pathMatcher.find();
        String newPath = path.replace(pathMatcher.group(1) + ".", "");

        CommandTreeEntity child = this.nodes.get(pathMatcher.group(1));
        if(child != null){
            child.addLeaf(newPath, clazz);
        }else{
            this.nodes.put(pathMatcher.group(1), new CommandTreeEntity(pathMatcher.group(1)));
            child = this.nodes.get(pathMatcher.group(1));
            child.addLeaf(newPath, clazz);
        }
        return this;
    }

    public CommandTreeEntity getLeaf(String path){
        if(this.isLeaf())
            return this;

        Matcher pathMatcher = Pattern.compile("([a-z0-9]+)").matcher(path);
        pathMatcher.find();
        String newPath = path.replace(pathMatcher.group(1) + ".", "");

        return getChild(pathMatcher.group(1)).getLeaf(newPath);
    }

    public void printTree(String spacer){
        for(Map.Entry<String, CommandTreeEntity> e : this.nodes.entrySet()){
            System.out.println(spacer + e.getKey() + (e.getValue().isLeaf()?"*":""));
            e.getValue().printTree(spacer + "  ");
        }
    }

    public boolean isLeaf(){
        return clazz != null;
    }

    public void setClazz(Class<? extends ICommand> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends ICommand> getClazz(){
        return clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, CommandTreeEntity> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        return "CommandTreeEntity{" +
                "nodes=" + nodes.keySet() +
                ", name='" + name + '\'' +
                ", clazz=" + clazz +
                '}';
    }
}
