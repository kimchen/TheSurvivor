package st.kimsmik.thesurvivor.managers;

import android.content.Context;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import st.kimsmik.thesurvivor.objects.ItemInfo;
import st.kimsmik.thesurvivor.R;

/**
 * Created by Kim on 2016/1/24.
 */
public class ItemManager {
    private static ItemManager mIns = null;
    private ItemManager(){}
    public static ItemManager ins(){
        if(mIns == null){
            mIns = new ItemManager();
        }
        return mIns;
    }

    private ConcurrentHashMap<String,ItemInfo> itemMap = new ConcurrentHashMap<>();

    public void init(Context c){
        itemMap = new ConcurrentHashMap<>();
        XmlResourceParser parser = c.getResources().getXml(R.xml.item_info);
        try{
            ItemInfo itemInfo = null;
            while (parser.getEventType() != XmlResourceParser.END_DOCUMENT) {
                if(parser.getEventType() == XmlResourceParser.START_TAG){
                    if(parser.getName().equals("Item")){
                        itemInfo = new ItemInfo();
                        String id = parser.getAttributeValue(null, "id");
                        itemInfo.setId(id);
                        itemInfo.setName(parser.getAttributeValue(null,"name"));
                        itemInfo.setDescription(parser.getAttributeValue(null, "description"));
                        itemMap.put(id,itemInfo);
                    }else if(parser.getName().equals("Formula")){
                        String id = parser.getAttributeValue("", "id");
                        int num = parser.getAttributeIntValue(null,"num",0);
                        if(num > 0 && itemInfo!=null){
                            itemInfo.addFormula(id,num);
                        }
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

    public ItemInfo getItem(String id){
        if(!itemMap.containsKey(id)){
            return null;
        }else{
            return itemMap.get(id);
        }
    }
}
