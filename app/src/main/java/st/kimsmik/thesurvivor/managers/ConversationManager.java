package st.kimsmik.thesurvivor.managers;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import st.kimsmik.thesurvivor.objects.ConversationInfo;
import st.kimsmik.thesurvivor.R;

/**
 * Created by Kim on 2016/1/26.
 */
public class ConversationManager {
    private static ConversationManager mIns = null;
    private ConversationManager(){}
    public static ConversationManager ins(){
        if(mIns == null){
            mIns = new ConversationManager();
        }
        return mIns;
    }

    private ConcurrentHashMap<String,ConversationInfo> objMap = new ConcurrentHashMap<>();

    public void init(Context c){
        objMap = new ConcurrentHashMap<>();
        XmlResourceParser parser = c.getResources().getXml(R.xml.conversation);
        try{
            ConversationInfo conversationInfo = null;
            while (parser.getEventType() != XmlResourceParser.END_DOCUMENT) {
                if(parser.getEventType() == XmlResourceParser.START_TAG){
                    if(parser.getName().equals("Conversation")){
                        conversationInfo = new ConversationInfo();
                        String id = parser.getAttributeValue(null, "id");
                        conversationInfo.setId(id);
                        objMap.put(id, conversationInfo);
                    }else if(parser.getName().equals("Dialog")){
                        String target = parser.getAttributeValue(null, "character");
                        String text = parser.getAttributeValue(null, "text");
                        conversationInfo.addDialog(target,text);
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

    public ConversationInfo getConversation(String id){
        if(!objMap.containsKey(id)){
            return null;
        }else{
            return objMap.get(id);
        }
    }
}
