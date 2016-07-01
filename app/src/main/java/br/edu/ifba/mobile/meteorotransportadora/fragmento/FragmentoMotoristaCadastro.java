package br.edu.ifba.mobile.meteorotransportadora.fragmento;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifba.mobile.meteorotransportadora.R;
import br.edu.ifba.mobile.meteorotransportadora.bd.Motorista;
import br.edu.ifba.mobile.meteorotransportadora.tarefas.GravacaoMotorista;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoMotoristaCadastro extends Fragment {
    private static FragmentoMotoristaCadastro instancia = null;
    public static FragmentoMotoristaCadastro getInstancia() {
        if (instancia == null) {
            instancia = new FragmentoMotoristaCadastro();
        }
        return instancia;
    }

    private View tela = null;

    private EditText nomeMotorista = null;
    private EditText telefoneMotorista = null;
    private Button btnGravar = null;

    private Motorista motorista = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_motorista_cadastro, vgrupo, false);

        preparar();
        return tela;
    }

    private void preparar() {
        nomeMotorista = (EditText) tela.findViewById(R.id.nomeMotorista);
        telefoneMotorista = (EditText) tela.findViewById(R.id.telefoneMotorista);
        btnGravar = (Button) tela.findViewById(R.id.btnGravar);

        btnGravar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GravacaoMotorista gravacao =
                                new GravacaoMotorista(getContexto(), getMotorista());
                        gravacao.execute();
                    }
                }
        );
    }

    private Context getContexto() {
        return this.getContext();
    }

    private Motorista getMotorista() {
        motorista.setNome(nomeMotorista.getText().toString());
        motorista.setTelefone(telefoneMotorista.getText().toString());
        return motorista;
    }

    public void exibirMotoristaSelecionado() {
        motorista = FragmentoMotoristaLista.getInstancia().getMotoristaSelecionado();

        if (motorista.getCodigo() == -1 ){
            limparCampos();
        } else {
            carregarCampos();
        }

    }

    private void limparCampos() {
        nomeMotorista.setText("");
        telefoneMotorista.setText("");
    }

    private void carregarCampos () {
        nomeMotorista.setText(motorista.getNome());
        telefoneMotorista.setText(motorista.getTelefone());
    }
}
