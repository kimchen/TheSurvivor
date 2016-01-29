package st.kimsmik.thesurvivor;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Map;

import st.kimsmik.thesurvivor.managers.ItemManager;
import st.kimsmik.thesurvivor.objects.ItemInfo;

/**
 * Created by Kim on 2016/1/29.
 */
public class InventoryFragment extends Fragment {
    private GridView itemList = null;
    private TextView inventoryName = null;
    private Inventory inventory = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_inventory, container, false);
        itemList = (GridView)root.findViewById(R.id.itemList);
        inventoryName = (TextView)root.findViewById(R.id.inventoryName);
        ItemLayoutAdapter ila = new ItemLayoutAdapter(getActivity(),inventory.getAllItems());
        itemList.setAdapter(ila);
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map.Entry<String,Integer> entry = inventory.getAllItems().get(position);
                ItemInfo item = ItemManager.ins().getItem(entry.getKey());
                if(item == null)
                    return;
                UseItemDialog uid = new UseItemDialog(getActivity());
                uid.showInfo(item,entry.getValue());
            }
        });
        return root;
    }

    public void setInventory(Inventory in){
        inventory = in;
    }

}
