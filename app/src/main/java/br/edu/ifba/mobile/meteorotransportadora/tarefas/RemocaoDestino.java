package br.edu.ifba.mobile.meteorotransportadora.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.meteorotransportadora.bd.Destino;
import br.edu.ifba.mobile.meteorotransportadora.bd.FachadaBD;

/**
 * Created by armgfilho on 03/06/2016.
 */
public class RemocaoDestino extends AsyncTask<Void, Void, String> {

    private Destino destino = null;
    private Context contexto = null;

    public RemocaoDestino(Context contexto, Destino destino) {
        this.destino = destino;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo= -1;

        if (destino.getCodigo() != -1) {
            if (FachadaBD.getInstancia().removerDestino(destino) == 0) {
                mensagem = "Problemas de Remoção!";
            } else {
                mensagem = "Destino removido!";
            }
        } else {
            mensagem = "Selecione um destino!";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }
}
