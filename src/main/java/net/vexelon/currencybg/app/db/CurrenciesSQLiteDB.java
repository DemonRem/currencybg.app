/*
 * CurrencyBG App
 * Copyright (C) 2016 Vexelon.NET Services
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.vexelon.currencybg.app.db;

import net.vexelon.currencybg.app.Defs;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CurrenciesSQLiteDB extends SQLiteOpenHelper {

	// Table Create Statements
	// TABLE_CURRENCY table create statement
	private static final String CREATE_TABLE_CURRENCY_BG = String.format(
			"create table %s(%s integer primary key autoincrement, %s text not null, %s integer not null, %s text, %s text, %s text not null, %s integer not null);",
			Defs.TABLE_CURRENCY, Defs.COLUMN_ID, Defs.COLUMN_CODE, Defs.COLUMN_RATIO, Defs.COLUMN_BUY, Defs.COLUMN_SELL,
			Defs.COLUMN_CURR_DATE, Defs.COLUMN_SOURCE);

	public CurrenciesSQLiteDB(Context context) {
		super(context, Defs.DATABASE_NAME, null, Defs.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// creating required tables

		database.execSQL(CREATE_TABLE_CURRENCY_BG);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		Log.i(Defs.LOG_TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
		switch (oldVersion) {
		case 1:
		case 2:
			/**
			 * Upgrade from database v1 or v2 to v3
			 */
			// Delete old table, if they are existing
			database.execSQL("DROP TABLE IF EXISTS " + Defs.TABLE_CURRENCY);
			database.execSQL("DROP TABLE IF EXISTS " + Defs.TABLE_CURRENCY_DATE);
			database.execSQL("DROP TABLE IF EXISTS " + Defs.TABLE_FIXED_CURRENCY);

			// Create a new table(with new structure) in which is collected all
			// data
			database.execSQL(CREATE_TABLE_CURRENCY_BG);
			break;
		default:
			Log.w(Defs.LOG_TAG, "Unknown old db version=" + oldVersion);
			break;
		}
	}

}
