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
package net.vexelon.currencybg.app.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;
import net.vexelon.currencybg.app.R;

public class PrefsFragment extends PreferenceFragment
		implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

	public static final String TAG = "preferences";
	public static final String KEY_SCREEN_APP_PREFS = "app_prefs";
	public static final String KEY_PREF_CURRENCIES_PRECISION = "pref_currencies_precision";
	public static final String KEY_PREF_WIFI_ONLY_DOWNLOADS = "pref_wifi_only_downloads";
	public static final String KEY_PREF_RATEUS = "rateus";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferences);

		findPreference(KEY_PREF_RATEUS).setOnPreferenceClickListener(this);
		findPreference(KEY_PREF_CURRENCIES_PRECISION).setOnPreferenceChangeListener(this);
		findPreference(KEY_PREF_WIFI_ONLY_DOWNLOADS).setOnPreferenceChangeListener(this);
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		if (preference.getKey().equals(KEY_PREF_RATEUS)) {
			final String appPackageName = getActivity().getPackageName();

			try {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
			} catch (android.content.ActivityNotFoundException e) {
				startActivity(new Intent(Intent.ACTION_VIEW,
						Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
			}
		}
		return true;
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		if (preference.getKey().equals(KEY_PREF_CURRENCIES_PRECISION)) {
			Toast.makeText(getActivity(), getString(R.string.pref_value_update, newValue), Toast.LENGTH_SHORT).show();
		} else if (preference.getKey().equals(KEY_PREF_WIFI_ONLY_DOWNLOADS)) {
			Toast.makeText(getActivity(),
					getString(R.string.pref_value_update,
							getString((Boolean) newValue ? R.string.text_enabled : R.string.text_disabled)),
					Toast.LENGTH_SHORT).show();
		}
		// update state with new value
		return true;
	}
}
