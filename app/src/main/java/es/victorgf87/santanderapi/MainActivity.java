package es.victorgf87.santanderapi;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import es.victorgf87.santanderopenapiwrapper.apicalls.SantanderAPI;
import es.victorgf87.santanderopenapiwrapper.serializedclasses.CollectionsList;
import es.victorgf87.santanderopenapiwrapper.serializedclasses.DataSet;
import es.victorgf87.santanderopenapiwrapper.serializedclasses.DataSetResource;
import es.victorgf87.santanderopenapiwrapper.serializedclasses.Kollektion;
import es.victorgf87.santanderopenapiwrapper.serializedclasses.MetaDataAttributeGroup;


public class MainActivity extends ActionBarActivity
{
    TextView text;
    //@InjectView(R.id.activity_main_text_view)TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text=(TextView)findViewById(R.id.activity_main_text_view);


        HandlerThread ht=new HandlerThread("mythread");
        ht.start();

        Handler hand=new Handler(ht.getLooper());
        hand.post(new Runnable()
        {
            @Override
            public void run()
            {
                SantanderAPI api=new SantanderAPI();
                String show="";
                /////////////


                CollectionsList list=api.getAllCollections();
                final List<String> ids=new LinkedList<String>();
                for(Kollektion kol: list.getCollections())
                {
                    ids.add(kol.getId());
                }




                /////////////
                final String finalShow = show;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //text.setText(Html.fromHtml(finalCarril_biciString + "\n\n--------------\n\n" + finalStr));
                        text.setText(ids.toString());

                    }
                });



            }
        });
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
}
