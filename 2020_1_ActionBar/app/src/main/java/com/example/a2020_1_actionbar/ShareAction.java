package com.example.a2020_1_actionbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.ShareActionProvider;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

public class ShareAction extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView text = new TextView(this);
        text.setText("공유 액션 프로바이더를 테스트합니다.");
        setContentView(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shareactionmenu, menu);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "sharing text");

        MenuItem share = menu.findItem(R.id.share);
        ShareActionProvider provider = (ShareActionProvider)MenuItemCompat.getActionProvider(share);
        provider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
        provider.setShareIntent(intent);

        MenuItem sharemenu = menu.findItem(R.id.sharemenu);
        ShareActionProvider providermenu = (ShareActionProvider)MenuItemCompat.getActionProvider(sharemenu);
        providermenu.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
        providermenu.setShareIntent(intent);

        return true;
    }
}
