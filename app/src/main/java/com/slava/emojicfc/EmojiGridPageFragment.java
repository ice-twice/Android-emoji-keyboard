package com.slava.emojicfc;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;


public class EmojiGridPageFragment extends Fragment {

    private int page;

    public EmojiGridPageFragment() {
        // Required empty public constructor
    }

    public void createInstance(int page) {
        this.page = page;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.emoji_grid_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridView gridView = (GridView) view.findViewById(R.id.emoji_grid_container);
        new EmojiGridHandler().execute(gridView);

    }

    private class EmojiGridAdapter extends BaseAdapter {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        private int emojiPage;

        public EmojiGridAdapter(int emojiPage) {
            this.emojiPage = emojiPage;
        }

        @Override
        public int getCount() {
            if (emojiPage == -1) {
                return 1;
            } else {
                return EmojiData.emojiData[emojiPage].length;
            }
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null)
                convertView = inflater.inflate(R.layout.emoji_item, parent, false);


            if (emojiPage == -1) {
                //recent emoji
                ((TextView) convertView.findViewById(R.id.emoji_icon)).setText("0");
            } else {
                String coloredCode = EmojiData.emojiData[emojiPage][position];
                ((TextView) convertView.findViewById(R.id.emoji_icon)).setText(coloredCode);
            }

            return convertView;
        }
    }

    private class EmojiGridHandler extends AsyncTask<GridView, Void, GridView> {

        @Override
        protected GridView doInBackground(GridView... params) {
            return params[0];
        }

        @Override
        protected void onPostExecute(GridView g) {
            super.onPostExecute(g);
            g.setAdapter(new EmojiGridAdapter(page));
        }
    }

}
