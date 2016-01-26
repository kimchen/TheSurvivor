package st.kimsmik.thesurvivor;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.logging.Logger;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Player player = null;

    public MainActivityFragment() {
        this.player = Player.ins();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Player.UiRef uiRef = new Player.UiRef();
        uiRef.staminaText = (TextView)root.findViewById(R.id.staminaText);
        uiRef.spiritText = (TextView)root.findViewById(R.id.spiritText);
        uiRef.fullText = (TextView)root.findViewById(R.id.fullText);
        uiRef.waterText = (TextView)root.findViewById(R.id.waterText);
        uiRef.coinsText = (TextView)root.findViewById(R.id.coinText);
        uiRef.healthText = (TextView)root.findViewById(R.id.healthText);
        player.setUiRef(uiRef);

        ImageView skillBtn = (ImageView)root.findViewById(R.id.skillBtn);
        skillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Test","skillBtn click");
                ConversationDialog conversationDialog = new ConversationDialog(getActivity());
                conversationDialog.startConversation("con0001");
            }
        });
        ImageView craftBtn = (ImageView)root.findViewById(R.id.craftBtn);
        craftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ImageView backpackBtn = (ImageView)root.findViewById(R.id.backpackBtn);
        backpackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return root;
    }
}
