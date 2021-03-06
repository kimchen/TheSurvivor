package st.kimsmik.thesurvivor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;


import st.kimsmik.thesurvivor.managers.CharacterManager;
import st.kimsmik.thesurvivor.managers.ConversationManager;
import st.kimsmik.thesurvivor.managers.ItemManager;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ConversationManager.ins().init(this);
        CharacterManager.ins().init(this);
        ItemManager.ins().init(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment target){

        FragmentManager fragmentMgr = getSupportFragmentManager();
        FragmentTransaction fragmentTrans = fragmentMgr.beginTransaction();
        fragmentTrans.replace(R.id.fragment,target);
        fragmentTrans.commit();
    }
}
