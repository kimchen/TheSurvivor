package st.kimsmik.thesurvivor.objects;

import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    private List<Pair<String,String>> dialog = new ArrayList<>();
    public void addDialog(String target,String text){
        dialog.add(new Pair<String, String>(target,text));
    }

    public void setDialog(List<Pair<String,String>> list){
        this.dialog = list;
    }
    public List<Pair<String,String>> getDialog(){
        return dialog;
    }
//    private String character = "";
//    public void setCharacter(String s){
//        this.character = s;
//    }
//    public String getCharacter(){
//        return this.character;
//    }
//
//    private String text = "";
//    public void setText(String s){
//        this.text = s;
//    }
//    public String getText(){
//        return this.text;
//    }
}
