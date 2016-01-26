package st.kimsmik.thesurvivor.objects;

/**
 * Created by Kim on 2016/1/24.
 */
public class SceneInfo {
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
}
