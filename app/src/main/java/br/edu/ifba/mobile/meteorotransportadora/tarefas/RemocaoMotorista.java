package br.edu.ifba.mobile.meteorotransportadora.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.meteorotransportadora.bd.FachadaBD;
import br.edu.ifba.mobile.meteorotransportadora.bd.Motorista;

/**
 * Created by armgfilho on 03/06/2016.
 */
public class RemocaoMotorista extends AsyncTask<Void, Void, String> {

    private Motorista motorista = null;
    private Context contexto = null;

    public RemocaoMotorista(Context contexto, Motorista motorista) {
        this.motorista = motorista;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo= -1;

        if (motorista.getCodigo() != -1) {
            if (FachadaBD.getInstancia().removerMotorista(motorista) == 0) {
                mensagem = "Problemas de Remoção!";
            } else {
                mensagem = "Motorista removido!";
            }
        } else {
            mensagem = "Selecione um motorista!";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }
}
