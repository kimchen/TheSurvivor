package st.kimsmik.thesurvivor;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import st.kimsmik.thesurvivor.objects.CharacterInfo;

/**
 * Created by Kim on 2016/1/24.
 */
public class Player {
    private static Player mIns = null;
    private Player(){
        bag.addItem("mat001",10);
        bag.addItem("mat002",10);
    }
    public static Player ins(){
        if(mIns == null){
            mIns = new Player();
        }
        return mIns;
    }

    public enum HEALTH_STATUS {
        GOOD("good"),
        BAD("bad");
        private String text="";
        HEALTH_STATUS(String s){
            this.text = s;
        }
        public String getText(){
            return this.text;
        }
    }

    private Inventory bag = new Inventory();
    public Inventory getBag(){
        return bag;
    }
    private Inventory warehouse = new Inventory();
    public Inventory getWarehouse(){
        return warehouse;
    }
    private UiRef uiRef = null;

    private Integer stamina = 100;
    public void setStamina(Integer i){
        this.stamina = i;
        this.uiRef.staminaText.setText(this.stamina.toString());
    }
    public Integer getStamina(){
        return this.stamina;
    }

    private Integer spirit = 100;
    public void setSpirit(Integer i){
        this.spirit = i;
        this.uiRef.spiritText.setText(this.spirit.toString());
    }
    public Integer getSpirit(){
        return this.spirit;
    }

    private Integer full = 100;
    public void setFull(Integer i){
        this.full = i;
        this.uiRef.fullText.setText(this.full.toString());
    }
    public Integer getFull(){
        return this.full;
    }

    private Integer water = 100;
    public void setWater(Integer i){
        this.water = i;
        this.uiRef.waterText.setText(this.water.toString());
    }
    public Integer getWater(){
        return this.water;
    }

    private Integer coins = 0;
    public void setCoins(Integer i){
        this.coins = i;
        this.uiRef.coinsText.setText(this.coins.toString());
    }
    public Integer getCoins(){
        return this.coins;
    }

    private HEALTH_STATUS state = HEALTH_STATUS.GOOD;
    public void setState(HEALTH_STATUS h){
        this.state = h;
        this.uiRef.stateText.setText(this.state.getText());
    }
    public HEALTH_STATUS getState(){
        return this.state;
    }

    private List<CharacterInfo> parterners = new ArrayList<>();

    public void setUiRef(UiRef ref){
        this.uiRef = ref;
        this.uiRef.staminaText.setText(this.stamina.toString());
        this.uiRef.spiritText.setText(this.spirit.toString());
        this.uiRef.fullText.setText(this.full.toString());
        this.uiRef.waterText.setText(this.water.toString());
        this.uiRef.coinsText.setText(this.coins.toString());
        this.uiRef.stateText.setText(this.state.getText());
    }

    public static class UiRef{
            public TextView staminaText = null;
            public TextView spiritText = null;
            public TextView fullText = null;
            public TextView waterText = null;
            public TextView coinsText = null;
            public TextView stateText = null;
    }
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
