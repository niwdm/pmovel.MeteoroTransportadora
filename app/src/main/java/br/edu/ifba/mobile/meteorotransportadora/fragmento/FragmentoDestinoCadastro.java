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
import br.edu.ifba.mobile.meteorotransportadora.bd.Destino;
import br.edu.ifba.mobile.meteorotransportadora.tarefas.GravacaoDestino;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoDestinoCadastro extends Fragment {
    private static FragmentoDestinoCadastro instancia = null;
    public static FragmentoDestinoCadastro getInstancia() {
        if (instancia == null) {
            instancia = new FragmentoDestinoCadastro();
        }
        return instancia;
    }

    private View tela = null;

    private EditText nomeDestino = null;
    private EditText quantidadeDestino = null;
    private Button btnGravar = null;

    private Destino destino = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_destino_cadastro, vgrupo, false);

        preparar();
        return tela;
    }

    private void preparar() {
        nomeDestino = (EditText) tela.findViewById(R.id.nomeDestino);
        quantidadeDestino = (EditText) tela.findViewById(R.id.quantidadeDestino);
        btnGravar = (Button) tela.findViewById(R.id.btnGravar);

        btnGravar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GravacaoDestino gravacao =
                                new GravacaoDestino(getContexto(), getDestino());
                        gravacao.execute();
                    }
                }
        );
    }

    private Context getContexto() {
        return this.getContext();
    }

    private Destino getDestino() {
        destino.setNome(nomeDestino.getText().toString());
        destino.setQuantidade(Integer.valueOf(quantidadeDestino.getText().toString()));
        return destino;
    }

    public void exibirDestinoSelecionado() {
        destino = FragmentoDestinoLista.getInstancia().getDestinoSelecionado();

        if (destino.getCodigo() == -1 ){
            limparCampos();
        } else {
            carregarCampos();
        }

    }

    private void limparCampos() {
        nomeDestino.setText("");
        quantidadeDestino.setText("0");
    }

    private void carregarCampos () {
        nomeDestino.setText(destino.getNome());
        quantidadeDestino.setText(destino.getQuantidade() + "");
    }
}
