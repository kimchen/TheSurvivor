package st.kimsmik.thesurvivor;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Kim on 2016/1/24.
 */
public class Inventory {
    private static int DEFAULT_INVENTORY_SIZE = 10;

    private ConcurrentHashMap<String,Integer> itemTable = new ConcurrentHashMap<>();

    private int size = DEFAULT_INVENTORY_SIZE;
    public void setSize(int i){
        this.size = i;
    }
    public int getSize(){
        return size;
    }

    public boolean hasItem(String id){
        return itemTable.containsKey(id);
    }

    public int getItemNum(String id){
        if(!hasItem(id)) {
            return 0;
        }
        return itemTable.get(id);
    }

    public boolean addItem(String id,int num){
        if(hasItem(id)) {
            itemTable.replace(id, itemTable.get(id) + num);
            return true;
        }else{
            if(itemTable.entrySet().size() >= size){
                return false;
            }
            itemTable.put(id,num);
            return true;
        }
    }

    public boolean deleteItem(String id,int num){
        if(!hasItem(id) || itemTable.get(id)<num){
            return false;
        }else{
            int resNum = itemTable.get(id) - num;
            itemTable.replace(id, resNum);
            if(resNum == 0){
                itemTable.remove(id);
            }
            return true;
        }
    }

    public Set<Map.Entry<String,Integer>> getAllItems(){
        return itemTable.entrySet();
    }
}
