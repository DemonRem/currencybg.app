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
package net.vexelon.currencybg.app.ui;

import android.content.res.Resources;

import com.google.common.collect.Maps;

import net.vexelon.currencybg.app.R;

import java.util.Map;

/**
 * Operations based on currency codes
 * 
 */
public final class UiCodes {

	private static Map<String, String> mapping;

	/**
	 * Fetch currency name by code
	 *
	 * @param res
	 * @param code
	 * @return
	 */
	public static String getCurrencyName(Resources res, String code) {
		if (mapping == null) {
			String[] codes = res.getStringArray(R.array.currency_codes);
			String[] names = res.getStringArray(R.array.currency_names);

			mapping = Maps.newHashMap();
			for (int i = 0; i < codes.length; i++) {
				mapping.put(codes[i], names[i]);
			}
		}

		return mapping.get(code);
	}
}
