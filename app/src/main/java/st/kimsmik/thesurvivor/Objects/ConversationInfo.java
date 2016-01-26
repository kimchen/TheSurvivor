package st.kimsmik.thesurvivor.objects;

/**
 * Created by Kim on 2016/1/26.
 */
public class ConversationInfo {
    private String id = "";
    public void setId(String s){
        this.id = s;
    }
    public String getId(){
        return this.id;
    }

    private String character = "";
    public void setCharacter(String s){
        this.character = s;
    }
    public String getCharacter(){
        return this.character;
    }

    private String text = "";
    public void setText(String s){
        this.text = s;
    }
    public String getText(){
        return this.text;
    }
}
