package br.edu.ifba.mobile.meteorotransportadora.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.meteorotransportadora.bd.FachadaBD;
import br.edu.ifba.mobile.meteorotransportadora.bd.Motorista;


/**
 * Created by armgfilho on 27/05/2016.
 */
public class ListagemMotorista extends AsyncTask<Void, Void, List<Motorista>> {

    private Context contexto = null;
    private ListView listaMotoristas = null;

    public ListagemMotorista(Context contexto, ListView listaMotoristas) {
        this.contexto = contexto;
        this.listaMotoristas = listaMotoristas;
    }

    @Override
    protected List<Motorista> doInBackground(Void... params) {
        List<Motorista> motoristas = FachadaBD.getInstancia().listarMotoristas();

        return motoristas;
    }

    @Override
    protected void onPostExecute(List<Motorista> motoristas){
        if (motoristas.isEmpty()) {
            Toast.makeText(contexto, "Lista vazia! Cadastre um motorista!",
                    Toast.LENGTH_LONG).show();
        } else {
            ArrayAdapter<Motorista> adaptador =
                    new ArrayAdapter<Motorista>(contexto,
                            android.R.layout.simple_list_item_single_choice, motoristas);
            listaMotoristas.setAdapter(adaptador);
        }


    }
}
