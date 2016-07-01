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
import br.edu.ifba.mobile.meteorotransportadora.bd.Motorista;
import br.edu.ifba.mobile.meteorotransportadora.tarefas.ListagemMotorista;
import br.edu.ifba.mobile.meteorotransportadora.tarefas.RemocaoMotorista;

public class FragmentoMotoristaLista extends Fragment {
    private static FragmentoMotoristaLista instancia = null;

    public static FragmentoMotoristaLista getInstancia() {
        if (instancia == null) {
            instancia = new FragmentoMotoristaLista();
        }

        return instancia;
    }
    //instanciar o xml na memoria

    private View tela = null;
    private ListView lista = null;

    @Override
    public View onCreateView (LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        //Resource_Com_ID_No caso um layout...
        tela = inflador.inflate(R.layout.fragmento_motorista_lista, vgrupo, false);

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
            if (id == R.id.remover_motorista){
                RemocaoMotorista remocao =
                        new RemocaoMotorista(
                                this.getContext(),
                                this.getMotoristaSelecionado());
                remocao.execute();
                atualizar();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void preparar() {
        lista = (ListView) tela.findViewById(R.id.motoristaLista);
        this.setHasOptionsMenu(true);
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    public void atualizar () {
        ListagemMotorista listagem = new ListagemMotorista(this.getContext(), lista);
        listagem.execute();
    }

    public Motorista getMotoristaSelecionado () {
        Motorista motorista = new Motorista();

        int posicao = lista.getCheckedItemPosition(); //não é lista.getSelectedItemPosition()

        if (posicao != ListView.INVALID_POSITION) {
            motorista = (Motorista)lista.getItemAtPosition(posicao);
        }

        return motorista;
    }


}
