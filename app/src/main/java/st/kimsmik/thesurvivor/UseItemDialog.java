package st.kimsmik.thesurvivor;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import st.kimsmik.thesurvivor.objects.ItemInfo;

/**
 * Created by Kim on 2016/1/29.
 */
public class UseItemDialog extends Dialog {
    private TextView itemNameText = null;
    private TextView itemNumText = null;
    private TextView itemDesText = null;

    private Button useBtn = null;
    private Button cancelBtn = null;

    public UseItemDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_use_item);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        itemNameText = (TextView)findViewById(R.id.itemNameText);
        itemNumText = (TextView)findViewById(R.id.itemNumText);
        itemDesText = (TextView)findViewById(R.id.itemDesText);

        useBtn = (Button)findViewById(R.id.useBtn);
        cancelBtn = (Button)findViewById(R.id.cancelBtn);
    }

    public void showInfo(ItemInfo item,Integer num){
        itemNameText.setText(item.getName());
        itemDesText.setText(item.getDescription());
        itemNumText.setText(num.toString());

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UseItemDialog.this.dismiss();
            }
        });
        this.show();
    }
}
