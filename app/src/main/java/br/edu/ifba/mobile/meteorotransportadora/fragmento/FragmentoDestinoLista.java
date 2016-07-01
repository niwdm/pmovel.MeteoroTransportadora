package br.edu.ifba.mobile.meteorotransportadora.fragmento;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.ifba.mobile.meteorotransportadora.R;
import br.edu.ifba.mobile.meteorotransportadora.bd.Destino;
import br.edu.ifba.mobile.meteorotransportadora.tarefas.ListagemDestino;
import br.edu.ifba.mobile.meteorotransportadora.tarefas.RemocaoDestino;

public class FragmentoDestinoLista extends Fragment {
    private static FragmentoDestinoLista instancia = null;

    public static FragmentoDestinoLista getInstancia() {
        if (instancia == null) {
            instancia = new FragmentoDestinoLista();
        }

        return instancia;
    }
    //instanciar o xml na memoria

    private View tela = null;
    private ListView lista = null;

    @Override
    public View onCreateView (LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        //Resource_Com_ID_No caso um layout...
        tela = inflador.inflate(R.layout.fragmento_destino_lista, vgrupo, false);

        preparar();
        return tela;
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflador){
        super.onCreateOptionsMenu(menu, inflador);

        inflador.inflate(R.menu.menu_meteoro_transportadora, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        long id = item.getItemId();
        if (id != AdapterView.INVALID_ROW_ID) {
            if (id == R.id.remover_destino){
                RemocaoDestino remocao =
                        new RemocaoDestino(
                                this.getContext(),
                                this.getDestinoSelecionado());
                remocao.execute();
                atualizar();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void preparar() {
        lista = (ListView) tela.findViewById(R.id.destinoLista);
        this.setHasOptionsMenu(true);
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    public void atualizar () {
        ListagemDestino listagem = new ListagemDestino(this.getContext(), lista);
        listagem.execute();
    }

    public Destino getDestinoSelecionado () {
        Destino destino = new Destino();

        int posicao = lista.getCheckedItemPosition(); //não é lista.getSelectedItemPosition()

        if (posicao != ListView.INVALID_POSITION) {
            destino = (Destino)lista.getItemAtPosition(posicao);
        }

        return destino;
    }


}
