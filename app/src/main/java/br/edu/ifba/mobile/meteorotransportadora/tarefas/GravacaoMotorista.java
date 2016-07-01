package br.edu.ifba.mobile.meteorotransportadora.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.meteorotransportadora.bd.Motorista;
import br.edu.ifba.mobile.meteorotransportadora.bd.FachadaBD;

/**
 * Created by armgfilho on 27/05/2016.
 */
public class GravacaoMotorista extends AsyncTask<Void, Void, String> {

    private Motorista motorista = null;
    private Context contexto = null;

    public GravacaoMotorista(Context contexto, Motorista motorista) {
        this.motorista = motorista;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo= -1;

        if (motorista.getCodigo() == -1) {
            codigo = FachadaBD.getInstancia().inserirMotorista(motorista);
        } else {
            codigo = FachadaBD.getInstancia().atualizarMotoristas(motorista);
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
