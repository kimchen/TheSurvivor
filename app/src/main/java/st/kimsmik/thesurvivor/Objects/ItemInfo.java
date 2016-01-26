package st.kimsmik.thesurvivor.objects;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Kim on 2016/1/24.
 */
public class ItemInfo {
    private String id = "";
    public void setId(String s){
        this.id = s;
    }
    public String getId(){
        return this.id;
    }

    private String name = "";
    public void setName(String s){
        this.name = s;
    }
    public String getName(){
        return this.name;
    }

    private String description = "";
    public void setDescription(String s){
        this.description = s;
    }
    public String getDescription(){
        return this.description;
    }

    private ConcurrentHashMap<String,Integer> formula = new ConcurrentHashMap<String,Integer>();
    public void addFormula(String fid,int num){
        //formula.put(fid,num);
    }
    public void setFormula(ConcurrentHashMap chm){
        this.formula = chm;
    }
    public Set<Map.Entry<String,Integer>> getFormula(){
        return this.formula.entrySet();
    }
}
