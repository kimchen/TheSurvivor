package st.kimsmik.thesurvivor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import st.kimsmik.thesurvivor.managers.ItemManager;
import st.kimsmik.thesurvivor.objects.ItemInfo;

/**
 * Created by Kim on 2016/1/29.
 */
public class ItemLayoutAdapter extends BaseAdapter{
    private Context context = null;
    private LayoutInflater mInflater = null;
    private List< Map.Entry<String,Integer>> mList = null;
    private View.OnClickListener deleteListener = null;

    public ItemLayoutAdapter(Context c, List< Map.Entry<String,Integer>> list){
        context = c;
        mInflater = LayoutInflater.from(c);
        mList = list;
    }

    public void setOnDeleteDeckListener(View.OnClickListener l){
        deleteListener = l;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutComponent lc = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.layout_item, null);
            lc = new LayoutComponent((TextView)convertView.findViewById(R.id.itemNameText),
                    (TextView)convertView.findViewById(R.id.itemNumText));
            convertView.setTag(lc);
        }else{
            lc = (LayoutComponent)convertView.getTag();
        }

        Map.Entry<String,Integer> entry = mList.get(position);
        String itemId = entry.getKey();
        Integer itemNum = entry.getValue();
        ItemInfo item = ItemManager.ins().getItem(itemId);
        if(item != null){
            lc.name.setText(item.getName());
            lc.num.setText(itemNum.toString());
        }
        return convertView;
    }

    class LayoutComponent{
        public TextView name = null;
        public TextView num = null;
        public LayoutComponent(TextView nameV,TextView numV){
            this.name = nameV;
            this.num = numV;
        }
    }
}

