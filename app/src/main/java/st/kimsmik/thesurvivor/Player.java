package st.kimsmik.thesurvivor;

import java.util.ArrayList;
import java.util.List;

import st.kimsmik.thesurvivor.Objects.CharacterInfo;

/**
 * Created by Kim on 2016/1/24.
 */
public class Player {
    private Inventory bag = new Inventory();
    private Inventory warehouse = new Inventory();

    private int stamina = 100;
    private int spirit = 100;
    private int full = 100;
    private int water = 100;

    private List<CharacterInfo> parterners = new ArrayList<>();
//    public boolean buildItem(String id,int num){
//        ItemInfo item =ItemManager.ins().getItem(id);
//        Set<Map.Entry<String,Integer>> formula = item.getFormula();
//        if(formula.size() <= 0)
//            return false;
//        for(Map.Entry<String,Integer> material : formula){
//
//        }
//    }
}
