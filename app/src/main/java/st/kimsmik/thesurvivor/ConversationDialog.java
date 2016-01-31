package st.kimsmik.thesurvivor;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

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
    private ConversationInfo conversationInfo = null;
    private int dialogIndex = 0;

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

    public void startConversation(String id) {
        conversationInfo = ConversationManager.ins().getConversation(id);
        if (conversationInfo == null) {
            Log.e("Test", "Has No ConversationInfo with ID:" + id);
            return;
        }
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                nextDialog();
            }
        });
        dialogIndex = 0;
        nextDialog();
    }
    public void nextDialog() {
        if(dialogIndex>=conversationInfo.getDialog().size())
            return;
        Pair<String, String> dialog = conversationInfo.getDialog().get(dialogIndex);
        dialogIndex++;
        CharacterInfo characterInfo = CharacterManager.ins().getCharacter(dialog.first);
        if (characterInfo == null) {
            Log.e("Test", "Has No CharacterInfo with ID:" + dialog.first);
            nextDialog();
            return;
        }
        this.nameText.setText(characterInfo.getName());
        final String text = dialog.second;
        //this.targetImg.setImageResource(imgId);
        dialogThread = new Thread(new Runnable() {
            @Override
            public void run() {
                dialoging = true;
                try {
                    String nowDailog = "";
                    int charIndex = 0;
                    while (dialoging && charIndex < text.length()) {
                        nowDailog += text.charAt(charIndex);
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
                    msg.obj = text;
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
