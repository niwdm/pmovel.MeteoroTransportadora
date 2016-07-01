package br.edu.ifba.mobile.meteorotransportadora.fragmento;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.edu.ifba.mobile.meteorotransportadora.R;

/**
 * Created by armgfilho on 01/07/2016.
 */
public class FragmentoInformacao extends Fragment {
    private static FragmentoInformacao instancia = null;

    public static FragmentoInformacao getInstancia(){
        if (instancia == null ){
            instancia = new FragmentoInformacao();
        }

        return instancia;
    }

    private View tela = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_informacao, vgrupo, false);
        return tela;
    }

}
