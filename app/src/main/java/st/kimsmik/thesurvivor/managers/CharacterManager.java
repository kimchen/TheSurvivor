package st.kimsmik.thesurvivor.managers;

import android.content.Context;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import st.kimsmik.thesurvivor.objects.CharacterInfo;
import st.kimsmik.thesurvivor.R;

/**
 * Created by Kim on 2016/1/26.
 */
public class CharacterManager {
    private static CharacterManager mIns = null;
    private CharacterManager(){}
    public static CharacterManager ins(){
        if(mIns == null){
            mIns = new CharacterManager();
        }
        return mIns;
    }

    private ConcurrentHashMap<String,CharacterInfo> objMap = new ConcurrentHashMap<>();

    public void init(Context c){
        objMap = new ConcurrentHashMap<>();
        XmlResourceParser parser = c.getResources().getXml(R.xml.character);
        try{
            CharacterInfo characterInfo = null;
            while (parser.getEventType() != XmlResourceParser.END_DOCUMENT) {
                if(parser.getEventType() == XmlResourceParser.START_TAG){
                    if(parser.getName().equals("Character")){
                        characterInfo = new CharacterInfo();
                        String id = parser.getAttributeValue(null, "id");
                        characterInfo.setId(id);
                        characterInfo.setName(parser.getAttributeValue(null,"name"));
                        characterInfo.setDescription(parser.getAttributeValue(null, "description"));
                        objMap.put(id,characterInfo);
                    }
                }
                parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public CharacterInfo getCharacter(String id){
        if(!objMap.containsKey(id)){
            return null;
        }else{
            return objMap.get(id);
        }
    }
}
