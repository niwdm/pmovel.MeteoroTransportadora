package br.edu.ifba.mobile.meteorotransportadora;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import br.edu.ifba.mobile.meteorotransportadora.bd.FachadaBD;
import br.edu.ifba.mobile.meteorotransportadora.fragmento.FragmentoDestinoCadastro;
import br.edu.ifba.mobile.meteorotransportadora.fragmento.FragmentoDestinoLista;
import br.edu.ifba.mobile.meteorotransportadora.fragmento.FragmentoInformacao;
import br.edu.ifba.mobile.meteorotransportadora.fragmento.FragmentoMotoristaCadastro;
import br.edu.ifba.mobile.meteorotransportadora.fragmento.FragmentoMotoristaLista;

public class MeteoroTransportadoraActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager paginador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteoro_transportadora);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        paginador = (ViewPager) findViewById(R.id.container);
        paginador.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(paginador);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        paginador.addOnPageChangeListener(this);

        FachadaBD.criarInstancia(this.getApplicationContext());

    }


    /*@Override //Metodos apagados para dar lugar ao menu em apenas uma tela.
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_controle_notas, menu);
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
    }*/

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 1) {
            FragmentoMotoristaLista.getInstancia().atualizar();
        } else if (position == 2) {
            FragmentoMotoristaCadastro.getInstancia().exibirMotoristaSelecionado();
        } else if (position == 3) {
            FragmentoDestinoLista.getInstancia().atualizar();
        } else if (position == 4) {
            FragmentoDestinoCadastro.getInstancia().exibirDestinoSelecionado();
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            Fragment frag = null;
            switch (position) {
                case 0 :
                    frag = FragmentoInformacao.getInstancia();

                    break;
                case 1 :
                    frag = FragmentoMotoristaLista.getInstancia();

                    break;
                case 2 :
                    frag = FragmentoMotoristaCadastro.getInstancia();

                    break;
                case 3 :
                    frag = FragmentoDestinoLista.getInstancia();

                    break;
                case 4 :
                    frag = FragmentoDestinoCadastro.getInstancia();

                    break;
            }


            return frag;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Infor";
                case 1:
                    return "Lis.Motorista";
                case 2:
                    return "Motorista";
                case 3:
                    return "Lis.Destino";
                case 4:
                    return "Destino";
            }
            return null;
        }
    }
}
