package st.kimsmik.thesurvivor;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import st.kimsmik.thesurvivor.managers.CharacterManager;
import st.kimsmik.thesurvivor.managers.ConversationManager;
import st.kimsmik.thesurvivor.objects.CharacterInfo;
import st.kimsmik.thesurvivor.objects.ConversationInfo;

/**
 * Created by Kim on 2016/1/26.
 */
public class ConversationDialog extends Dialog {
    private static int DIALOG_SPEED = 200;
    private TextView nameText = null;
    private ImageView targetImg = null;
    private TextView dialogText = null;
    private RelativeLayout dialogBg = null;
    private DialogHandler dialogHandler = null;

    private Thread dialogThread = null;
    private Boolean dialoging = false;

    public ConversationDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_conversation);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        int h = ( (Activity)context).getWindow().getDecorView().getHeight()*9/10;
        int w = ( (Activity)context).getWindow().getDecorView().getWidth()*9/10;
        getWindow().setLayout(w,h);

        dialogHandler = new DialogHandler();
        this.nameText = (TextView) findViewById(R.id.nameText);
        this.targetImg = (ImageView) findViewById(R.id.targetImg);
        this.dialogText = (TextView) findViewById(R.id.dialogText);
        this.dialogBg = (RelativeLayout)findViewById(R.id.dialogBg);
    }

    public void startConversation(String id){
        ConversationInfo conversationInfo = ConversationManager.ins().getConversation(id);
        if(conversationInfo == null) {
            Log.e("Test","Has No ConversationInfo with ID:"+id);
            return;
        }
        CharacterInfo characterInfo = CharacterManager.ins().getCharacter(conversationInfo.getCharacter());
        if(characterInfo == null) {
            Log.e("Test","Has No CharacterInfo with ID:"+conversationInfo.getCharacter());
            return;
        }

        this.nameText.setText(characterInfo.getName());
        final String dialog = conversationInfo.getText();
        //this.targetImg.setImageResource(imgId);
        dialogThread = new Thread(new Runnable() {
            @Override
            public void run() {
                dialoging = true;
                try {
                    String nowDailog = "";
                    int charIndex = 0;
                    while(dialoging && charIndex < dialog.length()){
                        nowDailog += dialog.charAt(charIndex);
                        Message msg = new Message();
                        msg.obj = nowDailog;
                        dialogHandler.sendMessage(msg);
                        charIndex++;
                        Thread.sleep(DIALOG_SPEED);
                    }
                    dialoging = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        dialogThread.start();
        this.dialogBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialoging) {
                    dialoging = false;
                    dialogThread.interrupt();
                    dialogThread = null;
                    Message msg = new Message();
                    msg.obj = dialog;
                    dialogHandler.sendMessage(msg);
                } else {
                    ConversationDialog.this.dismiss();
                }
            }
        });
        show();

    }

    private class DialogHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            dialogText.setText((String)msg.obj);
        }
    }
}
