package br.edu.ifba.mobile.meteorotransportadora.bd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FachadaBD extends SQLiteOpenHelper {

	private static FachadaBD instancia = null;

	public static FachadaBD criarInstancia(Context context){
		if (instancia == null){
			instancia = new FachadaBD(context);
		}

		return instancia;
	}

	public static FachadaBD getInstancia() {
		return instancia;
	}

	private static String NOME_BANCO = "MeteoroTransportadora";
	private static int VERSAO_BANCO = 1;

	public FachadaBD(Context context) {
		super(context, NOME_BANCO, null, VERSAO_BANCO);

	}

	private static String COMANDO_CRIACAO_TABELA_MOTORISTA =
			"CREATE TABLE MOTORISTAS(" +
					"CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "NOME TEXT, TELEFONE TEXT)";

	private static String COMANDO_CRIACAO_TABELA_DESTINO =
			"CREATE TABLE DESTINOS(" +
					"CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "NOME TEXT, QUANTIDADE INTEGER)";


	@Override
	public void onCreate(SQLiteDatabase db) { //adiciona +BD que n tem... 1.0 na 2.0 usa o BD+n coisas
		db.execSQL(COMANDO_CRIACAO_TABELA_MOTORISTA); db.execSQL(COMANDO_CRIACAO_TABELA_DESTINO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
		// TODO Auto-generated method stub
	}

	// metodos de criacao de um CRUD utilizando o SQLite
	
	public long inserirMotorista(Motorista motorista) { //C
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valores = new ContentValues();

		valores.put("NOME", motorista.getNome());
		valores.put("TELEFONE", motorista.getTelefone());

		long codigo = db.insert("MOTORISTAS", null, valores);
		return codigo;
	}

    public long inserirDestino(Destino destino) { //C
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", destino.getNome());
        valores.put("QUANTIDADE", destino.getQuantidade());

        long codigo = db.insert("DESTINOS", null, valores);
        return codigo;
    }

	public List<Motorista> listarMotoristas() { //R
		List<Motorista> motoristas = new ArrayList<Motorista>();
		SQLiteDatabase db = this.getReadableDatabase();

		String selecao = "SELECT CODIGO, NOME, TELEFONE FROM MOTORISTAS";

		Cursor cursor = db.rawQuery(selecao, null);
		if (cursor != null) {
			boolean temProximo = cursor.moveToFirst();
			while (temProximo) {
				Motorista motorista = new Motorista();
				motorista.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
				motorista.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
				motorista.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));

				motoristas.add(motorista);

				temProximo = cursor.moveToNext();
			}

		}
		return motoristas;
	}

    public List<Destino> listarDestinos() { //R
        List<Destino> destinos = new ArrayList<Destino>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selecao = "SELECT CODIGO, NOME, QUANTIDADE FROM DESTINOS";

        Cursor cursor = db.rawQuery(selecao, null);
        if (cursor != null) {
            boolean temProximo = cursor.moveToFirst();
            while (temProximo) {
                Destino destino = new Destino();
                destino.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                destino.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                destino.setQuantidade(cursor.getInt(cursor.getColumnIndex("QUANTIDADE")));

                destinos.add(destino);

                temProximo = cursor.moveToNext();
            }

        }
        return destinos;
    }

	public long atualizarMotoristas(Motorista motorista) { //U
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valores = new ContentValues();

		valores.put("NOME", motorista.getNome());
		valores.put("TELEFONE", motorista.getTelefone());

		long codigo = db.update("MOTORISTAS", valores, "CODIGO = " + motorista.getCodigo(), null);
		return codigo;
	}

    public long atualizarDestinos(Destino destino) { //U
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", destino.getNome());
        valores.put("QUANTIDADE", destino.getQuantidade());

        long codigo = db.update("DESTINOS", valores, "CODIGO = " + destino.getCodigo(), null);
        return codigo;
    }


    public int removerMotorista(Motorista motorista) { //D
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("MOTORISTAS", "CODIGO = " + motorista.getCodigo(), null);
	}

    public int removerDestino(Destino destino) { //D
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("DESTINOS", "CODIGO = " + destino.getCodigo(), null);
    }
	
}