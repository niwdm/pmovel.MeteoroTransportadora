package br.edu.ifba.mobile.meteorotransportadora.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.meteorotransportadora.bd.Destino;
import br.edu.ifba.mobile.meteorotransportadora.bd.FachadaBD;


/**
 * Created by armgfilho on 27/05/2016.
 */
public class ListagemDestino extends AsyncTask<Void, Void, List<Destino>> {

    private Context contexto = null;
    private ListView listaDestinos = null;

    public ListagemDestino(Context contexto, ListView listaDestinos) {
        this.contexto = contexto;
        this.listaDestinos = listaDestinos;
    }

    @Override
    protected List<Destino> doInBackground(Void... params) {
        List<Destino> destinos = FachadaBD.getInstancia().listarDestinos();

        return destinos;
    }

    @Override
    protected void onPostExecute(List<Destino> destinos){
        if (destinos.isEmpty()) {
            Toast.makeText(contexto, "Lista vazia! Cadastre um destino!",
                    Toast.LENGTH_LONG).show();
        } else {
            ArrayAdapter<Destino> adaptador =
                    new ArrayAdapter<Destino>(contexto,
                            android.R.layout.simple_list_item_single_choice, destinos);
            listaDestinos.setAdapter(adaptador);
        }


    }
}
