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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import net.vexelon.currencybg.app.AppSettings;
import net.vexelon.currencybg.app.R;
import net.vexelon.currencybg.app.db.models.CurrencyData;
import net.vexelon.currencybg.app.common.CurrencyLocales;
import net.vexelon.currencybg.app.ui.events.Notifications;
import net.vexelon.currencybg.app.ui.events.NotificationsListener;

public class AbstractFragment extends Fragment {

	protected View rootView;
	protected Menu mMenu;
	protected List<NotificationsListener> listeners = new ArrayList<NotificationsListener>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		mMenu = menu;
		super.onCreateOptionsMenu(menu, inflater);
	}

	// public void addListener(NotificationsListener listner) {
	// listeners.add(listner);
	// }
	//
	// public void removeListener(NotificationsListener listener) {
	// listeners.remove(listener);
	// }

	public void notifyListeners(Notifications notification) {
		for (NotificationsListener listener : listeners) {
			listener.onNotification(notification);
		}
	}

	protected void setRefreshActionButtonState(final boolean isRefreshing) {
		if (mMenu != null) {
			MenuItem menuItem = mMenu.findItem(R.id.action_refresh);
			if (menuItem != null) {
				if (isRefreshing) {
					menuItem.setActionView(isRefreshing ? R.layout.actionbar_indeterminate_progress : null);
				} else {
					menuItem.setActionView(null);
				}
			}
		}
	}

	protected CurrencyLocales getSelectedCurrenciesLocale() {
		return new AppSettings(getActivity()).getCurrenciesLanguage();
	}

	protected int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
	}

	protected Map<String, CurrencyData> getCurreniesMap(List<CurrencyData> currenciesList) {
		Map<String, CurrencyData> currenciesMap = Maps.newHashMap();
		for (CurrencyData currencyData : currenciesList) {
			currenciesMap.put(currencyData.getCode(), currencyData);
		}
		return currenciesMap;
	}

	protected void showSnackbar(String text, int duration) {
		Snackbar snackbar = Snackbar.make(rootView, text, duration);
		View v = snackbar.getView();
		v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
		snackbar.show();
	}

	protected void showSnackbar(String text) {
		showSnackbar(text, Snackbar.LENGTH_SHORT);
	}

	protected void showSnackbar(int resId, int duration) {
		Snackbar snackbar = Snackbar.make(rootView, resId, duration);
		View v = snackbar.getView();
		// TextView textView = (TextView)
		// v.findViewById(android.support.design.R.id.snackbar_text);
		// textView.setTextColor(getResources().getColor(R.color.colorAccent));
		v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
		snackbar.show();
	}

	protected void showSnackbar(int resId) {
		showSnackbar(resId, Snackbar.LENGTH_SHORT);
	}
}
