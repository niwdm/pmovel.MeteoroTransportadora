package br.edu.ifba.mobile.meteorotransportadora.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.meteorotransportadora.bd.Destino;
import br.edu.ifba.mobile.meteorotransportadora.bd.FachadaBD;

/**
 * Created by armgfilho on 27/05/2016.
 */
public class GravacaoDestino extends AsyncTask<Void, Void, String> {

    private Destino destino = null;
    private Context contexto = null;

    public GravacaoDestino(Context contexto, Destino destino) {
        this.destino = destino;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo= -1;

        if (destino.getCodigo() == -1) {
            codigo = FachadaBD.getInstancia().inserirDestino(destino);
        } else {
            codigo = FachadaBD.getInstancia().atualizarDestinos(destino);
        }

        if (codigo > 0) {
            mensagem = "Dados gravados com sucesso!";
        } else {
            mensagem = "Erro de gravação!";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }
}
