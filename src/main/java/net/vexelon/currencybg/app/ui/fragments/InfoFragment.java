package net.vexelon.currencybg.app.ui.fragments;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.vexelon.currencybg.app.Defs;
import net.vexelon.currencybg.app.R;
import net.vexelon.currencybg.app.ui.components.InfoListAdapter;
import net.vexelon.currencybg.app.utils.StringUtils;

import java.util.List;
import java.util.Map;

public class InfoFragment extends AbstractFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View view) {
        ListView lvInfo = (ListView) view.findViewById(R.id.list_info);
        final InfoListAdapter adapter = new InfoListAdapter(getActivity(), R.layout.info_row, getInfosList());
        lvInfo.setAdapter(adapter);
        lvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = adapter.getUrl(position);
                if (url != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                }
            }
        });
    }

    private List<Map<String, String>> getInfosList() {
        List<Map<String, String>> infosList = Lists.newArrayList();

        PackageInfo packageInfo = null;
        try {
            packageInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), PackageManager.GET_GIDS);
            infosList.add(newInfoRow("Version", packageInfo.versionName));
        } catch (Exception e) {
            Log.e(Defs.LOG_TAG, "", e);
        }

        infosList.add(newInfoRow(getString(R.string.about_rates_provider), getString(R.string.about_rates_provider_text), "http://www.bnb.bg"));
        infosList.add(newInfoRow(getString(R.string.about_join_appdev), "github.com/vexelon-dot-net/currencybg.app", "https://github.com/vexelon-dot-net/currencybg.app"));
        infosList.add(newInfoRow(getString(R.string.about_author), getString(R.string.about_author_text)));
        infosList.add(newInfoRow(getString(R.string.about_logo), getString(R.string.about_logo_text), "http://www.stremena.com"));
        infosList.add(newInfoRow(getString(R.string.about_flag_icons), "Copyright (c) 2013 Aha-Soft. http://www.aha-soft.com/free-icons/free-yellow-button-icons", "http://www.aha-soft.com/free-icons/free-yellow-button-icons"));
        infosList.add(newInfoRow(getString(R.string.about_flag_icons), "Copyright (CC BY-ND 3.0) Visual Pharm. http://icons8.com", "http://icons8.com"));
        infosList.add(newInfoRow(getString(R.string.about_3rdparty), ""));

        return infosList;
    }

    private Map<String, String> newInfoRow(String name, String value, String url) {
        Map<String, String> row = Maps.newHashMap();
        row.put(InfoListAdapter.ROW_NAME, name);
        row.put(InfoListAdapter.ROW_VALUE, value);
        if (!StringUtils.isEmpty(url)) {
            row.put(InfoListAdapter.ROW_URL, url);
        }
        return row;
    }

    private Map<String, String> newInfoRow(String name, String value) {
        return newInfoRow(name, value, null);
    }
}
